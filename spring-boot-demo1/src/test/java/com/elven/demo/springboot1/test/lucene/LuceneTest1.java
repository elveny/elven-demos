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
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename LuceneTest1.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/10 15:11</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class LuceneTest1 {
    @Test
    public void luceneCreateIndex() throws IOException {

        long t1 = System.nanoTime();
        Map<String, List<String[]>> data = POIUtil.parse(ResourceUtils.getFile("classpath:20170118.xlsx"));
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
        Directory directory = FSDirectory.open(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\20170210.index").toPath());

        long t3 = System.nanoTime();

        LuceneUtil.createIndex(analyzer, directory, documents);

        long t4 = System.nanoTime();

        System.out.println("LuceneUtil.createIndex:::spent::"+(t4-t3)/1000000000.0f+"(s)");

        directory.close();
    }

    @Test
    public void luceneSearch() throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();

//        Directory directory = new RAMDirectory();
        Directory directory = FSDirectory.open(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\20170210.index").toPath());


        long t5 = System.nanoTime();
        List<Document> list = LuceneUtil.search(analyzer, directory, new String[]{"bankName"}, new String[]{"中国工商银行股份有限公司北京通州"}, 10);
        long t6 = System.nanoTime();
        System.out.println("LuceneUtil.search:::spent::"+(t6-t5)/1000000000.0f+"(s)");

        if(list.size() > 0){
            for(Document hitDoc : list){
                System.out.println("more::::"+hitDoc.get("subBankName")+"::"+hitDoc.get("areaName")+"::"+hitDoc.get("unitedBankNo")+"::");
            }
        }

        directory.close();

    }
}