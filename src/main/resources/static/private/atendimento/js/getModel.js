function getArea() {
    let listaSistemas = document.getElementById('caspSistemasList');
    var sistemas = [];
    for (i = 0; i < listaSistemas.length; i = i + 1) {
        let sistema = {isistema: listaSistemas.options[i].value, descricaoSistema: listaSistemas.options[i].text};
        sistemas.push(sistema);
    }
    let area = {
        iarea: document.getElementById('iarea').value,
        descricaoArea: document.getElementById('descricaoArea').value,
        emailArea: document.getElementById('emailArea').value,
        situacaoArea: document.getElementById('situacaoArea').value,
        caspSistemasList: sistemas
    };
    let dto = JSON.stringify(area);
    return dto;
}

function getSistema() {
    let listaProblemas = document.getElementById('caspProblemasList');
    let problemas = [];
    for (i = 0; i < listaProblemas.length; i = i + 1) {
        let problema = {iproblema: listaProblemas.options[i].value, descricaoProblema: listaProblemas.options[i].text};
        problemas.push(problema);
    }
    let listaAreas = document.getElementById('caspAreasList');
    let areas = [];
    for (i = 0; i < listaAreas.length; i = i + 1) {
        let area = {iarea: listaAreas.options[i].value, descricaoArea: listaAreas.options[i].text};
        areas.push(area);
    }
    let sistema = {
        isistema: document.getElementById('isistema').value,
        descricaoSistema: document.getElementById('descricaoSistema').value,
        situacaoSistema: document.getElementById('situacaoSistema').value,
        caspProblemasList: problemas,
        caspAreasList: areas
    };
    let dto = JSON.stringify(sistema);
    return dto;
}

function getProblema() {
    let listaSistemas = document.getElementById('caspSistemasList');
    var sistemas = [];
    for (i = 0; i < listaSistemas.length; i = i + 1) {
        let sistema = {isistema: listaSistemas.options[i].value, descricaoSistema: listaSistemas.options[i].text};
        sistemas.push(sistema);
    }
    let problema = {
        iproblema: document.getElementById('iproblema').value,
        descricaoProblema: document.getElementById('descricaoProblema').value,
        situacaoProblema: document.getElementById('situacaoProblema').value,
        caspSistemasList: sistemas
    };
    let dto = JSON.stringify(problema);
    return dto;
}

function getNivel() {
    let nivel = {
        inivel: document.getElementById('inivel').value,
        descricaoNivel: document.getElementById('descricaoNivel').value,
        situacaoNivel: document.getElementById('situacaoNivel').value
    };
    let dto = JSON.stringify(nivel);
    return dto;
}

function getOrigem() {
    let nivel = {
        iorigemChamado: document.getElementById('iorigemChamado').value,
        descricaoOrigem: document.getElementById('descricaoOrigem').value,
        situacaoOrigem: document.getElementById('situacaoOrigem').value
    };
    let dto = JSON.stringify(nivel);
    return dto;
}

function getTipoArquivo() {
    let nivel = {
        itipoArquivo: document.getElementById('itipoArquivo').value,
        descricaoTipoArquivo: document.getElementById('descricaoTipoArquivo').value,
        situacaoTipoArquivo: document.getElementById('situacaoTipoArquivo').value
    };
    let dto = JSON.stringify(nivel);
    return dto;
}

function getTipoEntidade() {
    let nivel = {
        itiposEntidade: document.getElementById('itiposentidade').value,
        tipoDescricao: document.getElementById('tipoDescricao').value,
        situacaoTipoEntidade: document.getElementById('situacaoTipoEntidade').value
    };
    let dto = JSON.stringify(nivel);
    return dto;
}

