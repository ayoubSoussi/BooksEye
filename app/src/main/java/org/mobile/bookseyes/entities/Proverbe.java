package org.mobile.bookseyes.entities;


public class Proverbe {
    private int id ;
    private String titre, contenu, page;

    public Proverbe() {
    }

    public Proverbe(String titre, String contenu, String page) {
        this.titre = titre;
        this.contenu = contenu;
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public String getPage() {
        return page;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
