package br.com.mysales.logica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaProdutoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Servico> servicos = new ServicoDAO().getServicos();
		// envia na requisi��o a lista de servi�os para exibir na table.
		request.setAttribute("servicos", servicos);
		return "servicos-admin.jsp";
	}

}