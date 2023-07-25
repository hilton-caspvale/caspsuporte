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
    requestDef(clientesPath + event.currentTarget.value, 'POST', getCliente('#cadastroUsuarioModal'))
            .then(response => {
                carregarToast("Cliente [" + response.nlogin + "] cadastrado!", "sucesso");
                limparAlertaModal();
                fecharModal('#modal');
                atualizarTabela('table');
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
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
    requestDef(usuariosPath + event.currentTarget.value, 'POST', getUsuario('#cadastroUsuarioModal'))
            .then(response => {
                carregarToast("Usuário [" + response.nlogin + "] cadastrado!", "sucesso");
                limparAlertaModal();
                fecharModal('#modal');
                atualizarTabela('table');
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarUsuario(event) {
    requestDef(usuariosPath + event.currentTarget.value, 'PUT', getUsuario('#cadastroUsuarioModal'))
            .then(response => {
                carregarToast("Usuário [" + response.nlogin + "] atualizado!", "sucesso");
                limparAlertaModal();
                fecharModal('#modal');
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function editarUsuarioAbertura(event) {
    event.preventDefault();
    limparAlertaModal();
    let form = document.querySelector('#edicaoUsuarioModalAbertura');
    let login = form.querySelector('#nlogin').value;
    let tipo = form.querySelector('#itipoUsuario').value;
    let usuario = getUsuario('#edicaoUsuarioModalAbertura');
    let uri = usuariosPath + event.currentTarget.value;
    if (tipo === '3') {
        usuario = getCliente('#edicaoUsuarioModalAbertura');
        uri = clientesPath + event.currentTarget.value;
    }

    requestDef(uri, 'PUT', usuario)
            .then(response => {
                carregarToast("Usuário [" + response.nlogin + "] atualizado!", "sucesso");
                limparAlertaModal();
                fecharModal('#modal');
                requestMV(MV_A_CHAMADO + '?user=' + login);
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function criarClienteAbertura(event) {
    requestDef(clientesPath + event.currentTarget.value, 'POST', getCliente('#cadastroUsuarioModalAbertura'))
            .then(response => {
                carregarToast("Usuário [" + response.nlogin + "] cadastrado!", "sucesso");
                limparAlertaModal();
                fecharModal('#modal');
                requestMV(MV_A_CHAMADO + '?user=' + response.nlogin);
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}





