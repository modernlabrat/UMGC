/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// DB resources
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author jim
 * 
 * Commentated By - Kyra Samuel
 *  
 * Comments on suggested changes
 */
public class ShowAccount extends HttpServlet {

    // Variable
    private HttpSession session;
    // Database field data
    private int user_id;
    private String Cardholdername;
    private String CardType;
    private String ServiceCode;
    private String CardNumber;
    private int CAV_CCV2;
    private Date expiredate;
    private String FullTrackData;
    private String PIN;

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

        session = request.getSession(true);
        if (session.getAttribute("UMUCUserEmail") == null) {
            // Send back to login page 
            response.sendRedirect("login.jsp");
        } else {
            // Connect to the Database and pull the data
            getData();
            
            // Set the Attribute for viewing in the JSP

            /*
             * Requirement 3: Protect stored cardholder data
             * I would not display the PIN, ServiceCode, or CCV
             * I also do not know why this application is storing this information,
             * At the very least, the last four digits of the card number should only
             * be visible. 
             * 
             * The Application should not store any of this information, if for some reason
             * an application needed to send and receive payments. A third party application
             * such as Stripe, Paypal, or Square is better off handling the transactions.
             * These services offer better security because the customer_id should only be 
             * stored in the SQL database, then with that customer_id, we should be able to 
             * connect to our third-party app and manage billing.
             * 
             * Full Track Data, CVCs, and PINs should NEVER be stored after authorization.
             * 
             */
            request.setAttribute("Cardholdername", Cardholdername);
            request.setAttribute("CardType", CardType);
            request.setAttribute("ServiceCode", ServiceCode);
            request.setAttribute("CardNumber", CardNumber);
            request.setAttribute("CAV_CCV2", CAV_CCV2);
            request.setAttribute("expiredate", expiredate);
            request.setAttribute("FullTrackData", FullTrackData);
            request.setAttribute("PIN", PIN);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
            dispatcher.forward(request, response); 
        }

    }

    /*
     * Requirement 4: Encrypt transmission of cardholder data across open, public networks
     * 
     * This requires strong cryptography and security protocols such as TLS, SSH, or IPSec. In other words,
     * we should not deploy this application for production use as is. I recommended using a virtual private
     * server to run server with a reverse proxy to a domain that has a TLS certificate at the very least. 
     * Firebase hosting provides domains with a free certificate domain, per project!
     * 
     * The VPS should have a firewall setup as well. The server code should not run
     * on the root's account. For example, DigitalOcean provides virtual private servers
     * that run indefinitely (unless you do not pay) called Droplets, which can serve
     * this application under an account with the appropriate permissions.
     *
     */

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
        processRequest(request, response);
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

    public void getData() {

        try {
            Connection conn = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/sdev425","admin","adm1n");
            Statement stmt = conn.createStatement();

            String sql = "select user_id,Cardholdername, Cardtype,"
                    + "ServiceCode, CardNumber,CAV_CCV2,expiredate,FullTrackData,PIN"
                    + " from customeraccount  where user_id = " + session.getAttribute("UMUCUserID");
            ResultSet rs = stmt.executeQuery(sql);
            // Assign values
            while (rs.next()) {
                user_id = rs.getInt(1);
                Cardholdername = rs.getString(2);
                CardType = rs.getString(3);
                ServiceCode = rs.getString(4);
                CardNumber = rs.getString(5);
                CAV_CCV2 = rs.getInt(6);
                expiredate = rs.getDate(7);
                FullTrackData = rs.getString(8);
                PIN = rs.getString(9);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
