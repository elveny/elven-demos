package com.yeepay.sqkkseperator.config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class test {
	
	public  String getkey(){
		String key=null;
		String JsonContext=Util.ReadFile("src/config/yop_sdk_config_default.json");
	JSONObject jsonobject=JSONObject.fromObject(JsonContext);
	Set<Entry<String, Object>> set1=jsonobject.entrySet();
	for(Entry<String, Object> en:set1){
		key=en.getKey();
		System.out.println(key+"\n"+en.getValue());
	}
	return key;
	}
	public static  String getvalue(String key){
		String value=null;
		String JsonContext=new Util().ReadFile("/SQKK-Seperator-demo/src/config/yop_sdk_config_default.json");
		JSONObject jsonobject=JSONObject.fromObject(JsonContext);
	JSONArray jsonarray=jsonobject.getJSONArray(key);
		Iterator iteratorResult = jsonarray.iterator();
	     while (iteratorResult.hasNext()) {
	         JSONObject iteratorJson = (JSONObject) iteratorResult.next();
	          value=iteratorJson.getString("value");
	       System.out.println("商户私钥："+value);
	     }
	     return value;
		}
public static void main(String[] args) {
	test.getvalue("isv_private_key");
}
}
