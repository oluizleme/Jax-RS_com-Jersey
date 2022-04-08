package br.com.alura.loja.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import com.thoughtworks.xstream.XStream;

import java.net.URI;

@Path("carrinhos")
public class CarrinhoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
	}

	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}

	@Path("{id}")
	@DELETE
	public Response removeCarrinho(@PathParam("id") long id) {
		new CarrinhoDAO().remove(id);
		return Response.ok().build();
	}

	@Path("{id}/produtos/{produtoId}")
	@PUT
	public Response alteraProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, Produto produto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.troca(produto);
		return Response.ok().build();
	}

	@Path("{id}/produtos/{produtoId}/quantidade")
	@PUT
	public Response alteraQuantidadeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, Produto produto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	}
}
