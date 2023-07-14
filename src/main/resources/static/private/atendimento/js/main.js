function getUrl() {
    return 'http://localhost:8081';
}

function usuarioLogado() {
    return document.getElementById('usuarioLogado').value;
}

function mostraModal(nomeModal) {
    $(nomeModal).modal('show');
}

function fecharModal(nomeModal) {
    $(nomeModal).modal('hide');
}

function limparModal() {
    document.getElementById('modalBody').innerHTML = '';
}

function login() {
    return document.querySelector('.loginlog').id;
}

function adicionarClass(elemento, classe) {
    var classes = elemento.className.split(' ');
    var getIndex = classes.indexOf(classe);

    if (getIndex === -1) {
        classes.push(classe);
        elemento.className = classes.join(' ');
    }
}

function deletarClass(elemento, classe) {
    var classes = elemento.className.split(' ');
    var getIndex = classes.indexOf(classe);

    if (getIndex > -1) {
        classes.splice(getIndex, 1);
    }
    elemento.className = classes.join(' ');
}

function addClass(id, classe) {
    var elemento = document.getElementById(id);
    var classes = elemento.className.split(' ');
    var getIndex = classes.indexOf(classe);

    if (getIndex === -1) {
        classes.push(classe);
        elemento.className = classes.join(' ');
    }
}

function delClass(id, classe) {
    var elemento = document.getElementById(id);
    var classes = elemento.className.split(' ');
    var getIndex = classes.indexOf(classe);

    if (getIndex > -1) {
        classes.splice(getIndex, 1);
    }
    elemento.className = classes.join(' ');
}

function carregarChoices() {
    document.querySelectorAll('.choicesmultiple').forEach(function (element) {
        new Choices(element, {
            removeItemButton: true,
            maxItemCount: 999,
            searchResultLimit: 5,
            renderChoiceLimit: 9999
        });

    });
    document.querySelectorAll('.choicessingle').forEach(function (element) {
        new Choices(element, {
            removeItemButton: true,
            maxItemCount: 1,
            searchResultLimit: 5,
            renderChoiceLimit: 9999
        });

    });
}

function choicesUsuarios() {
    document.querySelectorAll('.choicesUsuarios').forEach(function (element) {
        new Choices(element, {
            placeholder: true,
            placeholderValue: 'TESTE',
            removeItemButton: true,
            maxItemCount: 1,
            searchResultLimit: 10,
            renderChoiceLimit: 9999
        });

    });
}

function carregaHtmlPromise(uri) {
    return new Promise((resolve, reject) => {
        fetch(getUrl() + uri, {
            method: 'GET',
            headers: new Headers({
                'Content-Type': 'text/html'
            })
        })
                .then(response => {
                    if (!response.ok) {
                        response.json().then(function (json) {
                            if (json.detail === undefined) {
                                carregarToast(response.status + ' - Recurso não encontrado! ' + json.path);
                            } else {
                                carregarToast(json.userMessage);
                            }
                            reject(new Error('Requisição falhou'));
                        });
                    } else {
                        resolve(response.text());
                    }
                })
                .catch(function (error) {
                    carregarToast('Erro ao processar requisição!' + error);
                    reject(error);
                });
    });
}

function carregaHtmlvelho(uri, elemento) {
    return fetch(getUrl() + uri, {
        method: 'GET',
        headers: new Headers({
            'Content-Type': 'text/html'
        })
    })
            .then(response => {
                if (!response.ok) {
                    response.json().then(function (json) {
                        if (json.detail === undefined) {
                            carregarToast(response.status + ' - Recurso não encontrado! ' + json.path);
                        } else {
                            carregarToast(json.userMessage);
                        }
                    });
                } else {
                    return response.text();
                }
            })
            .then(html => {
                $("#" + elemento).html(html);
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição!' + error);
            });
}

function redirectHtml(uri, metodo, elemento, object) {
    let conteudo;
    let header = new Headers({'Content-Type': 'text/html'});
    if (object === null) {
        metodo = 'GET';
        conteudo = {
            method: metodo,
            headers: header
        };
    } else {
        conteudo = {
            method: metodo,
            headers: header,
            body: object
        };
    }
    fetch(getUrl() + uri, conteudo)
            .then(response => {
                if (!response.ok) {
                    response.json().then(function (json) {
                        if (json.detail === undefined) {
                            carregarToast(response.status + ' - Recurso não encontrado! ' + json.path);
                        } else {
                            carregarToast(json.userMessage);
                        }
                    });
                } else {
                    return response.text();
                }
            })
            .then(html => {
                $("#" + elemento).html(html);
            })
            .catch(function (error) {
                carregarToast('Erro ao processar requisição!' + error);
            });
}

function trocarSenha() {
    let senha = document.getElementById('senha');
    let confirmacao = document.getElementById('confirmacao');
    let senhaInvalida = document.getElementById("senhaInvalida");
    let confirmacaoInvalida = document.getElementById("confirmacaoInvalida");
    let botaoGravar = document.getElementById("gravar");
    if (senha.value === '') {
        addClass('senha', 'is-invalid');
        delClass('senha', 'is-valid');
        botaoGravar.setAttribute('disabled', '');
        senhaInvalida.innerHTML = "Informa uma senha";
    }
    if (confirmacao.value === '') {
        addClass('confirmacao', 'is-invalid');
        delClass('confirmacao', 'is-valid');
        botaoGravar.setAttribute('disabled', '');
        confirmacaoInvalida.innerHTML = "Confirme a senha";
    } else {
        if (senha.value === confirmacao.value) {
            addClass('senha', 'is-valid');
            delClass('senha', 'is-invalid');
            addClass('confirmacao', 'is-valid');
            delClass('confirmacao', 'is-invalid');
            botaoGravar.removeAttribute('disabled');
            senhaInvalida.innerHTML = "";
            confirmacaoInvalida.innerHTML = "";
        } else {
            addClass('confirmacao', 'is-invalid');
            delClass('confirmacao', 'is-valid');
            botaoGravar.setAttribute('disabled', '');
            confirmacaoInvalida.innerHTML = "As senhas não conferem";
        }
    }
}

function validarSelectUsuario(select, bt) {
    if (select.length === 1) {
        bt.removeAttribute('disabled');
    } else {
        bt.setAttribute('disabled', '');
    }
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

function alertaUnico(titulo, detalhe) {
    let wrapper = document.createElement('div');
    wrapper.innerHTML = [
        `<div class="text-start fixed-top" style="margin-top: 4rem">`,
        `<div class="row justify-content-md-center">`,
        `<div class="col-md-auto" onclick="this.style.display = 'none'">`,
        `   <div class="alert alert-danger alert-dismissible" role="alert">`,
        `       <div>${titulo}</div>`,
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