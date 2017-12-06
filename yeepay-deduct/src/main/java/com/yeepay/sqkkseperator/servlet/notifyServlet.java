package com.yeepay.sqkkseperator.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeepay.g3.facade.yop.ca.dto.DigitalEnvelopeDTO;
import com.yeepay.g3.facade.yop.ca.enums.CertTypeEnum;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class notifyServlet  {
	//测试异步回调
	public static void main(String[] args) {
		
		String response="NlylsGELAg2o8X2NqD9qf7kZdiiewcyUZ1PYQnxOFMVMtdmOyI0p2eY6agNSxhXu0sqxaCkoVS2vkBsF_mDQOLryIoT4D9tV3SwElT1uwB7z8vb4HpDewjVY10R1fG_LWjTZ7zU1pdX_q0uAlJOQfeARQmnt_72yGBHVAeBpKIi0Q7F6fgzZCM8HmLi2JJQkqxoEr5ZkP5EDR9DxJH1CKca9p0ezcM34mRVc7S_8XCNRrEGkCq1dyUwdrgrCNhyrVSTFKOAkjvO8xwWDhgwgnHzq9oP0xI8jVx-tcDtYRmxtxhOIwWhqn4LGWXYsAT4Fm61PPTgXVIivye0lbhliZA$8LVaWiT2RB660xY2EIxDttoH0IVYHuHSAnZ_VdVVk3iFZTCmSfjCOWn0NlON6pBNUgXizlpRtjU1sTWSyP7HRXK5uSe57u2u7diaGr-C-VBlitGM7ooxu_8WqrmRVWI9s1h9Dz-IEV-l5s9H1A-Mre6iolgORDlusqlTAr_Bn92fME5oqyRkyQs14b4poyzeXGWTYRkJLhmS-rvKQanqPHkzWqcwMU_gEMSCljjUjMTwB-S5wh5NboGVOoidnB3pSQpU2NyfPKG7Lv5jd6_1hcOx581Cv0XJg5lCgKNfLyho7HNKQCEhW38s9IqEjnjP4d2tpWMueueLU3TXONKh4YZsx2_MrA0g0uzrZjwLjSmgTAFVAULNVTMnCQnfLQtoLx-OYgaLTumEr2A9qtYgC5mm0-JobRn0tNeU6aTOvvqtHiUqHxVx6DIDPxFBVNspiwi4-4naiQiNj2H5w__AC5LYWqtnLr_uNFYqHhDvl9srUnCBl23OVFWfl0uPX2QOwi4ckHUy7hPTsXXNUngqO5FqTjpIzcslKfkBuZwH8Ulgcg_Vw1rADtvQKrFIHLw2GVxUYfCLpt82U5ymT6zgCbOVIf66nrUZxkwotUnh1V0hPU2FoTWe-HS6hstF2gpY-BY-TbZuervpFzmp2IKnISrbgUSbYDkaWFsfO2yc4wt7v7DnQppeHr_3lYZEv4-q6bbijX0WzvLDRlNqA1dqnRbOLTdo9gg4fhhWXiElpMua08eIcbvh5-yCB3wlZJm68SuwlkqY7KyV5TElr60JNg$AES$SHA256";
		try {
			//开始解密
		Map<String,String> jsonMap  = new HashMap<>();
			DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
			dto.setCipherText(response);
			//InternalConfig internalConfig = InternalConfig.Factory.getInternalConfig();
			PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
			System.out.println("privateKey: "+privateKey);
			PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
			System.out.println("publicKey: "+publicKey);
			
			dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
			System.out.println("-------:"+dto.getPlainText());
			jsonMap = parseResponse(dto.getPlainText());
			System.out.println(jsonMap);
		} catch (Exception e) {
			throw new RuntimeException("回调解密失败！");
	}

	}

	public static Map<String, String> parseResponse(String response){
		
		Map<String,String> jsonMap  = new HashMap<>();
		jsonMap	= JSON.parseObject(response, 
				new TypeReference<TreeMap<String,String>>() {});
		
		return jsonMap;
	}
}
