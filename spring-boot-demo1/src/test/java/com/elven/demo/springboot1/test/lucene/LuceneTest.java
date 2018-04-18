/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.lucene;

import com.elven.demo.springboot1.test.common.util.LuceneUtil;
import com.elven.demo.springboot1.test.common.util.POIUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename LuceneTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/19 16:36</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class LuceneTest {

    @Test
    public void test1() throws IOException, ParseException {

        Analyzer analyzer = new StandardAnalyzer();

        // Store the index in memory:
        Directory directory = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter iwriter = new IndexWriter(directory, config);

        Document doc = new Document();
        String text = "中国工商银行股份有限公司北京市分行营业部";
        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
// Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("工商银行 北京");
        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
//        assertEquals(1, hits.length);
// Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
//            assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
            System.out.println(hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
    }

    @Test
    public void test2() throws IOException, ParseException {

        Analyzer analyzer = new StandardAnalyzer();

        Directory directory = new RAMDirectory();

        long t1 = System.nanoTime();

        //Excel文件
//        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:20170118.xlsx")));
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:20170118.xlsx")));

        wb.getNumberOfSheets();
        //Excel工作表
//        HSSFSheet sheet = wb.getSheetAt(0);
        XSSFSheet sheet = wb.getSheetAt(0);

        int firstNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        System.out.println("firstNum:::"+firstNum);
        System.out.println("lastRowNum:::"+lastRowNum);
        List<Document> list = new ArrayList<Document>();
        for (int i=1; i<lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();
            String unitedBankNo = row.getCell(1).getStringCellValue();
            String subBankName = row.getCell(2).getStringCellValue();
            String clearBankNo = row.getCell(3).getStringCellValue();
            String areaCode = row.getCell(4).getStringCellValue();
            String areaName = row.getCell(5).getStringCellValue();
            String parentAreaCode = row.getCell(6).getStringCellValue();

            Document doc = new Document();
            doc.add(new Field("unitedBankNo", unitedBankNo, TextField.TYPE_STORED));
            doc.add(new Field("subBankName", subBankName, TextField.TYPE_STORED));
            doc.add(new Field("clearBankNo", clearBankNo, TextField.TYPE_STORED));
            doc.add(new Field("areaCode", areaCode, TextField.TYPE_STORED));
            doc.add(new Field("areaName", areaName, TextField.TYPE_STORED));
            doc.add(new Field("parentAreaCode", parentAreaCode, TextField.TYPE_STORED));

            list.add(doc);

        }

        createIndex(analyzer, directory, list);

        long t2 = System.nanoTime();

        System.out.println("iwriter:::spent::"+(t2-t1));

// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


        list = query(analyzer, directory, "subBankName", "兴业银行北京魏公村支行");

        if(list.size() > 0){
            createIndex(analyzer, directory, list);

            list = query(analyzer, directory, "areaName", "11");



            for(Document hitDoc : list){
                System.out.println("more::::"+hitDoc.get("subBankName")+"::"+hitDoc.get("areaName")+"::"+hitDoc.get("unitedBankNo")+"::");
            }
        }

        directory.close();

    }

    @Test
    public void test3() throws IOException, ParseException {

        long t1 = System.nanoTime();
        Map<String, List<String[]>> data = POIUtil.read(ResourceUtils.getFile("classpath:20170118.xlsx"));
        long t2 = System.nanoTime();

        System.out.println("解析excel:::spent::"+(t2-t1)/1000000000.0f+"(s)");
        List<Document> documents = new ArrayList<Document>();

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

                    Document doc = new Document();
                    doc.add(new Field("unitedBankNo", unitedBankNo, TextField.TYPE_STORED));
                    doc.add(new Field("subBankName", subBankName, TextField.TYPE_STORED));
                    doc.add(new Field("clearBankNo", clearBankNo, TextField.TYPE_STORED));
                    doc.add(new Field("areaCode", areaCode, TextField.TYPE_STORED));
                    doc.add(new Field("areaName", areaName, TextField.TYPE_STORED));
                    doc.add(new Field("parentAreaCode", parentAreaCode, TextField.TYPE_STORED));

                    documents.add(doc);

                }

            }
            else {
                continue;
            }

        }

        Analyzer analyzer = new StandardAnalyzer();

