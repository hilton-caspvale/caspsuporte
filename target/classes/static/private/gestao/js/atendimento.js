function getUriViewAtendimento() {
    return '/mv/atendimento/';
}

function moduloAtendimento() {
    let elemento = 'root';
    carregaHtml(getUriViewAtendimento(), elemento);
    menuCadastrosAtendimento();
}
function menuCadastrosAtendimento() {
    let uri = getUriViewAtendimento() + 'menuAtendimento';
    let elemento = 'menuPrincipal';
    carregaHtml(uri, elemento);
}

function modalSelecionarUsuario() {
    carregaHtml(getUriViewAtendimento()+'modal-selecionar-usuario', 'modalBody');
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Selecionar usuário');
}