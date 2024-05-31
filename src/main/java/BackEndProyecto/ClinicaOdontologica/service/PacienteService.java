package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.model.Paciente;
import BackEndProyecto.ClinicaOdontologica.dao.PacienteDAOH2;
import BackEndProyecto.ClinicaOdontologica.dao.iDao;
import BackEndProyecto.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
    //relacion de asociacion directa con el DAO
    private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPaciente(Integer id){
        return pacienteiDao.buscarPorID(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteiDao.buscarTodos();
    }
    public Paciente buscarPorCorreo(String correo){
        return pacienteiDao.buscarPorString(correo);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public void eliminarPaciente(Integer id){
        pacienteiDao.eliminar(id);
    }
}