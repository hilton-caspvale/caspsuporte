function validarUsuario(formulario) {
    //let login = document.querySelector('#cadastroUsuarioModal input[id="nlogin"]');
    let form = document.querySelector(formulario);
    let login = form.querySelector('#nlogin');
    let usuarioInvalido = form.querySelector('#usuarioInvalido');
    let botaoGravar = form.querySelector('#gravarCliente');
    let texto = 'Informe um login';
    let uri = usuariosPath + 'consulta-login?nlogin=' + login.value;
    if (login.value === '') {
        adicionarClass(login, 'is-invalid');
        deletarClass(login, 'is-valid');
        botaoGravar.setAttribute('disabled', '');
        usuarioInvalido.innerHTML = texto;
    } else {
        fetch(uri)
                .then(response => {
                    response.json()
                            .then(json => {
                                if (response.ok) {
                                    adicionarClass(login, 'is-invalid');
                                    deletarClass(login, 'is-valid');
                                    botaoGravar.setAttribute('disabled', '');
                                    usuarioInvalido.innerHTML = 'Login [' + json.nlogin + '] está em uso!';
                                } else {
                                    if (response.status === 401) {
                                        window.location.href = '/login';
                                    }
                                    if (response.status === 404) {
                                        if (json.detail === 'Usuário não localizado!') {
                                            texto = "";
                                            adicionarClass(login, 'is-valid');
                                            deletarClass(login, 'is-invalid');
                                            botaoGravar.removeAttribute('disabled');
                                        }
                                    } else {
                                        adicionarClass(login, 'is-invalid');
                                        deletarClass(login, 'is-valid');
                                        botaoGravar.setAttribute('disabled', '');
                                        usuarioInvalido.innerHTML = 'Não foi possível verificar o login informado!!';
                                    }
                                }
                            })
                            .catch(function (error) {
                                carregarToast('Não foi possível consultar o login!' + error.message);
                            });
                })
                .catch(function (error) {
                    carregarToast('Erro ao processar requisição! - ' + error.message);
                });
    }
}