function getEntidade() {
    let listaTipos = document.getElementById('iTipoEntidade');
    let tipoSelecionado = listaTipos.options[listaTipos.selectedIndex].value;
    let tipo = {itiposEntidade: tipoSelecionado};
    let entidade = {
        ientidade: document.getElementById('ientidade').value,
        nomeEntidade: document.getElementById('nomeEntidade').value,
        situacaoEntidade: document.getElementById('situacaoEntidade').value,
        cnpjEntidade: document.getElementById('cnpjEntidade').value,
        telefoneEntidade: document.getElementById('telefoneEntidade').value,
        emailEntidade: document.getElementById('emailEntidade').value,
        enderecoEntidade: document.getElementById('enderecoEntidade').value,
        dadosServidor: document.getElementById('dadosServidor').value,
        comentarioEntidade: document.getElementById('comentarioEntidade').value,
        itipoEntidade: tipo
    };
    let dto = JSON.stringify(entidade);
    return dto;
}

function getCliente(formularioNome) {
    let form = document.querySelector(formularioNome);
    let listaEntidades = form.querySelector('#caspEntidadesList');
    let entidades = [];
    for (i = 0; i < listaEntidades.length; i = i + 1) {
        let entidade = {ientidade: listaEntidades.options[i].value,
            nomeEntidade: listaEntidades.options[i].text};
        entidades.push(entidade);
    }
    let listaAreas = form.querySelector('#caspAreasList');
    let areas = [];
    for (i = 0; i < listaAreas.length; i = i + 1) {
        let area = {iarea: listaAreas.options[i].value,
            descricaoArea: listaAreas.options[i].text};
        areas.push(area);
    }
    let listaSistemas = form.querySelector('#caspSistemasList');
    let sistemas = [];
    for (i = 0; i < listaSistemas.length; i = i + 1) {
        let sistema = {isistema: listaSistemas.options[i].value,
            descricaoSistema: listaSistemas.options[i].text};
        sistemas.push(sistema);
    }
    let usuario = {
        iusuario: form.querySelector('#iusuario').value,
        nlogin: form.querySelector('#nlogin').value,
        nomeUsuario: form.querySelector('#nomeUsuario').value,
        emailUsuario: form.querySelector('#emailUsuario').value,
        contatoUsuario: form.querySelector('#contatoUsuario').value,
        situacaoUsuario: form.querySelector('#situacaoUsuario').value,
        caspEntidadesList: entidades,
        caspAreasList: areas,
        caspSistemasList: sistemas
    };
    let dto = JSON.stringify(usuario);
    return dto;
}

function getUsuario(formularioNome) {
    let form = document.querySelector(formularioNome);
    let listaEntidades = form.querySelector('#caspEntidadesList');
    let entidades = [];
    for (i = 0; i < listaEntidades.length; i = i + 1) {
        let entidade = {ientidade: listaEntidades.options[i].value,
            nomeEntidade: listaEntidades.options[i].text};
        entidades.push(entidade);
    }
    let listaAreas = form.querySelector('#caspAreasList');
    let areas = [];
    for (i = 0; i < listaAreas.length; i = i + 1) {
        let area = {iarea: listaAreas.options[i].value,
            descricaoArea: listaAreas.options[i].text};
        areas.push(area);
    }
    let listaSistemas = form.querySelector('#caspSistemasList');
    let sistemas = [];
    for (i = 0; i < listaSistemas.length; i = i + 1) {
        let sistema = {isistema: listaSistemas.options[i].value,
            descricaoSistema: listaSistemas.options[i].text};
        sistemas.push(sistema);
    }
    let tipoUsuario = {
        itipoUsuario: form.querySelector('#itipoUsuario').value
    };
    let usuario = {
        iusuario: form.querySelector('#iusuario').value,
        nlogin: form.querySelector('#nlogin').value,
        nomeUsuario: form.querySelector('#nomeUsuario').value,
        emailUsuario: form.querySelector('#emailUsuario').value,
        contatoUsuario: form.querySelector('#contatoUsuario').value,
        situacaoUsuario: form.querySelector('#situacaoUsuario').value,
        itipoUsuario: tipoUsuario,
        caspEntidadesList: entidades,
        caspAreasList: areas,
        caspSistemasList: sistemas
    };
    let dto = JSON.stringify(usuario);
    return dto;
}
