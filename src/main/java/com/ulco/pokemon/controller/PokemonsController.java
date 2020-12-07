package com.ulco.pokemon.controller;
import java.util.List;

import com.ulco.pokemon.dto.PokemonDTO;
import com.ulco.pokemon.service.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pokemons")
public class PokemonsController {

  @Autowired
  private IPokemonService pokemonService;

  @GetMapping
  public List<PokemonDTO> getPokemons() {
    return pokemonService.getAll();
  }

  @GetMapping("/{id}")
  public PokemonDTO getPokemonById(@PathVariable Integer id){
    return  pokemonService.getPokemonById(id);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAllPokemons() {
    return pokemonService.deleteAllPokemons();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePokemonsById(@PathVariable Integer id) {
    return pokemonService.deletePokemonsById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateAPokemon(
          @PathVariable Integer id,
          @RequestBody PokemonDTO updateAPokemon) {

    return pokemonService.updateAPokemon(id,updateAPokemon);
  }

  @PostMapping()
  public ResponseEntity<Void> createAPokemon(@RequestBody PokemonDTO pokemon) {
    return pokemonService.createAPokemon(pokemon);
  }

}
