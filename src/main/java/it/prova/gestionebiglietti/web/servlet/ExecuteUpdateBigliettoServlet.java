package it.prova.gestionebiglietti.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionebiglietti.model.Biglietto;
import it.prova.gestionebiglietti.service.MyServiceFactory;
import it.prova.gestionebiglietti.utility.UtilityBigliettoForm;

@WebServlet("/ExecuteUpdateBigliettoServlet")
public class ExecuteUpdateBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idBigliettoDaAggiornare = request.getParameter("idBiglietto");

		if (!NumberUtils.isCreatable(idBigliettoDaAggiornare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		String provenienzaInputParam = request.getParameter("provenienza");
		String destinazioneInputParam = request.getParameter("destinazione");
		String dataStringParam = request.getParameter("data");
		String prezzoInputStringParam = request.getParameter("prezzo");

		Biglietto bigliettoInstance = UtilityBigliettoForm.createBigliettoFromParams(provenienzaInputParam,
				destinazioneInputParam, dataStringParam, prezzoInputStringParam);

		bigliettoInstance.setId(Long.parseLong(idBigliettoDaAggiornare));

		if (!UtilityBigliettoForm.validateBigliettoBean(bigliettoInstance)) {
			request.setAttribute("aggiorna_biglietto_attr", bigliettoInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/biglietto/edit.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getBigliettoServiceInstance().aggiorna(bigliettoInstance);
			request.setAttribute("listaBigliettiAttribute", MyServiceFactory.getBigliettoServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/biglietto/results.jsp").forward(request, response);
	}

}
