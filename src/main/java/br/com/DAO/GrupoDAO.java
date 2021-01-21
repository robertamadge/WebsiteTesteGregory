package br.com.DAO;

import br.com.model.Grupo;
import br.com.util.ConnectionFactory;
import br.com.util.SessionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {
    public List<Grupo> listarGrupo() {
        List<Grupo> listaGrupo = new ArrayList<>();

        String sql = "SELECT " +
                "nome,  " +
                "id,  " +
                "descricao,  " +
                "usuarioCriador,  " +
                "FROM bartpapo.usuarios_ ";

        Connection conexao = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Grupo g = new Grupo();
                g.setNome(rs.getString("nome"));
                g.setId(rs.getInt("id"));
                g.setDescricao(rs.getString("descricao"));

                listaGrupo.add(g);
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

        return listaGrupo;
    }

    public void cadastrar(Grupo grupo) {
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement("" +
                    "INSERT INTO bartpapo.grupos " +
                    "(nome, descricao, id_usuario_criador) " +
                    "VALUES(?, ?, ?) RETURNING id ");
            ps.setString(1, grupo.getNome());
            ps.setString(2, grupo.getDescricao());
            ps.setInt(3, grupo.getUsuarioCriador().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                grupo.setId(rs.getInt("id"));
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
    public List<Grupo> listarGrupos() {
        List<Grupo> listarGrupos = new ArrayList<>();

        String sql = "SELECT nome, descricao, id  " +
                "FROM bartpapo.grupos " +
                "WHERE id_usuario_criador = ?";

        Connection conexao = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, SessionUtil.getInstance().getUserSession().getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Grupo g = new Grupo();
                g.setNome(rs.getString("nome"));
                g.setDescricao(rs.getString("descricao"));
                g.setId(rs.getInt("id"));


                listarGrupos.add(g);
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

        return listarGrupos;
    }


}
