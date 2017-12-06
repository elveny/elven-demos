package com.yeepay.sqkkseperator.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Util {
	public static  String ReadFile(String Path){
		BufferedReader reader = null;
		String laststr = "";
		try{
		FileInputStream fileInputStream = new FileInputStream(Path);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		reader = new BufferedReader(inputStreamReader);
		String tempString = null;
		while((tempString = reader.readLine()) != null){
		laststr += tempString;
		}
		reader.close();
		}catch(Exception e){
		e.printStackTrace();
		}finally{
		if(reader != null){
		try {
		reader.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		}
		return laststr;
		}
//public static String getkey(){
//	String key=null;
//	String JsonContext=Util.ReadFile("src/config/yop_sdk_config_default.json");
//JSONObject jsonobject=JSONObject.fromObject(JsonContext);
//Set<Map.Entry<String, Object>> set1=jsonobject.entrySet();
//for(Entry<String, Object> en:set1){
//	key=en.getKey();
//	System.out.println(key+"\n"+en.getValue());
//}
//return key;
//}
//public static String getvalue(String key){
//	String value=null;
//	String JsonContext=ReadFile("src/config/yop_sdk_config_default.json");
//	//String JsonContext=Util.ReadFile("src/config/yop_sdk_config_default.json");
//	JSONObject jsonobject=JSONObject.fromObject(JsonContext);
//JSONArray jsonarray=jsonobject.getJSONArray(key);
//	Iterator iteratorResult = jsonarray.iterator();
//     while (iteratorResult.hasNext()) {
//         JSONObject iteratorJson = (JSONObject) iteratorResult.next();
//          value=iteratorJson.getString("value");
//       System.out.println("商户私钥："+value);
//     }
//     return value;
//	}


//public static void main(String[] args) {
//	//Util.getvalue("isv_private_key");
//	//Util.getkey();
//	//Util.getvalue("yop_public_key");
//	//Util.getvalue("default_protocol_version");
	//Util.getvalue("isv_private_key");
//
//}

}

