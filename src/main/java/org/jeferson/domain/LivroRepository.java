package org.jeferson.domain;

import org.jeferson.domain.model.Livro;

import java.sql.SQLException;
import java.util.List;

public interface LivroRepository {

    public Livro consultarLivro(long id) throws SQLException;

    public List<Livro> listarLivros() throws SQLException;;

    public Livro salvarLivro(Livro livro) throws SQLException;;

}
