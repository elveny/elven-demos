package com.elven.demo.springboot1.test.common;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class TCPClient {

    @Test
    public void deduct(){
        String serverName = "10.125.5.8";
        int port = 9911;
        try
        {
            System.out.println("连接到服务器：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程服务器主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            String header_tag             = "MSXF";
            String header_trans_code      = "LJDK";
            String header_inst_req_no     = appendRightStr("201801220000000001", 20, " ");
            String body_acct_no           = appendRightStr("6228480402564890018", 20, " ");
            String body_acct_name         = appendRightStr("张农行", 60, " ");
            String body_cert_type         = appendRightStr("身份证", 6, " ");
            String body_cert_no           = appendRightStr("500222198801012487", 20, " ");
            String body_trans_amount      = appendRightStr("1", 16, " ");
            String body_inst_acct_no      = appendRightStr("6228480402564890019", 18, " ");
            String body_summary           = appendRightStr("", 6, " ");
            String body_remark            = appendRightStr("", 100, " ");

            StringBuilder stringBuilder = new StringBuilder("");

            stringBuilder.append(header_tag             );
            stringBuilder.append(header_trans_code      );
            stringBuilder.append(header_inst_req_no     );
            stringBuilder.append(body_acct_no           );
            stringBuilder.append(body_acct_name         );
            stringBuilder.append(body_cert_type         );
            stringBuilder.append(body_cert_no           );
            stringBuilder.append(body_trans_amount      );
            stringBuilder.append(body_inst_acct_no      );
            stringBuilder.append(body_summary           );
            stringBuilder.append(body_remark            );

            byte[] gbkData = stringBuilder.toString().getBytes("GBK");

            out.write(gbkData, 0, gbkData.length);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            IOUtils.copy(in, outputStream);

            System.out.println("["+new String(outputStream.toByteArray(), "UTF-8")+"]");


            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void auth(){
        String serverName = "10.125.5.8";
        int port = 9911;
        try
        {
            System.out.println("连接到服务器：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程服务器主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            String header_tag             = "MSXF";
            String header_trans_code      = "SMHC";
            String header_inst_req_no     = appendRightStr(""+System.currentTimeMillis(), 20, " ");
            String body_acct_no           = appendRightStr("6228480402564890018", 20, " ");
            String body_acct_name         = appendRightStr("张农行", 60, " ");
            String body_cert_type         = appendRightStr("身份证", 6, " ");
            String body_cert_no           = appendRightStr("500222198801012487", 20, " ");
            String body_phone_flag        = "0";
            String body_phone_no          = appendRightStr("", 11, " ");
            String body_valide            = appendRightStr("", 4, " ");
            String body_cvv2              = appendRightStr("", 3, " ");

            StringBuilder stringBuilder = new StringBuilder("");

            stringBuilder.append(header_tag             );
            stringBuilder.append(header_trans_code      );
            stringBuilder.append(header_inst_req_no     );
            stringBuilder.append(body_acct_no           );
            stringBuilder.append(body_acct_name         );
            stringBuilder.append(body_cert_type         );
            stringBuilder.append(body_cert_no           );
            stringBuilder.append(body_phone_flag      );
            stringBuilder.append(body_phone_no      );
            stringBuilder.append(body_valide           );
            stringBuilder.append(body_cvv2            );

            String req = stringBuilder.toString();
            System.out.println("请求报文：["+req+"]");

            byte[] gbkData = req.getBytes("GBK");

            out.write(gbkData, 0, gbkData.length);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            IOUtils.copy(in, outputStream);

            System.out.println("响应报文：["+new String(outputStream.toByteArray(), "GBK")+"]");


            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public String appendRightStr(String lengthStr, int totalLength, String padding) {
        if (null == lengthStr) {
            lengthStr = "";
        }
        int len = 0;
        try {
//            len = lengthStr.getBytes("GBK").length;
            len = lengthStr.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (len >= totalLength) {
            return lengthStr;
        }
        int ss = totalLength - len;
        String res = "";
        for (int i = 0; i < ss; i++) {
            res = res + padding;
        }
        return lengthStr + res;
    }

}
