package fr.romgrm.microcommerce.model;

public class Product {
    private int id;
    private String nom;
    private int prix;

    // DEFAULT CONSTRUCTOR

    public Product() {
    }

    // TEST CONSTRUCTOR
    public Product(int id, String nom, int prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }


    // GETTERS & SETTERS
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
