package wangyin.wepay.join.demo.web.controller;

import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Decoder;
import wangyin.wepay.join.demo.utils.BASE64;
import wangyin.wepay.join.demo.utils.DESUtil;
import wangyin.wepay.join.demo.utils.JsonUtil;
import wangyin.wepay.join.demo.web.constant.MerchantConstant;
import wangyin.wepay.join.demo.web.domain.request.AsynNoticeResDto;
import wangyin.wepay.join.demo.web.domain.request.AsynNotificationReqDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 说明：接收异步通知控制器
 * author:wyyusong
 * Date:14-8-25
 * Time:上午11:36
 */


@Controller
@RequestMapping(value = "/demo")
public class WebAsynNotificationCtrl {

    @Resource
    private MerchantConstant merchantConstant;

    @Resource
    private HttpServletRequest request;

    private static final Logger logger = Logger.getLogger(WebAsynNotificationCtrl.class);

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void execute() {
        logger.info("**********接收异步通知开始。**********");
        //获取通知原始信息
        String resp = request.getParameter("resp");
        resp=testString;
        logger.info("异步通知原始数据:"+resp);
        if(null == resp){
            return ;
        }
        //获取配置密钥
        String desKey = merchantConstant.getMerchantDESKey();
        String md5Key ="test";
        logger.info("desKey:"+ desKey);
        logger.info("md5Key:"+ md5Key);
        try {
            //首先对Base64编码的数据进行解密
            byte[] decryptBASE64Arr = BASE64.decode(testString);
            String decryptBASE64ArrStr = new String(decryptBASE64Arr,"utf-8");
            System.out.println(decryptBASE64ArrStr);
            AsynNotificationReqDto dto = parseXML(decryptBASE64Arr);
            //解析XML
            logger.info("解析XML得到对象:"+ JsonUtil.write2JsonStr(dto));
            //验证签名
            String ownSign = generateSign(dto.getVersion(), dto.getMerchant(), dto.getTerminal(), dto.getData(), md5Key);
            logger.info("根据传输数据生成的签名:"+ownSign);
            if (!dto.getSign().equals(ownSign)) {
                //验签不对
                logger.info("签名验证错误!");
                throw new RuntimeException();
            }else{
                logger.info("签名验证正确!");
            }
            //验签成功，业务处理
            //对Data数据进行解密
            byte[] rsaKey = decryptBASE64(desKey);
            String decryptArr = DESUtil.decrypt(dto.getData(), rsaKey, "utf-8");
            System.out.println("-------------------"+decryptArr+"---------------------");
            //解密出来的数据也为XML文档，可以用dom4j解析
            logger.info("对<DATA>进行解密得到的数据:"+decryptArr);
            dto.setData(decryptArr);
              parseNoticeXML(decryptArr);
            logger.info("**********接收异步通知结束。**********");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

        return ;
    }
    //XML解析为Java对象
    private static AsynNotificationReqDto parseXML(byte[] xmlString) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            InputStream is = new ByteArrayInputStream(xmlString);
            SAXReader sax = new SAXReader(false);
            document = sax.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        AsynNotificationReqDto dto = new AsynNotificationReqDto();
        Element rootElement = document.getRootElement();
        if (null == rootElement) {
            return dto;
        }
        Element versionEliment = rootElement.element("VERSION");
        if (null != versionEliment) {
            dto.setVersion(versionEliment.getText());
        }
        Element merchantEliment = rootElement.element("MERCHANT");
        if (null != merchantEliment) {
            dto.setMerchant(merchantEliment.getText());
        }
        Element terminalEliment = rootElement.element("TERMINAL");
        if (null != terminalEliment) {
            dto.setTerminal(terminalEliment.getText());
        }
        Element datalEliment = rootElement.element("DATA");
        if (null != datalEliment) {
            dto.setData(datalEliment.getText());
        }
        Element signEliment = rootElement.element("SIGN");
        if (null != signEliment) {
            dto.setSign(signEliment.getText());
        }
        return dto;
    }



