package application.DAO;

import application.Conexion.Conexion;
import application.Model.Coche;
import application.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MultaDAO implements MultaDAOImpl {
    private SessionFactory factory;
    private Session session;

    public MultaDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    @Override
    public void desconectarBD() {
        factory.close();
        session.close();

    }

    @Override
    public void insertarMulta(Multa multa) {
        session.beginTransaction();
        session.save(multa);
        session.close();
    }

    @Override
    public List<Multa> getMultas(Coche coche) {
        System.out.println(coche);
        session.beginTransaction();
        List<Multa> multasTotales = session.createQuery("from Multa").getResultList();
        session.close();
        return multasTotales.stream().filter(multa -> multa.getMatricula().equalsIgnoreCase(coche.getMatricula())).toList();
    }

    @Override
    public void modificarMulta(Multa multa) {
        session.beginTransaction();
        session.update(multa);
        session.close();
    }

    @Override
    public void eliminarMulta(Multa multa) {
        session.beginTransaction();
        session.delete(multa);
        session.close();
    }
}
