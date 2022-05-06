<%-- 
    Document   : login
    Created on : Aug 10, 2015, 7:53:14 PM
    Author     : jim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SDEV425 Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="styles.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="main">
            <%@include file="WEB-INF/jspf/menus.jspf" %>
            <p></p>
            <p></p>
            <h2>Login</h2>

            <% if (session.getAttribute("UMUCUserEmail") == null) {
            %>



            <form action="Authenticate" method="post">
                <table class="center">
                    <tr>
                    <td>Email: </td><td><input type="text"  name="emailAddress"  size="50" autofocus> </td>
                    </tr>
                    <tr>
                        <td>
                            Password: </td><td><input type="password" name="pfield" size="50" autocomplete="off"></td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            <input type="submit" name="SignIn" value="Sign In">
                        </td>
                    </tr>
                </table>
                <p></p>
                <!-- Print Error Message if any -->
                <% String e = (String) request.getAttribute("ErrorMessage");
                    if (e != null) {
                        out.print(e);
                    }
                %>

            </form>
            <%
                } else {

                    request.setAttribute("ErrorMessage", "You are already logged in.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                    dispatcher.forward(request, response);                    
                }
            %>
        </div>
    </body>
</html>
