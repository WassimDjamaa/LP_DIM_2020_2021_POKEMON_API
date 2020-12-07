package com.ulco.pokemon.service;

import com.ulco.pokemon.dto.PokemonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IPokemonService {
    List<PokemonDTO> getAll();
    PokemonDTO getPokemonById(Integer id);
    ResponseEntity<Void> deleteAllPokemons();
    ResponseEntity<Void> deletePokemonsById(Integer id);
    ResponseEntity<Void> updateAPokemon(Integer id,PokemonDTO updateAPokemon);
    ResponseEntity<Void> createAPokemon(@RequestBody PokemonDTO pokemon);
}
