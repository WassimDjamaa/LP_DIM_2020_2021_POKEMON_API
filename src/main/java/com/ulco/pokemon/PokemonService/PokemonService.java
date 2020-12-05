package com.ulco.pokemon.PokemonService;

import com.ulco.pokemon.dto.PokemonDTO;
import com.ulco.pokemon.enums.PokemonTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class PokemonService {
    private final List<PokemonDTO> pokemonList;

    public PokemonService(){
        pokemonList = new ArrayList<>();

        pokemonList.add(new PokemonDTO(1, "salamèche", 3.5, 3.5,PokemonTypeEnum.FEU));
        pokemonList.add(new PokemonDTO(2, "carapus", 3.5, 3.5, PokemonTypeEnum.EAU));
        pokemonList.add(new PokemonDTO(3, "dany", 3.5, 3.5, PokemonTypeEnum.PLANTE));
        pokemonList.add(new PokemonDTO(4, "herbivore", 3.5, 3.5, PokemonTypeEnum.PLANTE));
    }

}
