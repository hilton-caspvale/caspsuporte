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

