package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airport;

public class AirportController {
	public static final String EMPLOYEES_NAME = "src/doc/countryOri.txt";
	private Airport airport;
	public AirportController() {
		airport = new Airport();
	}
	  @FXML
	    private TableView<String> countryOri;

	    @FXML
	    private TableColumn<Airport, String> origen;

	    @FXML
	    private TableView<Airport> countyDest;

	    @FXML
	    private TableColumn<Airport, ?> destino;

 
	 @FXML
	    void costMin(ActionEvent event) throws IOException {
		 
		 BufferedReader br = new BufferedReader(new FileReader("src/doc/countryOri.txt"));
		 System.out.println("hasta aqui");
			String line = br.readLine();
			System.out.println(line);
	    }

	    @FXML
	    void search(ActionEvent event) {

	    }
	    public void initializeTableCountryOr() {
			ObservableList<String> observableList;
			observableList = FXCollections.observableArrayList(airport.getCountryOri());
			countryOri.setItems(observableList);
			origen.setCellValueFactory(new PropertyValueFactory<Airport, String>("countryOri"));
			
	    }
}
