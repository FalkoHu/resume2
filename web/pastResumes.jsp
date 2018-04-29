<%-- 
    Document   : newcreateresume
    Created on : Apr 24, 2018, 3:37:43 PM
    Author     : Christian
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="resume.sqlconnect"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="resume.resumeFiller2"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!--
        Forty by HTML5 UP
        html5up.net | @ajlkn
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
    <head>
        <title>Elements - Forty by HTML5 UP</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
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

            <jsp:include page="header.jsp" />

            <!-- Main -->
            <div id="main" class="alt">

                <!-- One -->
                <section id="one">
                    <div class="inner">
                        <header class="major">
                            <h1>View your past Resumes</h1>
                        </header>

                        <div class="row 200%">
                            <div class="6u 12u$(medium)">
                                
                                    <div class="row uniform">
                                        <div class="6u 12u$(xsmall)">

                                            <table>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Template</th> 
                                                    <th>Date</th>
                                                    <th></th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                                <%
                                                    String id = null;
                                                    String type = null;
                                                    Timestamp date = null;
                                                    String dateNice = null;
                                                    String pattern = "MM/dd/yyyy hh:mm a";
                                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                                                    try {

                                                        java.sql.Connection con;
                                                        Class.forName("com.mysql.jdbc.Driver");
                                                        con = sqlconnect.getConnection();
                                                        //con = DriverManager.getConnection("jdbc:mysql://redroll.com:3306/itresume4u", "itresume4u", "illinoisstate");

                                                        String user = session.getAttribute("userid").toString();
                                                        Statement st = con.createStatement();
                                                        ResultSet rs = st.executeQuery("select * from createdResumes WHERE username='" + user + "' order by id desc");
                                                        while (rs.next()) {
                                                            type = rs.getString("type");
                                                            date = rs.getTimestamp("date");
                                                            id = rs.getString("id");
                                                            dateNice = simpleDateFormat.format(date);
                                                %> 

                                                <tr>
                                                    <td><%= id%></td>
                                                    <td><%= type%></td> 
                                                    <td><%= dateNice%></td>
                                                    <td><a href="#" class="view<%= id%>" onclick="changeView(<%= id%>)">View</a></td>

                                                    <td><a href="CreateResume?view=download&id=<%= id%>&type=<%= type%>">Download</a></td>
                                                    <td>Delete</td>
                                                </tr>                                

                                                <%
                                                        } 

                                                        st = con.createStatement();
                                                        rs = st.executeQuery("select id, type from createdResumes WHERE username='" + user + "' order by id desc limit 1");
                                                        while (rs.next()) {
                                                            id = rs.getString("id");
                                                            type = rs.getString("type");
                                                        }

                                                    } catch (SQLException e) {
                                                        out.println("SQL Exception caught: " + e.getMessage());
                                                    }
                                                %>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="6u$ 12u$(medium)">

                                    <object width="500" height="700" data="CreateResume?view=view&id=<%= id%>&type=<%= type%>" id="resumeView" style="position: fixed; bottom: 120px;"></object>

                                </div>
                            </div>
                            </section>
                        </div>

                        <!--             Contact 
                                    <section id="contact">
                                        <div class="inner">
                                            <section>
                                                <form method="post" action="#">
                                                    <div class="field half first">
                                                        <label for="name">Name</label>
                                                        <input type="text" name="name" id="name" />
                                                    </div>
                                                    <div class="field half">
                                                        <label for="email">Email</label>
                                                        <input type="text" name="email" id="email" />
                                                    </div>
                                                    <div class="field">
                                                        <label for="message">Message</label>
                                                        <textarea name="message" id="message" rows="6"></textarea>
                                                    </div>
                                                    <ul class="actions">
                                                        <li><input type="submit" value="Send Message" class="special" /></li>
                                                        <li><input type="reset" value="Clear" /></li>
                                                    </ul>
                                                </form>
                                            </section>
                                            <section class="split">
                                                <section>
                                                    <div class="contact-method">
                                                        <span class="icon alt fa-envelope"></span>
                                                        <h3>Email</h3>
                                                        <a href="#">information@untitled.tld</a>
                                                    </div>
                                                </section>
                                                <section>
                                                    <div class="contact-method">
                                                        <span class="icon alt fa-phone"></span>
                                                        <h3>Phone</h3>
                                                        <span>(000) 000-0000 x12387</span>
                                                    </div>
                                                </section>
                                                <section>
                                                    <div class="contact-method">
                                                        <span class="icon alt fa-home"></span>
                                                        <h3>Address</h3>
                                                        <span>1234 Somewhere Road #5432<br />
                                                            Nashville, TN 00000<br />
                                                            United States of America</span>
                                                    </div>
                                                </section>
                                            </section>
                                        </div>
                                    </section>
                        
                                     Footer 
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
                                    </footer>-->

                    </div>

                    <!-- Scripts -->
                    <script src="assets/js/jquery.min.js"></script>
                    <script src="assets/js/jquery.scrolly.min.js"></script>
                    <script src="assets/js/jquery.scrollex.min.js"></script>
                    <script src="assets/js/skel.min.js"></script>
                    <script src="assets/js/util.js"></script>
                    <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
                    <script src="assets/js/main.js"></script>

                    <SCRIPT>
                        $(document).ready(function () {
                            //-----------------------ADD/REMOVE JOBS---------------------------------------
                            var numberResumes = 1;
                            var count = 1;

                            document.getElementById("addJob").onclick = function () {
                                if (numberResumes < 3) {
                                    var clone = $(".jobss:first").clone();
                                    clone.find(':input').val('');
                                    clone.find(":input").attr("name", function (i, val) {
                                        return val + count;
                                    });
                                    var text = $("<h3></h3>");
                                    text.html("Please Provide Your Job Experience:");
                                    text.appendTo(".addJobs");
                                    clone.appendTo(".addJobs");
                                    count++;
                                    numberResumes++;
                                } else {
                                    var error = $("<h2></h2>");
                                    error.html("You can maximum have 3 job experiences");
                                    error.appendTo(".addJobs");
                                }
                            }
                            document.getElementById("removeJob").onclick = function () {
                                $(".jobss:last").remove();
                                $("h3:last").remove();
                                $("h2:last").remove();
                                numberResumes--;
                            }

                            //-----------------------------COURSES---------------------------------


                            var isChecked = document.getElementById('majorIS');
                            var bisChecked = document.getElementById('majorBIS');
                            $("#coursesBIS").hide();
                            $("#tableBIS").show();

                            isChecked.onclick = showIS;
                            bisChecked.onclick = showBIS;

                            function showIS() {
                                $("#coursesIS").show();
                                $("#coursesBIS").hide();
                                $("#tableIS").show();
                                $("#tableBIS").hide();
                            }

                            function showBIS() {
                                $("#coursesBIS").show();
                                $("#coursesIS").hide();
                                $("#tableBIS").show();
                                $("#tableIS").hide();
                            }
                        });

                    </SCRIPT>
                    <script>
                        $(document).ready(function () {
                            $('textarea[data-limit-rows=true]')
                                    .on('keypress', function (event) {
                                        var textarea = $(this),
                                                text = textarea.val(),
                                                numberOfLines = (text.match(/\n/g) || []).length + 1,
                                                maxRows = parseInt(textarea.attr('rows'));

                                        if (event.which === 13 && numberOfLines === maxRows) {
                                            return false;
                                        }
                                    });
                        });
                    </script>
                    <script>
                        function changeView(id) {
                            document.getElementById("resumeView").setAttribute('data', 'CreateResume?view=view&id=' + id + '&type=Modern');
                            //return false;
                        }
                    </script>
                    </body>
                    </html>
