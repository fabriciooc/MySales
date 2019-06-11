package br.com.mysales.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarClienteLogica implements Logica {


	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String cpf = request.getParameter("cpf");

		Cliente cliente = new Cliente();

		cliente.setEmail(email);
		cliente.setSenha(pwd);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setCpfCnpj(cpf);

		ClienteDAO dao = new ClienteDAO();
		dao.adiciona(cliente);
		return "welcome.jsp";

	}

}