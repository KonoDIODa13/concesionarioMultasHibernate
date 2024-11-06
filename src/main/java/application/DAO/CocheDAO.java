package application.DAO;

import application.Conexion.Conexion;
import application.Model.Coche;
import application.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CocheDAO implements CocheDAOImpl {
    private SessionFactory factory;
    private Session session;

    // Creo la conexión y recogo los datos de dicha conexión (el factory y la session).
    public CocheDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    /*
    Llamo de nuevo a la conexión que tiene un metodo para desconectarse de la BD.
    */
    @Override
    public void desconectarBD() {
        factory.close();
        session.close();
    }

    /*
    Inserto un Coche con la sintaxis de Hibernate.
    */
    @Override
    public void insertarCoche(Coche coche) {
        try {
            session.beginTransaction();
            session.save(coche);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    /*
    Modifico un Coche con la sintaxis de Hibernate.
    */
    @Override
    public void modificarCoche(Coche coche) {
        try {
            session.beginTransaction();
            session.update(coche);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    /*
    Elimino un Coche con la sintaxis de Hibernate.
    */
    @Override
    public void eliminarCoche(Coche coche) {
        try {
            session.beginTransaction();
            session.delete(coche);
            session.getTransaction().commit();
            eliminaMultas(coche.getMultas());

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    /*
    Recorro la tabla con createQuery(realiza un select * según la tabla que le pongas) y me lo devuelve como lista.
    */
    @Override
    public List<Coche> getCoches() {
        List<Coche> coches = new ArrayList<>();
        try {
            session.beginTransaction();
            coches = session.createQuery("from Coche").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return coches;
    }

    public void eliminaMultas(List<Multa> multas) {
        try {
            session.beginTransaction();
            MultaDAO multaDAO = new MultaDAO();
            multas.forEach(multaDAO::eliminarMulta);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

}
