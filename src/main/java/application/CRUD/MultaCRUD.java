package application.CRUD;

import application.DAO.MultaDAO;
import application.Model.Coche;
import application.Model.Multa;
import application.Utils.AlertUtils;

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
    public boolean insertarMulta(List<String> campos) {
        String matricula = campos.getFirst();
        double precio = Double.parseDouble(campos.get(1));
        LocalDate localDate = LocalDate.parse(campos.getLast());

        Multa multa = new Multa(matricula, precio, localDate);

        if (multas.contains(multa)) {
            AlertUtils.mostrarError("La multa ya esta en la bd.");
            return false;
        }

        dao.insertarMulta(multa);
        return true;
    }

    @Override
    public boolean modificarMulta(List<String> campos, Multa MultaPreCambios) {
        return false;
    }

    @Override
    public void eliminarMulta(Multa multa) {

    }
}
