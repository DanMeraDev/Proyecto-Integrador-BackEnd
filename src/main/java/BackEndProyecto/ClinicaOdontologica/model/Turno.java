package BackEndProyecto.ClinicaOdontologica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turno {
    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;
}
