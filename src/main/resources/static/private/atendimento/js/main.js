function usuarioLogado() {
    return document.getElementById('usuarioLogado').value;
}

function mostraModalResponse(titulo, response) {
    $("#modalBody").html(response);
    $('#staticBackdropLabel').html(titulo);
    $('#modal').modal('show');
    limparAlertaModal();
}

function mostraModal99(nomeModal) {
    $(nomeModal).modal('show');
}

function fecharModal(nomeModal) {
    limparAlertaModal();
    $(nomeModal).modal('hide');
}

function limparModal() {
    document.getElementById('modalBody').innerHTML = '';
    document.getElementById('alertaModal').innerHTML = '';
}

function limparAlertaModal() {
    document.getElementById('alertaModal').innerHTML = '';
}
function limparAlertaGeral() {
    document.getElementById('alertaGeral').innerHTML = '';
}

function atualizarTabela(tabela) {
    $('#' + tabela).bootstrapTable('refresh');
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
            renderChoiceLimit: 999
        });
    });
    document.querySelectorAll('.choicessingle').forEach(function (element) {
        new Choices(element, {
            removeItemButton: true,
            maxItemCount: 1,
            searchResultLimit: 5,
            renderChoiceLimit: 999
        });
    });
}

function choicesUsuarios() {
    document.querySelectorAll('.choicesUsuarios').forEach(function (element) {
        new Choices(element, {
            placeholder: true,
            placeholderValue: 'Usuário',
            removeItemButton: true,
            maxItemCount: 1,
            searchResultLimit: 10,
            renderChoiceLimit: 999
        });
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

function alertaCampos(mensagem, detalhe, idLocal) {
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
    document.getElementById(idLocal).append(wrapper);
}

function alertaUnico(titulo, detalhe, idLocal) {
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
    document.getElementById(idLocal).append(wrapper);
}

function alertaSucesso(detalhe, idLocal) {
    let wrapper = document.createElement('div');
    wrapper.innerHTML = [
        `<div class="text-start fixed-top" style="margin-top: 4rem">`,
        `<div class="row justify-content-md-center">`,
        `<div class="col-md-auto" onclick="this.style.display = 'none'">`,
        `   <div class="alert alert-success alert-dismissible" role="alert">`,
        `       <div>${detalhe}</div>`,
        '       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '   </div>',
        '</div>',
        '</div>',
        '</div>'
    ].join('');
    document.getElementById(idLocal).append(wrapper);
}

// Lista para armazenar os toasts
let toasts = [];
var tempo_ms_toast = 5000;
function carregarToast(mensagem, tipo, titulo) {
    let ti = "Mensagem";
    if (titulo) {
        ti = titulo;
    }
    let cor = "bg-info";
    switch (tipo) {
        case "sucesso":
            cor = "bg-success";
            break;
        case "erro":
            cor = "bg-danger";
            break;
        case "alerta":
            cor = "bg-warning";
            break;
        default:
            cor = "bg-info";
            break;
    }
    let wrapper = document.createElement('div');
    let toastId = `liveToast-${Date.now()}`; // Adicionando um ID único para cada toast
    wrapper.innerHTML = [
        `<div id="${toastId}" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-autohide="false">`, // Removemos o autohide padrão para controlar manualmente o fechamento
        `   <div class="toast-header">`,
        `       <strong class="me-auto">${mensagem}</strong>`,
        `       <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>`,
        `   </div>`,
        `   <div class="progress" style="height: 5px;">`, // Adicionamos a barra de progresso
        `       <div id="progressBar-${toastId}" class="progress-bar ${cor} progress-bar-striped progress-bar-animated" role="progressbar" style="width: 100%;"></div>`,
        `   </div>`,
        '</div>'
    ].join('');
    document.getElementById("toastId").append(wrapper);
    toasts.push({
        id: toastId,
        remainingTime: tempo_ms_toast
    });
    exibirToasts();
}

// Função para adicionar um novo toast à lista
function carregarToast99(mensagem, tipo, titulo) {
    let ti = "Mensagem";
    if (titulo) {
        ti = titulo;
    }
    let cor = "bg-info";
    switch (tipo) {
        case "sucesso":
            cor = "bg-success";
            break;
        case "erro":
            cor = "bg-danger";
            break;
        case "alerta":
            cor = "bg-warning";
            break;
        default:
            cor = "bg-info";
            break;
    }
    let wrapper = document.createElement('div');
    let toastId = `liveToast-${Date.now()}`; // Adicionando um ID único para cada toast
    wrapper.innerHTML = [
        `<div id="${toastId}" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-autohide="false">`, // Removemos o autohide padrão para controlar manualmente o fechamento
        `   <div class="toast-header">`,
        `       <strong class="me-auto">${ti}</strong>`,
        `       <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>`,
        `   </div>`,
        `   <div class="toast-body" id="msgToast"><strong>${mensagem}<strong></div>`,
        `   <div class="progress" style="height: 5px;">`, // Adicionamos a barra de progresso
        `       <div id="progressBar-${toastId}" class="progress-bar ${cor} progress-bar-striped progress-bar-animated" role="progressbar" style="width: 100%;"></div>`,
        `   </div>`,
        '</div>'
    ].join('');
    document.getElementById("toastId").append(wrapper);
    toasts.push({
        id: toastId,
        remainingTime: tempo_ms_toast
    });
    exibirToasts();
}

/// Função para exibir todos os toasts presentes na lista
function exibirToasts() {
    toasts.forEach((toastObj) => {
        const toastElement = document.getElementById(toastObj.id);
        const progressBar = toastElement.querySelector(`#progressBar-${toastObj.id}`);
        progressBar.classList.add('custom-progress-bar'); // Adicionando a classe personalizada à barra de progresso
        const toast = new bootstrap.Toast(toastElement);
        toast.show();
        let remainingTime = toastObj.remainingTime; // Tempo restante do toast
        let intervalId;
        let paused = false; // Variável para controlar o estado de pausa da contagem

        const updateProgressBar = () => {
            const percentage = (remainingTime / tempo_ms_toast) * 100;
            progressBar.style.width = `${percentage}%`;
        };
        const startCountdown = () => {
            clearInterval(intervalId);
            updateProgressBar();
            intervalId = setInterval(() => {
                if (!paused) {
                    remainingTime -= 100;
                    if (remainingTime <= 0) {
                        clearInterval(intervalId);
                        removerToast(toastObj.id); // Remover o toast da lista quando o tempo expirar
                        toast.hide(); // Fechar o toast após o tempo expirar
                    } else {
                        updateProgressBar();
                    }
                }
            }, 100);
        };
        const resetCountdown = () => {
            remainingTime = tempo_ms_toast;
            updateProgressBar();
        };
        const pauseCountdown = () => {
            paused = true;
        };
        const resumeCountdown = () => {
            paused = false;
        };
        toastElement.addEventListener('mouseenter', () => {
            resetCountdown();
            pauseCountdown(); // Pausar a contagem ao passar o mouse sobre o toast
        });
        toastElement.addEventListener('mouseleave', () => {
            resumeCountdown(); // Retomar a contagem ao tirar o mouse do toast
        });
        toastElement.querySelector('.btn-close').addEventListener('click', () => {
            removerToast(toastObj.id); // Remover o toast da lista quando o botão de fechar é clicado manualmente
            toast.hide(); // Fechar o toast manualmente ao clicar no botão de fechar
        });
        startCountdown(); // Iniciar a contagem do tempo e a barra de progresso

        // Adicionando um ouvinte para remover o toast da lista quando for fechado
        toastElement.addEventListener('hidden.bs.toast', () => {
            removerToast(toastObj.id);
            clearInterval(intervalId); // Limpa o intervalo quando o toast é fechado
        });
    });
}

function removerToast(toastId) {
    toasts = toasts.filter((toastObj) => toastObj.id !== toastId);
}

function checkFileSize(event, alertId) {
    const tamanhoMaximoString = event.target.getAttribute('data-tamanho-maximo');
    const tamanhoMaximo = parseInt(tamanhoMaximoString.slice(0, -2), 10); // Remove o "MB" e converte para número

    const files = event.target.files;
    for (const file of files) {
        const fileSize = file.size;
        if (fileSize > tamanhoMaximo * 1024 * 1024) {
            let titulo = "Anexo não permitido";
            let detalhe = `O arquivo ${file.name} excede o tamanho máximo de ${tamanhoMaximoString}.`;
            alertaUnico(titulo, detalhe, alertId);
            event.target.value = ''; // Limpa a seleção do arquivo
            break;
        }
    }
}


