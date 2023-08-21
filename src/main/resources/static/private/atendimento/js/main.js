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
    fecharModal('#modal');
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

    /*var $select = $('#caspEntidadesList').selectize({
     maxItems: null,
     valueField: 'id',
     labelField: 'title',
     searchField: 'title'
     });*/

    /*document.querySelectorAll('.choicesmultiple').forEach(function (element) {
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
     });*/

    const selectElementsMultiple = document.querySelectorAll('.choicesmultiple');
    selectElementsMultiple.forEach(function (element) {
        // Verifique se o elemento já foi atualizado anteriormente
        if (!element.classList.contains("atualizado")) {
            new Choices(element, {
                removeItemButton: true,
                maxItemCount: 999,
                searchResultLimit: 5,
                renderChoiceLimit: 999
            });
            element.classList.add("atualizado");
        }
    });

    const selectElementsSingle = document.querySelectorAll('.choicessingle');
    selectElementsSingle.forEach(function (element) {
        // Verifique se o elemento já foi atualizado anteriormente
        if (!element.classList.contains("atualizado")) {
            new Choices(element, {
                removeItemButton: true,
                maxItemCount: 999,
                searchResultLimit: 5,
                renderChoiceLimit: 999
            });
            element.classList.add("atualizado");
        }
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

function removeInvalidClass() {
    if (this.checkValidity()) {
        this.classList.remove('is-invalid');
        this.classList.add('is-valid');
    } else {
        this.classList.remove('is-valid');
        this.classList.add('is-invalid');
    }
}

function invalidFeedback(objects) {
    objects.forEach(campo => {
        let id = '#' + campo.name;
        let feedback = document.querySelector(id + '-feedback');
        document.querySelector(id).classList.remove('is-valid');
        document.querySelector(id).classList.add('is-invalid');
        if (feedback) {
            document.querySelector(id + '-feedback').innerHTML = campo.userMessage;
        }
    });

    const inputElements = document.querySelectorAll('.form-control, .form-select');

    inputElements.forEach(inputElement => {
        inputElement.addEventListener('input', removeInvalidClass);
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

function alertaCampos(titulo, detalhe, idLocal) {
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

function alerta(mensagem, idLocal) {
    let wrapper = document.createElement('div');
    wrapper.innerHTML = [
        `<div class="text-start fixed-top" style="margin-top: 4rem">`,
        `<div class="row justify-content-md-center">`,
        `<div class="col-md-auto" onclick="this.style.display = 'none'">`,
        `   <div class="alert alert-danger alert-dismissible" role="alert">`,
        `       <div><strong>${mensagem}</strong></div>`,
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
    
    let toastId = `liveToast-${Date.now()}`; // Adicionando um ID único para cada toast
    let wrapper = document.createElement('div');
    wrapper.classList.add('toast');
    wrapper.setAttribute('role', 'alert');
    wrapper.setAttribute('aria-live', 'assertive');
    wrapper.setAttribute('aria-atomic', 'true');
    wrapper.setAttribute('data-bs-autohide', 'false');
    wrapper.setAttribute('id', toastId);
    wrapper.innerHTML = [
        `   <div class="toast-header">`,
        `       <strong class="me-auto">${mensagem}</strong>`,
        `       <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>`,
        `   </div>`,
        `   <div class="progress" style="height: 5px;">`, // Adicionamos a barra de progresso
        `       <div id="progressBar-${toastId}" class="progress-bar ${cor} custom-progress-bar progress-bar-striped" role="progressbar" style="width: 100%;"></div>`,
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

// Função para exibir todos os toasts presentes na lista
function exibirToasts() {
    let activeToastId; // Variável para armazenar o ID do toast ativo

    toasts.forEach((toastObj) => {
        const toastElement = document.getElementById(toastObj.id);
        const progressBar = toastElement.querySelector(`#progressBar-${toastObj.id}`);
        progressBar.classList.add('custom-progress-bar'); // Adicionar a classe custom-progress-bar
        const toast = new bootstrap.Toast(toastElement);
        const animationDuration = 5000; // Duração da animação em milissegundos

        const startAnimation = () => {
            progressBar.style.animation = 'none'; // Reiniciar a animação
            void progressBar.offsetWidth; // Forçar o recálculo do estilo (reflow)
            progressBar.style.animation = `toast-progress ${animationDuration}ms linear`; // Definir a duração da animação individualmente para cada toast
        };

        const resetAnimation = () => {
            progressBar.style.animation = 'none'; // Reiniciar a animação
            void progressBar.offsetWidth; // Forçar o recálculo do estilo (reflow)
            progressBar.style.animation = `toast-progress ${animationDuration}ms linear`; // Definir a duração da animação individualmente para cada toast
        };

        toastElement.addEventListener('shown.bs.toast', () => {
            if (activeToastId !== toastObj.id) {
                startAnimation(); // Iniciar a animação somente se o toast não estiver ativo
            }
        }, {once: true}); // Usar { once: true } para que o ouvinte seja removido após a primeira execução

        const toastMouseEnterHandler = () => {
            if (activeToastId === toastObj.id) {
                resetAnimation(); // Reiniciar a animação somente se o toast estiver ativo
            }
        };

        const toastMouseLeaveHandler = () => {
            if (activeToastId === toastObj.id) {
                progressBar.style.animationPlayState = 'paused'; // Pausar a animação ao passar o mouse sobre o toast
            }
        };

        toastElement.addEventListener('mouseenter', toastMouseEnterHandler);
        toastElement.addEventListener('mouseleave', toastMouseLeaveHandler);

        toastElement.addEventListener('hidden.bs.toast', () => {
            progressBar.style.animation = 'none'; // Parar a animação quando o toast for fechado manualmente
            toastElement.removeEventListener('mouseenter', toastMouseEnterHandler);
            toastElement.removeEventListener('mouseleave', toastMouseLeaveHandler);
        });

        toastElement.addEventListener('shown.bs.toast', () => {
            activeToastId = toastObj.id; // Definir o ID do toast como ativo quando for exibido
            progressBar.style.animationPlayState = 'running'; // Iniciar a animação da barra de progresso
            setTimeout(() => {
                progressBar.style.animationPlayState = 'paused'; // Pausar a animação após o tempo definido
            }, animationDuration);
        }, {once: true}); // Usar { once: true } para que o ouvinte seja removido após a primeira execução

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
            progressBar.style.animationPlayState = 'paused'; // Pausar a animação da barra de progresso
        };
        const resumeCountdown = () => {
            paused = false;
            progressBar.style.animationPlayState = 'running'; // Retomar a animação da barra de progresso
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
            toastElement.remove();
        });
    });
}

/// Função para exibir todos os toasts presentes na lista
function exibirToastsOK() {
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




var tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
var tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));