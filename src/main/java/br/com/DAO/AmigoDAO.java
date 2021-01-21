package br.com.DAO;

import br.com.model.Amigo;
import br.com.model.Usuario;
import br.com.util.ConnectionFactory;
import br.com.util.SessionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmigoDAO {

    public void cadastrar(Amigo amigo) {
        String sql = "INSERT INTO bartpapo.amigos (id_usuario_principal, id_usuario_amigo) VALUES(?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, amigo.getUsuarioPrincipal());
            ps.setInt(2, amigo.getUsuarioAmigo());
            ps.executeUpdate();
            conexao.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Usuario> listarAmigosdoUsuario() {
        List<Usuario> listaAmigos = new ArrayList<>();
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT nome, email, telefone, cuu AS codigo_unico_do_usuario " +
                            "FROM bartpapo.amigos a " +
                            "JOIN bartpapo.usuarios_ u ON u.id= a.id_usuario_amigo " +
                            "WHERE a.id_usuario_principal = ?");

            ps.setInt(1, SessionUtil.getInstance().getUserSession().getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNome(rs.getString("Nome"));
                u.setEmail(rs.getString("Email"));
                u.setTelefone(rs.getString("Telefone"));
                u.setCuu(rs.getString("codigo_unico_do_usuario"));
                listaAmigos.add(u);
            }
            conexao.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return listaAmigos;
    }
}
