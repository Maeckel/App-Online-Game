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
    </head>
    <body>
        <h1>Menu</h1>
        <br/>
        <% out.println("<p>Bonjour " + prenom + " " + nom + " !</p>");
            if(age >= 18){
                out.println("<p>Vous êtes autorisé à jouer.</p>");
                out.println("</br>");
                out.println("<a href='jeu'>Commencez une nouvelle partie</a>");
            } else {
                out.println("<p>Vous n'êtes pas autorisé à jouer.</p>");
            }
            out.println("</BODY></HTML>");                                      %>
    </body>
</html>
