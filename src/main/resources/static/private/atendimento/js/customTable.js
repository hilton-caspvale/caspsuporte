function modalXL() {
    addClass('tamanhoModal', 'modal-xl');
    delClass('tamanhoModal', 'modal-lg');
}

function modalSM() {
    addClass('tamanhoModal', 'modal-sm');
    delClass('tamanhoModal', 'modal-lg');
}

function textoNormalTabela(value) {
    return value;//'<p style="font-size: 12px">'+value+'</p>';
}

function spanTabela(value) {
    return '<span class="badge text-dark">' + value + '</span>';
}

function situacaoTabela(value) {
    if (value === 'A') {
        return '<span class="badge bg-success bg-gradient">Ativo</span>';
    } else {
        return '<span class="badge bg-secondary bg-gradient">Inativo</span>';
    }
}

function situacaoChamado(value) {
    switch (value.situacao) {
        case 'A':
            return '<span class="badge bg-warning bg-gradient text-dark text-wrap">' + value.descricaoSituacao + '</span>';
        case 'R':
            return '<span class="badge bg-primary bg-gradient text-dark text-wrap">' + value.descricaoSituacao + '</span>';
        case 'E':
            return '<span class="badge bg-secondary bg-gradient text-dark text-wrap">' + value.descricaoSituacao + '</span>';
        case 'C':
            return '<span class="badge bg-danger bg-gradient text-dark text-wrap">' + value.descricaoSituacao + '</span>';
        default:
            return '<span class="badge bg-secondary bg-gradient text-dark text-wrap">' + value.descricaoSituacao + '</span>';
    }
    if (value === 'A') {

    } else {
        return '<span class="badge bg-secondary bg-gradient">Inativo</span>';
    }
}

function acoesTabelaChamado(value) {
    return '<div class="btn-group btn-group-sm" role="group"><a class="btn btn-outline-success" role="button"title="Abrir" data-id=' + value + ' onclick="visualizarChamado(' + value + ')">' + value + '</a></div>';
}

function botaoEditarItemTabela(dataId, onClick) {
    return `<div class="btn-group btn-group-sm" role="group"><a role="button" class="btn btn-outline-secondary" ${dataId} ${onClick}><i class="fas fa-pencil-alt"></i></a></div>`;
}

function botaoAddTabela(evento) {
    return {
        btnAdd: {
            text: 'Novo',
            icon: 'bi bi-plus-lg',
            event: evento
        }
    };
}

function bgArea(descricaoArea) {
    let classe_cor = "cor-g";
    switch (descricaoArea) {
        case 'CONTABILIDADE':
            classe_cor = "cor-contabil";
            break;
        case "ADMINISTRAÇÃO E GESTÃO":
            classe_cor = "cor-compras";
            break;
        case 'ARRECADAÇÃO E FISCALIZAÇÃO':
            classe_cor = "cor-tributos";
            break;
        case 'RECURSOS HUMANOS':
            classe_cor = "cor-rh";
            break;
        case 'FERRAMENTAS':
            classe_cor = "cor-betha text-dark";
            break;
        case 'SUPORTE':
            classe_cor = "cor-suporte text-dark";
            break;
        default:
            classe_cor = "cor-g text-dark";
            break;
    }
    return classe_cor;
}

function entidades(value) {
    let saida = [];
    let classe_cor = "cor-g";
    JSON.parse(JSON.stringify(value)).forEach(function (el) {
        saida.push('<span class="badge ' + classe_cor + ' text-dark" style="padding: 7px;margin-right: 3px;margin-top: 3px;">' + el.nomeEntidade + '</span>');
    });
    return saida;
}

function areas(value) {
    let saida = [];
    JSON.parse(JSON.stringify(value)).forEach(function (el) {
        saida.push('<span class="badge ' + bgArea(el.descricaoArea) + ' " style="padding: 7px;margin-right: 3px;margin-top: 3px;">' + el.descricaoArea + '</span>');
    });
    return saida;
}

