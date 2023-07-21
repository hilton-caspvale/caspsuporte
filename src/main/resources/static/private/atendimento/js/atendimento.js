var MV_A_MENU_ATENDIMENTO = getUriAtendimento() + 'menuAtendimento';
var MV_A_MODAl_SELECIONAR_USUARIO = getUriAtendimento() + 'modal-selecionar-usuario';

function getUriAtendimento() {
    return '/mv/atendimento/';
}

function moduloAtendimento() {
    const elementos = [
        [requestDef(getUriAtendimento(), 'GET', null), '#root'],
        [requestDef(MV_A_MENU_ATENDIMENTO, 'GET', null), '#menuPrincipal']
    ];
    promiseHtml(elementos).catch(error => {
        requestError(error, 'alertaGeral');
    });
}

function modalSelecionarUsuario() {
    carregaHtml(MV_A_MODAl_SELECIONAR_USUARIO, 'modalBody');
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Selecionar usuário');
}


/*Verificar*/
function moduloAtendimentoCliente() {
    let uri = '/mv/atendimento-cliente';
    let elemento = 'root';
    carregaHtml(uri, elemento);
    menuCadastrosAtendimento();
}

function menuCadastrosAtendimentoCliente() {
    let uri = '/mv/atendimento-cliente/menuAtendimentoCliente';
    let elemento = 'menuPrincipal';
    carregaHtml(uri, elemento);
}