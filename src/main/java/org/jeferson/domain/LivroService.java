package org.jeferson.domain;

import com.google.gson.Gson;
import jakarta.ws.rs.core.Response;
import org.jeferson.domain.model.Livro;
import org.jeferson.repository.LivroRepositoryImp;
import java.util.HashMap;
import java.util.Map;

public class LivroService {

    private final LivroRepositoryImp livroRepository;

    public LivroService() {
        this.livroRepository = new LivroRepositoryImp();
    }

    public Response getLivro(long id) {
        try {
            return Response.ok(new Gson().toJson(livroRepository.consultarLivro(id))).build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("error", "Falha ao consultar o banco de dados.");
            errorMessage.put("message", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(errorMessage)).build();
        }
    }

    public Response listarLivros() {
        try {
            return Response.ok(new Gson().toJson(livroRepository.listarLivros())).build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("error", "Falha ao consultar o banco de dados.");
            errorMessage.put("message", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(errorMessage)).build();
        }
    }

    public Response salvarLivro(Livro livro) {
        try {
            if(livro.getAutor() == null){
                Map<String, Object> errorMessage = new HashMap<>();
                errorMessage.put("error", "Autor não pode ser Null.");
                return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(errorMessage)).build();
            }
            return Response.ok(new Gson().toJson(livroRepository.salvarLivro(livro))).build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("error", "Falha ao consultar o banco de dados.");
            errorMessage.put("message", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(errorMessage)).build();
        }
    }

}
