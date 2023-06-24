var contextPath = '/atendimento/';
var usuariosPath = contextPath + 'usuarios/';
var chamadosPatch = contextPath + 'chamados/';


function trocarsenha(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value + '/trocar-senha', 'PATCH', document.getElementById('senha').value, null);
    limparModal();
}

function gravarChamado(event) {
    event.preventDefault();
    let object = getChamado();
    fetch(getUrl() + "/atendimento/chamados", {
        method: "POST",
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
                        carregarToast("Chamado " + json.ichamado + " criado com sucesso!");
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
                                json.objects.forEach(ob => {
                                    detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                });
                                alertaCampos(json.userMessage, detalhe);
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
    patchUrl += "/atendimento/chamados/" + iChamado + "/movimentar?acao=" + acao;
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
                                json.objects.forEach(ob => {
                                    detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                });
                                alertaCampos(json.userMessage, detalhe);
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
    fetch(getUrl() + "/atendimento/chamados/" + iChamado + "/anexos/" + idAnexo, {
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
                                json.objects.forEach(ob => {
                                    detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                });
                                alertaCampos(json.userMessage, detalhe);
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
        url: "/atendimento/chamados/" + idChamado + "/anexar",
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
        url: "/atendimento/chamados/" + idChamado + "/anexar",
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
    let uri = getUrl() + "/atendimento/chamados/" + idChamado + "/encerrar";
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
                                json.objects.forEach(ob => {
                                    detalhe = detalhe + 'Campo <strong>' + ob.name + '</strong> ' + ob.userMessage + '<br>';
                                });
                                alertaCampos(json.userMessage, detalhe);
                            }
                        }
                    });
                }
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição!');
            });
}

/*function baixarArquivo(event) {
 event.preventDefault();
 let currentTarget = $(event.currentTarget);
 let idAnexo = currentTarget.attr('data-anexo');
 let iChamado = currentTarget.attr('data-id');
 let uri = chamadosPatch + iChamado + '/anexos/' + idAnexo;
 carregaHtml(uri, 'root');
 }*/

function alertaCampos(mensagem, detalhe) {
    let wrapper = document.createElement('div');
    wrapper.innerHTML = [
        `<div class="text-start fixed-top" style="margin-top: 4rem">`,
        `<div class="row justify-content-md-center">`,
        `<div class="col-md-auto" onclick="this.style.display = 'none'">`,
        `   <div class="alert alert-danger alert-dismissible" role="alert">`,
        `       <div>${mensagem}</div>`,
        `       <hr>`,
        `       <div>${detalhe}</div>`,
        '       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '   </div>',
        '</div>',
        '</div>',
        '</div>'
    ].join('');
    document.getElementById('alertaChamado').append(wrapper);
}