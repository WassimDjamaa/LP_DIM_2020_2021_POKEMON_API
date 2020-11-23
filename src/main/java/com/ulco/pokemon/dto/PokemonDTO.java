package com.ulco.pokemon.dto;


import com.ulco.pokemon.enums.PokemonTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {
    @ApiModelProperty("l'id du pokemon")
    private Integer id;

    @ApiModelProperty("l'id du pokemon")
    private String name;

    @ApiModelProperty("l'id du pokemon")
    private Double taille;

    @ApiModelProperty("l'id du pokemon")
    private Double poids;

    @ApiModelProperty("l'id du pokemon")
    private PokemonTypeEnum type;
}
