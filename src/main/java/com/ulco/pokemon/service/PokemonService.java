package com.ulco.pokemon.service;

import com.ulco.pokemon.dto.PokemonDTO;
import com.ulco.pokemon.enums.PokemonTypeEnum;
import com.ulco.pokemon.exception.AlreadyExistException;
import com.ulco.pokemon.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService implements IPokemonService{

    private List<PokemonDTO> pokemonList;

    // Affiche tous les pokemons
    public PokemonService(){
        pokemonList = new ArrayList<>();

        pokemonList.add(new PokemonDTO(1, "Carapuce", 1.0, 1.0, PokemonTypeEnum.EAU));
        pokemonList.add(new PokemonDTO(2, "Bulbizare", 1.0, 1.0, PokemonTypeEnum.PLANTE));
        pokemonList.add(new PokemonDTO(3, "Salamèche", 1.0, 1.0, PokemonTypeEnum.FEU));
        pokemonList.add(new PokemonDTO(4, "Carabaffe", 10.0, 10.0, PokemonTypeEnum.EAU));
    }

    @Override
    public List<PokemonDTO> getAll() {
        return pokemonList;
    }

    // fonction pour vérifier si l'id du pokemon existe ou non
    private PokemonDTO checkIfPokemonsExistForId(Integer id){
        return pokemonList.stream()
                .filter(pokemon -> pokemon.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Le pokémon avec l'id " + id + " n'existe pas."));
    }

    // Affiche un pokemon par rapport à son id

    @Override
    public PokemonDTO getPokemonById(Integer id) {
       return checkIfPokemonsExistForId(id);
    }

    // Supprime tous les pokémons

    @Override
    public ResponseEntity<Void> deleteAllPokemons() {
        pokemonList.removeAll(pokemonList);
        return ResponseEntity.noContent().build();
    }

    // Supprime un pokemon par rapport à son id

    @Override
    public ResponseEntity<Void> deletePokemonsById(Integer id){
        PokemonDTO deleteAllPokemons = checkIfPokemonsExistForId(id);
        pokemonList.remove(deleteAllPokemons);

        return ResponseEntity.noContent().build();
    }

    // Mets à jour les caractéristiques d'un pokemon en particulier

    @Override
    public ResponseEntity<Void> updateAPokemon(
            @PathVariable Integer id,
            @RequestBody PokemonDTO updateAPokemon) {

        boolean idAlreadyExist = pokemonList.stream()
                .anyMatch(pokemon -> pokemon.getId().equals(updateAPokemon.getId()));

        if (idAlreadyExist) {
            throw new AlreadyExistException();
        }

        PokemonDTO pokemonToUpgrade = pokemonList.stream()
                .filter(pokemon -> pokemon.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);

        pokemonToUpgrade.setId(updateAPokemon.getId());
        pokemonToUpgrade.setNom(updateAPokemon.getNom());
        pokemonToUpgrade.setTaille(updateAPokemon.getTaille());
        pokemonToUpgrade.setPoids(updateAPokemon.getPoids());
        pokemonToUpgrade.setType(updateAPokemon.getType());

        return ResponseEntity.noContent().build();
    }

    // fonction pour créer un pokemon

    @Override
    public ResponseEntity<Void> createAPokemon(@RequestBody PokemonDTO pokemon) {
        boolean idAlreadyExist = pokemonList.stream()
                .anyMatch(pokemonToSearch -> pokemonToSearch.getId().equals(pokemon.getId()));

        if (idAlreadyExist) {
            throw new AlreadyExistException();
        }

        pokemonList.add(pokemon);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pokemon.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
