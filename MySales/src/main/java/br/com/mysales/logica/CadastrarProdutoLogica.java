package br.com.mysales.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarProdutoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

	
		int id = Integer.parseInt(request.getParameter("id"));
		String servico = request.getParameter("produto");
		String descricaoServico = request.getParameter("descricaoProduto");
		//Lista verificar
		//List<FormaCobranca>formasCobranca=request.getParameterValues("formasCobranca");
		//String valor = request.getParameter("valor");

		Produto serv = new Produto();
		
		serv.setId(id);
		serv.setServico(servico);
		serv.setDescricaoServico(descricaoProduto);
		ProdutoDAO dao = new ProdutoDAO();
		dao.adiciona(serv);
		return "produto-admin.jsp";

	}

}