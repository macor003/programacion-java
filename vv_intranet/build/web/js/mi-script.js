$(document).on('ready', iniciarTemporizador);

function iniciarTemporizador() {

	var fechaFinal = new Date(2015, 11, 06, 01, 00, 00);
	var fechaHoy = new Date();

	var diferenciaMilisegundos = fechaFinal.getTime() - fechaHoy.getTime();
	var diferenciaSegundos = (diferenciaMilisegundos/1000);

	var clock = $("#mi-reloj").FlipClock({
    	clockFace: 'DailyCounter',
        callbacks: {
            interval: redireccionar
        },
	});

	clock.setTime(diferenciaSegundos);
	clock.setCountdown(true);

    function redireccionar()
    {
        if (clock.getTime() <= 0) {
            window.location.replace('https://twitter.com/danielromeroauk');
        }
    }

}