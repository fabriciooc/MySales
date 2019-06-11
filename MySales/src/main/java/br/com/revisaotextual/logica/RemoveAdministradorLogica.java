package br.com.revisaotextual.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveAdministradorLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Administrador admin = new Administrador();
		admin.setId(id);
		AdminDAO dao = new AdminDAO();
		dao.exclui(admin);
		return "administrador.jsp";
	}

}
