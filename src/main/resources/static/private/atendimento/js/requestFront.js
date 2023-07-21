var MV_ATENDIMENTO_CLIENTE = '/mv/atendimento-cliente/';//getUriViewAtendimentoCliente()
var MV_ATENDIMENTO = '/mv/atendimento/';//getUriViewAtendimento();contextPath;
var MV_A_TOAST_GERAL = '/mv/atendimento/toast';//getUriToastGeral()
var MV_A_CHAMADO = MV_ATENDIMENTO + 'chamado';//chamadoPath
var MV_A_EDICAO_CHAMADO = MV_ATENDIMENTO + 'edicaoChamado';//chamadoPath
var MV_A_SELECIONAR_USUARIO = MV_ATENDIMENTO + 'selecionar-usuario';
var MV_A_MODAL_USUARIOS = MV_ATENDIMENTO + 'conteudoModalUsuarios?abertura=true';
var MV_A_MODAL_CLIENTES = MV_ATENDIMENTO + 'conteudoModalClientes?abertura=true';

function chamadoUsuarioSelecionado() {
    let user = document.getElementById('choices-usuarios');
    carregaHtml(MV_A_CHAMADO + '?user=' + user.value, 'root');
    limparAlerta();
}

function chamadoUsuarioLogado() {
    document.getElementById('alertaGeral').innerHTML = '';
    return carregaHtml(MV_A_CHAMADO + '?user=' + login(), 'root');
}

function chamadoUsuarioEditado(user) {
    limparAlerta();
    return carregaHtml(MV_A_CHAMADO + '?user=' + user, 'root');
}

function chamadoEdicao(event) {
    event.preventDefault();
    limparAlerta();
    let currentTarget = $(event.currentTarget);
    let iChamado = currentTarget.attr('data-id');
    carregaHtml(MV_A_EDICAO_CHAMADO + '?iChamado=' + iChamado, 'root');
}

function visualizarChamado(ichamado) {
    carregaHtml(MV_A_CHAMADO + '/' + ichamado, 'root');
}

function selectUsuarios() {
    let parametros = selectUsuariosParametros();
    carregaHtml(MV_A_SELECIONAR_USUARIO + parametros, 'idSelectUsuarios');
}

function selectUsuariosParametros() {
    let entidade = document.getElementById("ientidade");
    let area = document.getElementById("iarea");
    let sistema = document.getElementById("isistema");
    let exibir = document.getElementById("exibir");
    let par = '?exibir=' + exibir.options[exibir.selectedIndex].value;
    par += '&entidades=' + entidade.options[entidade.selectedIndex].value;
    par += '&areas=' + area.options[area.selectedIndex].value;
    par += '&sistemas=' + sistema.options[sistema.selectedIndex].value;
    par += '&somenteClientes=' + document.getElementById('somenteClientes').checked;
    return par;
}

function modalEditarUsuario(id) {
    let value = document.getElementById(id).value;
    let uriModalCadastro = MV_A_MODAL_USUARIOS + '&id=' + value;
    redirectHtml(uriModalCadastro, 'GET', 'modalBody', null);
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Editar Usuario');
}

function modalCriarUsuarioAbertura() {
    carregaHtml(MV_A_MODAL_CLIENTES, 'modalBody');
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Adicionar Cliente');
}

function carregarToast99(mensagem) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', MV_A_TOAST_GERAL);
    xhr.send();
    xhr.onload = function () {
        let toastId = document.getElementById('toastId');
        toastId.innerHTML = xhr.responseText;
        document.getElementById('msgToast').innerHTML = mensagem;
        new bootstrap.Toast(document.getElementById('liveToast')).show();
    };
}
