package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtParola;

	@FXML
	private Button btnAnagrammi;

	@FXML
	private TextArea txtCorretti;

	@FXML
	private TextArea txtErrati;

	@FXML
	private Button btnReset;

	@FXML
	void calcolaAnagrammi(ActionEvent event) {
		this.txtCorretti.clear();
		this.txtErrati.clear();
		boolean corretta = true;

		String parola = this.txtParola.getText();

		// controlli sulla parola
		for (int i = 0; i < parola.length(); i++) {
			Character c = parola.charAt(i);
			if (!Character.isAlphabetic(c)) {
				corretta = false;

			}
		}

		if (corretta) {
			this.model.anagrammi(parola); // popolare due liste

			Set<String> anagrammiC = this.model.getAnagrammiC();
			Set<String> anagrammiE = this.model.getAnagrammiE();

			for (String s : anagrammiC) {
				this.txtCorretti.appendText(s + "\n");
			}
			for (String s : anagrammiE) {
				this.txtErrati.appendText(s + "\n");
			}
		}else {
			this.txtCorretti.setText("Inserisci parola");
		}
	}

	@FXML
	void pulisci(ActionEvent event) {

		this.txtCorretti.clear();
		this.txtErrati.clear();
		this.txtParola.clear();

	}

	@FXML
	void initialize() {
		assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnAnagrammi != null : "fx:id=\"btnAnagrammi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

	}
}
