package br.com.revisaotextual.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoverServicoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Servico serv = new Servico();
		serv.setId(id);
		ServicoDAO dao = new ServicoDAO();
		dao.exclui(serv);
		return "servicos-admin.jsp";
	}

}