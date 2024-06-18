package BackEndProyecto.ClinicaOdontologica.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologs_id")
    private Odontologo odontologo;
    private LocalDate fecha;


    public Turno() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
