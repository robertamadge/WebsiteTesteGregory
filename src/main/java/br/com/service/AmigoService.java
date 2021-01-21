package br.com.service;

import br.com.DAO.AmigoDAO;
import br.com.model.Amigo;
import br.com.model.Usuario;

import java.util.List;

public class AmigoService {

    private final AmigoDAO amigoDAO;

    public AmigoService() {
        this.amigoDAO = new AmigoDAO();
    }

    public void cadastrar(Amigo amigo) {
        this.amigoDAO.cadastrar(amigo);
    }

    public List<Usuario> listarAmigosdoUsuario() {
        return this.amigoDAO.listarAmigosdoUsuario();
    }
}
