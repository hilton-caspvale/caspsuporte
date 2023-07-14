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
    let url = "'/mv/atendimento/chamado/" + value + "','root'";
    return '<div class="btn-group btn-group-sm" role="group"><a class="btn btn-outline-success" role="button"title="Abrir" data-id=' + value + ' onclick="carregaHtml(' + url + ')">' + value + '</a></div>';
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

function botaoAddTabelaChamado() {
    chamadoUsuarioLogado();
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

function atualizarTabela(idtabela) {
    let $table = $('#' + idtabela);
    $table.bootstrapTable('refresh');
}