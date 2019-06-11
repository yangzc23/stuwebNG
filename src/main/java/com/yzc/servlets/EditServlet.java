package com.yzc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yzc.utils.DBUtil;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		Connection conn = null;
		PreparedStatement stmt = null;
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("application/json;charset=utf-8");
		//
		PrintWriter pw = resp.getWriter();
		//
		int sid = Integer.parseInt(req.getParameter("sid"));
		//
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE SNO=?");
			stmt.setInt(1, sid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				pw.println("{\"result\":\"success\",\"message\":\"\",\"data\":"+
				"{\"sno\":"+rs.getInt(1)+","+"\"sname\":\""+rs.getString(2)+"\""+
				",\"isMale\":"+rs.getString(3).equals("男")+
				",\"birth\":"+"\""+rs.getDate(4)+"\",\"filePath\":\""+rs.getString(5)+"\"}"+"}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("{\"result\":\"fail\",\"message\":\"出现异常！\"}");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
