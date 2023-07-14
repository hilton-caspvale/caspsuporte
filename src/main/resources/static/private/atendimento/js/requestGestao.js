var contextPath = '/atendimento/';
var areasPath = contextPath + 'areas/';
var sistemasPath = contextPath + 'sistemas/';
var problemasPath = contextPath + 'problemas/';
var niveisPath = contextPath + 'niveis/';
var origensPath = contextPath + 'origens/';
var tiposArquivosPath = contextPath + 'tipos-arquivos/';
var tiposEntidadesPath = contextPath + 'tipos-entidades/';
var entidadesPath = contextPath + 'entidades/';
var clientesPath = contextPath + 'clientes/';
var usuariosPath = contextPath + 'usuarios/';

function editarSistema(event) {
    requestAtendimento(sistemasPath + event.currentTarget.value, 'PUT', getSistema(), 'table');
    limparModal();
}

function criarSistema(event) {
    requestAtendimento(sistemasPath + event.currentTarget.value, 'POST', getSistema(), 'table');
    limparModal();
}

function deletarSistema(event) {
    requestAtendimento(sistemasPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function editarProblema(event) {
    requestAtendimento(problemasPath + event.currentTarget.value, 'PUT', getProblema(), 'table');
    limparModal();
}

function criarProblema(event) {
    requestAtendimento(problemasPath + event.currentTarget.value, 'POST', getProblema(), 'table');
    limparModal();
}

function deletarProblema(event) {
    requestAtendimento(problemasPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function editarArea(event) {
    requestAtendimento(areasPath + event.currentTarget.value, 'PUT', getArea(), 'table');
    limparModal();
}

function criarArea(event) {
    requestAtendimento(areasPath + event.currentTarget.value, 'POST', getArea(), 'table');
    limparModal();
}

function deletarArea(event) {
    requestAtendimento(areasPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function editarNivel(event) {
    requestAtendimento(niveisPath + event.currentTarget.value, 'PUT', getNivel(), 'table');
    limparModal();
}

function criarNivel(event) {
    requestAtendimento(niveisPath + event.currentTarget.value, 'POST', getNivel(), 'table');
    limparModal();
}

function deletarNivel(event) {
    requestAtendimento(niveisPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function editarOrigem(event) {
    requestAtendimento(origensPath + event.currentTarget.value, 'PUT', getOrigem(), 'table');
    limparModal();
}

function deletarOrigem(event) {
    requestAtendimento(origensPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function criarOrigem(event) {
    requestAtendimento(origensPath + event.currentTarget.value, 'POST', getOrigem(), 'table');
    limparModal();
}

function editarTipoArquivo(event) {
    requestAtendimento(tiposArquivosPath + event.currentTarget.value, 'PUT', getTipoArquivo(), 'table');
    limparModal();
}

function criarTipoArquivo(event) {
    requestAtendimento(tiposArquivosPath + event.currentTarget.value, 'POST', getTipoArquivo(), 'table');
    limparModal();
}

function deletarTipoArquivo(event) {
    requestAtendimento(tiposArquivosPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function editarTipoEntidade(event) {
    requestAtendimento(tiposEntidadesPath + event.currentTarget.value, 'PUT', getTipoEntidade(), 'table');
    limparModal();
}

function criarTipoEntidade(event) {
    requestAtendimento(tiposEntidadesPath + event.currentTarget.value, 'POST', getTipoEntidade(), 'table');
    limparModal();
}

function deletarTipoEntidade(event) {
    requestAtendimento(tiposEntidadesPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function editarEntidade(event) {
    requestAtendimento(entidadesPath + event.currentTarget.value, 'PUT', getEntidade(), 'table');
    limparModal();
}

function deletarEntidade(event) {
    requestAtendimento(entidadesPath + event.currentTarget.value, 'DELETE', null, 'table');
    limparModal();
}

function criarEntidade(event) {
    requestAtendimento(entidadesPath + event.currentTarget.value, 'POST', getEntidade(), 'table');
    limparModal();
}

function editarCliente(event) {
    requestAtendimento(clientesPath + event.currentTarget.value, 'PUT', getCliente('#cadastroUsuarioModal'), 'table');
    limparModal();
}

function editarUsuarioAbertura(event) {
    event.preventDefault();
    let form = document.querySelector('#edicaoUsuarioModalAbertura');
    let login = form.querySelector('#nlogin').value;
    let tipo = form.querySelector('#itipoUsuario').value;
    if (usuarioLogado() === login) {
        return requestAtendimento(usuariosPath + event.currentTarget.value, 'PUT', getUsuario('#edicaoUsuarioModalAbertura'), null);
    } else {
        if (tipo === '3') {
            return requestAtendimento(clientesPath + event.currentTarget.value, 'PUT', getCliente('#edicaoUsuarioModalAbertura'), null);
        } else {
            return requestAtendimento(usuariosPath + event.currentTarget.value, 'PUT', getUsuario('#edicaoUsuarioModalAbertura'), null);
        }
    }
}

function criarClienteAbertura(event) {
    requestAtendimento(clientesPath + event.currentTarget.value, 'POST', getCliente('#cadastroUsuarioModalAbertura'), null);
    let form = document.querySelector('#cadastroUsuarioModalAbertura');
    let novoLogin = form.querySelector('#nlogin').value;
    chamadoUsuarioEditado(novoLogin);
    limparModal();
}

function criarCliente(event) {
    requestAtendimento(clientesPath + event.currentTarget.value, 'POST', getCliente('#cadastroUsuarioModal'), 'table');
    limparModal();
}

function editarUsuario(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value, 'PUT', getUsuario('#cadastroUsuarioModal'), 'table');
    limparModal();
}

function criarUsuario(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value, 'POST', getUsuario('#cadastroUsuarioModal'), 'table');
    limparModal();
}