function trocarsenha(event) {
    requestDef(usuariosPath + event.currentTarget.value + '/trocar-senha', 'PATCH', document.getElementById('senha').value)
            .then(response => {
                carregarToast(response, "sucesso");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
    limparModal();
}

function gravarChamado(event) {
    event.preventDefault();
    let currentTarget = $(event.currentTarget);
    let object;
    let uri = getUrl();
    let method;
    let ichamado;
    let edicao = currentTarget.attr('data-edicao');
    let msg_complemento;
    if (edicao === 'edicao') {
        ichamado = currentTarget.attr('data-id');
        object = getEdicaoChamado(ichamado);
        uri += chamadosPatch;
        method = 'PUT';
        msg_complemento = ' editado com sucesso!';
    } else {
        object = getChamado();
        uri += chamadosPatch;
        method = 'POST';
        msg_complemento = ' criado com sucesso!';
    }
    fetch(uri, {
        method: method,
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: object
    })
            .then(function (response) {
                if (response.ok) {
                    response.json().then(function (json) {
                        let file = document.getElementById('file');
                        if (file.value) {
                            patchEnviarAnexo(event, json.ichamado);
                        }
                        carregarToast("Chamado " + json.ichamado + msg_complemento);
                        visualizarChamado(json.ichamado);
                    });
                } else {
                    if (response.status === 401) {
                        window.location.href = '/login';
                    } else {
                        response.json().then(function (json) {
                            if (json.status === 500) {
                                carregarToast(json.userMessage);
                            } else {
                                if (json.detail === undefined) {
                                    carregarToast(response.status + ' - Recurso não encontrado! ' + json.path);
                                } else {
                                    let detalhe = '';
                                    if (json.objects === undefined) {
                                        alertaUnico(json.title, json.userMessage, 'alertaGeral');
                                    } else {
                                        json.objects.forEach(ob => {
                                            detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                        });
                                        invalidFeedback(json.objects);
                                        if (detalhe === '') {
                                            alerta(json.userMessage, 'alertaGeral');
                                        } else {
                                            alertaCampos(json.userMessage, detalhe, 'alertaGeral');
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição!');
            });
}

function movimentarChamado(event, acao, comentarioId, dataagendamentoinput) {
    event.preventDefault();
    let iChamado = document.querySelector('#iChamado').value;
    let patchUrl = getUrl();
    patchUrl += chamadosPatch + iChamado + "/movimentar?acao=" + acao;
    let comentario = '';
    if (comentarioId !== null) {
        let comentarioElement = document.getElementById(comentarioId);
        if (comentarioElement) {
            if (comentarioElement.value) {
                if (comentarioElement.value !== '') {
                    comentario = comentarioElement.value;
                }
            }
        }
    }

    if (dataagendamentoinput !== null) {
        let dataAgendamentoElement = document.getElementById('dataagendamentoinput');
        if (dataAgendamentoElement) {
            if (dataAgendamentoElement.value) {
                agenda = dataAgendamentoElement.value;
                patchUrl += "&agenda=" + agenda;
            }
        }
    }

    if (acao === 'encaminhar') {
        let listaUsuarios = document.getElementById("choices-analistas") || null;
        let usuarioEncaminhar = listaUsuarios.options[listaUsuarios.selectedIndex].value || null;
        patchUrl += '&usuario-encaminhar=' + usuarioEncaminhar;
    }

    requestDef(patchUrl, 'PATCH', comentario)
            .then(response => {
                let titulo = "Chamado " + response.ichamado + " atualizado!";
                let msg = response.situacaoChamado;
                if (acao === "comentario") {
                    carregarToast("Comentário adicionado", 'sucesso', "");
                } else {
                    carregarToast(msg, 'sucesso', titulo);
                }
                visualizarChamado(iChamado);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function setarIAnexo(event) {
    event.preventDefault();
    let currentTarget = $(event.currentTarget);
    let idAnexo = currentTarget.attr('data-anexo');
    let iChamado = currentTarget.attr('data-id');
    document.getElementById("btExcluir").setAttribute("data-anexo", idAnexo);
    document.getElementById("btExcluir").setAttribute("data-id", iChamado);
}

function deletarComentario(event) {
    event.preventDefault();
    let currentTarget = $(event.currentTarget);
    let idAnexo = currentTarget.attr('data-anexo');
    let iChamado = currentTarget.attr('data-id');

    requestDef(getUrl() + chamadosPatch + iChamado + "/anexos/" + idAnexo, 'DELETE', null)
            .then(response => {
                let titulo = "Registro excluído!";
                carregarToast(titulo, 'alerta', titulo);
                visualizarChamado(iChamado);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function patchEnviarAnexo(event, idChamado) {
    event.preventDefault();
    let form = $('#formAnexo')[0];
    let comentarioAnexo = '';
    if (!idChamado) {
        idChamado = document.getElementById('iChamado').value;
        comentarioAnexo = document.getElementById('comentarioAnexo').value;
    }
    var data = new FormData(form);
    data.append("iChamado", idChamado);
    data.append("comentarioAnexo", comentarioAnexo);

    let header = new Headers({
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000
    });

    const conteudo = {
        method: 'PATCH',
        headers: header,
        body: data
    };

    fetch(chamadosPatch + idChamado + "/anexar", conteudo)
            .then(response => {
                if (response.ok) {
                    response.text().then(text => {
                        carregarToast(text, 'sucesso', text);
                        visualizarChamado(idChamado);
                    });
                } else {
                    if (response.status === 401) {
                        window.location.href = '/login';
                    } else {
                        response.json()
                                .then(json => {
                                    carregarToast(json.userMessage, 'erro', json.userMessage);
                                })
                                .catch(er => {
                                    throw {errorType: 'other', data: {status: response.status, body: er}};
                                });
                    }
                }
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function encerrarChamado(event) {
    event.preventDefault();
    let currentTarget = $(event.currentTarget);
    let idChamado = currentTarget.attr('data-id');
    let uri = chamadosPatch + idChamado + "/encerrar";
    let conteudo = {
        descricaoProblema: document.getElementById('descricaoProblemaModal').value,
        descricaoSolucao: document.getElementById('descricaoSolucaoModal').value
    };
    let boddy = JSON.stringify(conteudo);

    requestDef(uri, 'PATCH', boddy)
            .then(response => {
                let titulo = "Chamado " + response.ichamado + " ENCERRADO!";
                carregarToast(titulo, 'sucesso', titulo);
                visualizarChamado(idChamado);
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function atualizarTotais() {
    return requestDef(chamadosPatch + 'quantidade', 'GET', null)
            .then(response => {
                document.getElementById('spanPendente').innerHTML = response.totalAguardando;
                document.getElementById('spanEmAnalise').innerHTML = response.totalEmAtendimento;
                document.getElementById('spanFinalizados').innerHTML = response.totalEncerrado;
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function requestDef(url, metodo, object) {
    let header = new Headers({'Content-Type': 'application/json'});

    if (metodo === 'GET') {
        object = null;
        header = new Headers({'Content-Type': 'text/html'});
    }

    const conteudo = {
        method: metodo,
        headers: header,
        body: object
    };
    return fetch(url, conteudo)
            .then(response => {
                const contentType = response.headers.get('content-type');
                if (response.ok) {
                    if (response.status === 204) {
                        return "";
                    } else if (contentType && contentType.includes('application/json')) {
                        return response.json();
                    } else if (contentType && contentType.includes('text/html')) {
                        return response.text();
                    } else {
                        return response.text();
                    }
                } else {

                    if (response.status === 401) {
                        window.location.href = '/login';
                    } else {
                        if (contentType && contentType.includes('application/json')) {
                            return response.json()
                                    .then(json => {
                                        if (json.objects !== undefined) {
                                            throw {errorType: 'json-object', data: json};
                                        } else {
                                            throw {errorType: 'json-default', data: json};
                                        }
                                    });
                        } else {
                            return response.text()
                                    .then(text => {
                                        throw {errorType: 'http-error', data: {status: response.status, body: text}};
                                    });
                        }
                    }
                }
            })
            .catch(error => {
                throw error;
            });
}

function requestError(rejection, idLocal) {
    const {errorType, data} = rejection;
    switch (errorType) {
        case 'json-object':
            let json = JSON.parse(JSON.stringify(rejection.data));
            invalidFeedback(json.objects);
            alerta(json.userMessage, idLocal);
            return json.objects;
        case 'json-default':
            return carregarToast(JSON.parse(JSON.stringify(rejection.data)).userMessage, 'erro');
        case 'other':
            return carregarToast(JSON.parse(JSON.stringify(rejection)).userMessage, 'erro');
        default:
            return carregarToast(JSON.parse(JSON.stringify(rejection)).userMessage, 'erro');
    }
}

function requestExcluirCadastro(url) {
    requestDef(url, 'DELETE', null)
            .then(response => {
                carregarToast("Regitro excluído!", "alerta");
                atualizarTabela('table');
                limparModal();
            })
            .catch(error => {
                requestError(error, 'alertaModal');
            });
}

function atualizarPerfil() {
    requestDef(perfilPath, 'PATCH', getPerfil())
            .then(response => {
                carregarToast("perfil [" + response.nlogin + "] atualizado!", "sucesso");
            })
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}

function promiseHtml(promisesEElementos, promiseAPI) {
    // Verifica se o primeiro parâmetro é um array
    if (!Array.isArray(promisesEElementos)) {
        throw new Error('O primeiro parâmetro deve ser do tipo array.');
    }

    // Verifica se o array tem pelo menos um elemento
    if (promisesEElementos.length === 0) {
        throw new Error('O array deve ter pelo menos um elemento.');
    }

    // Executa a promise para a requisição na API
    let resultPromise = Promise.resolve();

    if (promiseAPI) {
        resultPromise = promiseAPI;
    }

    // Executa as promises para carregar o conteúdo HTML
    promisesEElementos.forEach(([promise, elemento]) => {
        resultPromise = resultPromise.then(() => {
            return promise.then(resultado => {
                $(elemento).html(resultado);
            });
        });
    });

    return resultPromise;
}

function moduloAtendimentoPromiseHtml() {
    const elementos = [
        [requestDef(getUriAtendimento(), 'GET', null), '#root'],
        [requestDef(MV_A_MENU_ATENDIMENTO, 'GET', null), '#menuPrincipal']
    ];
    promiseHtml(elementos)
            .catch(error => {
                requestError(error, 'alertaGeral');
            });
}
