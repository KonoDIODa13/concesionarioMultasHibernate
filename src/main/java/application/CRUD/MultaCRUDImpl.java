package application.CRUD;

import application.Model.Coche;
import application.Model.Multa;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface MultaCRUDImpl {
    void desconectar();

    List<Multa> getMultas(Coche coche);

    public boolean insertarMulta(Coche coche, List<String> campos);

    public boolean modificarMulta(List<String> campos, Multa multa);

    public void eliminarMulta(Multa multa);
}
