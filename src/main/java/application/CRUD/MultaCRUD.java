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
        Coche coche = null;
        System.out.println(campos.getFirst());
        //Coche coche = campos.getFirst();
        double precio = Double.parseDouble(campos.get(1));
        LocalDate localDate = LocalDate.parse(campos.getLast());

        Multa multa = new Multa(coche, precio, localDate);

        if (multas.contains(multa)) {
            AlertUtils.mostrarError("La multa ya esta en la bd.");
            return false;
        }

        dao.insertarMulta(multa);
        return true;
    }

    @Override
    public boolean modificarMulta(List<String> campos, Multa multa) {
        double precio = Double.parseDouble(campos.getFirst());
        LocalDate localDate = LocalDate.parse(campos.getLast());
        multa.setPrecio(precio);
        multa.setFecha(localDate);
        dao.modificarMulta(multa);
        return true;
    }

    @Override
    public void eliminarMulta(Multa multa) {
        dao.eliminarMulta(multa);

    }
}
