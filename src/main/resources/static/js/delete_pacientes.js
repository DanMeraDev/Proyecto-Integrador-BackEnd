function deletePacienteBy(id)
{
    //con fetch invocamos a la API de pacientes con el método DELETE
    //pasandole el id en la URL
    const url = '/paciente/'+ id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
        .then(response => response.json())

    //borrar la fila del paciente eliminada
    let row_id = id;
    document.querySelector(row_id).remove();


}