package fr.romgrm.microcommerce.dao;

import fr.romgrm.microcommerce.model.Product;

import java.util.List;

// le DAO signifie Data Acces Object est permet d'avoir une interface avec des fonctions qui vont être implémentées afin
//de récupérer des données concernant les objets (bdd)

public interface ProductDao {
    public List<Product> findAll(); // une fonction qui va renvoyer une liste complète des produits (objets)
    public Product findById(int id); // une fonction qui permet de trouver un produit (de la classe Product) en fonction de son id
    public Product save(Product productASave); // fonction qui permet d'ajouter un produit à la classe product

}
