package application.DAO;

import application.Model.Coche;
import application.Model.Multa;

import java.util.List;

public interface MultaDAOImpl {

    void desconectarBD();

    void insertarMulta(Multa multa);

    List<Multa> getMultas(Coche coche);

    void modificarMulta(Multa multa);

    void eliminarMulta(Multa multa);
}
