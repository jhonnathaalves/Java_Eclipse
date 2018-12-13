package br.com.treinaweb.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.interfaces.PessoaBuscaPorNome;
import br.com.treinaweb.jpa.utils.JpaUtils;

public class PessoaService implements PessoaBuscaPorNome {

	@Override
	public List<Pessoa> all() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			pessoas = em.createQuery("from Pessoa", Pessoa.class).getResultList();
			return pessoas;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public Pessoa ById(Integer id) {
		Pessoa resultado = null;
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(Pessoa.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Pessoa insert(Pessoa entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public Pessoa update(Pessoa entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
//			em.unwrap(Session.class).update(entity);
			em.getTransaction().commit();
			return entity;

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void delete(Pessoa entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();

		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			Pessoa PessoaASerDeletada = em.find(Pessoa.class, id);
			if (PessoaASerDeletada != null) {
				em.getTransaction().begin();
				em.remove(PessoaASerDeletada);
				em.getTransaction().commit();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public List<Pessoa> searchByName(String name) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			List<Pessoa> pessoas = em.createQuery("from Pessoa p where lower(p.nome) like lower(concat('%', :nome, '%'))", Pessoa.class)
						.setParameter("nome", name)
						.getResultList();
		return pessoas;
			}
		} finally

	{

		if (em != null) {
			em.close();
		}
	}
}

}
