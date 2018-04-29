package resume;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/uploadImage")
@MultipartConfig(maxFileSize = 16177216)
public class UploadImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = "jdbc:mysql://redroll.com:3306/itresume4u";
        String username = "itresume4u";
        String password = "illinoisstate";

        int result = 0;
        Connection con = null;
        Part part = request.getPart("file");

        if (part != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
                HttpSession session = request.getSession();
                String user = session.getAttribute("userid").toString();
                PreparedStatement ps = con.prepareStatement("update members set profilePicture=? where uname='" + user + "'");
                InputStream is = part.getInputStream();
                ps.setBlob(1, is);
                result = ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (result > 0) {
            resp.sendRedirect("changeInformation.jsp?message=Image+Uploaded");
        } else {
            resp.sendRedirect("changeInformation.jsp?message=Some+Error+Occurred");
        }
    }
}
