package br.com.revisaotextual.logica;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;


public class UploadFileServletLogica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pega a quantidade de paginas
		String rootPath = getServletContext().getRealPath("/");
		String relativePath = "/uploads";

		// Obtendo cliente da sessao
		HttpSession sessao = request.getSession();
		Cliente clienteDaSessao = (Cliente) sessao.getAttribute("clienteAutenticado");

		PdfReader reader = new PdfReader(rootPath + File.separator + relativePath + clienteDaSessao.getNome() + ".pdf");
		int numPaginas = reader.getNumberOfPages();

		// Obtendo numero caracteres
		StringBuilder sb = new StringBuilder();
		PdfTextExtractor extractor = new PdfTextExtractor(reader);
		for (int i = 1; i <= numPaginas; i++) {
			sb.append(extractor.getTextFromPage(i));
		}
		int numCaracteres = sb.length();

		request.setAttribute("numPaginas", numPaginas);
		request.setAttribute("numCaracteres", numCaracteres);
		request.setAttribute("numLaudas", 0);

		request.setAttribute("valorPaginas", 0);
		request.setAttribute("valorCaracteres", 0);
		request.setAttribute("valorLaudas", 0);

		getServletContext().getRequestDispatcher("/requisicao-servico.jsp").forward(request, response);
	}

}
