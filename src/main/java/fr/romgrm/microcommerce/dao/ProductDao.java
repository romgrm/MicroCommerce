package fr.romgrm.microcommerce.dao;

import fr.romgrm.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// le DAO signifie Data Acces Object est permet d'avoir une interface avec des fonctions qui vont être implémentées afin
//de récupérer des données concernant les objets (bdd)

// Au lieu d'utiliser le DAO, on va utiliser JPA, un framework qui permet de faire le pont entre l'api et la bdd
// JPA va générer toutes sortes de requetes vers la bdd sans qu'on est besoin d'écrire quoi que ce soit
// JPA a besoin de 2 paramètres -> le premier c'est l'entité concernée (ici 'Product') et le type de l'id (ici Integer)
// on voit que si on enlève nos fonctions (findAll,save etc) ça marche quand même dans le Controller car il auto-génère
// la fonction findAll

public interface ProductDao extends JpaRepository <Product, Integer> {

   Product findById(int id);

   // cette methode vaut en sql select * from product  where prix > [un chiffre ici] . Mais elle est générée automatiquement
   // grâce à JPA
   List<Product> findByPrixGreaterThan(int prixLimit);

   // Cette method recherche un produit par le nom. findByNomLike est une method reconnue/auto generee de JPA, il ne faut pas la modifier
   List<Product> findByNomLike(String recherche);






    /*public List<Product> findAll(); // une fonction qui va renvoyer une liste complète des produits (objets)
    public Product findById(int id); // une fonction qui permet de trouver un produit (de la classe Product) en fonction de son id
    public Product save(Product productASave); // fonction qui permet d'ajouter un produit à la classe product
    */
}
