package it.prova.gestionebiglietti.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionebiglietti.model.Biglietto;
import it.prova.gestionebiglietti.service.MyServiceFactory;
import it.prova.gestionebiglietti.utility.UtilityBigliettoForm;

@WebServlet("/ExecuteSearchBigliettoServlet")
public class ExecuteSearchBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String provenienzaInputParam = request.getParameter("provenienza");
		String destinazioneInputParam = request.getParameter("destinazione");
		String dataStringParam = request.getParameter("data");
		String prezzoInputStringParam = request.getParameter("prezzo");

		Biglietto bigliettoInstance = UtilityBigliettoForm.createBigliettoFromParams(provenienzaInputParam,
				destinazioneInputParam, dataStringParam, prezzoInputStringParam);
		try {
			request.setAttribute("listaBigliettiAttribute",
					MyServiceFactory.getBigliettoServiceInstance().cercaByExample(bigliettoInstance));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/biglietto/results.jsp").forward(request, response);
	}

}
