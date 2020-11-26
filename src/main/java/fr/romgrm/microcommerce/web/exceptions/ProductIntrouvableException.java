package fr.romgrm.microcommerce.web.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Cette class permet de gérer nos errors renvoyées. Ici on gère l'error HTTP.NOT_FOUND quand un id ne correspond pas
* a un produit avec la method findById*/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductIntrouvableException extends RuntimeException {

    public ProductIntrouvableException(String s) {
        super(s);
    }
}
