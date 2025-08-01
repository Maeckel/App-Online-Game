/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cnam.informatique.nt2;

import com.mysql.cj.xdevapi.Statement;
import jakarta.jms.Connection;
import jakarta.resource.cci.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author networks
 */
public class Inscription extends HttpServlet {

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
            out.println("<title>Servlet Inscription</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inscription at " + request.getContextPath() + "</h1>");
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
        
        String message = null;     

        try {
            //DÃ©claration du driver :
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Url de connexion
            String dbUrl="jdbc:mysql://localhost:3306/Jeu?useSSL=false";
            String user = "jeu";
            String password = "java123";
            //connexion Ã  la base
            java.sql.Connection dbcon = DriverManager.getConnection(dbUrl,user,password);
                                
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String login = request.getParameter("login");
            String mdp = request.getParameter("password");
            int age = Integer.parseInt(request.getParameter("age"));

            PreparedStatement psmt = dbcon.prepareStatement("insert into comptes(Nom, Prenom, Age, Login, Motdepasse) values(?, ?, ?, ?, ?)");
            psmt.setString(1, nom);
            psmt.setString(2, prenom);
            psmt.setInt(3, age);
            psmt.setString(4, login);
            psmt.setString(5, mdp);
            psmt.executeUpdate();
                                
            message = "Bravo ! Vous êtes maintenant enregisté.";
                                
            request.setAttribute("message", message);
            request.getRequestDispatcher("inscription").forward(request, response);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            }       
                        
        request.setAttribute("message", message);
        request.getRequestDispatcher("inscription").forward(request, response);
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

}
