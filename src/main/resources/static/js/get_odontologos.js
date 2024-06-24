window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
         for(odontologo of data){ 
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("odontologoTable");
            var odontologoRow =table.insertRow();
            let tr_id = odontologo.id;
            odontologoRow.id = tr_id;

            //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una pelicula
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                      ' type="button" onclick="deleteOdontologoBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                                  ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                                  ' type="button" onclick="findOdontologoBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                                  odontologo.id +
                                                  '</button>';
            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_odontologos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })

    })

    function findOdontologoBy(id) {
              console.log("Entró xd")

          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#matricula').value = odontologo.matricula;
              document.querySelector('#nombre').value = odontologo.nombre;
              document.querySelector('#apellido').value = odontologo.apellido;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
    }

    window.addEventListener('load', function () {


        //Buscamos y obtenemos el formulario donde estan
        //los datos que el usuario pudo haber modificado de la pelicula
        const formulario = document.querySelector('#update_odontologo_form');

        formulario.addEventListener('submit', function (event) {
            let odontologoId = document.querySelector('#odontologo_id').value;
            //creamos un JSON que tendrá los datos de la película
            //a diferencia de una pelicula nueva en este caso enviamos el id
            //para poder identificarla y modificarla para no cargarla como nueva
            const formData = {
                id: document.querySelector("#odontologo_id").value,
                matricula: document.querySelector('#matricula').value,
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,

            };

            //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
            //la película que enviaremos en formato JSON
            const url = '/odontologos';
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

    function findOdontologoBy(id) {
              console.log("Entró xd")

          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#matricula').value = odontologo.matricula;
              document.querySelector('#nombre').value = odontologo.nombre;
              document.querySelector('#apellido').value = odontologo.apellido;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
    }

