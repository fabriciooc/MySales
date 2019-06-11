package br.com.revisaotextual.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditaAdministradorLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		AdminDAO dao = new AdminDAO();
		//Busca o Admin pelo id selecionado
		Administrador admin = dao.consultar(id);
		
		request.setAttribute("admin", admin);
		// envia a p�gina passando o id do administrador na url
		return "edita-admin.jsp?id=" + admin.getId();
	}

}
