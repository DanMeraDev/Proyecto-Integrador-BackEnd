window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
         for(turno of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = turno.id;
            turnoRow.id = tr_id;

            //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una pelicula
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteTurnoBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findTurnoBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
//                    '<h3>Datos paciente: </h3>' +
                    '<td class=\"td_paciente_nombre\">' + turno.paciente.nombre+ '</td>' +
                    '<td class=\"td_paciente_apellido\">' + turno.paciente.apellido + '</td>' +
                    '<td class=\"td_paciente_cedula\">' + turno.paciente.cedula + '</td>' +
//                    '<h3>Datos paciente: </h3>' +
                    '<td class=\"td_odontologo_matricula\">' + turno.odontologo.matricula + '</td>' +
                    '<td class=\"td_odontologo_nombre\">' + turno.odontologo.nombre + '</td>' +
                    '<td class=\"td_odontologo_apellido\">' + turno.odontologo.apellido + '</td>' +
                    '<td class=\"td_fecha_turno\">' + turno.fecha + '</td>' +

                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })

