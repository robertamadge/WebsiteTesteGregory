package br.com.controller;

import br.com.model.Usuario;
import br.com.service.UsuarioService;
import br.com.util.MatematicaUtil;
import br.com.util.MensagemUtil;
import br.com.util.PagesUtil;
import br.com.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean(name = "usuarioMB")
public class UsuarioController {
    private UsuarioService usuarioService;
    private List<Usuario> listaUsuarios;
    private String confirmacaoSenha;

    private Usuario usuarioLogin;
    private Usuario usuarioCadastro;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
        this.usuarioLogin = new Usuario();
        this.usuarioCadastro = new Usuario();
    }


    public void logar() {
        this.usuarioLogin = this.usuarioService.autenticar(usuarioLogin);
        if (usuarioLogin.getId() != null) {
            SessionUtil.getInstance().gravarUsuario(usuarioLogin);
            PagesUtil.redirectPage("home");
        } else {
            MensagemUtil.erro("Falha na autenticação!\nUsuário não encontrado.");
        }
    }


    public void cadastrar() {
        if (this.usuarioCadastro.getSenha().equals(this.confirmacaoSenha)) {
            this.usuarioCadastro.setCuu(String.valueOf(MatematicaUtil.retornarNumeroAleatorioEntreIntervalo(100000, 999999).intValue()));
            this.usuarioService.cadastrar(usuarioCadastro);
            if (usuarioCadastro.getId() != null) {
                SessionUtil.getInstance().gravarUsuario(usuarioCadastro);
                PagesUtil.redirectPage("home");
                MensagemUtil.sucesso("Cadastro realizado com sucesso!");
            } else {
                MensagemUtil.erro("Falha na cadastrar!\nErro ao realizar o cadastro.");
            }
        } else {
            MensagemUtil.alerta("A senha e sua confirmação não conferem!");
        }
    }

    public void sair(){
        SessionUtil.destruirSessao();
        PagesUtil.redirectPage("login");
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public Usuario getUsuarioAtual() {
        return SessionUtil.getInstance().getUserSession();
    }
}
