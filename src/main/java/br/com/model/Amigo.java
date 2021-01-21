package br.com.model;

public class Amigo {

    private Long id;
    private Integer usuarioPrincipal;
    private Integer usuarioAmigo;

    public Amigo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsuarioPrincipal() {
        return usuarioPrincipal;
    }

    public void setUsuarioPrincipal(Integer usuarioPrincipal) {
        this.usuarioPrincipal = usuarioPrincipal;
    }

    public Integer getUsuarioAmigo() {
        return usuarioAmigo;
    }

    public void setUsuarioAmigo(Integer usuarioAmigo) {
        this.usuarioAmigo = usuarioAmigo;
    }
}
