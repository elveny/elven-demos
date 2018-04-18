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

import java.io.*;
import java.util.*;

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
        Map<String, List<String[]>> data = POIUtil.read(ResourceUtils.getFile("classpath:20180418.xlsx"));

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
    public void test2() throws IOException {
        Map<String, List<String[]>> data = POIUtil.read(new FileInputStream(ResourceUtils.getFile("classpath:20180418.csv")), "xls");

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
    public void write1() throws IOException {

        List<Object[]> rowList = new ArrayList<>();
        Object[] collData = new String[]{"1", "2", "3"};
        rowList.add(collData);

        collData = new String[]{"4", "5", "6"};
        rowList.add(collData);

        collData = new String[]{"7", "8", "9"};
        rowList.add(collData);

        collData = new Integer[]{0, 1};
        rowList.add(collData);

        collData = new POIUtil[]{new POIUtil()};
        rowList.add(collData);

        Map<String, List<Object[]>> data = new HashMap<>();
        data.put("sheeeeeeeet1", rowList);

        POIUtil.write(data, "xls", new File("D:\\temp\\20180418\\test1.xls"));
        POIUtil.write(data, "xlsx", new File("D:\\temp\\20180418\\test1.xlsx"));

        OutputStream os = new ByteArrayOutputStream();
        POIUtil.write(data, "xls", os);
        byte[] bytes = new byte[1024];
        os.write(bytes);
        System.out.println(Base64.getEncoder().encodeToString(bytes));
    }

    @Test
    public void updateBankInfoFromExcel() throws IOException {

        String excelFile = "classpath:bank_info20170118.xlsx";

        StopWatch stopWatch = new StopWatch("BankInfo");
        stopWatch.start("加载excel数据源");
        Map<String, List<String[]>> data = POIUtil.read(ResourceUtils.getFile(excelFile));
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

        FileUtils.writeStringToFile(new File("E:\\code\\idea\\elven\\elven-demos\\spring-boot-demo1\\src\\test\\resources\\bank_info.sql"), bank_info_sql, "UTF-8");

        stopWatch.stop();

        logger.info("{}", stopWatch.prettyPrint());

    }


    @Test
    public void batchDeductTest() throws IOException {
        Map<String, List<String[]>> data = POIUtil.read(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\bbbbbb.xlsx"));

        for(String key : data.keySet()){
            System.out.println("key:::::"+key);

            List<String[]> list = data.get(key);
            StringBuilder stringBuilder = new StringBuilder("");
            int i = 0;
            for(String[] strs : list){
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String date = "20170728";
                String deptCode = strs[5];
                String z = "0";
                String acctNo = strs[2];
                String acctName = strs[1];
                String z2 = "01";
                String certNo = strs[3];
                String transAmt = strs[4];
                String z3 = "正常扣款";
                String z4 = "100861001001";
                String provName = strs[8];
                String cityName = strs[10];
                String prodCode = "0000500";
                String userid = strs[11];
                String systemid = "paycore";
                String secene = "0302";
                String z5 = "201703271738";
                String z6 = "1";
                String phoneNo = "15808096890";
                String z7 = "1";

                System.out.println(uuid+"|"+date+"|"+deptCode+"|"+z+"|"+acctNo+"|"+acctName+"|"+z2+"|"+certNo+"|"+transAmt+"|"+z3+"|"+z4+"|"+provName+"|"+cityName+"|"+prodCode+"|"+userid+"|"+systemid+"|"+secene+"|"+z5+"|"+z6+"|"+phoneNo+"|"+z7);

                if(i > 0){
                    stringBuilder.append(uuid).append("|").append(date).append("|").append(deptCode).append("|").append(z).append("|").append(acctNo).append("|").append(acctName).append("|").append(z2).append("|").append(certNo).append("|").append(transAmt).append("|").append(z3).append("|").append(z4).append("|").append(provName).append("|").append(cityName).append("|").append(prodCode).append("|").append(userid).append("|").append(systemid).append("|").append(secene).append("|").append(z5).append("|").append(z6).append("|").append(phoneNo).append("|").append(z7).append("\n");
                }

                i++;
            }

            FileOutputStream fos = new FileOutputStream(ResourceUtils.getFile("C:\\Users\\qiusheng.wu\\Downloads\\90_20170803220344.txt"));
            fos.write(stringBuilder.toString().getBytes());
            fos.flush();
            fos.close();

            System.out.println(":::::::::::::::::::::::end::::::::::::::::"+key);
        }
    }
}