package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.dto.TurnoDTO;
import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import BackEndProyecto.ClinicaOdontologica.entity.Turno;
import BackEndProyecto.ClinicaOdontologica.repository.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService {
    @Autowired
    public TurnosRepository turnosRepository;
    @Autowired
    public PacienteService pacienteService;
    @Autowired
    public OdontologoService odontologoService;

    public TurnoDTO guardarTurno(Turno turno) {
        return turnoATurnoDTO(turnosRepository.save(turno));
    }
    public Optional<TurnoDTO> buscarPorID(Long id) {
        return Optional.ofNullable(turnoATurnoDTO(turnosRepository.findById(id).get()));
    }
    public List<TurnoDTO> listarTurnos() {

        return turnosRepository.findAll().stream().map(this::turnoATurnoDTO).collect(Collectors.toList());
    }
    public void actualizarTurno(TurnoDTO turno) {
        turnosRepository.save(turnoDTOATurno(turno));
    }
    public void eliminarTurno(Long id) {
        turnosRepository.deleteById(id);
    }

    public TurnoDTO turnoATurnoDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        return turnoDTO;
    }

    public Turno turnoDTOATurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setPaciente(pacienteService.buscarPaciente(turnoDTO.getPacienteId()).get());
        turno.setOdontologo(odontologoService.buscarPorId(turnoDTO.getOdontologoId()).get());

        return turno;
    }
}
