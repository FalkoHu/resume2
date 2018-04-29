<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<%

    if (session == null || session.getAttribute("userid") == null) {
        // user is not logged in, do something about it
        System.out.println("not logged in, showing non members header");
%>

<!-- Note: The "styleN" class below should match that of the banner element. -->
<header id="header" class="alt style2">
    <a href="index.html" class="logo"><strong>IT Resume 4 U</strong> <span>by Illinois State University</span></a>
    <nav>
        <a href="#menu">Menu</a>
    </nav>
</header>

<!-- Menu -->
<nav id="menu">
    <ul class="links">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="landing.html">Landing</a></li>
        <li><a href="generic.html">Generic</a></li>
        <li><a href="elements.html">Elements</a></li>
    </ul>
    <ul class="actions vertical">
        <li><a href="#one" class="button special fit scrolly">Get Started</a></li>
        <li><a href="#" class="button fit">Log In</a></li>
    </ul>
</nav>

<%
} else {
    // user IS logged in
    System.out.println("logged in, showing members header");
    if (request.getParameter("page") != null) {
        if (request.getParameter("page").equals("home")) {
            System.out.println("1");
            //HOMEPAGE
%>

            <header id="header" class="alt style2">

<%
        }
    } else {
%>

    <header id="header">

<% } %>

        <!-- Header -->
        <!--<header id="header">-->
        <a href="index.jsp" class="logo"><strong>IT Resume 4 U</strong> <span>by Illinois State University</span></a>
        <nav>
            <a href="#menu">Menu</a>
        </nav>
    </header>

    <!-- Menu -->
    <nav id="menu">
        <ul class="links">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="changeInformation.jsp">Edit Profile</a></li>
            <li><a href="pastResumes.jsp">Past Resumes</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
        <ul class="actions vertical">
            <li><a href="index.jsp#one" class="button special fit scrolly">Get Started</a></li>
        </ul>
    </nav>

    <%    }


    %>
