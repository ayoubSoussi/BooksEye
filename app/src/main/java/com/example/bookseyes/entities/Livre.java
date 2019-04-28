package com.example.bookseyes.entities;

public class Livre {
    private int id ;
    private String  Titre,auteur ;
    public Livre() {
    }

    public Livre(int id, String titre, String auteur) {
        this.id = id;
        Titre = titre;
        this.auteur = auteur;
    }

    public Livre(String titre, String auteur) {
        Titre = titre;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