    //递归取出所有元素
    public void element(Element element) {
        System.out.println("元素： " + element.getName());
//如果只是一个普通的元素没有子元素的，就直接输出
        if (element.isTextOnly() == true) {
            AsynNoticeResDto noticeResDto=new AsynNoticeResDto();
            System.out.println(element.getText());
            element.getName();
            if (element.getName().equals("TYPE")) {
                noticeResDto.setTIME(element.getText());
            }
            if (element.getName().equals("ID")) {
                noticeResDto.setID(element.getText());
            }
            if (element.getName().equals("AMOUNT")) {
                noticeResDto.setAMOUNT(element.getText());
            }
            if (element.getName().equals("CURRENCY")) {
                noticeResDto.setCURRENCY(element.getStringValue());
            }
            if (element.getName().equals("DATE")) {
                noticeResDto.setDATE(element.getStringValue());
            }
            if (element.getName().equals("TIME")) {
                noticeResDto.setTIME(element.getTextTrim());
            }
            if (element.getName().equals("NOTE")) {
                noticeResDto.setNOTE(element.getTextTrim());
            }
            if (element.getName().equals("STATUS")) {
                noticeResDto.setSTATUS(element.getTextTrim());
            }
            if (element.getName().equals("CODE")) {
                noticeResDto.setCODE(element.getText());
            }
            if (element.getName().equals("DESC")) {
                noticeResDto.setDESC(element.getText());
            }
            System.out.println("元素 名称:  " + element.getName() + "||||" + "元素内容：" + element.getText());
        } else {
//如果里面还有字元素的，那么就把它里面的内容输出
            List elements = element.elements();
            int size = elements.size();
            for (int i = 0; i < size; i++) {
                Element emt = (Element) elements.get(i);
//System.out.println("元素:"+emt.getName());
//递归遍历出所有的元素
                element(emt);
//某一个元素的所有属性列出来
                arr(emt);
            }
        }
    }
    public void arr(Element element) {
//取出所有的属性，放在list中
        List att = element.attributes();
        int size = att.size();
        for (int i = 0; i < size; i++) {
//依次取出属性
            Attribute attb = (Attribute) att.get(i);
//输出元素名称，属性名称和属性内容
            System.out.println("元素名称" + element.getName() + "   属性名称：" + attb.getName() + "属性内容：" + attb.getValue());

        }
    }



    //XML解析为Java对象
    private void  parseNoticeXML(String xmlString) {
        Map map=new HashMap();
        Document document = null;
        try {
            xmlString= URLDecoder.decode(xmlString);
             document = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();
        element(rootElement);
    }


    //对Base64进行解密
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * 签名
     */
    public static String generateSign(String version, String merchant, String terminal, String data, String md5Key) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(version);
        sb.append(merchant);
        sb.append(terminal);
        sb.append(data);
        String sign = "";
        sign = md5(sb.toString(), md5Key);
        return sign;
    }

