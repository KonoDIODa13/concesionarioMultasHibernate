package application.Controller;

import application.CRUD.MultaCRUD;
import application.Model.Coche;
import application.Model.Multa;
import application.Utils.CambioEscenas;
import application.Utils.Comprobaciones;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;


public class MultasController {

    @FXML
    private AnchorPane multasPane;

    @FXML
    private DatePicker dpFecha;


    @FXML
    private TextField tvIdentificador, tvPrecio, tvMatricula;

    @FXML
    public TableView tvMultas;

    @FXML
    private TableColumn<Multa, String> tcIdentificador, tcPrecio, tcFecha;

    MultaCRUD crud;
    Coche cocheSeleccionado = null;
    Multa multaSeleccionada = null;
    List<Multa> multas;

    public MultasController() {
        crud = new MultaCRUD();
    }

    public void setCoche(Coche coche) {
        cocheSeleccionado = coche;
        tvMatricula.setText(coche.getMatricula());
        tvMatricula.setEditable(false);
        tvMatricula.setDisable(true);
        cargarTabla();
        tcIdentificador.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    @FXML
    public void borrarMulta(ActionEvent event) {

    }

    @FXML
    public void insertarMulta(ActionEvent event) {
        List<String> campos = new ArrayList<>();
        /*if (ControllerUtils.compruebaInt(tvIdentificador.getText(), "identificador")) {
            campos.add(tvIdentificador.getText());
        }*/
        campos.add(tvMatricula.getText());
        if (Comprobaciones.compruebaDouble(tvPrecio.getText(), "precio")) {
            campos.add(tvPrecio.getText());
        }
        campos.add(dpFecha.getValue().toString());

        crud.insertarMulta(campos);
        limpiarCampos(event);
        cargarTabla();
    }

    @FXML
    public void limpiarCampos(ActionEvent event) {
        tvIdentificador.setText("");
        tvPrecio.setText("");
        dpFecha.setValue(null);
    }

    @FXML
    public void modificarMulta(ActionEvent event) {

    }

    @FXML
    public void seleccionarMulta(MouseEvent mouseEvent) {
    }

    @FXML
    public void salir(ActionEvent event) {
        CambioEscenas.cambioEscena("concesionario.fxml", multasPane);
    }

    public void cargarTabla() {
        tvMultas.getItems().clear();
        multas = crud.getMultas(cocheSeleccionado);
        tvMultas.setItems(FXCollections.observableList(multas));
    }

}
