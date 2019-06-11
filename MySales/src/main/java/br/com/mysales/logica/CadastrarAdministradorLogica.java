package br.com.mysales.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarAdministradorLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");

		Administrador admin = new Administrador();

		admin.setId(id);
		admin.setUsuario(usuario);
		admin.setSenha(senha);
		AdminDAO dao = new AdminDAO();
		dao.adiciona(admin);
		return "administrador.jsp";

	}

}