//        Directory directory = new RAMDirectory();
        Directory directory = FSDirectory.open(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\20170118.index").toPath());

        long t3 = System.nanoTime();

        LuceneUtil.createIndex(analyzer, directory, documents);

        long t4 = System.nanoTime();

        System.out.println("LuceneUtil.createIndex:::spent::"+(t4-t3)/1000000000.0f+"(s)");

        long t5 = System.nanoTime();
        List<Document> list = LuceneUtil.search(analyzer, directory, new String[]{"subBankName"}, new String[]{"中国建设银行"}, 10);
        long t6 = System.nanoTime();
        System.out.println("LuceneUtil.search:::spent::"+(t6-t5)/1000000000.0f+"(s)");

        if(list.size() > 0){
            for(Document hitDoc : list){
                System.out.println("more::::"+hitDoc.get("subBankName")+"::"+hitDoc.get("areaName")+"::"+hitDoc.get("unitedBankNo")+"::");
            }
        }

        directory.close();
    }

    @Test
    public void test4() throws IOException, ParseException {
        String[] bankNames = new String[]{"中国工商银行",
                "中国农业银行",
                "中国银行",
                "中国建设银行",
                "国家开发银行",
                "中国进出口银行",
                "中国农业发展银行",
                "交通银行",
                "中国邮政储蓄银行",
                "中信银行",
                "中国光大银行",
                "华夏银行",
                "中国民生银行",
                "广东发展银行",
                "平安银行",
                "招商银行",
                "兴业银行",
                "上海浦东发展银行",
                "城市商业银行",
                "农村商业银行",
                "恒丰银行",
                "农村合作银行",
                "村镇银行",
                "徽商银行",
                "城市信用社",
                "农村信用联社",
                "香港上海汇丰银行",
                "东亚银行",
                "南洋商业银行",
                "恒生银行",
                "中国银行（香港）",
                "(香港地区)银行",
                "集友银行",
                "星展银行（香港）",
                "永亨银行",
                "美国花旗银行",
                "美国银行",
                "美国摩根大通银行",
                "日本三菱东京日联银行",
                "日本日联银行",
                "日本三井住友银行",
                "日本瑞穗实业银行",
                "日本山口银行",
                "韩国外换银行",
                "韩国新韩银行",
                "韩国友利银行",
                "韩国产业银行",
                "韩国中小企业银行",
                "新加坡星展银行",
                "奥地利中央合作银行",
                "比利时联合银行",
                "荷兰银行",
                "荷兰商业银行",
                "渣打银行",
                "法国兴业银行",
                "法国巴黎银行",
                "法国东方汇理银行",
                "德国德累斯登银行",
                "德意志银行",
                "德国商业银行",
                "德国西德银行",
                "德国巴伐利亚州银行",
                "瑞士信贷银行",
                "加拿大蒙特利尔银行",
                "澳大利亚和新西兰银行集团",
                "德富泰银行",
                "厦门国际银行",
                "法国巴黎银行（中国）",
                "深圳发展银行",
                "青岛国际银行",
                "华一银行",
                "渤海银行"};

        List<Document> documents = new ArrayList<Document>();
        for(String bankName : bankNames){
            Document document = new Document();
            document.add(new Field("bankName", bankName, TextField.TYPE_STORED));
            documents.add(document);
        }

        Analyzer analyzer = new StandardAnalyzer();

        Directory directory = new RAMDirectory();
        LuceneUtil.createIndex(analyzer, directory, documents);

        long t5 = System.nanoTime();
        List<Document> list = LuceneUtil.search(analyzer, directory, new String[]{"bankName"}, new String[]{"中国工商银行陕西省西安北关支行"}, 10);
        long t6 = System.nanoTime();
        System.out.println("LuceneUtil.search:::spent::"+(t6-t5)/1000000000.0f+"(s)");

        if(list.size() > 0){
            for(Document hitDoc : list){
                System.out.println("more::::"+hitDoc.get("bankName")+"::");
            }
        }

        directory.close();


    }

    public void createIndex(Analyzer analyzer, Directory directory, List<Document> list) throws IOException {

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory, config);

        indexWriter.addDocuments(list);

        indexWriter.close();
    }

    public List<Document> query(Analyzer analyzer, Directory directory, String fieldName, String keyword) throws IOException, ParseException {
        List<Document> list = new ArrayList<Document>();

        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
// Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser(fieldName, analyzer);
        Query query = parser.parse(keyword);
        ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
//        assertEquals(1, hits.length);
// Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
//            assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
            System.out.println(hitDoc.get("subBankName")+"::"+hitDoc.get("areaName")+"::"+hitDoc.get("unitedBankNo")+"::");
            list.add(hitDoc);
        }
        ireader.close();

        return list;
    }
}