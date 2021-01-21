package br.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grupo {
    private Integer id;
    private String nome;
    private String descricao;
    private Usuario usuarioCriador;
    private List<Usuario> listaMembros;

    public Grupo() {
        this.listaMembros = new ArrayList<>();
    }

    public Grupo(Integer id, String nome, String descricao, Usuario usuarioCriador, List<Usuario> listaMembros) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuarioCriador = usuarioCriador;
        this.listaMembros = listaMembros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuarioCriador() {
        return usuarioCriador;
    }

    public void setUsuarioCriador(Usuario usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(id, grupo.id) &&
                Objects.equals(nome, grupo.nome) &&
                Objects.equals(descricao, grupo.descricao) &&
                Objects.equals(usuarioCriador, grupo.usuarioCriador) &&
                Objects.equals(listaMembros, grupo.listaMembros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, usuarioCriador, listaMembros);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuarioCriador=" + usuarioCriador +
                ", listaMembros=" + listaMembros +
                '}';
    }
}


