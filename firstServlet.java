package com.xxj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class firstServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public firstServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doDelete method of the servlet. <br>
	 *
	 * This method is called when a HTTP delete request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.sendRedirect("../index.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SmartUpload su=new SmartUpload();//初始化
		su.initialize(getServletConfig(), request,response);//初始化
		su.setMaxFileSize(100*1024*1024);//设置单个文件上传的最大尺寸（100M）
		su.setTotalMaxFileSize(1024*1024*1024);//设置总的上传尺寸（1g）
		String urlpath="";
		try {
			su.setAllowedFilesList("jpg,png,gif");//设置允许上传的文件类型
			su.setDeniedFilesList("exe,jar");//设置拒绝上传的文件类型
			su.upload();//接收客户端文件
			File file=su.getFiles().getFile(0);//获得第一个传递过来的文件对象
			Random rd=new Random();//服务器存储文件时创建随机的文件名
			Date day=new  Date();//服务器存储文件时创建随机的文件名
			//保存到发布的tomcat目录下，项目实施后只需要此项即可
			String filename=day.getTime()+rd.nextInt(1000000)+file.getFileName().substring(file.getFileName().lastIndexOf('.'));
		
			//保存到开发目录下，项目调试阶段使用，项目实施发布后可以取消该代码
			String developpath="g:\\a\\"+filename;
			System.out.println(developpath);
			file.saveAs(developpath);//保存到项目目录
			//urlpath="../admin/header/"+filename;//写入数据库
		} catch (SmartUploadException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(SecurityException e){//上传错误时抛出
			System.out.println("error!");
		}
	}

	/**
	 * The doPut method of the servlet. <br>
	 *
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
