<%-- 
    Document   : authentification
    Created on : 1 avr. 2025, 22:56:52
    Author     : networks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String login = request.getParameter("Login");if(login==null) login=""; 
String mdp = request.getParameter("Mdp"); if(mdp==null) mdp="";
String erreur = null; if("POST".equals(request.getMethod())) erreur = "Échec de l'authentification ! Veuillez réessayer.";                                                              %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentification</title>
    </head>
    <body>
        <h1>Connexion</h1>
        <h2>Entrez votre login et votre mot de passe</h2>
        <form action="Authentification" method="POST">
        Login :<br/>
        <br/>
        <input name="Login" value="<%= login %>" type="text"><br/>
        <br/>
        Mot de passe :<br/> 
        <br/>
        <input name="Mdp" value="<%= mdp %>" type="password"><br/>
        <br/>
        <input type="submit" value="Se connecter"><br/>
        </form>
        <br/>
        <a href="inscription">Créer un compte</a>
        <% if(erreur != null){
                out.println("<br/>");
                out.println("<h3>" + erreur + "</h3>");
            }
        %>
    </body>
</html>
