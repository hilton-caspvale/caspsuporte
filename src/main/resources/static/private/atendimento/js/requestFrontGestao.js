function chamadoUsuarioSelecionado() {
    limparAlertaGeral();
    let user = document.getElementById('choices-usuarios');
    requestMV(MV_A_CHAMADO + '?user=' + user.value);
}

function atualizaDadosDoUsuario() {
    let user = document.getElementById('choices-usuarios');
    requestMVIdElemento(MV_A_CHAMADO_DADOS_USUARIO + user.value, 'dados-usuario');
}

function chamadoUsuarioEditado(user) {
    limparAlertaGeral();
    requestMV(MV_A_CHAMADO + '?user=' + user.value);
}

function selectUsuarios() {
    let parametros = selectUsuariosParametros();
    requestDef(MV_A_SELECIONAR_USUARIO + parametros, 'GET', null)
            .then(response => {
                $("#idSelectUsuarios").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
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
    requestMVModal(MV_A_MODAL_USUARIO, value, "Editar Usuario");
}

function modalEditarUsuarioAbertura(id) {
    let value = document.getElementById(id).value;
    let url = MV_A_MODAL_USUARIO_ABERTURA + '&id=' + value;
    requestMVModal(url, null, "Editar Cadastro");
}

function modalCriarUsuarioAbertura() {
    requestMVModal(MV_A_MODAL_CLIENTE_ABERTURA, null, "Adicionar Cliente");
}

function cadastroSistemas() {
    requestMV(MV_A_SISTEMA);
}

function cadastroAreas() {
    requestMV(MV_A_AREA);
}

function cadastroProblemas() {
    requestMV(MV_A_PROBLEMA);
}

function cadastroNiveis() {
    requestMV(MV_A_NIVEL);
}

function cadastroOrigens() {
    requestMV(MV_A_ORIGEM);
}

function cadastroTipoArquivos() {
    requestMV(MV_A_TIPOARQUIVO);
}

function cadastroTipoEntidades() {
    requestMV(MV_A_TIPOENTIDADE);
}

function cadastroEntidades() {
    requestMV(MV_A_ENTIDADE);
}

function cadastroClientes() {
    requestMV(MV_A_CLIENTE);
}

function cadastroUsuarios() {
    requestMV(MV_A_USUARIO);
}

function modalSistemas(id) {
    requestMVModal(MV_A_MODAL_SISTEMA, id, "Sistema");
}

function modalAreas(id) {
    console.log(MV_A_MODAL_AREA);
    requestMVModal(MV_A_MODAL_AREA, id, "Área");
}

function modalProblemas(id) {
    requestMVModal(MV_A_MODAL_PROBLEMA, id, "Problema");
}

function modalNiveis(id) {
    requestMVModal(MV_A_MODAL_NIVEL, id, "Nivel");
}

function modalOrigens(id) {
    requestMVModal(MV_A_MODAL_ORIGEM, id, "Origem");
}

function modalTipoArquivos(id) {
    requestMVModal(MV_A_MODAL_TIPOARQUIVO, id, "Tipo de Arquivo");
}

function modalTipoEntidades(id) {
    requestMVModal(MV_A_MODAL_TIPOENTIDADE, id, "Tipo de Entidade");
}

function modalEntidades(id) {
    requestMVModal(MV_A_MODAL_ENTIDADE, id, "Entidade");
}

function modalClientes(id) {
    requestMVModal(MV_A_MODAL_CLIENTE, id, "Cliente");
}

function modalUsuarios(id) {
    requestMVModal(MV_A_MODAL_USUARIO, id, "Usuário");
}

function modalTrocarSenha(id) {
    requestMVModal(MV_A_MODAL_TROCAR_SENHA, id, "Usuário");
}