/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	private List<Integer> list = new ArrayList<>();
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxMese"
    private ChoiceBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnUmidita"
    private Button btnUmidita; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaSequenza(ActionEvent event) {
    	txtResult.clear();
    	Integer mese = 0;
    	try {
    		mese = boxMese.getValue();
    		List<Citta> sequenza = model.trovaSequenza(mese);
    		txtResult.appendText("La sequenza che fa ottonere il costo minimo è:\n");
    		for(Citta c: sequenza)
    			txtResult.appendText(c+"\n");
    	}
    	catch(Exception e) {
    		txtResult.appendText("Devi scrivere un numero tra 1 e 12!\n");
    	}
    }

    @FXML
    void doCalcolaUmidita(ActionEvent event) {
    	txtResult.clear();
    	Integer mese = 0;
    	try {
    		mese = boxMese.getValue();
    		for(Citta c: model.getcLeCitta()) {
    			double m = model.getUmiditaMedia(mese, c);
    			txtResult.appendText(c+" "+m+"%\n");
    		}
    	}
    	catch(Exception e) {
    		txtResult.appendText("Devi selezionare un numero!\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		setList();
	}

	private void setList() {
		for(int i=1; i<=12; i++)
			list.add(i);
		boxMese.getItems().addAll(list);
	}
}

