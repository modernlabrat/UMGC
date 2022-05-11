/*
 * Commentated By - Kyra Samuel
 *  
 * Comments on suggested changes
 * 
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jim
 */
public class Authenticate extends HttpServlet {

    // variables    
    private String username;
    private String pword;
    private Boolean isValid;
    private int user_id;
    private HttpSession session;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Authenticate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authenticate at " + request.getContextPath() + "</h1>");
            out.println("<h1>Results are " + username + "," + isValid + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the post input 
        this.username = request.getParameter("emailAddress");
        this.pword = request.getParameter("pfield");
        /*
        * SQL Injection could occur when calling validate()
        *  
        *  the function below will check if a string isAlphaNumeric
        * 
        *  public static boolean isAlphaNumeric(String s) {
        *    return s != null && s.matches("^[a-zA-Z0-9]*$");
        *  }
        * the username should be validated first before any sql queries by 
        * returning an error if the username is not alphanumeric.
        *
        * this type of system needs to implement  multi-factor authentication
        *
        * the program should generate a random digit that is stored
        * and encrypted (maybe SHA256) in the sql database, email/text
        * 
        * twilio/signalwire offers plans for sending SMS messages,
        * Google provides a nice email service as well.
        * 
        */
        this.isValid = validate(this.username, this.pword);

        response.setContentType("text/html;charset=UTF-8");
        // Set the session variable

        /*
          * The following code does not have any audit tracker, which causes
          * an issue with the secureness of this application. The application
          * is easily accessible unmodified, at the very least the program should
          * track and log the user and time during all login and logout sessions.
          * 
          * The audit should be stored in the sql database in a new table called
          * Audits. There should be a new role to where only auditors can review
          * these audits for increases security.
          *
          */
        
        if (isValid) {
            // Create a session object if it is already not  created.
            session = request.getSession(true);
            session.setAttribute("UMUCUserEmail", username);         
            session.setAttribute("UMUCUserID", user_id);

            // Send to the Welcome JSP page              
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            dispatcher.forward(request, response);

        } else {
            // Not a valid login
            // refer them back to the Login screen

            request.setAttribute("ErrorMessage", "Invalid Username or Password. Try again or contact Jim.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // Method to Authenticate

    

    public boolean validate(String name, String pass) {
        boolean status = false;
        int hitcnt=0;

        try {
            Connection conn = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sdev425","ksamuel","22Bl22!!");
            Statement stmt = conn.createStatement();

            /* 
             * Prepared statements are a safe approach because the attacker cannot
             * change the query intent. The parameterized queries would insert
             * the entire input as a string, even if there are SQL commands present.
             * It would then search for that username, which would not be present because
             * the usernames should not have the ability to include special characters
             * unless it's an underscore or non-repetitive period
             * 
             * Use PreparedStatment after alphanumeric validation
             * instead of the chunk of code below:
             * 
             * String sql = "select user_id from sdev_users  where email = ?";
             * PreparedStatement pstmt = connection.prepareStatement(sql);
             * pstmt.setString( 1, this.username);
             * ResultSet rs = pstmt.executeQuery( )
             */
            String sql = "select user_id from sdev_users  where email = '" + this.username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user_id = rs.getInt(1);
            }

            if (user_id > 0) {  // use Prepared Statements with parameterized queries             
                String sql2 = "select user_id from user_info where user_id = " + user_id + "and password = '" + this.pword + "'";
                ResultSet rs2 = stmt.executeQuery(sql2);
                while (rs2.next()) {
                    hitcnt++;
                }   
                // Set to true if userid/password match

              /*
               *
               * Requirement 8: Identify and authenticate access to system components 
               * 
               * This is a very poor way to authenticate users as there are hardly any
               * security measures involved. I would suggest implementing an OAuth 2.0 
               * authorized protocol. OAuth 2.0 using access tokens which are pieces of
               * data the represent the authorization to the web application resources.
               * 
               * There are many third-party services that implement OAuth2.0, such as 
               * Firebase Authentication. This application should use the Firebase Admin 
               * JAVA SDK - https://firebase.google.com/support/release-notes/admin/java
               * which is free and supports registering  and logging in users. The Firebase
               * Admin SDK also keeps track of the user's sessions, so refreshing the
               * web application would not necessarily log out the user. However, 
               * after logging in, the sessions should be tracked which after 15 minutes
               * of inactivity - the program should instantly log out the user.
               * 
               */
              if(hitcnt>0){
                  status=true;
              }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

}
