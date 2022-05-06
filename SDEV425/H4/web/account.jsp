<%-- 
    Document   : account
    Created on : Nov 7, 2015, 10:49:40 AM
    Author     : jim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles.css" rel="stylesheet" type="text/css">
        <title>Your Account Information</title>
    </head>
    <body>
        <div id="main">
            <%@include file="WEB-INF/jspf/menus.jspf" %>
            <p></p>
            <p></p>

            <h3>Account Data</h3>

            <% if (session.getAttribute("UMUCUserEmail") == null) {
                    // Send back to login page 
                    response.sendRedirect("login.jsp"); %>
            <% } else {%>
            <%-- Gather the data from the servlet--%>
            <% String UserEmail = (String) session.getAttribute("UMUCUserEmail");%>  
            <% String user_id = (String) session.getAttribute("UMUCUserID").toString();%>  
            <% String Cardholdername = (String) request.getAttribute("Cardholdername");%>  
            <% String CardType = (String) request.getAttribute("CardType");%>  
            <% String ServiceCode = (String) request.getAttribute("ServiceCode");%>  
            <% String CardNumber = (String) request.getAttribute("CardNumber");%>  
            <% String CAV_CCV2 = (String) request.getAttribute("CAV_CCV2").toString();%> 
            <% String expiredate = (String) request.getAttribute("expiredate").toString();%> 
            <% String FullTrackData = (String) request.getAttribute("FullTrackData");%> 
            <% String PIN = (String) request.getAttribute("PIN");%> 
          
            <form action="ContinueIt" method="post">
                <table class="center">
                    <tr>
                    <td>Email: </td>
                    <td><input type="text"  name="emailAddress"  value="<%= UserEmail%>" size="50" readOnly> </td>
                    </tr>
                    <tr>
                     <td> User ID: </td>
                     <td><input type="text" name="userid" value="<%= user_id%>" size="50" readOnly></td>
                    </tr>
                    <tr>
                     <td> Card Holder Name: </td>
                     <td><input type="text" name="cardholdername" value="<%= Cardholdername%>" size="50" readOnly></td>
                    </tr>
                     <tr>
                     <td> Card Type: </td>
                     <td><input type="text" name="cardtype" 
                                value="<%= CardType%>" size="50" readOnly></td>
                    </tr>
                     <tr>
                     <td> Service Code: </td>
                     <td><input type="text" name="servicecode"  value="<%= ServiceCode%>" size="50" readOnly></td>
                    </tr>
                    <tr>
                     <td> Card Number: </td>
                     <td><input type="text" name="cardnumber" value="<%= CardNumber%>" size="50" readOnly></td>
                    </tr>
                    <tr>
                     <td> CAV CCV2: </td>
                     <td><input type="text" name="cavccv2" value="<%= CAV_CCV2%>" size="50" readOnly></td>
                    </tr>
                     <tr>
                     <td> Expire Date: </td>
                     <td><input type="text" name="expiredate" value="<%= expiredate%>" size="50" readOnly></td>
                    </tr>
                     <tr>
                     <td> Full Track Data: </td>
                     <td><input type="text" name="fulltrackdata" value="<%= FullTrackData%>" size="50" readOnly></td>
                    </tr>
                     <tr>
                     <td> PIN: </td>
                     <td><input type="text" name="pin" value="<%= PIN%>" size="50" readOnly></td>
                    </tr>                    
                </table>
            </form>
            <% }%>
        </div>
    </body>
</html>
