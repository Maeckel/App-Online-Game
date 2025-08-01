/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cnam.informatique.entites;

/**
 *
 * @author networks
 */
public class Joueur {
    
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String login;
    private String mdp;

    public Joueur(int id, String nom, String prenom, int age, String login, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.login = login;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", login=" + login + ", mdp=" + mdp + '}';
    }
    
}
