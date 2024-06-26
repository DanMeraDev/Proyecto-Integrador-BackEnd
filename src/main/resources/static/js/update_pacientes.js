let domicilioData = {};


window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;
        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const nuevaCalle = document.querySelector('#domicilio').value;
        domicilio = {
           id: domicilioData.id,
           calle: nuevaCalle,
           numero: domicilioData.numero,
           localidad: domicilioData.localidad,
           provincia: domicilioData.provincia,
        }
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: domicilio,
            email: document.querySelector('#email').value,

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/paciente';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
            .then(response => response.json())

    })
})

//Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
//se encarga de llenar el formulario con los datos de la pelicula
//que se desea modificar
function findPacienteBy(id) {
              console.log("Entró xd")

    const url = '/paciente'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            domicilioData = {
               id: paciente.domicilio.id,
               calle: paciente.domicilio.calle,
               numero: paciente.domicilio.numero,
               localidad: paciente.domicilio.localidad,
               provincia: paciente.domicilio.provincia,
            }
            document.querySelector('#paciente_id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#cedula').value = paciente.cedula;
            document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
            document.querySelector('#domicilio').value = paciente.domicilio.calle;
            document.querySelector('#email').value = paciente.email;
            //el formulario por default esta oculto y al editar se habilita
            document.querySelector('#div_paciente_updating').style.display = "block";


        }).catch(error => {
        alert("Error: " + error);
    })
}