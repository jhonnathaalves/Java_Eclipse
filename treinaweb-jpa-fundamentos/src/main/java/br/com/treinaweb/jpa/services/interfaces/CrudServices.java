package br.com.treinaweb.jpa.services.interfaces;

import java.util.List;

public interface CrudServices <T,K> {
	
	List <T> all();
	T ById(K id);
	T insert(T entity);
	T update(T entity);
	void delete(T entity);
	void deleteById(K id);
	
	
	
}
