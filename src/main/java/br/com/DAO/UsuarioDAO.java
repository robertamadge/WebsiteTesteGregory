package br.com.DAO;

import br.com.model.Usuario;
import br.com.util.ConnectionFactory;
import br.com.util.SessionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public List<Usuario> listarUsuario() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT " +
                "nome,  " +
                "id,  " +
                "senha,  " +
                "telefone,  " +
                "email,  " +
                "cuu,  " +
                "data_nascimento " +
                "FROM bartpapo.usuarios_ " +
                "WHERE id = ? ";

        Connection conexao = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                mapearUsuario(u, rs);
                listaUsuarios.add(u);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return listaUsuarios;
    }


    public Usuario auteticar(Usuario usuario) {
        String sql = "SELECT " +
                "nome,  " +
                "id,  " +
                "senha,  " +
                "telefone,  " +
                "email,  " +
                "cuu,  " +
                "data_nascimento " +
                "FROM bartpapo.usuarios_ " +
                "WHERE email like ? and senha like ?";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mapearUsuario(usuario, rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return usuario;
    }

    private void mapearUsuario(Usuario u, ResultSet rs) throws SQLException {
        u.setNome(rs.getString("nome"));
        u.setId(rs.getInt("id"));
        u.setSenha(rs.getString("senha"));
        u.setTelefone(rs.getString("telefone"));
        u.setEmail(rs.getString("email"));
        u.setCuu(rs.getString("cuu"));
        u.setDataNascimento(rs.getDate("data_nascimento"));
    }


    public void cadastrar(Usuario usuario) {
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(
                    "INSERT INTO bartpapo.usuarios_ " +
                            "            (nome, senha, telefone, email, cuu, data_nascimento) " +
                            "    VALUES(?, ?, ?, ?, ?, ?) " +
                            " RETURNING id "
            );

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getCuu());
            ps.setDate(6, new Date(usuario.getDataNascimento().getTime()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
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
    }

    public List<Usuario> listarAmigos() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT id, nome" +
                " FROM bartpapo.usuarios_" +
                " WHERE id <> ? AND id NOT IN (" +
                "   SELECT a.id_usuario_amigo" +
                "   FROM bartpapo.amigos a" +
                "   WHERE a.id_usuario_principal = ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, SessionUtil.getInstance().getUserSession().getId());
            ps.setInt(2, SessionUtil.getInstance().getUserSession().getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setId(rs.getInt("id"));
                listaUsuarios.add(u);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return listaUsuarios;
    }



}

