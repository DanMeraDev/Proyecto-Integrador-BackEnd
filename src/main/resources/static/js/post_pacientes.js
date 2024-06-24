window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva pelicula
    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        //creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
             domicilio: {
                 calle: document.querySelector('#calle').value,
                 numero: document.querySelector('#numero').value,
                 localidad: document.querySelector('#localidad').value,
                 provincia: document.querySelector('#provincia').value
             },
            email: document.querySelector('#email').value,
        };
        //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/paciente';
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
                '<span class="glyphicon glyphicon-ok"></span> <strong>Paciente agregado</strong>' +
                '<hr class="message-inner-separator">' +
                '<p>Se agregó correctamente al paciente</p>' +
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
                        '<span class="glyphicon glyphicon-record"></span> <strong>Odontólogo no agregado</strong>' +
                        '<hr class="message-inner-separator">' +
                        '<p>Ocurrió un error al agregar al odontólogo</p>' +
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
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#cedula').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
        document.querySelector('#email').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});