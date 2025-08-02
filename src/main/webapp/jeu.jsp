<%-- 
    Document   : jeu
    Created on : 6 avr. 2025, 21:57:13
    Author     : networks
--%>

<%@page import="cnam.informatique.entites.Carte"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session = request.getSession(false);
    Carte carte = (Carte) session.getAttribute("tableau");
    int compteur;
    String victoire = (String) request.getAttribute("victoire");
    System.out.println(victoire);
    if(request.getSession().getAttribute("compteur") == null){
        compteur = 1;
    } else {
        compteur = (int) request.getSession().getAttribute("compteur");
    }
    List<List<String>> tableau = null;
    if(carte != null){
        tableau = carte.getTableau(); // or getData(), depending on your method
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jeu</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/jeu_style.css">
    </head>
    <body>
        <header>
            <div class="header-content">
                <h1>NT2 - Jeux en ligne</h1>
                <a href="menu">Retour à l'accueil</a>
            </div>
        </header>
        <div class="container">
        <h1>Capturez Monsieur X</h1>
        <h2>Chargez une nouvelle carte de la ville :</h2>
        <form action="Jeu" enctype="multipart/form-data" method="POST">
            <input type="file" name="fileName" />
            <!-- 10 Mo maximum -->
            <input type="hidden" name="MAX_FILE_SIZE" value="10485760" /><br/><br/>
            <input type="submit" name="submit" value="Chargez" />
        </form>
        <%  if(tableau != null){ %>
        <br/>
        <% out.println("<h4>Nombre de tours : " + compteur + "/20</h4>"); %>
        <br/>
        </div>
        <div class="tableau-container">
        <table style="width:40%">
        <% } %>
        <%  if(tableau != null){
                for (List<String> row : tableau) {
                    out.println("<tr>");
                    for (String cell : row) {
                        if ("black".equals(cell)) {
                            out.println("<td class='black'></td>");
                        } else {
                            out.println("<td>" + cell + "</td>");
                        }
                    }
                    out.println("</tr>");
                }
            }
            out.println("</tr>");
            out.println("</table>");
        %>
        <%  if(tableau != null && victoire == null){ %>
        <%  if(tableau != null && compteur < 20){ %>
        <br/>
        <form action="Mouvement" method="POST">
        <div class="mouvement-bloc">
          <h4>A :</h4>
          <input type="radio" id="moveA1" name="move" value="hautA" />
          <label for="moveA1">Haut</label>

          <input type="radio" id="moveA2" name="move" value="basA" />
          <label for="moveA2">Bas</label>

          <input type="radio" id="moveA3" name="move" value="droiteA" />
          <label for="moveA3">Droite</label>

          <input type="radio" id="moveA4" name="move" value="gaucheA" />
          <label for="moveA4">Gauche</label>
          
          <button type="submit">Envoyer</button>
        </div>
        <div class="mouvement-bloc">
          <h4>B :</h4>
          <input type="radio" id="moveB1" name="move" value="hautB" />
          <label for="moveB1">Haut</label>

          <input type="radio" id="moveB2" name="move" value="basB" />
          <label for="moveB2">Bas</label>

          <input type="radio" id="moveB3" name="move" value="droiteB" />
          <label for="moveB3">Droite</label>

          <input type="radio" id="moveB4" name="move" value="gaucheB" />
          <label for="moveB4">Gauche</label>
          
          <button type="submit">Envoyer</button>
        </div>
        <div class="mouvement-bloc">
          <h4>C :</h4>
          <input type="radio" id="moveC1" name="move" value="hautC" />
          <label for="moveC1">Haut</label>

          <input type="radio" id="moveC2" name="move" value="basC" />
          <label for="moveC2">Bas</label>

          <input type="radio" id="moveC3" name="move" value="droiteC" />
          <label for="moveC3">Droite</label>

          <input type="radio" id="moveC4" name="move" value="gaucheC" />
          <label for="moveC4">Gauche</label>
          
          <button type="submit">Envoyer</button>
        </div>
        <div class="mouvement-bloc">
          <h4>D :</h4>
          <input type="radio" id="moveD1" name="move" value="hautD" />
          <label for="moveD1">Haut</label>

          <input type="radio" id="moveD2" name="move" value="basD" />
          <label for="moveD2">Bas</label>

          <input type="radio" id="moveD3" name="move" value="droiteD" />
          <label for="moveD3">Droite</label>

          <input type="radio" id="moveD4" name="move" value="gaucheD" />
          <label for="moveD4">Gauche</label>
          
          <button type="submit">Envoyer</button>
        </div>
      </form>
      </div>
      <% } %>
      <% } %>
      <% if(tableau != null && compteur <= 20 && victoire != null){
            out.println("<h4>" + victoire + "</h4>");
            request.getSession().removeAttribute("tableau");
          } else if(tableau != null && compteur == 20 && victoire == null){
            out.println("<h4>Perdu ! Le nombre maximal de tours est atteint.</h4>");
          }
          %>
        <footer>
            <div class="footer-content">
                <p>&copy; 2025 - Tous droits réservés</p>
            </div>
        </footer>
    </body>
</html>
