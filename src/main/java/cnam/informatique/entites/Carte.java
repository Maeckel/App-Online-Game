/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cnam.informatique.entites;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author networks
 */
public class Carte {
    
    private List<List<String>> tableau;

    public Carte(List<List<String>> tableau) {
        this.tableau = tableau;
    }

    public List<List<String>> getTableau() {
        return tableau;
    }

    public void setTableau(List<List<String>> tableau) {
        this.tableau = tableau;
    }
    
    // Retourne le nombre de lignes
    public int getNombreDeLignes() {
        int size = tableau.size() - 1;
        return size;
    }

    // Retourne le nombre de colonnes (suppose que toutes les lignes ont la même taille)
    public int getNombreDeColonnes() {
        int size = (tableau.isEmpty() ? 0 : tableau.get(0).size()) -1;
        return size;
    }
    
    public int[] getPositionDuPersonnage(Personnage personnage) {
    for (int ligne = 0; ligne < tableau.size(); ligne++) {
        for (int colonne = 0; colonne < tableau.get(ligne).size(); colonne++) {
            if (personnage.getId().equals(tableau.get(ligne).get(colonne))) {
                int[] position = {colonne,ligne};
                return position;
            }
        }
    }
    return null; // ou une exception si tu préfères
    }
    
    public void setJoueur(Personnage p1, Personnage p2, Personnage p3, Personnage p4, Personnage px) {
        Random rand = new Random();
        int lignes = getNombreDeLignes();
        int colonnes = getNombreDeColonnes();

        // Liste des positions déjà utilisées
        Set<String> positionsUtilisées = new HashSet<>();

        // Liste des joueurs (tu peux l’adapter selon ton besoin)
        List<Personnage> personnages = Arrays.asList(p1, p2, p3, p4, px);

        for (Personnage j : personnages) {
            int x, y;
            String key;

            do {
                x = rand.nextInt(lignes);
                y = rand.nextInt(colonnes);
                key = x + "-" + y;
            } while (positionsUtilisées.contains(key) || !tableau.get(x).get(y).isEmpty());

            tableau.get(x).set(y, j.getId());
            positionsUtilisées.add(key);
        }
    }
    
    public boolean moveGauche(Personnage personnage){
        int lastligne = getPositionDuPersonnage(personnage)[1];
        int lastcolonne = getPositionDuPersonnage(personnage)[0];
        
        if (lastcolonne - 1 > 0 && tableau.get(lastligne).get(lastcolonne - 1).isEmpty()) {
            Collections.swap(getTableau().get(lastligne), lastcolonne, lastcolonne - 1);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean moveDroite(Personnage personnage){
        int lastligne = getPositionDuPersonnage(personnage)[1];
        int lastcolonne = getPositionDuPersonnage(personnage)[0];
        
        if (lastcolonne + 1 <= getNombreDeColonnes() && tableau.get(lastligne).get(lastcolonne + 1).isEmpty()) {
            Collections.swap(getTableau().get(lastligne), lastcolonne, lastcolonne + 1);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean moveBas(Personnage personnage){
        int lastligne = getPositionDuPersonnage(personnage)[1];
        int lastcolonne = getPositionDuPersonnage(personnage)[0];
        
        if (lastligne + 1 <= getNombreDeLignes() && tableau.get(lastligne + 1).get(lastcolonne).isEmpty()) {
            getTableau().get(lastligne).set(lastcolonne, "");
            getTableau().get(lastligne + 1).set(lastcolonne, personnage.getId());
            return true;
        } else {
            return false;
        }
    }
    
    public boolean moveHaut(Personnage personnage){
        int lastligne = getPositionDuPersonnage(personnage)[1];
        int lastcolonne = getPositionDuPersonnage(personnage)[0];
        
        if (lastligne - 1 > 0 && tableau.get(lastligne - 1).get(lastcolonne).isEmpty()) {
            getTableau().get(lastligne).set(lastcolonne, "");
            getTableau().get(lastligne - 1).set(lastcolonne, personnage.getId());
            return true;
        } else {
            return false;
        }
    }
    
    public boolean estBloque(Personnage personnage) {
    int[] position = getPositionDuPersonnage(personnage);
    if (position == null) {
        return true; // Personnage pas sur la carte => considéré bloqué
    }

    int colonne = position[0];
    int ligne = position[1];

    // Vérifier gauche
    if (colonne - 1 >= 0 && tableau.get(ligne).get(colonne - 1).isEmpty()) {
        return false;
    }
    // Vérifier droite
    if (colonne + 1 <= getNombreDeColonnes() && tableau.get(ligne).get(colonne + 1).isEmpty()) {
        return false;
    }
    // Vérifier haut
    if (ligne - 1 >= 0 && tableau.get(ligne - 1).get(colonne).isEmpty()) {
        return false;
    }
    // Vérifier bas
    if (ligne + 1 <= getNombreDeLignes() && tableau.get(ligne + 1).get(colonne).isEmpty()) {
        return false;
    }

    // Si aucune case vide autour => bloqué
    return true;
}
    
}
