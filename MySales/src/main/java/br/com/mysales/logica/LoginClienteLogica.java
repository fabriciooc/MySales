package br.com.mysales.logica;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginClienteLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		ClienteDAO dao = new ClienteDAO();

		Cliente cl = dao.consultarPor(email, password);

		if (cl == null || cl.getId() == null) {
			return "login-invalido.jsp";
		} else {
			// Grava o cliente logado na sess�o.
			HttpSession sessao = request.getSession();
			sessao.setAttribute("clienteAutenticado", cl);
			return "apresentacao.jsp";
		}

	}

}