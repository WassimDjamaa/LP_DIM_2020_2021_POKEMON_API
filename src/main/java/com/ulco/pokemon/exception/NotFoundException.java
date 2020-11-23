package com.ulco.pokemon.exception;

import org.springframework.web.bind.annotation.ResponseStatus;



public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("La ressource n'existe pas");
    }
}
