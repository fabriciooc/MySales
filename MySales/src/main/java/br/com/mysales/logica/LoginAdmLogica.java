package br.com.mysales.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAdmLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//Obt�m o usuario e senha digitado na tela.
		String username = request.getParameter("username");
		String password = request.getParameter("pass");

		AdminDAO dao = new AdminDAO();

		//realiza a consulta no banco pelo usuario e senha.
		Administrador adm = dao.consultarPor(username, password);

		if (adm == null || adm.getId() == 0) {
			return "login-invalido.jsp";
		} else {
			//Grava o administrado logado na sess�o.
			HttpSession sessao = request.getSession();
			sessao.setAttribute("adminAutenticado", adm);
			return "admin.jsp";
		}

	}

}
