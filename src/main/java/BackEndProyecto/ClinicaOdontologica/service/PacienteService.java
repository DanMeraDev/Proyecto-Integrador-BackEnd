package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import BackEndProyecto.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    //relacion de asociacion directa con el DAO
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }
//    public Paciente buscarPorCorreo(String correo){
//        return pacienteRepository.findBy(correo);
//    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
}