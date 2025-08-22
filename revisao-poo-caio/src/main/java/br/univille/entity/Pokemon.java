package br.univille.entity;

import lombok.Getter;

@Getter
public class Pokemon {
  private final String nome;

  public Pokemon(String nome) {
    this.nome = nome;
  }
}