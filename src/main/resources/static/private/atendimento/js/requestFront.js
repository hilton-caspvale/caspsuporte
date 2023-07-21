var MV_ATENDIMENTO = '/mv/atendimento/';
var MV_ATENDIMENTO_CLIENTE = '/mv/atendimento-cliente/';
var MV_A_CHAMADO = MV_ATENDIMENTO + 'chamado';
var MV_A_EDICAO_CHAMADO = MV_ATENDIMENTO + 'edicaoChamado';

function requestMV(url) {
    requestDef(url, 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function requestMVModal(url, id, nomeCadastro) {
    let parametro = '';
    let tituloModal = "Cadastrar " + nomeCadastro;
    if (id) {
        parametro = '?id=' + id;
        tituloModal = "Editar " + nomeCadastro;
    }
    requestDef(url + parametro, 'GET', null)
            .then(response => {
                $("#modalBody").html(response);
                mostraModal('#modal');
                $('#staticBackdropLabel').html(tituloModal);
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function chamadoUsuarioLogado() {
    limparAlertaGeral();
    requestDef(MV_A_CHAMADO + '?user=' + login(), 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
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