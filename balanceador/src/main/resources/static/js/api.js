var api = (function () {
	var filas = 0;

	function borrarFilas() {
		while (filas > 0){
			$("#fila" + filas).remove();
			filas += -1;
		}
	}

	function agregarLog(log){
		filas+= 1;
		$("#Tabla").append("<tr id=\"fila" + filas + "\">" +
							"<td>" + log.mensaje + "</td>" +
							"<td>" + log.fecha + "</td>"+
							"</tr>");
		
	}

	function agregarLogs(lista){
		for (var i = lista.length - 1 ; i >= 0; i--){
			agregarLog(lista[i]);
		}
	}

	return {
		newMensaje: function (callback) {
			var postRequest = $.ajax({
				url: "addMensaje",
				type: 'POST',
				data: JSON.stringify($("#mensaje").val()),
				dataType: 'json',
			});
			postRequest.then(
				function (resp) {
					borrarFilas();
					agregarLogs(resp);
				},
				function (resp) {
					alert("error");
				}
			);
		}
	};
})();