    public static String md5(String text, String salt) throws Exception {
        byte[] bytes = (text + salt).getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 0x10) {
                sb.append("0");
            }
            sb.append(Long.toString(bytes[i] & 0xff, 16));
        }
        return sb.toString().toLowerCase();
    }
    public void listNodes(Element node) {
        System.out.println("当前节点的名称：：" + node.getName());
        // 获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        for (Attribute attr : list) {
            System.out.println(attr.getText() + "-----" + attr.getName()
                    + "---" + attr.getValue());
        }

        if (!(node.getTextTrim().equals(""))) {
            System.out.println("文本内容：：：：" + node.getText());
        }

        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();
        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            listNodes(e);
        }
    }

    /*****以下为测试代码*****/


    //通知数据样例
    private static String testString = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxDSElOQUJBTks%2BCiAgPFZFUlNJT04%2BMS4wLjA8L1ZFUlNJT04%2BCiAgPE1FUkNIQU5UPjIyMjk0NTMxPC9NRVJDSEFOVD4KICA8VEVSTUlOQUw%2BMDAwMDAwMDE8L1RFUk1JTkFMPgogIDxEQVRBPkMrRFVZQ3d4WUltNUp4SVo5am94ZFI5L0hrVklnSitTMldpUzFhYWkrZDA4TEhwakx1YmZsVEpqWFl1all2UzRjMmJYTlVHN3JiaGZpU2MxWmZnS083TUVuZFgydkhhcHZRVnVSUkx1SzFLV0lnKzFIanVLakpCaVFHRjFwOWwzd1RPenJ2UjZwOVBoMnIxZjZJM2liVFozTkRPc2RlMXFscmpVUHRJcmNjdlZLU3ZpeHVkVmo3Q09mUnN5UisxcHFBUEt3TVgwcFJDRCtodUJKTGcyR0pYQkU2bDB5MUlXcWFVUHZmellsYk1PdG8zUVhvNmxQRVJjT0UrTzFOd2JCRnZ5dEZSd3hmSmZkL2ppdDdZK3hvNnNIckZ6ZWUwT2wrdzVkVDJkNFRHcFMxL0V4Nzc3bVEvdmJUeVFib3J6YVB3TDBnU25oSnRjWW0rTlZyRmMzblRjcGtxZFRuOG1FUVoyUTRwZWpmR0hsOWJTdG1FRGxOeUUwK1Rvb3VjZWFZaG1aOVhhRjN1bjYyaWNRcnBKZVF6RVN3MUhCUS9sZ2tYaXl2SjY3c3FoOWpOb3IrNEJENVJYa2FwMFFjTFZzbUVZZ3pYWmwwTXF5QWVvMlpjQjNHZVJMK1NVOE16dTwvREFUQT4KICA8U0lHTj5lNGQ2ZDM1MWRlOTU3N2RjZmFhZmUyZmRjMDQwMmExNTwvU0lHTj4KPC9DSElOQUJBTks%2B";

    //DES 密钥
    private static final String DES_KEY = "Z8KMT8cT4z5ruu89znxFhRP4DdDBqLUH";

    //MD5 密钥
    private static final String MD5_KEY = "test";

    //供测试用
    public static void main(String[] args) throws Exception {

//        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DATA>\n" +
//                "<TRADE>\t\t<TYPE>S</TYPE>\t\t\n" +
//                "<ID>223127811409154509690</ID>\t\t\n" +
//                "<AMOUNT>1</AMOUNT>\t\t\n" +
//                "<CURRENCY>CNY</CURRENCY>\t\n" +
//                "<DATE>20140827</DATE>\t\n" +
//                "<TIME>160011</TIME>\t\n" +
//                "<NOTE>交易描述</NOTE>\t\n" +
//                "<STATUS>0</STATUS>\t</TRADE>\t\n" +
//                "<RETURN>\n" +
//                "<CODE>0000</CODE>\n" +
//                "<DESC>成功</DESC>\n" +
//                "</RETURN></DATA>";
//           Document document = DocumentHelper.parseText(text);
//        System.out.println(testString +"********");
        
        String testString = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxDSElOQUJBTks%2BCiAgPFZFUlNJT04%2BMS4wLjA8L1ZFUlNJT04%2BCiAgPE1FUkNIQU5UPjIyMjk0NTMxPC9NRVJDSEFOVD4KICA8VEVSTUlOQUw%2BMDAwMDAwMDE8L1RFUk1JTkFMPgogIDxEQVRBPkMrRFVZQ3d4WUltNUp4SVo5am94ZFI5L0hrVklnSitTMldpUzFhYWkrZDA4TEhwakx1YmZsVEpqWFl1all2UzRjMmJYTlVHN3JiaGZpU2MxWmZnS083TUVuZFgydkhhcHZRVnVSUkx1SzFLV0lnKzFIanVLakpCaVFHRjFwOWwzd1RPenJ2UjZwOVBoMnIxZjZJM2liVFozTkRPc2RlMXFscmpVUHRJcmNjdlZLU3ZpeHVkVmo3Q09mUnN5UisxcHFBUEt3TVgwcFJDRCtodUJKTGcyR0pYQkU2bDB5MUlXcWFVUHZmellsYk1PdG8zUVhvNmxQRVJjT0UrTzFOd2JCRnZ5dEZSd3hmSmZkL2ppdDdZK3hvNnNIckZ6ZWUwT2wrdzVkVDJkNFRHcFMxL0V4Nzc3bVEvdmJUeVFib3J6YVB3TDBnU25oSnRjWW0rTlZyRmMzblRjcGtxZFRuOG1FUVoyUTRwZWpmR0hsOWJTdG1FRGxOeUUwK1Rvb3VjZWFZaG1aOVhhRjN1bjYyaWNRcnBKZVF6RVN3MUhCUS9sZ2tYaXl2SjY3c3FoOWpOb3IrNEJENVJYa2FwMFFjTFZzbUVZZ3pYWmwwTXF5QWVvMlpjQjNHZVJMK1NVOE16dTwvREFUQT4KICA8U0lHTj5lNGQ2ZDM1MWRlOTU3N2RjZmFhZmUyZmRjMDQwMmExNTwvU0lHTj4KPC9DSElOQUJBTks%2B";
        testString = URLDecoder.decode(testString);
        byte[] decryptBASE64Arr = BASE64.decode(testString);
        String decryptBASE64ArrStr = new String(decryptBASE64Arr,"utf-8");
        System.out.println(decryptBASE64ArrStr);
        AsynNotificationReqDto dto = parseXML(decryptBASE64Arr);
        System.out.println("AsynNotificationReqDto:"+ JsonUtil.write2JsonStr(dto));
        byte[] rsaKey = decryptBASE64(DES_KEY);
        String decryptArr = DESUtil.decrypt(dto.getData(), rsaKey, "utf-8");
        System.out.println("decryptArr:"+decryptArr);
        //验证签名
        String ownSign = generateSign(dto.getVersion(), dto.getMerchant(), dto.getTerminal(), dto.getData(), MD5_KEY);
        System.out.println(ownSign);





    }