function acoesTabelaSistemas(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalSistemas(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTabelaSistemas() {
    const evento = function () {
        modalSistemas();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaAreas(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalAreas(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTabelaAreas() {
    const evento = function () {
        modalAreas();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaProblemas(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalProblemas(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTabelaProblemas() {
    const evento = function () {
        modalProblemas();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaNiveis(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalNiveis(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTabelaNiveis() {
    const evento = function () {
        modalNiveis();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaOrigens(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalOrigens(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTabelaOrigens() {
    const evento = function () {
        modalOrigens();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaTiposArquivos(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalTipoArquivos(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTiposArquivos() {
    const evento = function () {
        modalTipoArquivos();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaTiposEntidades(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalTipoEntidades(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddTiposEntidades() {
    const evento = function () {
        modalTipoEntidades();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaEntidades(value) {
    let dataId = ` data-id=${value} `;
    let onClick = ` onclick="modalEntidades(${value})" `;
    return botaoEditarItemTabela(dataId, onClick);
}

function botaoAddEntidades() {
    const evento = function () {
        modalEntidades();
    };
    return botaoAddTabela(evento);
}

function acoesTabelaClientes(value) {
    let botaoSenha = '<a role="button" class="btn btn-outline-secondary" data-id=' + value + ' onclick="modalTrocarSenha(' + value + ')"><i class="bi bi-key"></i></a>';
    return '<div class="btn-group btn-group-sm" role="group"><a role="button" class="btn btn-outline-secondary" data-id=' + value + ' onclick="modalClientes(' + value + ')"><i class="fas fa-pencil-alt"></i></a>' + botaoSenha + '</div>';
}

function botaoAddTabelaClientes() {
    const evento = function () {
        modalClientes();
    };
    return botaoAddTabela(evento);
}


function acoesTabelaUsuarios(value) {
    let uriModal = '/mv/atendimento/conteudoModalUsuarios';
    let tituloModal = 'Editar Usuario';
    return acoesTabela(uriModal, tituloModal, value);
}

//

function acoesTabelaAdminUsuarios(value) {
    let uriModalCadastro = '/mv/atendimento/conteudoModalUsuarios?id=' + value;
    let uriModalSenha = '/mv/atendimento/trocarSenha?id=' + value;
    let tituloModalCadastro = 'Editar Usuario';
    let tituloModalSenha = 'Alterar Senha';
    let botaoEditar = botaoEditarUsuario(value, uriModalCadastro, tituloModalCadastro);
    let botaoAlteraSenha = botaoAlterarSenha(value, uriModalSenha, tituloModalSenha);
    return '<div class="btn-group btn-group-sm" role="group">' + botaoEditar + botaoAlteraSenha + '</div>';
}

function botaoEditarUsuario(value, uriModalCadastro, tituloModalCadastro) {
    return '<a role="button" class="btn btn-outline-secondary" data-id=' + value + ' onclick="carregaHtml(' + "'" + uriModalCadastro + '' + "'" + ', ' + "'" + 'modalBody' + "'" + ');mostraModal(' + "'" + '#modal' + "'" + ');$(' + "'" + '#staticBackdropLabel' + "'" + ').html(' + "'" + tituloModalCadastro + "'" + ')"><i class="fas fa-pencil-alt"></i></a>';
}

function botaoAlterarSenha(value, uriModalSenha, tituloModalSenha) {
    return '<a role="button" class="btn btn-outline-secondary" data-id=' + value + ' onclick="carregaHtml(' + "'" + uriModalSenha + '' + "'" + ', ' + "'" + 'modalBody' + "'" + ');mostraModal(' + "'" + '#modal' + "'" + ');$(' + "'" + '#staticBackdropLabel' + "'" + ').html(' + "'" + tituloModalSenha + "'" + ')"><i class="bi bi-key"></i></a>';
}
//

function botaoAddTabelaUsuarios() {
    let uriModal = '/mv/atendimento/conteudoModalUsuarios';
    let tituloModal = 'Adicionar Usuario';
    return botaoAddTabela(uriModal, tituloModal);
}

function pesquisarUsuarios(data, text) {
    return data.filter(function (row) {
        if (row.emailUsuario.toLowerCase().indexOf(text.toLowerCase()) > -1) {
            return row.emailUsuario.toLowerCase().indexOf(text.toLowerCase()) > -1;
        }
        if (row.nomeUsuario.toLowerCase().indexOf(text.toLowerCase()) > -1) {
            return row.nomeUsuario.toLowerCase().indexOf(text.toLowerCase()) > -1;
        }
        if (row.nlogin.toLowerCase().indexOf(text.toLowerCase()) > -1) {
            return row.nlogin.toLowerCase().indexOf(text.toLowerCase()) > -1;
        }
        if (row.situacaoUsuario.toLowerCase().indexOf(text.toLowerCase()) > -1) {
            return row.situacaoUsuario.toLowerCase().indexOf(text.toLowerCase()) > -1;
        }
        let el = row.caspEntidadesList;
        for (let i in el) {
            if (el.hasOwnProperty(i)) {
                if (el[i].nomeEntidade.toLowerCase().indexOf(text.toLowerCase()) > -1) {
                    return true;
                }
            }
        }
        let area = row.caspAreasList;
        for (let i in area) {
            if (area.hasOwnProperty(i)) {
                if (area[i].descricaoArea.toLowerCase().indexOf(text.toLowerCase()) > -1) {
                    return true;
                }
            }
        }
    });
}

function queryParamsUsuarios(params) {
    let entidade = document.getElementById("ientidade");
    let area = document.getElementById("iarea");
    let sistema = document.getElementById("isistema");
    let exibir = document.getElementById("exibir");
    params.exibir = exibir.options[exibir.selectedIndex].value;
    params.entidades = entidade.options[entidade.selectedIndex].value;
    params.areas = area.options[area.selectedIndex].value;
    params.sistemas = sistema.options[sistema.selectedIndex].value;
    return params;
}