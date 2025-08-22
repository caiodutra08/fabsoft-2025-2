package br.univille.entity;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private String nome;
    private String email;
    private Cidade cidade;
    private ArrayList<Pokemon> listaPokemon = new ArrayList<>();
}