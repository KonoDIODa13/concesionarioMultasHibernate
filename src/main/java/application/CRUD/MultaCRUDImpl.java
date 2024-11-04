package application.CRUD;

import application.Model.Coche;
import application.Model.Multa;

import java.sql.SQLException;
import java.util.List;

public interface MultaCRUDImpl {
    void desconectar();

    List<Multa> getMultas(Coche coche);

    public boolean insertarMulta(List<String> campos);

    public boolean modificarMulta(List<String> campos, Multa MultaPreCambios);

    public void eliminarMulta(Multa multa);
}
