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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

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
        //获取学号
        int sid = Integer.parseInt(req.getParameter("sid"));
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM STUDENT WHERE SNO = ?");
            stmt.setInt(1, sid);
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
        pw.println("{\"result\":\"success\",\"message\":\"删除成功！\"}");
    }
}
