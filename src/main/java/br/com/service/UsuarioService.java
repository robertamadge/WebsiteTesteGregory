package br.com.service;

import br.com.DAO.UsuarioDAO;
import br.com.model.Usuario;

import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<Usuario> listarUsuarios() {
        return this.usuarioDAO.listarUsuario();
    }

    public Usuario autenticar(Usuario u) {
        return this.usuarioDAO.auteticar(u);
    }

    public void cadastrar(Usuario u) {
        this.usuarioDAO.cadastrar(u);
    }

    public List<Usuario> listarAmigos() {
        return this.usuarioDAO.listarAmigos();
    }
}
