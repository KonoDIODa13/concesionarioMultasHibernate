package application.Controller;

import application.CRUD.MultaCRUD;
import application.Model.Coche;
import application.Model.Multa;
import application.Utils.AlertUtils;
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

import javax.swing.*;
import java.time.LocalDate;
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
    public TableView<Multa> tvMultas;

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
        //coche.getMultas().forEach(System.out::println);
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

        if (crud.insertarMulta(campos)) {
            AlertUtils.mostrarConfirmacion("Multa creada correctamente.");
            cargarTabla();
            limpiarCampos(event);
        }
    }

    @FXML
    public void modificarMulta(ActionEvent event) {
        List<String> campos = new ArrayList<>();
        /*
        if (ControllerUtils.compruebaInt(tvIdentificador.getText(), "identificador")) {
            campos.add(tvIdentificador.getText());
        }
        */
        // campos.add(tvMatricula.getText());
        if (Comprobaciones.compruebaDouble(tvPrecio.getText(), "precio")) {
            campos.add(tvPrecio.getText());
        }
        campos.add(dpFecha.getValue().toString());

        if (crud.modificarMulta(campos, multaSeleccionada)) {
            AlertUtils.mostrarConfirmacion("Multa modificada correctamente.");
            cargarTabla();
            limpiarCampos(event);
        }
    }

    @FXML
    public void borrarMulta(ActionEvent event) {
        if (multaSeleccionada != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar el la multa?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                crud.eliminarMulta(multaSeleccionada);
                AlertUtils.mostrarConfirmacion("Multa eliminada con éxito");
                limpiarCampos(event);
                cargarTabla();
            } else {
                AlertUtils.mostrarError("Seleccione primero una multa");
            }
        }
    }

    @FXML
    public void seleccionarMulta(MouseEvent mouseEvent) {
        try {
            multaSeleccionada = tvMultas.getSelectionModel().getSelectedItem();
            cargarData();
        } catch (NullPointerException e) {
            AlertUtils.mostrarError("No has seleccionado ningun dato.\n");
        }
    }

    @FXML
    public void salir(ActionEvent event) {
        CambioEscenas.cambioEscena("concesionario.fxml", multasPane);
    }

    @FXML
    public void limpiarCampos(ActionEvent event) {
        tvIdentificador.setText("");
        tvPrecio.setText("");
        dpFecha.setValue(null);
        multaSeleccionada = null;
    }

    public void cargarTabla() {
        tvMultas.getItems().clear();
        multas = crud.getMultas(cocheSeleccionado);

        tvMultas.setItems(FXCollections.observableList(multas));
    }

    public void cargarData() {
        tvIdentificador.setText(String.valueOf(multaSeleccionada.getId()));
        tvPrecio.setText(String.valueOf(multaSeleccionada.getPrecio()));
        dpFecha.setValue(multaSeleccionada.getFecha());
    }


}
