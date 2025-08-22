package br.univille;

import br.univille.entity.Cidade;
import br.univille.entity.Cliente;
import br.univille.entity.Pokemon;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        var cliente = new Cliente();
        cliente.setNome("Caio");
        cliente.setEmail("contato.caiodutra08@gmail.com");

        Cidade cidade = new Cidade("Joinville");

        cliente.setCidade(cidade);

        Pokemon pokemon = new Pokemon("Giratina");
        Pokemon pokemon2 = new Pokemon("Darkrai");

        cliente.getListaPokemon().add(pokemon);
        cliente.getListaPokemon().add(pokemon2);
    }
}