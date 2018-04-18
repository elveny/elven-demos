package com.elven.demo.springboot1.test.common;

import com.elven.demo.springboot1.test.common.util.LuceneUtil;
import com.elven.demo.springboot1.test.common.util.POIUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Params
 * @Return
 * @Exceptions
 */
public class LuceneUtilTest {

    private Analyzer analyzer;
    private Directory directory;

    @Before
    public void setUp() throws Exception {
        analyzer = new StandardAnalyzer();
        directory = FSDirectory.open(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\20170221.index").toPath());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createIndex() throws Exception {

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

        LuceneUtil.createIndex(analyzer, directory, documents);

        Assert.assertNotEquals(analyzer, null);
        Assert.assertNotEquals(directory, null);

    }

    @Test
    public void search() throws Exception {
        List<Document> list = LuceneUtil.search(analyzer, directory, new String[]{"subBankName"}, new String[]{"中国建设银行"}, 10);
        Assert.assertNotEquals(list, null);
        Assert.assertTrue(list.size() > 0);
    }

}