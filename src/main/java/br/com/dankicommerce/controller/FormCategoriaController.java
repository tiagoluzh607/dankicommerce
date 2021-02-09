package br.com.dankicommerce.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import br.com.dankicommerce.interceptors.SomenteLogado;
import br.com.dankicommerce.model.Categoria;
import br.com.olimposistema.aipa.dao.DAO;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("formcategoria")
public class FormCategoriaController {
	
	@Inject Validator validator;
	@Inject DAO<Categoria> categoriaDao;
	@Inject Result result;
	
	@Get("") @SomenteLogado
	public void formcategoria(Categoria categoria) {
		if( Util.isNotNull(categoria) && Util.isPositivo(categoria.getId())) {
			Categoria categoriaDoBanco = categoriaDao.selectPorId(categoria);
			result.include("categoria",categoriaDoBanco);
		}
		
	}
	
	
	@IncludeParameters
	@SomenteLogado
	@Post("salvaCategoria")
	public void salvaCategoria(@Valid Categoria categoria) {
		validator.onErrorRedirectTo(this).formcategoria(categoria);
		categoriaDao.insertOrUpdate(categoria);
		result.redirectTo(CategoriasController.class).categorias();
	}
	
}
