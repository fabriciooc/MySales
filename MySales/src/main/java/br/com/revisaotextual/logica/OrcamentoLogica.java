package br.com.revisaotextual.logica;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;


@WebServlet("/orcamentoServlet")
public class OrcamentoLogica extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servico = request.getParameter("servico");

		// Cria a pasta uploads no contexto do servidor tomcat
		String rootPath = getServletContext().getRealPath("/");
		String relativePath = "/uploads";

		String nomeArquivo = "";

		//Verifica se possui o nome do arquivo gravado em sess�o
		if (request.getSession().getAttribute("nomeArquivo") != null) {
			nomeArquivo = (String) request.getSession().getAttribute("nomeArquivo");
		}

		PdfReader reader = new PdfReader(rootPath + File.separator + relativePath + File.separator + nomeArquivo);

		int numPaginas = reader.getNumberOfPages();

		//Realiza a extra��o do pdf
		StringBuilder sb = new StringBuilder();
		PdfTextExtractor extractor = new PdfTextExtractor(reader);
		for (int i = 1; i <= numPaginas; i++) {
			sb.append(extractor.getTextFromPage(i));
		}

		//recebe o texto e separa em palavras
		List<String> words = Arrays.asList(sb.toString().split("\\PL+"));

		int numPalavras = 0;
		for (String x : words) {
			if (x.length() > 1)
				numPalavras++;
		}

		int numLaudas = 0;
		int qtdCaracteres = sb.length();

		if (qtdCaracteres > 1250) {
			while (qtdCaracteres > 1250) {
				qtdCaracteres -= 1250;
				numLaudas++;
			}
		}

		request.setAttribute("numPaginas", numPaginas);
		request.setAttribute("numPalavras", numPalavras);
		request.setAttribute("numLaudas", numLaudas);

		NumberFormat format = NumberFormat.getCurrencyInstance();

		double valorPaginas = numPaginas * 20;
		double valorPalavras = numPalavras * 0.50;
		double valorLaudas = numLaudas * 35;

		request.setAttribute("valorPaginas", format.format(valorPaginas));
		request.setAttribute("valorPalavras", format.format(valorPalavras));
		request.setAttribute("valorLaudas", format.format(valorLaudas));
		
		HttpSession sessao = request.getSession();

		switch (servico) {
		case "pagina":
			request.setAttribute("valorTotal", format.format(valorPaginas));
			sessao.setAttribute("valorBoleto", valorPaginas);
			break;
		case "palavra":
			request.setAttribute("valorTotal", format.format(valorPalavras));
			sessao.setAttribute("valorBoleto", valorPalavras);
			break;
		case "lauda":
			request.setAttribute("valorTotal", format.format(valorLaudas));
			sessao.setAttribute("valorBoleto", valorLaudas);
			break;
		default:
			break;
		}
		
		getServletContext().getRequestDispatcher("/requisicao-servico.jsp").forward(request, response);

	}

}
