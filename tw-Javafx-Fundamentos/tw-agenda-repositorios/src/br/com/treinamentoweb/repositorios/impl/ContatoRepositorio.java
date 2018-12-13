package br.com.treinamentoweb.repositorios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.treinamentoweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;

public class ContatoRepositorio implements AgendaRepositorio<Contato> {

	private static List<Contato> contatos = new ArrayList<Contato>();

	@Override
	public List<Contato> selecionar() {
		return contatos;
	}

	@Override
	public void inserir(Contato elemento) {
		contatos.add(elemento);

	}

	@Override
	public void atualizar(Contato elemento) {
		Optional<Contato> original = contatos.stream().filter(contato -> contato.getNome().equals(elemento.getNome()))
				.findFirst();
		if (original.isPresent()) {
			original.get().setIdade(elemento.getIdade());
			original.get().setTelefone(elemento.getTelefone());
		}

	}

	@Override
	public void excluir(Contato elemento) {
		contatos.remove(elemento);

	}

}
