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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,
maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)

/**
 *
 * @author networks
 */
public class Jeu extends HttpServlet {

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
            out.println("<title>Servlet Jeu</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Jeu at " + request.getContextPath() + "</h1>");
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
    Part filePart = request.getPart("fileName"); // Permet de récupérer le fichier
    InputStream fileContent = filePart.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));

    List<List<String>> tableau = new ArrayList<>();
    int x = 0;

    while(reader.ready()) {
        String line = reader.readLine();
        if (line.contains(":")) {
            String[] parts = line.split(":");
            List<String> row = new ArrayList<>(); 
            for(String word : parts){
                if("TAILLE".equals(word)){
                    for(int i = 0; i <= Integer.parseInt(parts[2]); i++){
                        row.add(String.valueOf(i));
                    }
                }
                else if("LIGNE".equals(word)){ // Définition des index pour les lignes
                    row.add(String.valueOf(x));
                } else if (!line.startsWith("TAILLE")) { // Ignore la première ligne du fichier CSV
                    if ("1".equals(word)) {
                        row.add("black"); // On stocker les cellules remplies
                    } else {
                        row.add(""); // On stocker les cellules non remplies
                    }
                }
            }
            tableau.add(row); // Ajout des lignes au tableau
            x++;
        }
    }
    Carte carte = new Carte(tableau);
    Personnage p1 = new Personnage("A");
    Personnage p2 = new Personnage("B");
    Personnage p3 = new Personnage("C");
    Personnage p4 = new Personnage("D");
    Personnage px = new Personnage("X");
    carte.setJoueur(p1, p2, p3, p4, px);
    if(fileContent != null){
        int compteur = 1;
        request.getSession().setAttribute("compteur", compteur);
    }
    request.getSession().setAttribute("A", p1);
    request.getSession().setAttribute("B", p2);
    request.getSession().setAttribute("C", p3);
    request.getSession().setAttribute("D", p4);
    request.getSession().setAttribute("X", px);
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
