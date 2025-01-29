package backend.simulacionsuquilanda.Objetos;


import jakarta.persistence.*;

import java.io.Serializable;



@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public  class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    public Usuario() {}

    public Usuario(Long id, String nombre, String cedula) {
        this.id = id;
        this.nombre = nombre;

        this.cedula = cedula;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column (name = "cedula", nullable = false, length = 100)
    private String cedula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
