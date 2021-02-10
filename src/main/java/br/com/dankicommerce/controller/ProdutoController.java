package br.com.dankicommerce.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.dankicommerce.dao.ProdutoDAO;
import br.com.dankicommerce.model.Produto;
import br.com.olimposistema.aipa.vraptorcrud.CrudRest;

@Controller
@Path("/produto")
public class ProdutoController extends CrudRest<Produto> {

	@Inject
	public ProdutoController(ProdutoDAO dao) {
		super(Produto.class, dao);
	}
	
	@Deprecated public ProdutoController() {this(null);} 
	
	

}