//    public static void main(String[] args) {
//        try {
//            String ca="   <?xml version=\"1.0\" encoding=\"GBK\"?>" +
//                    "    <library>" +
//                    "    <!--this is a test-->" +
//                    "    <book show=\"yes\">" +
//                    "    <title>title is here</title>" +
//                    "    <title>new title</title>" +
//                    "    </book>" +
//                    "    <book show=\"no\">" +
//                    "    <title>this title</title>" +
//                    "    </book>" +
//                    "    <owner>i am owner</owner>" +
//                    "    </library>";
//            Document doc= DocumentHelper.parseText(ca);
//            //Document doc = new SAXReader().read("D:\\yourXml.xml");
//            Element root = doc.getRootElement();
//            Element eSCA = root.element("SubControlArea");
//            System.out.println(eSCA.attributeValue("ID"));
//            List<Element> es = eSCA.elements();
//            for (Element e : es) {
//                System.out.println(e.getTextTrim());
//                if (!(e.attribute("resource") == null)) {
//                    System.out.println(e.attributeValue("resource"));
//                }
//            }
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }

//接收到的数据
    /*
     "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxDSElOQUJBTks+DQogICAg\n" +
            "PFZFUlNJT04+MS4yLjA8L1ZFUlNJT04+DQogICAgPE1FUkNIQU5UPjIyMzEyNzgxPC9NRVJDSEFO\n" +
            "VD4NCiAgICA8VEVSTUlOQUw+MDAwMDAwMDE8L1RFUk1JTkFMPg0KICAgIDxEQVRBPkJiQ2NzeVUx\n" +
            "L3kyMjlIeXZ4RFQ1Rm1SVnVjSFRXUFJqaGhyWmViRW1wTVAvL2xjdW04cjBRdVhGcjZPeDY5NXdN\n" +
            "dEYwblMwWHhjYVYKOVNoMWdrYU16dVJHQXorcytjOWhYREVnMUFXNVNUY3lRM0c3UTZKdzlHVE9G\n" +
            "cERHVis5a1g0TVI3aGVUQUZPWjIyRWF2RzZzNVJYLwo4c3AzYlJKa3hKNnpDYlQ3ckw0anNJZm9G\n" +
            "T05BdDBIV1VYUUpiazRBa3NlK1d3emNybU5QUDVzMVcyckRPUnA5Z3cwcVVhVW9GUTFsCjNmV09S\n" +
            "WHR2ZVZlVmNZeHlBMHJrRk9xNnFIV0t1Mjd2LzN0UDVQbFJZQU9CYlA0TDJDSTVvK3dMQzNpV1Z5\n" +
            "NmVpTHd2QnNJeWM3amwKU2NhanNaTkgxeDlPbUhUVldoUDVZM3l5UkxEb0U1SGJUaDBmckdaeERZ\n" +
            "U2wwZlQvMnkvUDFpbnlrVUpwQktiVnA3c2w4NVVyZjVTcgpZZGM0VGVRS2lWSVFmSWdDb0l4ZVJD\n" +
            "dmFzZmEycWVUSUd0Z1M5akVSdWpGQ3p1Myt4T20wa1VyVHdrUXQxNW1SVUxZcVM2MC9mcDJKCjhj\n" +
            "VmF3WXFVMzNjL014ZVIySWZiZkgxais1OHVZbHNOajFlQjlyVThKQ29ncnFmbEFyMFY3R0NaPC9E\n" +
            "QVRBPg0KICAgIDxTSUdOPjQ1OWZjNjE2YjUwYmQyOWM2N2YxZjg0YmU3YzEwMzAzPC9TSUdOPg0K\n" +
            "PC9DSElOQUJBTks+DQoNCg=="
     */

