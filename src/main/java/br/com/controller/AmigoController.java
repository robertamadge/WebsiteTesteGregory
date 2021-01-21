package br.com.controller;

import br.com.model.Amigo;
import br.com.model.Usuario;
import br.com.service.AmigoService;
import br.com.service.UsuarioService;
import br.com.util.MensagemUtil;
import br.com.util.SessionUtil;
import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "amigoMB")
public class AmigoController {
    private AmigoService amigoService;
    private UsuarioService usuarioService;

    private List<Usuario> listaUsuarios;
    private List<Usuario> listaMeusAmigos;

    private Amigo amigo;

    public AmigoController() {
        this.amigoService = new AmigoService();
        this.usuarioService = new UsuarioService();

        this.amigo = new Amigo();

        this.listaUsuarios = new ArrayList<>();
        this.listaMeusAmigos = new ArrayList<>();
        carregarMeusAmigos();
    }

    public void carregarAmigos() {
        this.listaUsuarios = this.usuarioService.listarAmigos();
    }

    public void cadastrarAmigo(Usuario usuario) {
        this.amigo.setUsuarioAmigo(usuario.getId());
        this.amigo.setUsuarioPrincipal(SessionUtil.getInstance().getUserSession().getId());
        this.amigoService.cadastrar(this.amigo);
        this.carregarAmigos();
        MensagemUtil.sucesso("Amigo Adicionando com sucesso!");
    }

    private void fecharDialog(String dlg) {
        PrimeFaces.current().executeScript("PF('" + dlg + "').hide();");
    }

    public void carregarMeusAmigos() {
        this.listaMeusAmigos = this.amigoService.listarAmigosdoUsuario();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaMeusAmigos() {
        return listaMeusAmigos;
    }

    public void setListaMeusAmigos(List<Usuario> listaMeusAmigos) {
        this.listaMeusAmigos = listaMeusAmigos;
    }
}
