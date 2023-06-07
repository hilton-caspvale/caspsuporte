function getUrl() {
    return 'http://localhost:8081';
}

function getUriViewAtendimentoCliente() {
    return '/mv/atendimento-cliente/';
}

function getUriViewAtendimento() {
    return '/mv/atendimento/';
}

function getUriToastGeral() {
    return '/mv/atendimento/toast';
}

function usuarioLogado() {
    //alert('usuarioLogado');
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

function login(){
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


function carregaHtml(uri, elemento) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', getUrl() + uri);
    xhr.setRequestHeader('Content-Type', 'text/html');
    xhr.send();
    xhr.onprogress = function () {
    };
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
            } else {
                window.location.href = '/error';
            }
        }
    };
    xhr.onload = function () {
        $("#" + elemento).html(xhr.responseText);
    };
}

function carregarToast(mensagem) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', getUrl() + getUriToastGeral());
    xhr.send();
    xhr.onload = function () {
        let toastId = document.getElementById('toastId');
        toastId.innerHTML = xhr.responseText;
        document.getElementById('msgToast').innerHTML = mensagem;
        new bootstrap.Toast(document.getElementById('liveToast')).show();
    };
}

function requestAtendimento(uri, metodo, object, tabela) {
    fetch(getUrl() + uri, {
        method: metodo,
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        body: object
    })
            .then(function (response) {
                if (response.ok) {
                    carregarToast("Dados gravador com sucesso!");
                    if (tabela !== null) {
                        $('#' + tabela).bootstrapTable('refresh');
                    }
                } else {
                    response.json().then(function (json) {
                        if (json.detail === undefined) {
                            carregarToast(response.status + ' - Recurso não encontrado! ' + json.path);
                        } else {
                            carregarToast(json.userMessage);
                        }
                    });
                }
            })
            .catch(function (error) {
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
    let uri = '/atendimento/usuarios/consulta-login?nlogin=' + login.value;
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

function validarSelectUsuario(select, bt){
    if(select.length===1){
        bt.removeAttribute('disabled');
    }else{
        bt.setAttribute('disabled', '');
    }
}