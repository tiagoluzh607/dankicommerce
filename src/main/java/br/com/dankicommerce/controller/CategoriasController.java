package br.com.dankicommerce.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.dankicommerce.interceptors.SomenteLogado;
import br.com.dankicommerce.model.Categoria;
import br.com.dankicommerce.model.Usuario;
import br.com.olimposistema.aipa.dao.DAO;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("categorias")
public class CategoriasController {
	
	@Inject HttpSession session;
	@Inject Result result;
	@Inject DAO<Categoria> categoriaDao;

	@Get("") @SomenteLogado
	public void categorias() {
		List<Categoria> categorias = categoriaDao.selectAll();
		result.include("categorias",categorias);
	}
	
	@Post("salvacategoriaapi")
	@Consumes(value="application/json")
	public void salvaCategoriaAPI(Categoria categoria) {
		if(Util.isNull(categoria)) {
			result.use(Results.http()).sendError(400, "sem Categoria");
			return;
		}	
		categoria = categoriaDao.insertOrUpdate(categoria);
		result.use(Results.json())
			.withoutRoot()
			.from(categoria)
			.serialize();
	}
	
}
