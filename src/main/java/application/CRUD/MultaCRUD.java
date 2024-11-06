package application.CRUD;

import application.DAO.MultaDAO;
import application.Model.Coche;
import application.Model.Multa;
import application.Utils.AlertUtils;
import application.Utils.Comprobaciones;

import java.time.LocalDate;
import java.util.List;

public class MultaCRUD implements MultaCRUDImpl {
    MultaDAO dao;
    List<Multa> multas;

    public MultaCRUD() {
        dao = new MultaDAO();
    }

    @Override
    public void desconectar() {
        dao.desconectarBD();
    }

    @Override
    public List<Multa> getMultas(Coche coche) {
        multas = dao.getMultas(coche);
        return multas;
    }

    @Override
    public boolean insertarMulta(Coche coche, List<String> campos) {
        double precio;
        LocalDate fecha;
        if (Comprobaciones.compruebaDouble(campos.getFirst(), "precio")) {
            precio = Double.parseDouble(campos.getFirst());
        } else {
            return false;
        }
        if (Comprobaciones.compruebaFecha(campos.getLast(), "localDate")) {
            fecha = LocalDate.parse(campos.getLast());
        } else {
            return false;
        }
        Multa multa = new Multa(coche, precio, fecha);

        if (multas.contains(multa)) {
            AlertUtils.mostrarError("La multa ya esta en la bd.");
            return false;
        }
        dao.insertarMulta(multa);
        return true;
    }

    @Override
    public boolean modificarMulta(List<String> campos, Multa multa) {
        double precio;
        LocalDate fecha;
        if (Comprobaciones.compruebaDouble(campos.getFirst(), "precio")) {
            precio = Double.parseDouble(campos.getFirst());
        } else {
            return false;
        }
        if (Comprobaciones.compruebaFecha(campos.getLast(), "localDate")) {
            fecha = LocalDate.parse(campos.getLast());
        } else {
            return false;
        }
        multa.setPrecio(precio);
        multa.setFecha(fecha);
        dao.modificarMulta(multa);
        return true;
    }

    @Override
    public void eliminarMulta(Multa multa) {
        dao.eliminarMulta(multa);

    }
}
