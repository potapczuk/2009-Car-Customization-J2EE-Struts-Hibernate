package br.com.diegoliveira.indiana.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe da entidade Grupo
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class Grupo {
    private String nome;

    public Grupo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    static public List<Grupo> gerarGrupos(){
        List<Grupo> grupos = new ArrayList<Grupo>();

        grupos.add(new Grupo("admin"));
        grupos.add(new Grupo("user"));

        return grupos;
    }
}
