package application.Model;

import javax.persistence.*;
import java.time.LocalDate;

/*
id_multa integer unsigned NOT NULL AUTO_INCREMENT,
  precio DOUBLE NOT NULL,
  fecha DATE DEFAULT NULL,
  matricula varchar(7) NOT NULL,
 */

@Entity
@Table(name = "Multas")
public class Multa {
    @Id
    @Column(name = "id_multa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    private Coche coche;

    public Multa() {
    }

    public Multa(double precio, LocalDate fecha, Coche coche) {
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    @Override
    public String toString() {
        return "Multa{" +
                ", precio=" + getPrecio() +
                ", fecha=" + getFecha() +
                ", matricula=" + getCoche() +
                '}';
    }
}
