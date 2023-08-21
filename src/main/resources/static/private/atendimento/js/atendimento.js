var MV_A_MENU_ATENDIMENTO = getUriAtendimento() + 'menuAtendimento';
var MV_A_MODAl_SELECIONAR_USUARIO = getUriAtendimento() + 'modal-selecionar-usuario';

function getUriAtendimento() {
    return '/mv/atendimento/';
}

function moduloAtendimento() {
    requestDef(getUriAtendimento(), 'GET', null)
            .then(response => {
                $('#root').html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
    requestDef(MV_A_MENU_ATENDIMENTO, 'GET', null)
            .then(response => {
                $('#menuPrincipal').html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function modalSelecionarUsuario() {
    requestMVModal(MV_A_MODAl_SELECIONAR_USUARIO, null, 'Buscar Usuário');
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