//接收数据进行Base64转码获取的数据
/*
<?xml version="1.0" encoding="UTF-8"?>
<CHINABANK>
    <VERSION>1.2.0</VERSION>
    <MERCHANT>22312781</MERCHANT>
    <TERMINAL>00000001</TERMINAL>
    <DATA>BbCcsyU1/y229HyvxDT5FmRVucHTWPRjhhrZebEmpMP//lcum8r0QuXFr6Ox695wMtF0nS0XxcaV
9Sh1gkaMzuRGAz+s+c9hXDEg1AW5STcyQ3G7Q6Jw9GTOFpDGV+9kX4MR7heTAFOZ22EavG6s5RX/
8sp3bRJkxJ6zCbT7rL4jsIfoFONAt0HWUXQJbk4Akse+WwzcrmNPP5s1W2rDORp9gw0qUaUoFQ1l
3fWORXtveVeVcYxyA0rkFOq6qHWKu27v/3tP5PlRYAOBbP4L2CI5o+wLC3iWVy6eiLwvBsIyc7jl
ScajsZNH1x9OmHTVWhP5Y3yyRLDoE5HbTh0frGZxDYSl0fT/2y/P1inykUJpBKbVp7sl85Urf5Sr
Ydc4TeQKiVIQfIgCoIxeRCvasfa2qeTIGtgS9jERujFCzu3+xOm0kUrTwkQt15mRULYqS60/fp2J
8cVawYqU33c/MxeR2IfbfH1j+58uYlsNj1eB9rU8JCogrqflAr0V7GCZ</DATA>
    <SIGN>459fc616b50bd29c67f1f84be7c10303</SIGN>
</CHINABANK>
*/

//DATA解密出来的数据
/*
<?xml version="1.0" encoding="UTF-8"?>
<DATA>
	<TRADE>
		<TYPE>S</TYPE>
		<ID>223127811409087990546</ID>
		<AMOUNT>1</AMOUNT>
		<CURRENCY>CNY</CURRENCY>
		<DATE>20140826</DATE>
		<TIME>213101</TIME>
		<NOTE>交易描述</NOTE>
		<STATUS>7</STATUS>
	</TRADE>
	<RETURN>
		<CODE>EEB0004</CODE>
		<DESC>银行交易失败 需自动签到重试</DESC>
	</RETURN>
</DATA>
*/
}
