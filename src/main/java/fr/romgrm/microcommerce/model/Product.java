package fr.romgrm.microcommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// dit au compilateur json Jackson d'ignorer ces propriétés et donc de ne pas les afficher. Si l'on souhaite aller plus
// loin on peut utiliser la propriété JsonFilter pour trier dynamiquement (cf cours OpenClassroom)
// dans Postman on verra donc juste le nom et le prix du product, pas l'id et son prix d'achat
//@JsonIgnoreProperties(value = {"prixAchat", "id"})

@Entity // permet de dire que notre Bean Product est une entity pour que ça soit ses données qui soit sauvegardées dans
// la BDD
public class Product {

    // Id et GeneratedValue permet d'identifier la bean comme une clé unique/primaire auto-générée
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private int prix;

    // info que l'on veut cacher en requete
    private int prixAchat;

    // DEFAULT CONSTRUCTOR

    public Product() {
    }

    // TEST CONSTRUCTOR
    public Product(int id, String nom, int prix, int prixAchat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
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

    public int getPrixAchat() {return prixAchat;}

    public void setPrixAchat(int prixAchat){this.prixAchat = prixAchat;}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", prixAchat=" + prixAchat +
                '}';
    }
}
