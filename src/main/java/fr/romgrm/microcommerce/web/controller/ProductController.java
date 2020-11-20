package fr.romgrm.microcommerce.web.controller;


import fr.romgrm.microcommerce.dao.ProductDao;
import fr.romgrm.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    
}
