<%-- 
    Document   : index
    Created on : Aug 13, 2015, 8:35:23 PM
    Author     : jim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>SDEV 425 Final Project</title>
        <link href="styles.css" rel="stylesheet" type="text/css">
    </head>

    <div id="main">
        <%@include file="WEB-INF/jspf/menus.jspf" %>
        <p></p>
        <p></p>



        <div id="left">
            <section>
                <article>               
                    <!-- This is the main content area -->
                    <img src="Images/logo.png" width="150" height="90" alt=""/>

                    <p>The online bachelor's degree in software development and security from University of Maryland University 
                        College is designed to teach you in-demand programming languages and the best practices in software
                        development in today's workplace.</p>

                    <p> Your degree can prepare you to pursue technical and leadership roles in diverse and challenging application 
                        development and security settings, including high-demand positions such as software development and security 
                        analyst, software development and security manager, application and software architect, information security 
                        officer, intrusion analyst, penetration tester, programmer, software engineer, security and code auditor,
                        or system architect. </p>

                    
                    <p>Your software development degree courses will focus on developing your skills using multiple programming 
                        languages and relational databases while maintaining component security using industry and government 
                        best practices. You'll learn to design, develop, and test secure software applications, conduct software 
                        penetration testing, and provide recommendations for reducing computer security risks. </p>

                </article>
            </section>
        </div>

        <footer>
            <!-- Include copyright information -->
            &copy;2015 UMUC  
        </footer>
    </div>
</html>
