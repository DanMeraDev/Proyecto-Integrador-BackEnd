package BackEndProyecto.ClinicaOdontologica.dto;

import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.entity.Paciente;


import java.io.Serializable;
import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
