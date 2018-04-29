<%@page import="linkedin.commonthings"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<!--
        Forty by HTML5 UP
        html5up.net | @ajlkn
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%

    if (session == null || session.getAttribute("userid") == null) {
        // user is not logged in, do something about it
        System.out.println("not logged in");

        //bring to login page
    } else {
        // user IS logged in, do something: set model or do whatever you need
        System.out.println("user already logged in!");
        response.sendRedirect("index.jsp");
    }

%>
<html>
    <head>
        <title>Generic - Forty by HTML5 UP</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="assets/css/main.css" />
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
    </head>
    <body>

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
            <jsp:include page="header.jsp" />

            <!-- Main -->
            <div id="main" class="alt">

                <!-- One -->
                <section id="one">
                    <div class="inner">
                        <header class="major">
                            <h1>Please login to continue</h1>
                        </header>
                        <section id="contact">
                            <form method="post" action="login">
                                <center>
                                    <table border="1" width="30%" cellpadding="3" style="width: 50%">
                                        <tbody>
                                            <tr>
                                                <td>User Name</td>
                                                <td><input type="text" name="uname" value="" /></td>
                                            </tr>
                                            <tr>
                                                <td>Password</td>
                                                <td><input type="password" name="pass" value="" /></td>
                                            </tr>
                                            <tr>
                                                <td><input type="submit" value="Login" /></td>
                                                <td><input type="reset" value="Reset" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">No account? <a href="reg.jsp">Register Here</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </center>
                            </form>
                            <h3>
                                Or Sign in With Linked In:
                            </h3>
                            <a href="https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=<%=commonthings.client_id%>&redirect_uri=<%=commonthings.redirect_url%>&state=fdfdfdfd&scope=r_basicprofile%20r_emailaddress">

                                <img alt="" src="images/Linkedin-customized-button.png" style="width: 20%; height: 30% "><br><br>
                            </a>
                    </div>
                </section>

            </div>


            <!-- Footer -->
            <footer id="footer">
                <div class="inner">
                    <ul class="icons">
                        <li><a href="#" class="icon alt fa-twitter"><span class="label">Twitter</span></a></li>
                        <li><a href="#" class="icon alt fa-facebook"><span class="label">Facebook</span></a></li>
                        <li><a href="#" class="icon alt fa-instagram"><span class="label">Instagram</span></a></li>
                        <li><a href="#" class="icon alt fa-github"><span class="label">GitHub</span></a></li>
                        <li><a href="#" class="icon alt fa-linkedin"><span class="label">LinkedIn</span></a></li>
                    </ul>
                    <ul class="copyright">
                        <li>&copy; Untitled</li><li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
                    </ul>
                </div>
            </footer>

        </div>

        <!-- Scripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/jquery.scrolly.min.js"></script>
        <script src="assets/js/jquery.scrollex.min.js"></script>
        <script src="assets/js/skel.min.js"></script>
        <script src="assets/js/util.js"></script>
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="assets/js/main.js"></script>

    </body>
</html>