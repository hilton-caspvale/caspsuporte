var MV_A_MENU_ATENDIMENTO = getUriAtendimento() + 'menuAtendimento';
var MV_A_MODAl_SELECIONAR_USUARIO = getUriAtendimento() + 'modal-selecionar-usuario';

function getUriAtendimento() {
    return '/mv/atendimento/';
}

function moduloAtendimento() {
    const promise1 = carregaHtmlPromise(getUriAtendimento());
    const promise2 = carregaHtmlPromise(MV_A_MENU_ATENDIMENTO);
    const elementos = [
        [promise1, '#root'],
        [promise2, '#menuPrincipal']
    ];
    promiseHtml(elementos);
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