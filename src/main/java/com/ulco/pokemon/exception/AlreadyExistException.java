package com.ulco.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(){
        super("La ressource existe deja");
    }
}
