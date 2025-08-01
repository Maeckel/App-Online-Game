/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cnam.informatique.nt2;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cnam.informatique.entites.Joueur;
import jakarta.resource.cci.ResultSet;
import jakarta.servlet.http.HttpSession;
import static java.lang.Boolean.FALSE;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author networks
 */
public class Authentification extends HttpServlet {

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
            out.println("<title>Servlet Authentification</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authentification at " + request.getContextPath() + "</h1>");
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
        
        // Recherche du joueur apèrs la validation du formulaire
        String login = request.getParameter("Login");
        String mdp = request.getParameter("Mdp");
        Joueur joueur = null;
        
        try {
            //DÃ©claration du driver :
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Url de connexion
            String dbUrl="jdbc:mysql://localhost:3306/Jeu?useSSL=false";
            String user = "jeu";
            String password = "java123";
            //connexion Ã  la base
            java.sql.Connection dbcon = DriverManager.getConnection(dbUrl,user,password);

            PreparedStatement psmt = dbcon.prepareStatement("select * from comptes where Login = ? and Motdepasse = ?");
            psmt.setString(1, login);
            psmt.setString(2, mdp);
            java.sql.ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                joueur = new Joueur(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Age"), rs.getString("Login"), rs.getString("Motdepasse"));
                System.out.println(joueur);
            }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        // Création de la session et de ses attributs si les identifiants sont correct
        if (joueur != null) {
            HttpSession session = request.getSession();
            session.setAttribute("nom", joueur.getNom());
            session.setAttribute("prenom", joueur.getPrenom());
            session.setAttribute("age", joueur.getAge());
            request.getRequestDispatcher("menu").forward(request, response);
        } else {
            request.getRequestDispatcher("connexion").forward(request, response); // Retour au formulaire si échec de l'authentification
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

}
