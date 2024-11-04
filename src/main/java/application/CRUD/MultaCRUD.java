package application.CRUD;

import application.DAO.MultaDAO;
import application.Model.Coche;
import application.Model.Multa;
import application.Utils.AlertUtils;

import java.util.List;

public class MultaCRUD implements MultaCRUDImpl {
    MultaDAO dao;

    public MultaCRUD() {
        dao = new MultaDAO();
    }

    @Override
    public void desconectar() {
        dao.desconectarBD();
    }

    @Override
    public List<Multa> getMultas(Coche coche) {
        return dao.getMultas(coche);
    }

    @Override
    public boolean insertarMulta(List<String> campos) {
        // if (comprobaciones(campos)) return false;
        //Multa multa = new Multa(campos.get(0), campos.get(1), campos.get(2), )
        return false;
    }

    @Override
    public boolean modificarMulta(List<String> campos, Multa MultaPreCambios) {
        return false;
    }

    @Override
    public void eliminarMulta(Multa multa) {

    }

    public boolean comprobaciones(List<String> campos) {
        // Función para comprobar todos si los campos pasan las comprobaciones.
        return compruebaCampo(campos.get(0), "Matricula") ||
                compruebaCampo(campos.get(1), "LocalDate") ||
                compruebaCampo(campos.get(2), "Coche");
    }

    boolean compruebaCampo(String contenido, String campo) {
        // En esta función comprobamos si el campo está vacio. Si lo está, mostramos un error.
        boolean bool = false;
        if (contenido.isEmpty()) {
            AlertUtils.mostrarError("El campo " + campo + " está vacio");
            bool = true;
        }
        return bool;
    }
}
