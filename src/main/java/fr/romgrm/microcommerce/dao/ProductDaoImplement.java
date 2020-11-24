package fr.romgrm.microcommerce.dao;

import fr.romgrm.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Ici on fait l'implementation de l'interface ProductDao afin de définir les méthodes qui vont nous permettre de renvoyer
// des données à notre Controller. De base c'est ici qu'on récupère les données via la BDD. Ici on a pas de BDD donc on
// remplie les données en brutes. Ne pas oublier de mettre le @Repository

@Repository // il permet d'indiquer a springB que cette classe gère les données (normalement c'est avec une class JPA)

public class ProductDaoImplement implements ProductDao{

    public static List<Product> productsList = new ArrayList<>(); /* on créer une ArrayList de la classe Product (pour
    récupérer les infos des pdts) et on remplie cette liste avec des nouveau produits (et les infos, grâce au constructor)*/
    {
        productsList.add(new Product(1, "Aspirateur", 120, 80)); // ajout d'un nouveau pdt à la list
        productsList.add(new Product(2, "Assiettes", 4, 2));
        productsList.add(new Product(3, "Grille-pain", 35, 20));
        productsList.add(new Product(4, "Four", 300, 150));
    }




    @Override // Cette fonction nous return l'arraList entière de nos produits de la class Product
    public List<Product> findAll() {
        return productsList;
    }

    @Override // Cette fonction permet de return un produit en fonction de son id. On boucle sur tous les produits de l'arrayList
              // et si (items), avec la fonction getId de la class product, a le même Id que l'Id rentré dans l'url, alors
              // affiche items
    public Product findById(int id) {
        for(Product items : productsList) {
            if (items.getId() == id){
                return items;
            }
        }
        return null;
    }

    @Override // Cette fonction permet d'ajouter un produit  directement dans l'arrayList
    public Product save(Product productASave){
        productsList.add(productASave);
        return productASave;
    }
}
