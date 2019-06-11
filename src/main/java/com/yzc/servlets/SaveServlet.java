package com.yzc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yzc.utils.DBUtil;


@WebServlet("/save")
public class SaveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		Connection conn = null;
		PreparedStatement stmt = null;
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("application/json;charset=utf-8");
		//解决请求参数内容包含中文会出现乱码的问题
		req.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		//
		String sno = req.getParameter("sid");
		//
		String sname = req.getParameter("sname");
		//
		String gender = req.getParameter("gender").equals("0")?"女":"男";
		//
		String birth = req.getParameter("birth");
		//
		String filePath = req.getParameter("filePath");
		//
		try {
			conn = DBUtil.getConnection();
			if(sno==null||sno.equals("")) {
				stmt = conn.prepareStatement("INSERT INTO STUDENT(SNAME,GENDER,BIRTH,PHOTO_URL) VALUES(?,?,?,?)");
				stmt.setString(1, sname);
				stmt.setString(2, gender);
				stmt.setString(3, birth);
				stmt.setString(4, filePath);
			}else {
				stmt = conn.prepareStatement("UPDATE STUDENT SET SNAME=?,GENDER=?,BIRTH=?,PHOTO_URL=? WHERE SNO=?");
				stmt.setString(1, sname);
				stmt.setString(2, gender);
				stmt.setString(3, birth);
				stmt.setString(4, filePath);			
				stmt.setInt(5, Integer.parseInt(sno));
			}	
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();//关闭数据库的连接
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.println("{\"result\":\"success\",\"message\":\"保存成功！\"}");
	}
}
