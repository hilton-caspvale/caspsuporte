var contextPath = '/mv/atendimento/';
var chamadoPath = contextPath + 'chamado';

function chamadoUsuarioSelecionado() {
    let user = document.getElementById('choices-usuarios');
    carregaHtml(chamadoPath + '?user=' + user.value, 'root');
}

function chamadoUsuarioLogado() {
    carregaHtml(chamadoPath + '?user=' + login(), 'root');
}

function chamadoUsuarioEditado(user) {
    carregaHtml(chamadoPath + '?user=' + user, 'root');
}

function visualizarChamado(ichamado){
    carregaHtml(chamadoPath +'/'+ichamado,'root');
}

function selectUsuarios() {
    let parametros = selectUsuariosParametros();
    carregaHtml(contextPath + 'selecionar-usuario' + parametros, 'idSelectUsuarios');
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
    let uriModalCadastro = '/mv/atendimento/conteudoModalUsuarios?id=' + value + '&abertura=true';
    carregaHtml(uriModalCadastro, 'modalBody');
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Editar Usuario');
}

function modalCriarUsuarioAbertura() {
    carregaHtml('/mv/atendimento/conteudoModalClientes?cadastro-abertura=true', 'modalBody');
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Adicionar Cliente');
}

function atualizarTotais() {
    const xhttp1 = new XMLHttpRequest();
    xhttp1.open("GET", '/atendimento/chamados/quantidade');
    xhttp1.send();
    xhttp1.onload = function () {
        let obj = JSON.parse(this.responseText);
        document.getElementById('spanPendente').innerHTML = obj.totalAguardando;
        document.getElementById('spanEmAnalise').innerHTML = obj.totalEmAtendimento;
        document.getElementById('spanFinalizados').innerHTML = obj.totalEncerrado;
    };
}

