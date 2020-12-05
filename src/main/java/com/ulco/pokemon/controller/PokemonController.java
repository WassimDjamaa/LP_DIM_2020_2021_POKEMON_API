package com.ulco.pokemon.controller;

import com.ulco.pokemon.dto.PokemonDTO;
import com.ulco.pokemon.enums.PokemonTypeEnum;
import com.ulco.pokemon.exception.AlreadyExistException;
import com.ulco.pokemon.exception.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.ulco.pokemon.enums.PokemonTypeEnum.*;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private List<PokemonDTO> pokemonList;


    PokemonController() {
        pokemonList = new ArrayList<>();

        pokemonList.add(new PokemonDTO(1, "salamèche", 3.5, 3.5, PokemonTypeEnum.FEU));
        pokemonList.add(new PokemonDTO(2, "carapus", 3.5, 3.5, PokemonTypeEnum.EAU));
        pokemonList.add(new PokemonDTO(3, "dany", 3.5, 3.5, PokemonTypeEnum.PLANTE));
        pokemonList.add(new PokemonDTO(4, "herbivore", 3.5, 3.5, PokemonTypeEnum.PLANTE));
    }

    @GetMapping
    public List<PokemonDTO> getAllPokemon() {
        return pokemonList;
    }

    @GetMapping("/{id}")
    public PokemonDTO getOnePokemon(@PathVariable Integer id) {

        return pokemonList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Void> generatePokemon(@RequestBody PokemonDTO user) {
        pokemonList.add(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAPokemon(@PathVariable Integer id, @RequestBody PokemonDTO updatePokemon) {
            PokemonDTO pokemonToUpdate = pokemonList.stream()
                    .filter(pokemon -> pokemon.getId().equals(id))
                    .findFirst()
                    .orElseThrow(NotFoundException::new);

            pokemonToUpdate.setId(updatePokemon.getId());
            pokemonToUpdate.setName(updatePokemon.getName());
            pokemonToUpdate.setTaille(updatePokemon.getTaille());
            pokemonToUpdate.setPoids(updatePokemon.getPoids());
            pokemonToUpdate.setType(updatePokemon.getType());
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<Void> deleteAllPokemon() {
        pokemonList.clear();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAPokemon(@PathVariable Integer id) {
        pokemonList.removeIf(pokemon -> pokemon.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
