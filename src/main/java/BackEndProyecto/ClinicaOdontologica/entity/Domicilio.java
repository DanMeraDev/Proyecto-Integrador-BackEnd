package BackEndProyecto.ClinicaOdontologica.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String calle;
    @Column
    private Integer numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL)
    private List<Paciente> pacientes = new ArrayList<>();



    public Domicilio(Long id, String calle, Integer numero, String localidad, String provincia, List<Paciente> pacientes) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pacientes = pacientes;
    }

    public Domicilio(String calle, Integer numero, String localidad, String provincia, List<Paciente> pacientes) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pacientes = pacientes;
    }

    public Domicilio() {
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
