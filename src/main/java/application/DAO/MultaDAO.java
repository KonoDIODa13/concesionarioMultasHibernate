package application.DAO;

import application.Conexion.Conexion;
import application.Model.Coche;
import application.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MultaDAO implements MultaDAOImpl {
    private final SessionFactory factory = Conexion.getFactory();
    private final Session session = Conexion.getSession();

    public MultaDAO() {
        Conexion.conexion();
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
    public List<Multa> getMultas(Coche coche) {
        try {
            session.beginTransaction();
            List<Multa> multasTotales = session.createQuery("from Multa").getResultList();
            session.getTransaction().commit();

            List<Multa> multas = new ArrayList<>();
            multasTotales.forEach(multa -> {
                if (multa.getMatricula().equalsIgnoreCase(coche.getMatricula()))
                    multas.add(multa);
            });
            // me devuelve una lista inmodificable, por eso peta después
            //multas = multasTotales.stream().filter(multa -> multa.getMatricula().equalsIgnoreCase(coche.getMatricula())).toList();
            return multas;

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return null;
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
}
