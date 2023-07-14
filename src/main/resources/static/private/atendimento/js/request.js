var contextPath = '/atendimento/';
var usuariosPath = contextPath + 'usuarios/';
var chamadosPatch = contextPath + 'chamados/';

function requestAtendimento(uri, metodo, object, tabela) {
    return fetch(getUrl() + uri, {
        method: metodo,
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: object
    })
            .then(function (response) {
                if (response.ok) {
                    carregarToast("Dados gravados com sucesso!");
                    if (tabela !== null) {
                        $('#' + tabela).bootstrapTable('refresh');
                    }
                    if (response.status !== 204) {
                        response.json().then(function (j) {
                            console.log(j);
                        });
                    }
                } else {
                    response.json().then(function (json) {
                        if (json.detail === undefined) {
                            carregarToast(response.status + ' - Recurso não encontrado! ' + json.path);
                        } else {
                            let detalhe = '';
                            if (json.objects === undefined) {
                                alertaUnico(json.title, json.userMessage);
                            } else {
                                json.objects.forEach(ob => {
                                    detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                });
                                alertaCampos(json.userMessage, detalhe);
                            }
                        }
                        console.log(json);
                    });
                }
            })
            .catch(function (error) {
                console.log('erro: ' + error);
                carregarToast('Erro ao processar requisição!');
            });
}

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
                .then(response => response.json())
                .then(data => {
                    console.log('detail: ' + data.detail);
                    if (data.status === 404) {
                        if (data.detail === 'Usuário não localizado!') {
                            texto = "";
                            adicionarClass(login, 'is-valid');
                            deletarClass(login, 'is-invalid');
                            botaoGravar.removeAttribute('disabled');
                        } else {
                            adicionarClass(login, 'is-invalid');
                            deletarClass(login, 'is-valid');
                            botaoGravar.setAttribute('disabled', '');
                            usuarioInvalido.innerHTML = 'Não foi possível verificar o login informado!!';
                        }
                    } else {
                        adicionarClass(login, 'is-invalid');
                        deletarClass(login, 'is-valid');
                        botaoGravar.setAttribute('disabled', '');
                        usuarioInvalido.innerHTML = 'Login [' + data.nlogin + '] está em uso!';
                    }
                })
                .catch(function (error) {
                    console.log('Erro catch: ' + error.message);
                });
    }
}

function trocarsenha(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value + '/trocar-senha', 'PATCH', document.getElementById('senha').value, null);
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
    console.log("chamado: " + object);
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
                            patchEnviarAnexoNaAbertura(event, json.ichamado);
                        }
                        carregarToast("Chamado " + json.ichamado + msg_complemento);
                        visualizarChamado(json.ichamado);
                    });
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
                                    alertaUnico(json.title, json.userMessage);
                                } else {
                                    json.objects.forEach(ob => {
                                        detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                    });
                                    alertaCampos(json.userMessage, detalhe);
                                }
                            }
                        }
                    });
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

    fetch(patchUrl, {
        method: "PATCH",
        body: comentario
    })
            .then(function (response) {
                if (response.ok) {
                    response.text().then(function (text) {
                        carregarToast(text);
                        visualizarChamado(iChamado);
                    });
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
                                    alertaUnico(json.title, json.userMessage);
                                } else {
                                    json.objects.forEach(ob => {
                                        detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                    });
                                    alertaCampos(json.userMessage, detalhe);
                                }
                            }
                        }
                    });
                }
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição!');
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
    fetch(getUrl() + chamadosPatch + iChamado + "/anexos/" + idAnexo, {
        method: "DELETE"
    })
            .then(function (response) {
                if (response.ok) {
                    response.text().then(function (text) {
                        carregarToast(text);
                        visualizarChamado(iChamado);
                    });
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
                                    alertaUnico(json.title, json.userMessage);
                                } else {
                                    json.objects.forEach(ob => {
                                        detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                    });
                                    alertaCampos(json.userMessage, detalhe);
                                }
                            }
                        }
                    });
                }
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição! ' + error);
            });
}

function patchEnviarAnexo(event) {
    event.preventDefault();
    let form = $('#formAnexo')[0];
    const idChamado = document.getElementById('ichamado').value;
    const comentarioAnexo = document.getElementById('comentarioAnexo').value;
    var data = new FormData(form);
    data.append("iChamado", idChamado);
    data.append("comentarioAnexo", comentarioAnexo);
    $.ajax({
        url: chamadosPatch + idChamado + "/anexar",
        enctype: 'multipart/form-data',
        type: 'PATCH',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        error: function (msg) {
            carregarToast(msg.responseJSON.detail);
            return msg;
        },
        success: function (html) {
            visualizarChamado(idChamado);
            carregarToast(html);
        }
    });
}

function patchEnviarAnexoNaAbertura(event, idChamado) {
    event.preventDefault();
    let form = $('#formAnexo')[0];
    let comentarioAnexo = '';
    var data = new FormData(form);
    data.append("iChamado", idChamado);
    data.append("comentarioAnexo", comentarioAnexo);
    $.ajax({
        url: chamadosPatch + idChamado + "/anexar",
        enctype: 'multipart/form-data',
        type: 'PATCH',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        error: function (msg) {
            carregarToast(msg.responseJSON.detail);
            return msg;
        },
        success: function (html) {
//            visualizarChamado(idChamado);
//            carregarToast(html);
        }
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
    fetch(uri, {
        method: "PATCH",
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: boddy
    })
            .then(function (response) {
                console.log("response.ok: " + response.ok);
                if (response.ok) {
                    response.text().then(function (text) {
                        carregarToast(text);
                        visualizarChamado(idChamado);
                    });
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
                                    alertaUnico(json.title, json.userMessage);
                                } else {
                                    json.objects.forEach(ob => {
                                        detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                    });
                                    alertaCampos(json.userMessage, detalhe);
                                }
                            }
                        }
                    });
                }
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição!');
            });
}

function atualizarTotais() {
    const xhttp1 = new XMLHttpRequest();
    xhttp1.open("GET", chamadosPatch + 'quantidade');
    xhttp1.send();
    xhttp1.onload = function () {
        let obj = JSON.parse(this.responseText);
        document.getElementById('spanPendente').innerHTML = obj.totalAguardando;
        document.getElementById('spanEmAnalise').innerHTML = obj.totalEmAtendimento;
        document.getElementById('spanFinalizados').innerHTML = obj.totalEncerrado;
    };
}

function carregaHtml(uri, elemento) {
    carregaHtmlPromise(uri)
            .then(html => {
                $("#" + elemento).html(html);
            })
            .catch(error => {
                alertaUnico("Erro ao carregar conteúdo da página", error);
            });
}

function promiseHtml(promisesEElementos) {
    // Verifica se o parâmetro é um array
    if (!Array.isArray(promisesEElementos)) {
        throw new Error('O parâmetro deve ser do tipo array.');
    }

    // Verifica se o array tem pelo menos um elemento
    if (promisesEElementos.length === 0) {
        throw new Error('O array deve ter pelo menos um elemento.');
    }

    // Executa as promises em sequência
    let resultPromise = Promise.resolve();

    promisesEElementos.forEach(([promise, elemento]) => {
        resultPromise = resultPromise.then(() => {
            return promise.then(resultado => {
                $(elemento).html(resultado);
            });
        });
    });
    return resultPromise;
}

function inserirHtml(elementos) {
    promiseHtml(elementos)
            .then(() => {
                console.log('Inserção de conteúdo concluída.');
            })
            .catch(error => {
                console.error('Ocorreu um erro:', error);
            });
}