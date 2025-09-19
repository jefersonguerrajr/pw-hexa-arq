package org.jeferson.repository;

import org.jeferson.config.DbUtil;
import org.jeferson.domain.LivroRepository;
import org.jeferson.domain.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositoryImp implements LivroRepository {

    @Override
    public Livro consultarLivro(long id) throws SQLException{
        String sql = "SELECT id, titulo, autor, ano_publicacao FROM livro WHERE id = ?";

        Connection conn = DbUtil.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Livro livro = new Livro();
            livro.setId(rs.getLong("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
            return livro;
        } else {
            return null;
        }
    }

    @Override
    public List<Livro> listarLivros() throws SQLException{
        String sql = "SELECT id, titulo, autor, ano_publicacao FROM livro";

        Connection conn = DbUtil.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        List<Livro> livros = new ArrayList<>();
        while(rs.next()) {
            Livro livro = new Livro();
            livro.setId(rs.getLong("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
            livros.add(livro);
        }

        return livros;
    }

    @Override
    public Livro salvarLivro(Livro livro) throws SQLException {

        String sql = "INSERT INTO livro (titulo, autor, ano_publicacao) VALUES (?, ?, ?) RETURNING id";

        Connection conn = DbUtil.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setInt(3, livro.getAnoPublicacao());

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                livro.setId(rs.getLong("id"));
            }
        }
        return livro;
    }

}
