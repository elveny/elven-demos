package com.elven.demo.springboot1.test.groovy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @Description
 *
 * @Params
 * @Return
 * @Exceptions
 */
class XmlParserTest {

    Logger logger = LoggerFactory.getLogger(XmlParserTest.class)

    public void test1(){
        def respXmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<trans_content>\n" +
                "  <trans_head>\n" +
                "    <return_code>0000</return_code>\n" +
                "    <return_msg>代付请求成功</return_msg>\n" +
                "  </trans_head>\n" +
                "  <trans_reqDatas>\n" +
                "    <trans_reqData>\n" +
                "      <trans_orderid>200014</trans_orderid>\n" +
                "      <trans_batchid>20001</trans_batchid>\n" +
                "      <trans_no>1234567A</trans_no>\n" +
                "      <trans_money>1.23</trans_money>\n" +
                "      <to_acc_name>周小忍</to_acc_name>\n" +
                "      <to_acc_no>6222601234567890</to_acc_no>\n" +
                "      <to_acc_dept>上海市|上海市|工商银行张江支行</to_acc_dept>\n" +
                "      <trans_fee>2.0</trans_fee>\n" +
                "      <state>2</state>\n" +
                "      <trans_remark>已退款</trans_remark>\n" +
                "      <trans_starttime>20141210030455</trans_starttime>\n" +
                "      <trans_endtime>20141210030755</trans_endtime>\n" +
                "    </trans_reqData>\n" +
                "    <trans_reqData>\n" +
                "      <trans_orderid>200015</trans_orderid>\n" +
                "      <trans_batchid>20001</trans_batchid>\n" +
                "      <trans_no>1234567B</trans_no>\n" +
                "      <trans_money>1.23</trans_money>\n" +
                "      <to_acc_name>周小忍</to_acc_name>\n" +
                "      <to_acc_no>6222601234567890</to_acc_no>\n" +
                "      <to_acc_dept>上海市|上海市|工商银行张江支行</to_acc_dept>\n" +
                "      <trans_fee>2.0</trans_fee>\n" +
                "      <state>2</state>\n" +
                "      <trans_remark>已退款</trans_remark>\n" +
                "      <trans_starttime>20141210030455</trans_starttime>\n" +
                "      <trans_endtime>20141210030755</trans_endtime>\n" +
                "    </trans_reqData>\n" +
                "  </trans_reqDatas>\n" +
                "</trans_content>\n"

        //3、xml报文解析
        def root = new XmlParser().parseText(respXmlStr)
        // 这个只是系统层面交互的返回值，0000代表请求成功
        def respCode = root.trans_head.return_code.text()
        def respMsg = root.trans_head.return_msg.text()

        logger.info("respCode:"+respCode+", respMsg:"+respMsg)

        def trans_reqDatas = root.trans_reqDatas.trans_reqData
        logger.info("trans_reqDatas.size():"+trans_reqDatas.size())
        for(def reqData : trans_reqDatas){
            logger.info(reqData.trans_orderid.text())
        }

    }
}
