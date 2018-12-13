package br.com.treinamentoweb.repositorios.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.treinamentoweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.fabricas.FabricaConexaoJdbc;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;

public class ContatoRepositorioJdbc implements AgendaRepositorio<Contato> {

	@Override
	public List<Contato> selecionar() throws SQLException, IOException {
		List<Contato> contatos = new ArrayList<Contato>();
		try (Connection conexao = FabricaConexaoJdbc.criarConexao()) {

			Statement comando = conexao.createStatement();
			ResultSet rs = comando.executeQuery("SELECT * FROM contatos");
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setIdade(rs.getInt("idade"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contatos.add(contato);
			}
		}
		return contatos;
	}

	@Override
	public void inserir(Contato elemento) throws IOException, SQLException {
		Connection conexao = null;
		try {
			conexao = FabricaConexaoJdbc.criarConexao();
			PreparedStatement comando = conexao
					.prepareStatement("INSERT INTO contatos (nome,idade,telefone)" + "VALUES(? , ? , ? )");

			comando.setString(1, elemento.getNome());
			comando.setInt(2, elemento.getIdade());
			comando.setString(3, elemento.getTelefone());
			comando.execute();

		} finally {
			if (conexao != null) {
				conexao.close();
			}
		}

	}

	@Override
	public void atualizar(Contato elemento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Contato elemento) {
		// TODO Auto-generated method stub

	}

}
