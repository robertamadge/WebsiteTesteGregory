package br.com.controller;

import br.com.model.Grupo;
import br.com.model.Usuario;
import br.com.service.GrupoService;
import br.com.util.MensagemUtil;
import br.com.util.SessionUtil;
import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "grupoMB")
public class GrupoController {
    private GrupoService grupoService;
    private List<Usuario> listaGrupo;
    private List<Grupo> listarGrupos;
    private Grupo grupoCadastro;

    public GrupoController() {
        this.grupoService = new GrupoService();
        this.grupoCadastro = new Grupo();
        this.listarGrupos = new ArrayList<>();
        carregarMeusGrupos();
    }

    public void cadastrar() {
        this.grupoCadastro.setUsuarioCriador(SessionUtil.getInstance().getUserSession());
        this.grupoService.cadastrar(this.grupoCadastro);
        if (this.grupoCadastro.getId() != null) {
            MensagemUtil.sucesso("Grupo cadastro com sucesso!");
            fecharDialog("criarGrupo");
        } else {
            MensagemUtil.erro("Erro ao cadatrar grupo.");
        }
    }


    public void carregarMeusGrupos (){
       listarGrupos = this.grupoService.ListarGrupos();
    }

    public static void fecharDialog(String dlg) {
        PrimeFaces.current().executeScript("PF('" + dlg + "').hide();");
    }

    public void limparGrupoCadastro() {
        this.grupoCadastro = new Grupo();
    }

    public List<Usuario> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaUsuarios(List<Usuario> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public void setListaGrupo(List<Usuario> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public Grupo getGrupoCadastro() {
        return grupoCadastro;
    }

    public void setGrupoCadastro(Grupo grupoCadastro) {
        this.grupoCadastro = grupoCadastro;
    }

    public List<Grupo> getListarGrupos() {
        return listarGrupos;
    }

    public void setListarGrupos(List<Grupo> listarGrupos) {
        this.listarGrupos = listarGrupos;
    }
}
