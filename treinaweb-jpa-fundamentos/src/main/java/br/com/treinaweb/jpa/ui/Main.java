package br.com.treinaweb.jpa.ui;

import java.util.List;
import java.util.Scanner;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.interfaces.CrudServices;
import br.com.treinaweb.jpa.services.interfaces.PessoaBuscaPorNome;
import br.com.treinaweb.services.impl.PessoaService;

public class Main {

	private static Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {

		ListarPessoas();
		int opcao = 0;

		while (opcao != 6) {

			System.out.println("\n Escolha uma opção: ");
			System.out.println("1.Listar pessoas: ");
			System.out.println("2. Inserir pessoa: ");
			System.out.println("3. Atualizar pessoa: ");
			System.out.println("4. Excluir pessoa: ");
			System.out.println("5. Pesquisar pessoa pelo nome: ");
			System.out.println("6. Sair: ");
			System.out.print("\n sua opção: ");

			opcao = SCANNER.nextInt();
			SCANNER.nextLine();
			switch (opcao) {
			case 1:
				ListarPessoas();
				break;
			case 2:
				InserirPessoa();
				break;
			case 3:
				AtualizarPessoa();
				break;
			case 4:
				DeletarPessoa();
				break;
			case 5:
				PesquisarPessoaPorNome();
				break;
			default:
				System.out.println("**Opção Inválida!");
				break;
			}

		}
		System.out.println("Tchau!: ");
	}

	private static void PesquisarPessoaPorNome() {
		System.out.println("***Pesquisa de Pessoa por nome**");
		System.out.print("Digite o nome a ser Pesquisado:");
		String nomeASerPesquisado = SCANNER.nextLine();
		PessoaBuscaPorNome pessoaService = new PessoaService();
		pessoaService.searchByName(nomeASerPesquisado).forEach(pessoa -> {
			System.out.println(String.format(" - (%d) %s %s - %d anos", pessoa.getId(), pessoa.getNome(),
					pessoa.getSobrenome(), pessoa.getIdade()));

		});
	}

	private static void DeletarPessoa() {
		System.out.println("***Remoção de Pessoa**");
		System.out.print("Digite o ID da Pessoa a ser removida:");
		int idPessoaASerRemovida = SCANNER.nextInt();
		SCANNER.nextLine();
		CrudServices<Pessoa, Integer> pessoaService = new PessoaService();
		pessoaService.deleteById(idPessoaASerRemovida);
		System.out.println("Pessoa Removida com Sucesso!");
	}

	private static void AtualizarPessoa() {
		System.out.println("***Atualização de Pessoa**");
		System.out.print("Digite o ID da Pessoa a ser atualizada:");
		int idPessoa = SCANNER.nextInt();
		SCANNER.nextLine();
		CrudServices<Pessoa, Integer> pessoaService = new PessoaService();
		Pessoa pessoaAtual = pessoaService.ById(idPessoa);
		if (pessoaAtual != null) {
			System.out.println("Pessoa encontrada: ");
			System.out.println(String.format(" - Nome: %s", pessoaAtual.getNome()));
			System.out.println(String.format(" - Sobrenome: %s", pessoaAtual.getSobrenome()));
			System.out.println(String.format(" - Idade: %d\n", pessoaAtual.getIdade()));
			System.out.print("--Novo Nome: ");
			pessoaAtual.setNome(SCANNER.nextLine());
			System.out.print("--Novo Sobrenome: ");
			pessoaAtual.setSobrenome(SCANNER.nextLine());
			System.out.print("--Nova Idade: ");
			pessoaAtual.setIdade(SCANNER.nextInt());
			pessoaService.update(pessoaAtual);
			System.out.println("Pessoa atualizada com Sucesso!");
		} else {
			System.out.println("Não existem pessoas com esse ID");
		}
	}

	private static void InserirPessoa() {
		System.out.println("***Inclusão de Pessoa**");

		Pessoa novaPessoa = new Pessoa();
		System.out.print("Nome:");
		novaPessoa.setNome(SCANNER.nextLine());
		System.out.print("Sobrenome: ");
		novaPessoa.setSobrenome(SCANNER.nextLine());
		System.out.print("Idade: ");
		novaPessoa.setIdade(SCANNER.nextInt());
		CrudServices<Pessoa, Integer> pessoaService = new PessoaService();
		Pessoa pessoa = pessoaService.insert(novaPessoa);
		System.out.println(pessoa.getId());
		System.out.println("---Pessoa Inserida com sucesso!! ---");

	}

	private static void ListarPessoas() {
		CrudServices<Pessoa, Integer> PessoaService = new PessoaService();
		System.out.println("**** GERENCIAMENTO DE PESSOAS ****");
		System.out.println(" > Lista de Pessoas Cadastradas: \n");

		try {
			List<Pessoa> pessoas = PessoaService.all();
			pessoas.forEach(pessoa -> {
				System.out.println(String.format(" - (%d) %s %s - %d anos", pessoa.getId(), pessoa.getNome(),
						pessoa.getSobrenome(), pessoa.getIdade()));
			});
			if (pessoas.isEmpty()) {
				System.out.println(" - Não existe pessoas cadastradas");
			}
		} catch (Exception e) {
			System.out.println("Houve um erro ao utilizar a JPA: " + e.getMessage());
		}
	}

}
