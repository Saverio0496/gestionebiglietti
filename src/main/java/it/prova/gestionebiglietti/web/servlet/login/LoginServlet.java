package it.prova.gestionebiglietti.web.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionebiglietti.model.Utente;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		if (loginInput.equals("user") && passwordInput.equals("user")) {
			request.getSession().setAttribute("userInfo", new Utente(loginInput, passwordInput, "Classic", "User"));
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		request.setAttribute("messaggio", "Credenziali errate");
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

}
