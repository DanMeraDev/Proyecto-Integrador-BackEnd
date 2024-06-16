package BackEndProyecto.ClinicaOdontologica.service;


import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OdontologoService{
    @Autowired
    public OdontologoRepository odontologoRepository;


    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarPorId(Long id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarOdontologos() {
        return odontologoRepository.findAll();
    }
    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }
}
