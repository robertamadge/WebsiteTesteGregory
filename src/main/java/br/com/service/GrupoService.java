package br.com.service;

import br.com.DAO.GrupoDAO;
import br.com.model.Grupo;
import br.com.model.Usuario;

import java.util.List;

public class GrupoService {
    private GrupoDAO grupoDAO;

    public GrupoService() {
        this.grupoDAO = new GrupoDAO();
    }

    public List<Grupo> GrupoService() {
        return this.grupoDAO.listarGrupo();
    }

    public void cadastrar(Grupo g) {
        this.grupoDAO.cadastrar(g);
    }

    public List<Grupo> ListarGrupos(){
        return grupoDAO.listarGrupos();

    }
}
