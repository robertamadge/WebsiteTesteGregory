package br.com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Usuario {

    private Integer id;
    private String nome;
    private String senha;
    private String telefone;
    private String email;
    private String cuu;
    private Date dataNascimento;
    private List<Grupo> listaGrupos;
    private List<Usuario> listaAmigos;

    public Usuario() {
        this.listaGrupos = new ArrayList<>();
    }

    public Usuario(Integer id, String nome, String senha, String telefone, String email, String cuu, Date dataNascimento, List<Grupo> listaGrupos) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.cuu = cuu;
        this.dataNascimento = dataNascimento;
        this.listaGrupos = listaGrupos;
    }

    public Usuario(Integer id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuu() {
        return cuu;
    }

    public void setCuu(String cuu) {
        this.cuu = cuu;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<Usuario> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(List<Usuario> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(telefone, usuario.telefone) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(cuu, usuario.cuu) &&
                Objects.equals(dataNascimento, usuario.dataNascimento) &&
                Objects.equals(listaGrupos, usuario.listaGrupos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, senha, telefone, email, cuu, dataNascimento, listaGrupos);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cuu='" + cuu + '\'' +
                ", data_nascimento=" + dataNascimento +
                ", listaGrupos=" + listaGrupos +
                '}';
    }
}
