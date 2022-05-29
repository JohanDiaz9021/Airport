package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dataStructure.ListVertice;
import dataStructure.Vertice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Airport;
import model.Country;

public class AirportController {
	public static final String EMPLOYEES_NAME = "src/doc/countryOri.txt";
	private Airport airport;

	public AirportController() throws IOException {
		airport = new Airport();
		airport.importCountryDes("src/doc/countryDes.txt");
		airport.importCountryOri("src/doc/countryOri.txt");
	}

	@FXML
	private TableView<Country> countryOri;

	@FXML
	private TableColumn<Country, String> origen;

	@FXML
	void search(ActionEvent event) {
		String origin = countryOrig.getText();
		String destiny = countryDestin.getText();
		String country = "";
		if (countryOrig.getText().equals("") || countryDestin.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una paise de salida y destino los datos solicitados",
					"Error", JOptionPane.WARNING_MESSAGE);
		} else {
			if(destiny.equals("Todos")) {
				JOptionPane.showMessageDialog(null, "Debe selecionar un pais en especifico",
						"Error", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if (!mode) {
					ArrayList<ListVertice<String, String, Integer>> countries = airport.bfsInList(origin);
					for (int i = 0; i < countries.size(); i++) {
						if (destiny.equalsIgnoreCase(countries.get(i).getValue())) {
							System.out.println("error");
							country = destiny;
						}
					}
				} else {
					ArrayList<Vertice<String, String, Integer>> countries = airport.bfsInMatrix(origin);
					for (int i = 0; i < countries.size(); i++) {
						if (destiny.equalsIgnoreCase(countries.get(i).getValue())) {
							country = destiny;
						}
					}
				}

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Búsqueda realizada");

				if (country.equals("")) {
					alert.setHeaderText("No encontrado");
					alert.setContentText("No existe un vuelo entre " + origin + " y " + destiny);
					alert.showAndWait();

				} else {
					alert.setHeaderText("Se ha encontrado");
					alert.setContentText("Sí existe un vuelo entre " + origin + " y " + destiny);
					alert.showAndWait();
				}
			}
		}
	}

	@FXML
	void costMin(ActionEvent event) throws IOException {
		String ini = countryOrig.getText();
		String fin = countryDestin.getText();
		String out = "";

		if (countryOrig.getText().equals("") || countryDestin.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una paise de salida y destino los datos solicitados",
					"Error", JOptionPane.WARNING_MESSAGE);
		} else {
			if (fin.equals("Todos")) {
				if (!mode) {
					out = "Estos son los costos mínimos \n";
					ArrayList<Integer> inte = airport.dijkstraInList(ini);
					ArrayList<ListVertice<String, String, Integer>> list = airport.listGraph.getListVertice();
					for (int i = 0; i < list.size(); i++) {

						if (inte.get(i) != Integer.MAX_VALUE) {
							out += list.get(i).getValue() + " con costo: " + inte.get(i) + "\n";
						} else {
							out += "No se puede viajar desde " + ini + " hasta " + list.get(i).getValue();
						}
					}

				} else {
					out = "Estos son los costos mínimos \n";
					ArrayList<Integer> inte = airport.dijkstraInMatrix(ini);
					ArrayList<Vertice<String, String, Integer>> list = airport.matrixGraph.getVertice();
					for (int i = 0; i < list.size(); i++) {

						if (inte.get(i) != Integer.MAX_VALUE) {
							out += list.get(i).getValue() + " con costo: " + inte.get(i) + "\n";
						} else {
							out += "No se puede viajar desde " + ini + " hasta " + list.get(i).getValue();
						}
					}

				}

			} else {
				if (!mode) {
					out += "Este es el costo mínimo \n";
					out += airport.dijkstraInList(ini, fin);

				} else {
					out += "Este es el costo mínimo \n";
					out += airport.dijkstraInMatrix(ini, fin);
				}
			}
			JOptionPane.showMessageDialog(null, out, "Costo", JOptionPane.WARNING_MESSAGE);
		}

	}

	@FXML
	private TextField countryOrig;

	public void initializeTableCountryOr() throws IOException {
		ObservableList<Country> observableList;
		observableList = FXCollections.observableArrayList(airport.getCountryOri());
		countryOri.setItems(observableList);
		origen.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
		countryOri.setOnMouseClicked((MouseEvent eventM) -> {
			if (eventM.getButton().equals(MouseButton.PRIMARY) && eventM.getClickCount() == 2) {
				Country country;
				country = countryOri.getSelectionModel().getSelectedItem();

				if (country != null) {
					countryOrig.setText(country.getName());
				}

			}
		});
	}

	@FXML
	private TextField countryDestin;
	@FXML
	private TableView<Country> countyDest;

	@FXML
	private TableColumn<Country, String> destino;

	public void initializeTableCountryDes() throws IOException {

		ObservableList<Country> observableList;

		observableList = FXCollections.observableArrayList(airport.getCountryDes());
		countyDest.setItems(observableList);
		destino.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
		countyDest.setOnMouseClicked((MouseEvent eventM) -> {
			if (eventM.getButton().equals(MouseButton.PRIMARY) && eventM.getClickCount() == 2) {
				Country country;
				country = countyDest.getSelectionModel().getSelectedItem();

				if (country != null) {
					countryDestin.setText(country.getName());
				}

			}
		});
	}

	@FXML
	private Label txtMod;
	boolean mode = true;

	@FXML
	void changeMod(ActionEvent event) {
		if (!mode) {
			txtMod.setText("Modo matriz de adjacencia");
			mode = true;

		} else {
			txtMod.setText("Modo lista de adjacencia");
			mode = false;
		}
	}

}
