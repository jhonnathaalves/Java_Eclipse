package br.com.treinamentoweb.agenda.fx;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.treinamentoweb.agenda.entidades.Contato;
import br.com.treinamentoweb.repositorios.impl.ContatoRepositorio;
import br.com.treinamentoweb.repositorios.impl.ContatoRepositorioJdbc;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private TableView<Contato> tabelaContatos;
	@FXML
	private Button BotaoInserir;
	@FXML
	private Button BotaoAtualizar;
	@FXML
	private Button BotaoExcluir;
	@FXML
	private TextField txfNome;
	@FXML
	private TextField txfIdade;
	@FXML
	private TextField txfTelefone;
	@FXML
	private Button BotaoCancelar;
	@FXML
	private Button BotaoSalvar;

	private boolean ehInserir;
	private Contato contatoSelecionado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tabelaContatos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HabilitarEdicaoAgenda(false);
		CarregarTabelaContatos();
		this.tabelaContatos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contato>() {

			@Override
			public void changed(ObservableValue<? extends Contato> observable, Contato oldValue, Contato newValue) {
				if (newValue != null) {
					txfNome.setText(newValue.getNome());
					txfIdade.setText(String.valueOf(newValue.getIdade()));
					txfTelefone.setText(newValue.getTelefone());
					contatoSelecionado = newValue;
				}

			}
		});
	}

	public void botaoInserir_Action() {
		this.ehInserir = true;
		this.txfNome.setText("");
		this.txfIdade.setText("");
		this.txfTelefone.setText("");
		HabilitarEdicaoAgenda(true);

	}

	public void botaoAlterar_Action() {
		HabilitarEdicaoAgenda(true);
		this.ehInserir = false;
		this.txfNome.setText(this.contatoSelecionado.getNome());
		this.txfIdade.setText(Integer.toString((this.contatoSelecionado.getIdade())));
		this.txfTelefone.setText(this.contatoSelecionado.getTelefone());

	}

	public void botaoExcluir_Action() {
		Alert confirmacao = new Alert(AlertType.CONFIRMATION);
		confirmacao.setTitle("Confirmação");
		confirmacao.setHeaderText("Confirmação da exclusão do contato");
		confirmacao.setContentText("Tem certeza de que deseja excluir este contato?");
		Optional<ButtonType> resultadoConfirmacao = confirmacao.showAndWait();
		if (resultadoConfirmacao.isPresent() && resultadoConfirmacao.get() == ButtonType.OK) {
			AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorio();
			repositorioContato.excluir(this.contatoSelecionado);

			CarregarTabelaContatos();
			this.tabelaContatos.getSelectionModel().selectFirst();
		}
	}

	public void botaoCancelar_Action() {
		HabilitarEdicaoAgenda(false);
		this.tabelaContatos.getSelectionModel().selectFirst();

	}

	public void botaoSalvar_Action() {
		try {
			AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorioJdbc();
			Contato contato = new Contato();
			contato.setNome(txfNome.getText());
			contato.setIdade(Integer.parseInt(txfIdade.getText()));
			contato.setTelefone(txfTelefone.getText());
			if (this.ehInserir) {
				repositorioContato.inserir(contato);
			} else {
				repositorioContato.atualizar(contato);
			}
			HabilitarEdicaoAgenda(false);
			CarregarTabelaContatos();
			this.tabelaContatos.getSelectionModel().selectFirst();
		} catch (Exception e) {
			Alert mensagem = new Alert(AlertType.ERROR);
			mensagem.setTitle("Erro");
			mensagem.setHeaderText("Erro no banco de dados");
			mensagem.setContentText("Houve um erro ao manipular o contato: " + e.getMessage());
			mensagem.showAndWait();
		}
	}

	private void CarregarTabelaContatos() {
		try {
			AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorioJdbc();
			List<Contato> contatos = repositorioContato.selecionar();
//			if (contatos.isEmpty()) {
//				Contato contato = new Contato();
//				contato.setNome("Jhonnatha");
//				contato.setIdade(31);
//				contato.setTelefone("99532-5383");
//				contatos.add(contato);

//			}
			ObservableList<Contato> contatoObservableList = FXCollections.observableArrayList(contatos);
			this.tabelaContatos.getItems().setAll(contatoObservableList);
		} catch (Exception e) {
			Alert mensagem = new Alert(AlertType.ERROR);
			mensagem.setTitle("Erro");
			mensagem.setHeaderText("Erro no banco de dados");
			mensagem.setContentText("Houve um erro ao obter a lista de contatos: " + e.getMessage());
			mensagem.showAndWait();
		}

	}

	private void HabilitarEdicaoAgenda(boolean EdicaoEstaHabilitada) {
		this.txfNome.setDisable(!EdicaoEstaHabilitada);
		this.txfIdade.setDisable(!EdicaoEstaHabilitada);
		this.txfTelefone.setDisable(!EdicaoEstaHabilitada);
		this.BotaoSalvar.setDisable(!EdicaoEstaHabilitada);
		this.BotaoCancelar.setDisable(!EdicaoEstaHabilitada);
		this.BotaoInserir.setDisable(EdicaoEstaHabilitada);
		this.BotaoAtualizar.setDisable(EdicaoEstaHabilitada);
		this.BotaoExcluir.setDisable(EdicaoEstaHabilitada);
		this.tabelaContatos.setDisable(EdicaoEstaHabilitada);

	}

}
