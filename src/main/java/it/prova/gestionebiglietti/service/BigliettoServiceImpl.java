package it.prova.gestionebiglietti.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionebiglietti.web.listener.LocalEntityManagerFactoryListener;
import it.prova.gestionebiglietti.dao.BigliettoDAO;
import it.prova.gestionebiglietti.model.Biglietto;

public class BigliettoServiceImpl implements BigliettoService {

	private BigliettoDAO bigliettoDao;

	public void setBigliettoDao(BigliettoDAO bigliettoDao) {
		this.bigliettoDao = bigliettoDao;
	}

	@Override
	public List<Biglietto> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			bigliettoDao.setEntityManager(entityManager);
			return bigliettoDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Biglietto caricaSingoloElemento(Long idInput) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			bigliettoDao.setEntityManager(entityManager);
			return bigliettoDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Biglietto input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			bigliettoDao.setEntityManager(entityManager);

			bigliettoDao.update(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Biglietto input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			bigliettoDao.setEntityManager(entityManager);

			bigliettoDao.insert(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Biglietto input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			bigliettoDao.setEntityManager(entityManager);

			bigliettoDao.delete(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Biglietto> cercaByExample(Biglietto input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			bigliettoDao.setEntityManager(entityManager);

			return bigliettoDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
