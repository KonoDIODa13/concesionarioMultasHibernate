package application.DAO;

import application.Conexion.Conexion;
import application.Model.Coche;
import application.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MultaDAO implements MultaDAOImpl {
    private final SessionFactory factory;
    private final Session session;

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
        try {
            session.beginTransaction();
            session.save(multa);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    @Override
    public void modificarMulta(Multa multa) {
        try {
            session.beginTransaction();
            session.update(multa);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    @Override
    public void eliminarMulta(Multa multa) {
        try {
            session.beginTransaction();
            session.delete(multa);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    @Override
    public List<Multa> getMultas(Coche coche) {
        List<Multa> multas = new ArrayList<>();
        try {
            session.beginTransaction();
            multas = session.createQuery("from Multa where matricula=:matricula", Multa.class)
                    .setParameter("matricula", coche.getMatricula())
                    .getResultList();

            session.getTransaction().commit();

            // me devuelve una lista inmodificable, por eso peta después
            //multas = multasTotales.stream().filter(multa -> multa.getMatricula().equalsIgnoreCase(coche.getMatricula())).toList();
            
        } catch (
                Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return multas;
    }
}
