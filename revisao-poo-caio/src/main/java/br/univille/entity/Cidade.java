package br.univille.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cidade {
  private String nome;

  public Cidade(String nome) {
    this.nome = nome;
  }
}
