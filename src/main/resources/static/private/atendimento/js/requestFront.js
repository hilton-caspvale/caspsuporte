function requestMV(url) {
    requestDef(url, 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function requestMVModal(url, id, titulo) {
    let parametro = '';
    if (id) {
        parametro = '?id=' + id;
    }
    requestDef(url + parametro, 'GET', null)
            .then(response => {
                mostraModalResponse(titulo, response);
            })
            .catch(error => {
                alert(error);
                requestError(error, 'alertaModal');
            });
}

function chamadoUsuarioLogado() {
    limparAlertaGeral();
    requestMV(MV_A_CHAMADO + '?user=' + login());
}

function chamadoEdicao(event) {
    event.preventDefault();
    limparAlertaGeral();
    let currentTarget = $(event.currentTarget);
    let iChamado = currentTarget.attr('data-id');
    requestDef(MV_A_EDICAO_CHAMADO + '?iChamado=' + iChamado, 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function visualizarChamado(ichamado) {
    requestDef(MV_A_CHAMADO + '/' + ichamado, 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

/*function problemasDosSistemas() {
 ////<script>
 document.getElementById("sistemas").addEventListener("change", function () {
 let url = MV_A_PROBLEMA + "/problemasDosSistemas";
 const selectedOptions = document.getElementById("sistemas").selectedOptions;
 const dataObjects = Array.from(selectedOptions, option => {
 return {
 isistema: option.value,
 descricaoSistema: option.textContent.trim()
 };
 });
 requestDef(url, 'POST', JSON.stringify(dataObjects))
 .then(response => {
 $("#problemasSistemas").html(response);
 })
 .catch(error => {
 requestError(error, 'alertaGeral');
 });
 });
 ////</script>
 }*/