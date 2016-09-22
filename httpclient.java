package com.xxj.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xxj.bean.userBean;

public class httpclient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://192.168.0.132:8080/round2project/servlet/getUserListservlet");
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=UTF-8"); 
			con.addRequestProperty("length","2008");//ֵ
			con.addRequestProperty("name", "tom");
			con.setRequestMethod("POST");
			
			con.connect();
		
			DataInputStream dis=new DataInputStream(con.getInputStream());
			int ch=dis.read();
			String str="";
			while(ch!=-1){
				str+=(char)ch;
				ch=dis.read();
			}
			
			JSONArray json=new JSONArray(str);
			for(int i=0;i<json.length();i++){
				JSONObject jo=json.getJSONObject(i);
				System.out.println(" name is "+jo.getString("name"));
				System.out.println(" password is "+jo.getString("password"));
				System.out.println(" sex is "+jo.getString("sex"));
				System.out.println(" age is "+jo.getString("age"));
				System.out.println();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<userBean> gethttpUser(){
		
		
		
		
		return null;
	}

}
