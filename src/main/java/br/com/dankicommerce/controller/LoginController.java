package br.com.dankicommerce.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;

@Controller
@Path("login")
public class LoginController {

	@Get("")
	public void login() {
		System.out.println("%%%%%%%%%%%testes");
	}
}
