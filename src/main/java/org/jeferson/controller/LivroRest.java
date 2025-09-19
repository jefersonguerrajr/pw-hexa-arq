package org.jeferson.controller;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jeferson.domain.LivroService;
import org.jeferson.domain.model.Livro;

@Path("/livro")
public class LivroRest {

    private final LivroService livroService = new LivroService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLivro(@PathParam("id") long id) {
        return livroService.getLivro(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLivros(@PathParam("id") long id) {
        return livroService.listarLivros();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvarLivro(String livroStr) {
        return livroService.salvarLivro(new Gson().fromJson(livroStr, Livro.class));
    }

}
