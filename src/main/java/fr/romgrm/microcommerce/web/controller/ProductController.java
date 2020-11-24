package fr.romgrm.microcommerce.web.controller;


import fr.romgrm.microcommerce.dao.ProductDao;
import fr.romgrm.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
        return productDao.findById(id);
    }


    //Cette requête POST nous permet d'ajouter un produit. le @ResquestBody permet de convertir la requête POST de format
    // JSON en objet pour springboot. product sera donc sauvegarder via la fonction implémentée 'save' de productDao, en
    // tant qu'objet de la classe Product (donc avec un id, un nom et un prix)
    // On ne peut pas gérer directement une requête POST avec un naviguateur, c'est pour ça qu'on utilise PostMan
    // Le protocole HTTP veut que lorsqu'une requete POST est executée, on nous renvoie un code 201 created. Or la on a
    // un code 200. C'est pour ça qu'on va utiliser le ResponseEntity pour changer le code http a return
    // ResponseEntity permet de définir le code http à return et donc nous permettre de le modifier
    @PostMapping(value="/Products")
    public ResponseEntity<Void> ajouterProduit  (@RequestBody Product product ) {

        // on ajoute le produit, qu'on stock dans une variable pour pouvoir modifier l'url et la réponse
        // http par la suite
        Product productAdded =  productDao.save(product);

        // si notre product est vide/null, on renvoie le code 204 no Content grâce à la fonction 'noContent'
        if (productAdded == null)
            return ResponseEntity.noContent().build();

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

    }
    

    
}
