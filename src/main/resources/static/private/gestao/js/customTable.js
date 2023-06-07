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
    let url = "'/mv/atendimento/chamado/"+value+"','root'";
    return '<div class="btn-group btn-group-sm" role="group"><a class="btn btn-outline-success" role="button"title="Abrir" data-id=' + value + ' onclick="carregaHtml(' + url +')">' + value + '</a></div>';
}

function acoesTabela(uriModal, tituloModal, value) {
    let parametroId = '?id=';
    let uri = uriModal + parametroId + value;
    return '<div class="btn-group btn-group-sm" role="group"><a role="button" class="btn btn-outline-secondary" data-id=' + value + ' onclick="carregaHtml(' + "'" + uri + '' + "'" + ', ' + "'" + 'modalBody' + "'" + ');mostraModal(' + "'" + '#modal' + "'" + ');$(' + "'" + '#staticBackdropLabel' + "'" + ').html(' + "'" + tituloModal + "'" + ')"><i class="fas fa-pencil-alt"></i></a></div>';
}

function botaoAddTabela(uriModal, tituloModal) {
    return {
        btnAdd: {
            text: 'Novo',
            icon: 'bi bi-plus-lg',
            event: function () {
                carregaHtml(uriModal, 'modalBody');
                mostraModal('#modal');
                $('#staticBackdropLabel').html(tituloModal);
            }
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
    //alert("value["+value+"]");
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
    let uriModal = '/mv/atendimento/conteudoModalSistemas';
    let tituloModal = 'Editar Sistema';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTabelaSistemas() {
    let uriModal = '/mv/atendimento/conteudoModalSistemas';
    let tituloModal = 'Adicionar Sistema';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaProblemas(value) {
    let uriModal = '/mv/atendimento/conteudoModalProblemas';
    let tituloModal = 'Editar Problema';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTabelaProblemas() {
    let uriModal = '/mv/atendimento/conteudoModalProblemas';
    let tituloModal = 'Adicionar Problema';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaAreas(value) {
    let uriModal = '/mv/atendimento/conteudoModalAreas';
    let tituloModal = 'Editar Área';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTabelaAreas() {
    let uriModal = '/mv/atendimento/conteudoModalAreas';
    let tituloModal = 'Adicionar Área';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaNiveis(value) {
    let uriModal = '/mv/atendimento/conteudoModalNiveis';
    let tituloModal = 'Editar Nível';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTabelaNiveis() {
    let uriModal = '/mv/atendimento/conteudoModalNiveis';
    let tituloModal = 'Adicionar Nível';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaOrigens(value) {
    let uriModal = '/mv/atendimento/conteudoModalOrigens';
    let tituloModal = 'Editar Origem';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTabelaOrigens() {
    let uriModal = '/mv/atendimento/conteudoModalOrigens';
    let tituloModal = 'Adicionar Origem';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaTiposArquivos(value) {
    let uriModal = '/mv/atendimento/conteudoModalTiposArquivos';
    let tituloModal = 'Editar tipo de arquivo';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTiposArquivos() {
    let uriModal = '/mv/atendimento/conteudoModalTiposArquivos';
    let tituloModal = 'Adicionar tipo de arquivo';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaTiposEntidades(value) {
    let uriModal = '/mv/atendimento/conteudoModalTiposEntidades';
    let tituloModal = 'Editar tipo da entidade';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddTiposEntidades() {
    let uriModal = '/mv/atendimento/conteudoModalTiposEntidades';
    let tituloModal = 'Adicionar tipo da entidade';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaEntidades(value) {
    let uriModal = '/mv/atendimento/conteudoModalEntidades';
    let tituloModal = 'Editar Entidade';
    return acoesTabela(uriModal, tituloModal, value);
}

function botaoAddEntidades() {
    let uriModal = '/mv/atendimento/conteudoModalEntidades';
    let tituloModal = 'Adicionar Entidade';
    return botaoAddTabela(uriModal, tituloModal);
}

function acoesTabelaClientes(value) {
    let uriModalCadastro = '/mv/atendimento/conteudoModalClientes?id=' + value;
    let uriModalSenha = '/mv/atendimento/trocarSenha?id=' + value;
    let tituloModalCadastro = 'Editar Usuario';
    let tituloModalSenha = 'Alterar Senha';
    let botaoEditar = botaoEditarUsuario(value, uriModalCadastro, tituloModalCadastro);
    let botaoAlteraSenha = botaoAlterarSenha(value, uriModalSenha, tituloModalSenha);
    return '<div class="btn-group btn-group-sm" role="group">' + botaoEditar + botaoAlteraSenha + '</div>';
}

function botaoAddTabelaClientes() {
    let uriModal = '/mv/atendimento/conteudoModalClientes';
    let tituloModal = 'Adicionar Cliente';
    return botaoAddTabela(uriModal, tituloModal);
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

function atualizarTabela(idtabela) {
    let $table = $('#' + idtabela);
    $table.bootstrapTable('refresh');
}