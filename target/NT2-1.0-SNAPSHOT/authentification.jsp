<%-- 
    Document   : authentification
    Created on : 1 avr. 2025, 22:56:52
    Author     : networks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
request.getSession().invalidate();
String login = request.getParameter("Login");if(login==null) login=""; 
String mdp = request.getParameter("Mdp"); if(mdp==null) mdp="";
String erreur = null; if("POST".equals(request.getMethod())) erreur = "Échec de l'authentification ! Veuillez réessayer.";                                                              %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Authentification</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/auth_style.css">
</head>
<body>
    <header>
        <div class="header-content">
            <h1>NT2 - Jeux en ligne</h1>
            <a href="inscription">Créer un compte</a>
        </div>
    </header>
    <div class="container">
        <div class="form-box">
            <h1>Connexion</h1>
            <h2>Entrez votre login et votre mot de passe</h2>
            <form action="Authentification" method="POST">
                Login :<br/>
                <input name="Login" value="<%= login %>" type="text"><br/>
                <br/>
                Mot de passe :<br/>
                <input name="Mdp" value="<%= mdp %>" type="password"><br/>
                <br/>
                <input type="submit" value="Se connecter"><br/>
            </form>
            <% if(erreur != null){ %>
                <div class="error-message">
                    <h3><%= erreur %></h3>
                </div>
            <% } %>
        </div>
    </div>
    <footer>
        <div class="footer-content">
            <p>&copy; 2025 - Tous droits réservés</p>
        </div>
    </footer>
</body>
</html>
