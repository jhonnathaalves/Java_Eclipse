package br.com.treinaweb.agenda.repositorios.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AgendaRepositorio<T> {

	List<T> selecionar() throws SQLException, IOException;

	void inserir(T elemento) throws IOException, SQLException;

	void atualizar(T elemento);

	void excluir(T elemento);

}
