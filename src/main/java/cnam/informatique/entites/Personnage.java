/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cnam.informatique.entites;

/**
 *
 * @author networks
 */
public class Personnage {
    
    private String id;

    public Personnage(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Personnage{" + "id=" + id + '}';
    }
    
}
