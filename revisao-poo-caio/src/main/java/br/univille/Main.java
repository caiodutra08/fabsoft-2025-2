package br.univille;

import br.univille.entity.Cidade;
import br.univille.entity.Cliente;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        var cliente = new Cliente();
        cliente.setNome("Caio");
        cliente.setEmail("contato.caiodutra08@gmail.com");

        Cidade cidade = new Cidade("Joinville");
    }
}