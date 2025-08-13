package br.univille.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private String nome;
    private String email;
    private Cidade cidade;
}