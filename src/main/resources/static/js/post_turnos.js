window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva pelicula
    const formulario = document.querySelector('#add_new_turno');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
       //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            paciente: {
                id: document.querySelector('#paciente_id').value
            },
            odontologo: {
                id: document.querySelector('#odontologo_id').value
            },
            fecha: document.querySelector('#fecha_turno').value
        };
        //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que la pelicula
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' +
                    '<span class="glyphicon glyphicon-ok"></span> <strong>Turno creado</strong>' +
                    '<hr class="message-inner-separator">' +
                    '<p>Se creo turno correctamente</p>' +
                    '</div>';

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();
                 setTimeout(() => {
                    document.querySelector('#response').style.display = "none";
                }, 2000); 

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que la pelicula
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div>' +
                        '<div class="alert alert-warning alert-dismissible">' +
                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            '<span class="glyphicon glyphicon-record"></span> <strong>Turno no creado</strong>' +
                            '<hr class="message-inner-separator">' +
                            '<p>Ocurrió un error al crear turno</p>' +
                        '</div>' +
                    '</div>';

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otra pelicula
                    resetUploadForm();
                    setTimeout(() => {
                        document.querySelector('#response').style.display = "none";
                    }, 2000); 
                })
    });


    function resetUploadForm(){
        document.querySelector('#paciente_id').value = "";
        document.querySelector('#odontologo_id').value = "";
         document.querySelector('#fecha_turno').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});