package br.com.revisaotextual.logica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ListaAdministradoresLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Administrador> administradores = new AdminDAO().getAdministradores();
		//Envia na requisi��o a lista de administradores para listar na table.
		request.setAttribute("administradores", administradores);
		return "administrador.jsp";
	}

}
