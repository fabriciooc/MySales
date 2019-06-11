package br.com.revisaotextual.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class EditarServicosLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		ServicoDAO dao = new ServicoDAO();
		Servico serv = dao.consultar(id);
		
		request.setAttribute("serv", serv);
		//envia a p�gina com o id do servi�o selecionado
		return "edita-servico.jsp?id=" + serv.getId();
	}

}