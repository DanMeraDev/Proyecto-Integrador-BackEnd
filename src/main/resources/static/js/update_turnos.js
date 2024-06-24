
window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let turnoId = document.querySelector('#turno_id').value;
        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva

        let pacienteAcutalizado =  await findPBy(document.querySelector("#paciente_id").value);
        let odontologoAcutalizado =  await findOBy(document.querySelector("#odontologo_id").value);
        const formData = {
            id: turnoId,
            paciente: pacienteAcutalizado,
            odontologo: odontologoAcutalizado,
            fecha: document.querySelector('#fecha_turno').value,

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/turnos';
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
    function findTurnoBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#paciente_id').value = turno.paciente.id;
              document.querySelector('#odontologo_id').value = turno.odontologo.id;
              document.querySelector('#fecha_turno').value = turno.fecha;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }

    function findPBy(id) {
      let pacienteData = {};
      const url = '/paciente'+"/"+id;
      const settings = {
          method: 'GET'
      }
      fetch(url,settings)
          .then(response => response.json())
          .then(data => {
            console.log(data)
            let paciente = data;
            pacienteData = {
                id: paciente.id,
                nombre: paciente.nombre,
                apellido: paciente.apellido,
                cedula: paciente.cedula,
                fechaIngreso: paciente.fechaIngreso,
                domicilio: {
                    id: paciente.domicilio.id,
                    calle: paciente.domicilio.calle,
                    numero: paciente.domicilio.numero,
                    localidad: paciente.domicilio.localidad,
                    provincia: paciente.domicilio.provincia,
                },
                email: paciente.email
              }

      }).catch(error => {
        alert("Paciente error: "+error)
      })
      return pacienteData;
    }

    function findOBy(id) {
          let odontologoData = {};
          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
              .then(response => response.json())
              .then(data => {
                console.log(data)
                let odontologo = data;
                odontologoData = {
                    id: odontologo.id,
                    matricula: odontologo.matricula,
                    nombre: odontologo.nombre,
                    apellido: odontologo.apellido
                 }

          }).catch(error => {
            alert("Odontologo error: " + error)
          })
          return odontologoData;
        }