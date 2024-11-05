package application.DAO;

import application.Conexion.Conexion;
import application.Model.Coche;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        session.beginTransaction();
        session.save(coche);
        session.getTransaction().commit();
    }

    /*
    Modifico un Coche con la sintaxis de Hibernate.
    */
    @Override
    public void modificarCoche(Coche coche) {
        session.beginTransaction();
        session.update(coche);
        session.getTransaction().commit();
    }

    /*
    Elimino un Coche con la sintaxis de Hibernate.
    */
    @Override
    public void eliminarCoche(Coche coche) {
        session.beginTransaction();
        session.delete(coche);
        session.getTransaction().commit();
    }

    /*
    Busco un Coche según la matricula (realizara un: select * from Coche where matricula="?").
    */
    public Coche buscarCoche(int id) {
        Coche coche;
        coche = (Coche) session.get(Coche.class, id);
        return coche;
    }

    /*
    Recorro la tabla con createQuery(realiza un select * según la tabla que le pongas) y me lo devuelve como lista.
    */
    @Override
    public List<Coche> getCoches() {
        return session.createQuery("from Coche").getResultList();
    }

}
