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

function criarSistema(event) {
    requestDef(sistemasPath + event.currentTarget.value, 'POST', getSistema())
            .then(response => {
                carregarToast("Sistema [" + response.descricaoSistema + "] cadastrado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarSistema(event) {
    requestDef(sistemasPath + event.currentTarget.value, 'PUT', getSistema())
            .then(response => {
                carregarToast("Sistema [" + response.descricaoSistema + "] atualizado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarSistema(event) {
    requestExcluirCadastro(sistemasPath + event.currentTarget.value);
}

function criarProblema(event) {
    requestDef(problemasPath + event.currentTarget.value, 'POST', getProblema())
            .then(response => {
                carregarToast("Problema [" + response.descricaoSistema + "] cadastrado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarProblema(event) {
    requestDef(problemasPath + event.currentTarget.value, 'PUT', getProblema())
            .then(response => {
                carregarToast("Problema [" + response.descricaoProblema + "] atualizado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarProblema(event) {
    requestExcluirCadastro(problemasPath + event.currentTarget.value);
}

function criarArea(event) {
    requestDef(areasPath + event.currentTarget.value, 'POST', getArea())
            .then(response => {
                carregarToast("Área [" + response.descricaoArea + "] cadastrada!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarArea(event) {
    requestDef(areasPath + event.currentTarget.value, 'PUT', getArea())
            .then(response => {
                carregarToast("Área [" + response.descricaoArea + "] atualizada!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarArea(event) {
    requestExcluirCadastro(areasPath + event.currentTarget.value);
}

function criarNivel(event) {
    requestDef(niveisPath + event.currentTarget.value, 'POST', getNivel())
            .then(response => {
                carregarToast("Nível [" + response.descricaoNivel + "] cadastrado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarNivel(event) {
    requestDef(niveisPath + event.currentTarget.value, 'PUT', getNivel())
            .then(response => {
                carregarToast("Nível [" + response.descricaoNivel + "] atualizado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarNivel(event) {
    requestExcluirCadastro(niveisPath + event.currentTarget.value);
}

function criarOrigem(event) {
    requestDef(origensPath + event.currentTarget.value, 'POST', getOrigem())
            .then(response => {
                carregarToast("Origem [" + response.descricaoOrigem + "] cadastrada!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarOrigem(event) {
    requestDef(origensPath + event.currentTarget.value, 'PUT', getOrigem())
            .then(response => {
                carregarToast("Origem [" + response.descricaoOrigem + "] atualizada!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarOrigem(event) {
    requestExcluirCadastro(origensPath + event.currentTarget.value);
}

function criarTipoArquivo(event) {
    requestDef(tiposArquivosPath + event.currentTarget.value, 'POST', getTipoArquivo())
            .then(response => {
                carregarToast("Tipo [" + response.descricaoTipoArquivo + "] cadastrado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarTipoArquivo(event) {
    requestDef(tiposArquivosPath + event.currentTarget.value, 'PUT', getTipoArquivo())
            .then(response => {
                carregarToast("Tipo [" + response.descricaoTipoArquivo + "] atualizado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarTipoArquivo(event) {
    requestExcluirCadastro(tiposArquivosPath + event.currentTarget.value);
}

function criarTipoEntidade(event) {
    requestDef(tiposEntidadesPath + event.currentTarget.value, 'POST', getTipoEntidade())
            .then(response => {
                carregarToast("Tipo [" + response.tipoDescricao + "] cadastrado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarTipoEntidade(event) {
    requestDef(tiposEntidadesPath + event.currentTarget.value, 'PUT', getTipoEntidade())
            .then(response => {
                carregarToast("Tipo [" + response.tipoDescricao + "] atualizado!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarTipoEntidade(event) {
    requestExcluirCadastro(tiposEntidadesPath + event.currentTarget.value);
}

function criarEntidade(event) {
    requestDef(entidadesPath + event.currentTarget.value, 'POST', getEntidade())
            .then(response => {
                carregarToast("Entidade [" + response.nomeEntidade + "] cadastrada!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarEntidade(event) {
    requestDef(entidadesPath + event.currentTarget.value, 'PUT', getEntidade())
            .then(response => {
                carregarToast("Entidade [" + response.nomeEntidade + "] atualizada!", "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function deletarEntidade(event) {
    requestExcluirCadastro(entidadesPath + event.currentTarget.value);
}

function criarCliente(event) {
    requestAtendimento(clientesPath + event.currentTarget.value, 'POST', getCliente('#cadastroUsuarioModal'), 'table');
    limparModal();
}

function editarCliente(event) {
    requestDef(clientesPath + event.currentTarget.value, 'PUT', getCliente('#cadastroUsuarioModal'))
            .then(response => {
                alertaSucesso("Cliente [" + response.nlogin + "] atualizado!", "alertaModal");
                atualizarTabela('table');
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function criarUsuario(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value, 'POST', getUsuario('#cadastroUsuarioModal'), 'table');
    limparModal();
}

function editarUsuario(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value, 'PUT', getUsuario('#cadastroUsuarioModal'), 'table');
    limparModal();
}

function editarUsuarioAbertura(event) {
    event.preventDefault();
    let p1;
    let form = document.querySelector('#edicaoUsuarioModalAbertura');
    let login = form.querySelector('#nlogin').value;
    let tipo = form.querySelector('#itipoUsuario').value;
    if (tipo === '3') {
        p1 = () => requestAtendimento(clientesPath + event.currentTarget.value, 'PUT', getCliente('#edicaoUsuarioModalAbertura'), null);
    } else {
        p1 = () => requestAtendimento(usuariosPath + event.currentTarget.value, 'PUT', getUsuario('#edicaoUsuarioModalAbertura'), null);
    }
    const p2 = () => carregaHtml(MV_A_CHAMADO + '?user=' + login, 'root');
    const promises = [p1, p2];
    executePromisesSeq(promises)
            .then(() => {
            })
            .catch(error => {
                alertaUnico("Erro ao processar requisição", error);
            });
}

function criarClienteAbertura(event) {
    requestAtendimento(clientesPath + event.currentTarget.value, 'POST', getCliente('#cadastroUsuarioModalAbertura'), null);
    let form = document.querySelector('#cadastroUsuarioModalAbertura');
    let novoLogin = form.querySelector('#nlogin').value;
    chamadoUsuarioEditado(novoLogin);
    limparModal();
}





