package br.com.dankicommerce.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.dankicommerce.model.Usuario;
import br.com.olimposistema.aipa.dao.DAO;

@Controller
@Path("cadastrar")
public class CadastrarController {
	
	@Inject EntityManager em;
	@Inject Result result;
	@Inject DAO<Usuario> usuarioDao;

	@Get("")
	public void cadastrar() {
		
	}
	
	@Post("salvaUsuario")
	public void salvaUsuario(Usuario usuario) {
		//salvar no banco de dados
		usuarioDao.insert(usuario);
		result.redirectTo(ProdutosController.class).produtos();
	}
}
