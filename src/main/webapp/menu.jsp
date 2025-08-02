<%-- 
    Document   : menu
    Created on : 7 avr. 2025, 16:00:59
    Author     : networks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session = request.getSession(false);
    String nom = null;
    String prenom = null;
    Integer age = null;
    if (session != null) {
        nom = (String) session.getAttribute("nom");
        prenom = (String) session.getAttribute("prenom");
        age = (Integer) session.getAttribute("age");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/menu_style.css">
    </head>
    <body>
        <header>
            <div class="header-content">
                <h1>NT2 - Jeux en ligne</h1>
                <a href="connexion">Se déconnecter</a>
            </div>
        </header>
        <div class="container">
            <h1>Menu</h1>
            <br/>
            <% out.println("<h4>Bonjour " + prenom + " " + nom + " !");
            if(age >= 18){
                out.println("Vous êtes autorisé à jouer.</h4>");
                out.println("</br>");
                out.println("<a href='jeu'>Commencez une nouvelle partie</a>");
            } else {
                out.println("Vous n'êtes pas autorisé à jouer.</h4>");
            }
            out.println("</BODY></HTML>"); %>
        </div>
        <footer>
            <div class="footer-content">
                <p>&copy; 2025 - Tous droits réservés</p>
            </div>
        </footer>   
    </body>
</html>
