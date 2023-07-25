function getUrl() {
    return 'http://localhost:8081';
}

//request
var contextPath = '/atendimento/';
var usuariosPath = contextPath + 'usuarios/';
var chamadosPatch = contextPath + 'chamados/';
var areasPath = contextPath + 'areas/';
var sistemasPath = contextPath + 'sistemas/';
var problemasPath = contextPath + 'problemas/';
var niveisPath = contextPath + 'niveis/';
var origensPath = contextPath + 'origens/';
var tiposArquivosPath = contextPath + 'tipos-arquivos/';
var tiposEntidadesPath = contextPath + 'tipos-entidades/';
var entidadesPath = contextPath + 'entidades/';
var clientesPath = contextPath + 'clientes/';

//requestFront
var MV_A = '/mv/atendimento/';
var MV_A_CHAMADO = MV_A + 'chamado';
var MV_A_EDICAO_CHAMADO = MV_A + 'edicaoChamado';
var MV_A_CLIENTE = MV_A + 'cliente';
var MV_A_USUARIO = MV_A + 'usuario';
var MV_A_SISTEMA = MV_A + 'sistema';
var MV_A_AREA = MV_A + 'area';
var MV_A_PROBLEMA = MV_A + 'problema';
var MV_A_NIVEL = MV_A + 'nivel';
var MV_A_ORIGEM = MV_A + 'origem';
var MV_A_TIPOARQUIVO = MV_A + 'tipoArquivo';
var MV_A_TIPOENTIDADE = MV_A + 'tipoEntidade';
var MV_A_ENTIDADE = MV_A + 'entidade';

//requestFrontGestao
var MV_A_SELECIONAR_USUARIO = MV_A + 'selecionar-usuario';
var MV_A_MODAL_SISTEMA = MV_A + 'conteudoModalSistemas';
var MV_A_MODAL_AREA = MV_A + 'conteudoModalArea';
var MV_A_MODAL_PROBLEMA = MV_A + 'conteudoModalProblemas';
var MV_A_MODAL_NIVEL = MV_A + 'conteudoModalNiveis';
var MV_A_MODAL_ORIGEM = MV_A + 'conteudoModalOrigens';
var MV_A_MODAL_TIPOARQUIVO = MV_A + 'conteudoModalTiposArquivos';
var MV_A_MODAL_TIPOENTIDADE = MV_A + 'conteudoModalTiposEntidades';
var MV_A_MODAL_ENTIDADE = MV_A + 'conteudoModalEntidades';
var MV_A_MODAL_USUARIO_ABERTURA = MV_A + 'conteudoModalUsuarios?abertura=true';
var MV_A_MODAL_CLIENTE_ABERTURA = MV_A + 'conteudoModalClientes?abertura=true';
var MV_A_MODAL_USUARIO = MV_A + 'conteudoModalUsuarios';
var MV_A_MODAL_CLIENTE = MV_A + 'conteudoModalClientes';
var MV_A_MODAL_TROCAR_SENHA = MV_A + 'trocarSenha';