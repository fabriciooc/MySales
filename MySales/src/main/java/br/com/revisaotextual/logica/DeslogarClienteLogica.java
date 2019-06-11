package br.com.revisaotextual.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeslogarClienteLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession sessao = request.getSession();
		if (sessao.getAttribute("clienteAutenticado") == null) {
			return "apresentacao.jsp";
		} else {
			sessao.invalidate();
			return "login.jsp";
		}
	}

}