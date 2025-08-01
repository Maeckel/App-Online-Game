/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cnam.informatique.nt2;

import cnam.informatique.entites.Carte;
import cnam.informatique.entites.Personnage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author networks
 */
public class Mouvement extends HttpServlet {

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
            out.println("<title>Servlet Mouvement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Mouvement at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Carte carte = (Carte) request.getSession().getAttribute("tableau");
        Personnage p1 = (Personnage) request.getSession().getAttribute("A");
        Personnage p2 = (Personnage) request.getSession().getAttribute("B");
        Personnage p3 = (Personnage) request.getSession().getAttribute("C");
        Personnage p4 = (Personnage) request.getSession().getAttribute("D");
        Personnage px = (Personnage) request.getSession().getAttribute("X");
        // Choix Joueur et mouvement - Compteur - Joueur X - Mettre Joueurs en session
        //Gestion de A
        String victoire = null;
        String move = request.getParameter("move");
        
        int compteur;
        if (request.getSession().getAttribute("compteur") == null) {
            compteur = 1;
        } else {
            compteur = (int) request.getSession().getAttribute("compteur");
        }
      
        if(move != null){
            if(move.equals("hautA")) {
                carte.moveHaut(p1);
            } else if (move.equals("basA")) {
                carte.moveBas(p1);
            } else if (move.equals("droiteA")) {
               carte.moveDroite(p1);
            } else if (move.equals("gaucheA")) {
                carte.moveGauche(p1);
            }
            //Gestion de B
            if(move.equals("hautB")) {
                carte.moveHaut(p2);
            } else if (move.equals("basB")) {
                carte.moveBas(p2);
            } else if (move.equals("droiteB")) {
               carte.moveDroite(p2);
            } else if (move.equals("gaucheB")) {
                carte.moveGauche(p2);
            }
            //Gestion de C
            if(move.equals("hautC")) {
                carte.moveHaut(p3);
            } else if (move.equals("basC")) {
                carte.moveBas(p3);
            } else if (move.equals("droiteC")) {
               carte.moveDroite(p3);
            } else if (move.equals("gaucheC")) {
                carte.moveGauche(p3);
            }
            //Gestion de D
            if(move.equals("hautD")) {
                carte.moveHaut(p4);
            } else if (move.equals("basD")) {
                carte.moveBas(p4);
            } else if (move.equals("droiteD")) {
               carte.moveDroite(p4);
            } else if (move.equals("gaucheD")) {
                carte.moveGauche(p4);
            }
            
            int currentColonne = carte.getPositionDuPersonnage(px)[0];
            int currentLigne = carte.getPositionDuPersonnage(px)[1];
            
            while(currentColonne == carte.getPositionDuPersonnage(px)[0] && currentLigne == carte.getPositionDuPersonnage(px)[1]){
                if(carte.estBloque(px) == true){
                    victoire = "Bravo, vous avez gagné !!!!";
                    System.out.println(victoire);
                    System.out.println(carte.estBloque(px));
                    break;
                }
                int nombre = (int)(Math.random() * 4) + 1;
                switch (nombre) {
                  case 1:
                    carte.moveHaut(px);
                    break;
                  case 2:
                    carte.moveBas(px);
                    break;
                  case 3:
                    carte.moveDroite(px);
                    break;
                  case 4:
                    carte.moveGauche(px);
                    break;
                }
            }
            compteur = compteur + 1;
        }
        request.setAttribute("victoire", victoire);
        request.getSession().setAttribute("compteur", compteur);
        request.getSession().setAttribute("tableau", carte); // Retourne le tableau avec la JSP
        request.getRequestDispatcher("jeu").forward(request, response);
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
