<%-- 
    Document   : inscription
    Created on : 28 avr. 2025, 14:43:21
    Author     : networks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String message = null; 
if(request.getAttribute("message") != null){
    message = (String) request.getAttribute("message"); 
} %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/regi_style.css">
    </head>
    <body>
        <header>
            <div class="header-content">
                <h1>NT2 - Jeux en ligne</h1>
                <a href="connexion">Retour à l'accueil</a>
            </div>
        </header>
        <div class="container">
            <div class="form-box">
                <h1>Création de compte</h1>
                <h2>Veuillez remplir le formulaire ci-dessous</h4>
                <form action="Inscription" method="POST">
                    <div>
                        <label for="nom">Nom :</label>
                        <input type="text" name="nom" id="nom" maxlength="50" required/>
                    </div>
                    <br/>
                    <div>
                        <label for="prenom">Prénom :</label>
                        <input type="text" name="prenom" id="prenom" maxlength="50" required/>
                    </div>
                    <br/>
                    <div>
                        <label for="login">Login :</label>
                        <input type="text" name="login" id="login" maxlength="50" required/>
                    </div>
                    <br/>
                    <div>
                        <label for="password">Mot de passe :</label>
                        <input type="password" name="password" id="password" maxlength="50" required/>
                    </div>
                    <br/>
                    <div>
                        <label for="age">Age :</label>
                        <input type="number" name="age" id="age" min="0" max="150" required/>
                    </div>
                    <br/>
                    <input type="submit" value="Créer"><br/>
                </form>
                <% if(message != null){ %>
                    <div class="error-message">
                        <h4><%= message %></h4>
                    </div>
                <% } %>
                <br/>
            </div>
        </div>
        <footer>
            <div class="footer-content">
                <p>&copy; 2025 - Tous droits réservés</p>
            </div>
        </footer>        
    </body>
</html>
