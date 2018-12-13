package br.com.treinamentoweb.agenda.interfaces;

import java.util.List;

public interface AgendaRepositorio<T> {
	List<T> selecionar();

	void inserir(T entidade);

	void atualizar(T entidade);

	void excluir(T entidade);
}
