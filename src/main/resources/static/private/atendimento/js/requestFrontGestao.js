function chamadoUsuarioSelecionado() {
    limparAlertaGeral();
    let user = document.getElementById('choices-usuarios');
    requestDef(MV_A_CHAMADO + '?user=' + user.value, 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function chamadoUsuarioEditado(user) {
    limparAlertaGeral();
    requestDef(MV_A_CHAMADO + '?user=' + user.value, 'GET', null)
            .then(response => {
                $("#root").html(response);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
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
    let uriModalCadastro = MV_A_MODAL_USUARIOS + '&id=' + value;
    redirectHtml(uriModalCadastro, 'GET', 'modalBody', null);
    mostraModal('#modal');
    $('#staticBackdropLabel').html('Editar Usuario');
}

function modalCriarUsuarioAbertura() {
    requestDef(MV_A_MODAL_CLIENTES, 'GET', null)
            .then(response => {
                $("#modalBody").html(response);
                $('#staticBackdropLabel').html('Adicionar Cliente');
                mostraModal('#modal');
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
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

var MV_A_SELECIONAR_USUARIO = MV_ATENDIMENTO + 'selecionar-usuario';
var MV_A_MODAL_SISTEMA = MV_ATENDIMENTO + 'conteudoModalSistemas';
var MV_A_MODAL_AREA = MV_ATENDIMENTO + 'conteudoModalArea';
var MV_A_MODAL_PROBLEMA = MV_ATENDIMENTO + 'conteudoModalProblema';
var MV_A_MODAL_NIVEL = MV_ATENDIMENTO + 'conteudoModalNiveis';
var MV_A_MODAL_ORIGEM = MV_ATENDIMENTO + 'conteudoModalOrigens';
var MV_A_MODAL_TIPOARQUIVO = MV_ATENDIMENTO + 'conteudoModalTiposArquivos';
var MV_A_MODAL_TIPOENTIDADE = MV_ATENDIMENTO + 'conteudoModalTiposEntidades';
var MV_A_MODAL_ENTIDADE = MV_ATENDIMENTO + 'conteudoModalEntidades';
var MV_A_MODAL_USUARIO_ABERTURA = MV_ATENDIMENTO + 'conteudoModalUsuarios?abertura=true';
var MV_A_MODAL_CLIENTE_ABERTURA = MV_ATENDIMENTO + 'conteudoModalClientes?abertura=true';
var MV_A_MODAL_USUARIO = MV_ATENDIMENTO + 'conteudoModalUsuarios';
var MV_A_MODAL_CLIENTE = MV_ATENDIMENTO + 'conteudoModalClientes';
var MV_A_MODAL_TROCAR_SENHA = '/mv/atendimento/trocarSenha';
var MV_A_CLIENTE = '/mv/atendimento/cliente';
var MV_A_USUARIO = '/mv/atendimento/usuario';
var MV_A_SISTEMA = '/mv/atendimento/sistema';
var MV_A_AREA = '/mv/atendimento/area';
var MV_A_PROBLEMA = '/mv/atendimento/problema';
var MV_A_NIVEL = '/mv/atendimento/nivel';
var MV_A_ORIGEM = '/mv/atendimento/origem';
var MV_A_TIPOARQUIVO = '/mv/atendimento/tipoArquivo';
var MV_A_TIPOENTIDADE = '/mv/atendimento/tipoEntidade';
var MV_A_ENTIDADE = '/mv/atendimento/entidade';