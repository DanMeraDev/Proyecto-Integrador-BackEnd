package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.dao.OdontologoDaoH2;
import BackEndProyecto.ClinicaOdontologica.dao.iDao;
import BackEndProyecto.ClinicaOdontologica.model.Odontologo;

import java.util.List;

public class OdontologoService{
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao= new OdontologoDaoH2();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public Odontologo buscarPorId(Integer id){
        return odontologoiDao.buscarPorID(id);
    }
    public List<Odontologo> buscarOdontologos() {
        return odontologoiDao.buscarTodos();
    }
    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoiDao.actualizar(odontologo);
    }
    public void eliminarOdontologo(int id) {
        odontologoiDao.eliminar(id);
    }
}
