/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.poi;

import com.elven.demo.springboot1.test.common.util.POIUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename POITest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/20 10:59</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class POITest {

    private static final Logger logger = LoggerFactory.getLogger(POITest.class);

    @Test
    public void test1() throws IOException {
        Map<String, List<String[]>> data = POIUtil.parse(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\20170118.xls"));

        for(String key : data.keySet()){
            System.out.println("key:::::"+key);

            List<String[]> list = data.get(key);
            for(String[] strs : list){
                for(String str : strs){
                    System.out.print(str+" ");
                }
                System.out.println();
            }

            System.out.println(":::::::::::::::::::::::end::::::::::::::::"+key);
        }
    }

    @Test
    public void updateBankInfoFromExcel() throws IOException {

        String excelFile = "classpath:bank_info20170118.xlsx";

        StopWatch stopWatch = new StopWatch("BankInfo");
        stopWatch.start("加载excel数据源");
        Map<String, List<String[]>> data = POIUtil.parse(ResourceUtils.getFile(excelFile));
        stopWatch.stop();

        StringBuilder sqlStringBuilder = new StringBuilder("");

        stopWatch.start("读取excel数据，更新BankInfo数据");
        for(String key : data.keySet()){

            if("SQL Results".equals(key)){
                List<String[]> list = data.get(key);
                for(String[] strs : list){

                    int i = 1;
                    String unitedBankNo = strs[i++];
                    String subBankName = strs[i++];
                    String clearBankNo = strs[i++];
                    String areaCode = strs[i++];
                    String areaName = strs[i++];
                    String parentAreaCode = strs[i++];

                    logger.info("unitedBankNo:{}", unitedBankNo);

                    if(StringUtils.equals(unitedBankNo, "银行行号")){
                        continue;
                    }

                    sqlStringBuilder.append(" update bank_info  ")
                            .append(" set sub_bank_code = '").append(unitedBankNo).append("', ")
                            .append("     sub_bank_name = '").append(subBankName).append("', ")
                            .append("     parent_area_code = '").append(parentAreaCode).append("', ")
                            .append("     bank_area_code = '").append(areaCode).append("', ")
                            .append("     united_bank_no = '").append(unitedBankNo).append("', ")
                            .append("     clear_bank_no = '").append(clearBankNo).append("', ")
                            .append("     area_name = '").append(areaName).append("', ")
                            .append("     update_time = now() ")
                            .append(" where united_bank_no = '").append(unitedBankNo).append("'; \n")
                    ;

                }

            }
            else {
                continue;
            }

        }

        String bank_info_sql = sqlStringBuilder.toString();
        logger.info("{}", bank_info_sql);

        FileUtils.write(new File("E:\\code\\idea\\elven\\elven-demos\\spring-boot-demo1\\src\\test\\resources\\bank_info.sql"), bank_info_sql, "UTF-8");

        stopWatch.stop();

        logger.info("{}", stopWatch.prettyPrint());

    }
}