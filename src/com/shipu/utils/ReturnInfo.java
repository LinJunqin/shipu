package com.shipu.utils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import net.sf.json.JSONObject;

public class ReturnInfo {
	
	//获取json返回信息和其他返回信息
	public static JSONObject getJsonInfo(JSONObject data,String msg,int status,Date time){
		JSONObject res = new JSONObject();
		res.put("data", data);
		res.put("msg", msg);
		res.put("status", status);
		res.put("time", time.toString());
		return res;
	}
	
	//获取json返回信息和其他返回信息
	public static JSONObject getJsonInfo(ArrayList<JSONObject> data,String msg,int status,Date time){
		JSONObject res = new JSONObject();
		res.put("data", data);
		res.put("msg", msg);
		res.put("status", status);
		res.put("time", time.toString());
		return res;
	}
	
	public static void outJson(PrintWriter out,JSONObject data){
		out.println(data);
		out.flush();
		out.close();
	}
	
	public static void outJson(PrintWriter out,String data){
		out.println(data);
		out.flush();
		out.close();
	}
}
