package fr.romgrm.microcommerce.web.controller;


import fr.romgrm.microcommerce.dao.ProductDao;
import fr.romgrm.microcommerce.model.Product;
import fr.romgrm.microcommerce.web.exceptions.ProductIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;


@RestController // l'annotation @RestController permet de dire que cette classe va envoyer des requêtes et qu'il faudra
                // la lire dans le naviguateur sans passer par une vue 
public class ProductController {

    // on créer une var productDao de la class ProductDao. Cette var est automatiquement instanciée grâce à
    // @Autowired et donc notre var a accès à toutes les fonctions de la class ProductDao
    @Autowired
    private ProductDao productDao;

    // on créer des requête GET sur la class Product avec la var productDao et les fonctions implémentées
    @RequestMapping(value="/Products", method=RequestMethod.GET)
    public List<Product> listeAllProducts (){
        return productDao.findAll();
    }



    @GetMapping(value="/Products/{id}")
    public Product afficherUnProduitId(@PathVariable int id){
        Product product = productDao.findById(id);

        // si l'id rentré ne correspond a aucun prdt, on envoie une error personnalisé grâce à la class 'productIntrouvableException' créée
        if(product==null) {
            throw new ProductIntrouvableException("Le produit avec l'id " + id + " n'existe pas !");
        }

        return product;
    }

    // cette methode permet de retourner tous les produits a un prix superieur a {prixLimit}
    @GetMapping(value="test/produits/{prixLimit}")
    public  List<Product> testRequete(@PathVariable int prixLimit){
        return productDao.findByPrixGreaterThan(100);
    }

    // ici on recherche un pdt par son nom avec la method findByNomLike. le {recherche} correspondra a l'entrée URI de l'utilisateur
    // l'entrée URI doit correspondre parfaitement a nos requetes sql (ex: Aspitareur et non aspirateur) pour afficher le résultat
    @GetMapping(value = "/recherche/{recherche}")
    public List<Product> stringTest(@PathVariable String recherche) {
        return productDao.findByNomLike(recherche);
    }

    // .save() method connu par JPA donc pas besoin de l'implémenter dans JPArepository, juste la déclarer dans le controller
    // l'annotation @Valid sert à indiquer au Controller que le produit reçu (donc la requête Sql contenant le nom, prix de l'article) est à valider
    // dû aux constraints @Length et @Min d'hibernate
    @PostMapping(value="/Products")
    public ResponseEntity<Void> createProduct (@Valid @RequestBody Product product) {
        Product savedProduct = productDao.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // .deleteById() method connu par JPA donc pas besoin de l'implémenter dans JPArepository, juste la déclarer dans le controller
    @DeleteMapping (value = "/Products/{id}")
    public void deleteProduct(@PathVariable int id){
        productDao.deleteById(id);
    }

    // Ici .save() est utilisé en PUT donc l'id renseigné devra obligatoirement être le même que l'id du produit déja existant
    // que l'on souhaite update

    @PutMapping(value="/Products")
    public void updateProduct(@RequestBody Product product) {
        productDao.save(product);
    }
    
    
    /*
    //Cette requête POST nous permet d'ajouter un produit. le @ResquestBody permet de convertir la requête POST de format
    // JSON en objet pour springboot. product sera donc sauvegarder via la fonction implémentée 'save' de productDao, en
    // tant qu'objet de la classe Product (donc avec un id, un nom et un prix)
    // On ne peut pas gérer directement une requête POST avec un naviguateur, c'est pour ça qu'on utilise PostMan
    // Le protocole HTTP veut que lorsqu'une requete POST est executée, on nous renvoie un code 201 created. Or la on a
    // un code 200. C'est pour ça qu'on va utiliser le ResponseEntity pour changer le code http a return
    // ResponseEntity permet de définir le code http à return et donc nous permettre de le modifier

    // La method .save est autogeneree par la dépendance data/JPA ce qui fait qu'on a pas à la spécifier dans le JPArepository
    @PostMapping(value="/Products")
    public ResponseEntity<Void> ajouterProduit  (@RequestBody Product product ) {

        // on ajoute le produit, qu'on stock dans une variable pour pouvoir modifier l'url et la réponse
        // http par la suite
        Product productAdded =  productDao.save(product);

        // si notre product est vide/null, on renvoie le code 204 no Content grâce à la fonction 'noContent'
       // if (productAdded == null)
         //   return ResponseEntity.noContent().build();

        // Si le product n'est pas vide, alors on renvoie le code 201 + on ajoute cette nouvelle ressource
        // à l'URI, en lui ajoutant également un {id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        // on invoque ensuite le fonction created qui va permettre de renvoyer le code 201 et l'URI de
        // notre product, stocké dans 'location'
        return ResponseEntity.created(location).build();

        // L'intérêt d'avoir ajouté notre product à l'URI c'est de pouvoir le retrouver dans l'url via
        // une methode get et de lui attribuer un id et donc de l'afficher dans le nav web

    } */


    

    
}
