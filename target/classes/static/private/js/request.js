var contextPath = '/atendimento/';
var usuariosPath = contextPath + 'usuarios/';


function trocarsenha(event) {
    requestAtendimento(usuariosPath + event.currentTarget.value + '/trocar-senha', 'PATCH', document.getElementById('senha').value, null);
    limparModal();
}

function gravarAnexo(event) {
    let method = 'POST';
    event.preventDefault();
    let form = $('#formAnexo')[0];
    let idChamado = document.getElementById('iChamado').value;
    let data = new FormData(form);
    data.append("iChamado", idChamado);
    console.log('data: ' + data);
    $.ajax({
        url: "/atendimento/chamados/anexo",
        enctype: 'multipart/form-data',
        type: method,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        error: function (msg) {
            carregarToast("[" + msg.statusText) + "] - Não foi possível gravar o anexo!";
            return msg;
        },
        success: function (html) {
            carregarToast("Os anexos do chamado " + idChamado + " gravados com sucesso!");
        }
    });
}

function gravarChamado(event) {
    event.preventDefault();
    let listaEntidades = form.querySelector('#caspEntidadesList');
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
                        carregarToast("Chamado " + json.ichamado + " criado com sucesso!");
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
    let patchUrl = getUrl() + "/atendimento/chamados/" + iChamado + "?acao=" + acao;
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
    var currentTarget = $(event.currentTarget);
    var idAnexo = currentTarget.attr('data-anexo');
    var iChamado = currentTarget.attr('data-id');
    console.log('anexo: ' + idAnexo);
    console.log('iChamado: ' + iChamado);
    document.getElementById("btExcluir").setAttribute("data-anexo", idAnexo);
    document.getElementById("btExcluir").setAttribute("data-id", iChamado);
}

function deletarComentario(event, comentarioId) {
    event.preventDefault();
    var currentTarget = $(event.currentTarget);
    var idAnexo = currentTarget.attr('data-anexo');
    var iChamado = currentTarget.attr('data-id');
    fetch(getUrl() + "/atendimento/chamados/" + iChamado + "/comentario/" + idAnexo, {
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
                carregarToast('Erro ao processar requisição!');
            });
}

function patchEnviarAnexo(method, event) {
    event.preventDefault();
    //let form = document.getElementById('formAnexo');//$('#formAnexos')[0];
    let form = $('#formAnexo')[0];
    console.log('form: ' + form);
    const idChamado = document.getElementById('ichamado').value;
    const comentarioAnexo = document.getElementById('comentarioAnexo').value;
    var data = new FormData(form);
    data.append("iChamado", idChamado);
    data.append("comentarioAnexo", comentarioAnexo);
    $.ajax({
        url: "/atendimento/chamados/" + idChamado + "/anexar",
        enctype: 'multipart/form-data',
        type: method,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        error: function (msg) {
            carregarToast(msg.statusText);
            return msg;
        },
        success: function (html) {
            //$("#tabelapainel").html(html);
        }
    });
}


function patchEnviarAnexo2(method, event) {
    event.preventDefault();
    const form = $('#formAnexos')[0];
    const idChamado = document.getElementById('ichamado').value;
    const comentarioAnexo = document.getElementById('comentarioAnexo').value;
    var data = new FormData(form);
    data.append("iChamado", idChamado);
    data.append("comentarioAnexo", comentarioAnexo);
    $.ajax({
        url: "/atendimento/chamados/" + idChamado + "/anexar",
        enctype: 'multipart/form-data',
        type: method,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        error: function (msg) {
            alert(msg.statusText);
            return msg;
        },
        success: function (html) {
            alert('ussss');//$("#tabelapainel").html(html);
        }
    });
}

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