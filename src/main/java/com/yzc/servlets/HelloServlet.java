package com.yzc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yzc.utils.DBUtil;

@WebServlet("/welcome")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter pw = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("application/json;charset=utf-8");
		StringBuilder str = new StringBuilder();
		str.append("{\"result\":\"success\",\"message\":\"\",\"data\":[");
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM STUDENT");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				str.append("{\"sno\":"+rs.getInt(1)+","+
				"\"sname\":\""+rs.getString(2)+"\""+",\"isMale\":"+rs.getString(3).equals("男")+",\"birth\":"+
				"\""+rs.getDate(4)+"\"},");
			}
			str = new StringBuilder(str.substring(0, str.length()-1));
			str.append("]}");
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		pw.println(str);

	}
}
