package it.prova.gestionebiglietti.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionebiglietti.model.Utente;

@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = { "/login.jsp", "/LoginServlet", "/LogoutServlet", "/css/", "/js/" };
	private static final String[] PROTECTED_URLS = { "/admin/" };

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Nel filtro di check user in session");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String pathAttuale = httpRequest.getServletPath();
		System.out.println("Invocazione di: " + pathAttuale);

		boolean isInWhiteList = isPathInWhiteList(pathAttuale);

		if (!isInWhiteList) {
			Utente utenteInSession = (Utente) httpRequest.getSession().getAttribute("userInfo");
			// intanto verifico se utente in sessione
			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}
			// controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if (isPathForOnlyAdministrators(pathAttuale)) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private boolean isPathInWhiteList(String requestPath) {
		if (requestPath.equals(HOME_PATH))
			return true;

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile liberamente");
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile solo se sei Admin");
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}