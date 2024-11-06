package application.Controller;

import application.CRUD.CocheCRUD;
import application.Model.Tipo;
import application.Utils.AlertUtils;
import application.Utils.CambioEscenas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import application.Model.Coche;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CocheController implements Initializable {
    @FXML
    private AnchorPane concesionario;

    @FXML
    private ComboBox<Tipo> cbTipo;

    @FXML
    private TextField tfMatricula, tfMarca, tfModelo, tfTipo;

    @FXML
    private TableView<Coche> tvCoches;

    @FXML
    private TableColumn<Coche, String> tcMatricula, tcMarca, tcModelo, tcTipo;

    CocheCRUD crud;
    List<Coche> coches;
    Coche cocheSeleccionado = null;

    public CocheController() {
        // Creo un constructor en el que instancio el crud para acceder a la funcionalidad del crud.
        crud = new CocheCRUD();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarTabla();
        cargarCB();
        tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    @FXML
    void insertarCoche(ActionEvent event) {
        List<String> campos = new ArrayList<>();
        String matricula = tfMatricula.getText();
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String tipo;
        if (cbTipo.getValue() != null) {
            tipo = cbTipo.getValue().toString();
        } else {
            tipo = null;
        }
        insertarCampo(campos, matricula);
        insertarCampo(campos, marca);
        insertarCampo(campos, modelo);
        insertarCampo(campos, tipo);
        if (crud.insertarCoche(campos)) {
            AlertUtils.mostrarConfirmacion("Coche creado correctamente.");
            cargarTabla();
            limpiarCampos(event);
        }
    }

    @FXML
    void modificarCoche(ActionEvent event) {
        if (cocheSeleccionado != null) {
            List<String> campos = new ArrayList<>();
            String matricula = tfMatricula.getText();
            String marca = tfMarca.getText();
            String modelo = tfModelo.getText();
            String tipo;
            if (cbTipo.getValue() != null) {
                tipo = cbTipo.getValue().toString();
            } else {
                tipo = null;
            }

            insertarCampo(campos, matricula);
            insertarCampo(campos, marca);
            insertarCampo(campos, modelo);
            insertarCampo(campos, tipo);

            if (crud.modificarCoche(campos, cocheSeleccionado)) {
                AlertUtils.mostrarConfirmacion("Coche modificado correctamente.");
                limpiarCampos(event);
                cargarTabla();
            }
        } else {
            AlertUtils.mostrarError("Seleccione primero el coche");
        }
    }

    @FXML
    void eliminarCoche(ActionEvent event) {
        if (cocheSeleccionado != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar el coche?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                crud.eliminarCoche(cocheSeleccionado);
                limpiarCampos(event);
                cargarTabla();
            }
        } else {
            AlertUtils.mostrarError("Seleccione primero el coche");
        }
    }

    @FXML
    void mostrarMultas(ActionEvent event) {
        if (cocheSeleccionado != null) {
            CambioEscenas.cambioEscena("multas.fxml", concesionario, cocheSeleccionado);
            //crud.desconectar();
        } else {
            AlertUtils.mostrarError("Seleccione primero el coche");
        }
    }

    @FXML
    void seleccionarCoche(MouseEvent event) {
        try {
            cocheSeleccionado = tvCoches.getSelectionModel().getSelectedItem();
            cargarData();
        } catch (NullPointerException e) {
            AlertUtils.mostrarError("No has seleccionado ningun dato.\n");
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        tfMatricula.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        cargarCB();
        cocheSeleccionado = null;
    }

    @FXML
    void salir(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            crud.desconectar();
            ((Stage) concesionario.getScene().getWindow()).close();
        }

    }

    public void cargarTabla() {
        tvCoches.getItems().clear();
        coches = crud.getCoches();

        tvCoches.setItems(FXCollections.observableList(coches));
    }

    public void cargarCB() {
        // Al igual que el cargarTabla, cargo los valores del enum Tipo.
        cbTipo.getItems().clear();
        cbTipo.getItems().addAll(Tipo.values());
    }

    public void cargarData() {
        // Este metodo carga los datos del coche seleccionado.
        tfMatricula.setText(cocheSeleccionado.getMatricula());
        tfMarca.setText(cocheSeleccionado.getMarca());
        tfModelo.setText(cocheSeleccionado.getModelo());
        Tipo tipoCoche = null;
        for (Tipo tipo : Tipo.values()) {
            if (tipo.toString().equals(cocheSeleccionado.getTipo())) {
                tipoCoche = tipo;
            }
        }
        cbTipo.setValue(tipoCoche);
    }


    public void insertarCampo(List<String> campos, String campo) {
        // Metodo para insertar en un array de Strings el campo que me interesa meter.
        campos.add(campo);
    }
}
