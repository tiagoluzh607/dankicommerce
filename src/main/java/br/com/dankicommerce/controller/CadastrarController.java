package br.com.dankicommerce.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.dankicommerce.dao.UsuarioDAO;
import br.com.dankicommerce.model.Usuario;
import br.com.olimposistema.aipa.dao.DAO;

@Controller
@Path("cadastrar")
public class CadastrarController {
	
	@Inject EntityManager em;
	@Inject Result result;
	@Inject UsuarioDAO usuarioDao;
	@Inject Validator validator;
	@Inject HttpSession session;

	@Get("")
	public void cadastrar() {
		
	}
	
	@IncludeParameters
	@Post("salvaUsuario")
	public void salvaUsuario(@Valid Usuario usuario, String confirmaSenha) {
		
		boolean asSenhasSaoIguais = usuario.getSenha().equals(confirmaSenha);
		validator.ensure(asSenhasSaoIguais, new SimpleMessage("erro", "A Confirmação de Senha esta diferente"));
		validator.onErrorRedirectTo(this).cadastrar();

		usuarioDao.insert(usuario);
		
		session.setAttribute("usuarioLogado", usuario);
		result.redirectTo(ProdutosController.class).produtos(null);
	}
}
