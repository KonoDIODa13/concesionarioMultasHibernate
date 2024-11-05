package application.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/*
id_multa integer unsigned NOT NULL AUTO_INCREMENT,
  precio DOUBLE NOT NULL,
  fecha DATE DEFAULT NULL,
  matricula varchar(7) NOT NULL,
 */

@Entity
@Table(name = "Multas")
public class Multa implements Serializable {
    @Id
    @Column(name = "id_multa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;

   /* @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    */
   @Column(name = "matricula")
    private String matricula;

    public Multa() {
    }

    public Multa(String matricula, double precio, LocalDate fecha) {
        this.precio = precio;
        this.fecha = fecha;
        this.matricula = matricula;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Multa{" +
                ", precio=" + getPrecio() +
                ", fecha=" + getFecha() +
                ", matricula=" + getMatricula() +
                '}';
    }
}
