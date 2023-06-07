function modalXL(){
    addClass('tamanhoModal','modal-xl');
    delClass('tamanhoModal','modal-lg');
}

function modalSM(){
    addClass('tamanhoModal','modal-sm');
    delClass('tamanhoModal','modal-lg');
}

function situacaoTabela(value) {
    if (value === 'A') {
        return '<span class="badge bg-success bg-gradient">Ativo</span>';
    } else {
        return '<span class="badge bg-secondary bg-gradient">Inativo</span>';
    }
}

function acoesTabela(uriModal, tituloModal, value) {
    let parametroId = '?id=';
    let uri = uriModal+parametroId+value;    
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