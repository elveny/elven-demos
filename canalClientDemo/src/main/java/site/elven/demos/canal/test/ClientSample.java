/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.demos.canal.test;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename ClientSample.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/31 15:24</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ClientSample {

    protected final static Logger logger             = LoggerFactory.getLogger(ClientSample.class);

    public static void main(String[] args) {

//        String hostname = "127.0.0.1";
        String hostname = "192.168.56.1";
        int port = 11111;
        String destination = "example";
        String username = "";
        String password = "";
        // 创建canal数据操作客户端
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostname, port), destination, username, password);

        // 获取批次数量
        int batchSize = 1000;

        // 链接对应的canal server
        connector.connect();

        // 客户端订阅，重复订阅时会更新对应的filter信息
        connector.subscribe(".*\\..*");

        // 回滚到未进行 {@link #ack} 的地方，下次fetch的时候，可以从最后一个没有 {@link #ack} 的地方开始拿
        connector.rollback();

        while (true){

            // 尝试拿batchSize条记录，有多少取多少，不会阻塞等待。canal 会记住此 client 最新的position。
            Message message = connector.getWithoutAck(batchSize);

            long batchId = message.getId();
            int size = message.getEntries().size();

            if(batchId == -1 || size == 0){
                try {
                    logger.info(".");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                logger.info("size:{}", size);
                printEntry(message.getEntries());
            }

            connector.ack(batchId);
        }
    }

    private static void printEntry( List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            logger.info("entry:{}", entry);
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;

            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (InvalidProtocolBufferException e) {
                logger.error("ERROR ## parser of eromanga-event has an error , data: {}", entry.toString(), e);
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();

            logger.info("binlog[{}:{}], name[{}.{}], eventType:{}", entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType);

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                    logger.info(">>>> DELETE before: {}", getColumns(rowData.getBeforeColumnsList()));
                }
                else if(eventType == CanalEntry.EventType.INSERT){
                    logger.info(">>>> INSERT after: {}", getColumns(rowData.getAfterColumnsList()));
                }
                else if(eventType == CanalEntry.EventType.UPDATE){
                    logger.info(">>>> UPDATE before: {}", getColumns(rowData.getBeforeColumnsList()));
                    logger.info(">>>> UPDATE after: {}", getColumns(rowData.getAfterColumnsList()));
                }
                else {
                    logger.info(">>>> OTHER before: {}", getColumns(rowData.getBeforeColumnsList()));
                    logger.info(">>>> OTHER after: {}", getColumns(rowData.getAfterColumnsList()));
                }
            }
        }
    }

    private static Map<String, String> getColumns(List<CanalEntry.Column> columns){
        Map<String, String> result = new HashMap<>();
        for(CanalEntry.Column column : columns){
            result.put(column.getName(), column.getValue());
        }
        return result;
    }

}