package application.Controller;

import application.CRUD.MultaCRUD;
import application.Model.Coche;
import application.Model.Multa;
import application.Utils.Comprobaciones;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class MultasController implements Initializable {

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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        int identificador;
        double precio;
        LocalDate localdate;
        if (Comprobaciones.compruebaInt(tvIdentificador.getText(), "identificador")) {
            identificador = Integer.parseInt(tvIdentificador.getText());
        }
        if (Comprobaciones.compruebaDouble(tvPrecio.getText(), "precio")) {
            precio = Double.parseDouble(tvPrecio.getText());
        }
        localdate = LocalDate.parse(dpFecha.toString());
        System.out.println(localdate);

    }

    @FXML
    public void limpiarCampos(ActionEvent event) {

    }

    @FXML
    public void modificarMulta(ActionEvent event) {

    }

    @FXML
    public void seleccionarMulta(MouseEvent mouseEvent) {
    }

    public void cargarTabla() {
        tvMultas.getItems().clear();
        multas = crud.getMultas(cocheSeleccionado);

        tvMultas.setItems(FXCollections.observableList(multas));
    }


}
