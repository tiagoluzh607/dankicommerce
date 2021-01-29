package br.com.dankicommerce.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.dankicommerce.interceptors.SomenteLogado;
import br.com.dankicommerce.model.Usuario;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("categorias")
public class CategoriasController {
	
	@Inject HttpSession session;
	@Inject Result result;

	@Get("") @SomenteLogado
	public void categorias() {
	}
	
}
