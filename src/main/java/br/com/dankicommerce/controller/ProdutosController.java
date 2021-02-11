package br.com.dankicommerce.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.dankicommerce.dao.ProdutoDAO;
import br.com.dankicommerce.model.Categoria;
import br.com.dankicommerce.model.Produto;
import br.com.olimposistema.aipa.dao.DAO;

@Controller
@Path("produtos")
public class ProdutosController {
	
	@Inject Result result;
	@Inject ProdutoDAO produtoDao;
	@Inject DAO<Categoria> categoriaDao;

	@IncludeParameters
	@Get("")
	public void produtos(Produto filtro) {
		result.include("categorias", categoriaDao.selectAll());
		
		if(filtro != null) {
			result.include("produtos", produtoDao.filter(filtro));
			result.include("totalProdutos", produtoDao.filterTotal(filtro));
		}
		else result.include("produtos", produtoDao.selectAll());
	}
}
