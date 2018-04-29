<%@page import="java.util.Random"%>
<%@page import="linkedin.LinkedInProfile"%>
<%@page import="linkedin.Post_Test"%>
<%@page import="java.sql.*,resume.sqlconnect"%>

<%-- 
    Document   : linked
    Created on : Apr 25, 2018, 9:49:51 PM
    Author     : Falko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LinkedIn</title>
        <link rel="stylesheet" href="assets/css/main.css" />
    </head>
    <body>
        <%
            String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder ran = new StringBuilder();
            Random rnd = new Random();
            while (ran.length() < 10) {
                int index = (int) (rnd.nextFloat() * randomString.length());
                ran.append(randomString.charAt(index));
            }
            String password = ran.toString();

            String code = (String) request.getParameter("code");
            String state = (String) request.getParameter("state");

            Post_Test obj_Post = new Post_Test();
            LinkedInProfile OBJ_LinkedInProfile = obj_Post.sendPost(code);
            String fname = OBJ_LinkedInProfile.getFirstName();
            String lname = OBJ_LinkedInProfile.getLastName();
            String email = OBJ_LinkedInProfile.getEmailAddress();
            String img = OBJ_LinkedInProfile.getPictureUrl();
            String uname = fname + "-" + lname;
            String pass = password;
            session.setAttribute("userid", uname);

            java.sql.Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://redroll.com:3306/itresume4u", "itresume4u", "illinoisstate");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from members WHERE uname='" + uname + "'");
            if (rs.next()) {
                response.sendRedirect("success.jsp");
            } else {
                int i = st.executeUpdate("insert into members(first_name, last_name, email, uname, pass, profilePicture, regdate) values ('" + fname + "','" + lname + "','" + email + "','" + uname + "', '" + pass + "', '" + img + "', CURDATE())");
                if (i > 0) {
                    response.sendRedirect("success.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            }
        %>
    </body>
</html>
