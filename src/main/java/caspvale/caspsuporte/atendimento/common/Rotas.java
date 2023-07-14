
package caspvale.caspsuporte.atendimento.common;

import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Hilton
 */
@Configuration
public class Rotas {

    private final Permissoes permissoes;

    public Rotas(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public String CADASTRAR_TIPO_ENTIDADE = "redirect:/index";
    public String EDITAR_TIPO_ENTIDADE = "redirect:/index";
    public String CADASTRAR_TIPO_ARQUIVO = "redirect:/index";
    public String EDITAR_TIPO_ARQUIVO = "redirect:/index";
    public String CADASTRAR_SISTEMA = "redirect:/index";
    public String EDITAR_SISTEMA = "redirect:/index";
    public String CADASTRAR_PROBLEMA = "redirect:/index";
    public String EDITAR_PROBLEMA = "redirect:/index";
    public String CADASTRAR_ORIGEM = "redirect:/index";
    public String EDITAR_ORIGEM = "redirect:/index";
    public String CADASTRAR_NIVEL = "redirect:/index";
    public String EDITAR_NIVEL = "redirect:/index";
    public String CADASTRAR_ENTIDADE = "redirect:/index";
    public String EDITAR_ENTIDADE = "redirect:/index";
    public String CADASTRAR_AREA = "redirect:/index";
    public String EDITAR_AREA = "redirect:/index";
    public String MENU_ATENDIMENTO = "redirect:/index";
    public String CADASTRAR_CLIENTE = "redirect:/index";
    public String CADASTRAR_CLIENTE_ABERTURA = "redirect:/index";
    public String EDITAR_CLIENTE = "redirect:/index";
    public String CADASTRAR_USUARIO = "redirect:/index";
    public String EDITAR_USUARIO = "redirect:/index";
    public String EDITAR_USUARIO_ABERTURA = "redirect:/index";
    public String TROCAR_SENHA = "redirect:/index";
    public String CADASTRAR_CHAMADO = "redirect:/index";
    public String EDITAR_CHAMADO = "redirect:/index";
    public String PAGINA_CHAMADO = "redirect:/index";
    public String PAGINA_EDICAO_CHAMADO = "redirect:/index";
    public String MODAL_SELECIONAR_USUARIO = "redirect:/index";
    public String SELECIONAR_USUARIO = "redirect:/index";
    public String VER_CHAMADO = "redirect:/index";

    private void rotasAnalista() {
        TROCAR_SENHA = "/atendimento/components/admin/trocarSenha";
        CADASTRAR_CLIENTE = "/atendimento/components/cadastrarCliente";
        CADASTRAR_CLIENTE_ABERTURA = "/atendimento/components/cadastrarClienteAbertura";
        EDITAR_CLIENTE = "/atendimento/components/editarCliente";
        EDITAR_USUARIO_ABERTURA = "/atendimento/components/editarUsuarioAbertura";
        CADASTRAR_TIPO_ENTIDADE = "/atendimento/components/cadastrarTipoEntidade";
        EDITAR_TIPO_ENTIDADE = "/atendimento/components/editarTipoEntidade";
        CADASTRAR_TIPO_ARQUIVO = "/atendimento/components/cadastrarTipoArquivo";
        EDITAR_TIPO_ARQUIVO = "/atendimento/components/editarTipoArquivo";
        CADASTRAR_SISTEMA = "/atendimento/components/cadastrarSistema";
        EDITAR_SISTEMA = "/atendimento/components/editarSistema";
        CADASTRAR_PROBLEMA = "/atendimento/components/cadastrarProblema";
        EDITAR_PROBLEMA = "/atendimento/components/editarProblema";
        CADASTRAR_ORIGEM = "/atendimento/components/cadastrarOrigem";
        EDITAR_ORIGEM = "/atendimento/components/editarOrigem";
        CADASTRAR_ENTIDADE = "/atendimento/components/cadastrarEntidade";
        EDITAR_ENTIDADE = "/atendimento/components/editarEntidade";
        MENU_ATENDIMENTO = "/atendimento/components/analista/menuAtendimento";
        CADASTRAR_CHAMADO = "/atendimento/components/cadastrarChamado";
        EDITAR_CHAMADO = "/atendimento/components/editarChamado";
        MODAL_SELECIONAR_USUARIO = "/atendimento/components/selecionarUsuario";
        SELECIONAR_USUARIO = "/atendimento/components/selectUsuario";
        PAGINA_CHAMADO = "/atendimento/pageChamado";
        PAGINA_EDICAO_CHAMADO = "/atendimento/pageEdicaoChamado";
        VER_CHAMADO = "/atendimento/components/verChamado";
    }

    private void rotasAdministrador() {
        TROCAR_SENHA = "/atendimento/components/admin/trocarSenha";
        CADASTRAR_CLIENTE = "/atendimento/components/cadastrarCliente";
        CADASTRAR_CLIENTE_ABERTURA = "/atendimento/components/cadastrarClienteAbertura";
        EDITAR_CLIENTE = "/atendimento/components/editarCliente";
        CADASTRAR_USUARIO = "/atendimento/components/admin/cadastrarUsuario";
        EDITAR_USUARIO = "/atendimento/components/admin/editarUsuario";
        EDITAR_USUARIO_ABERTURA = "/atendimento/components/editarUsuarioAbertura";
        CADASTRAR_TIPO_ENTIDADE = "/atendimento/components/cadastrarTipoEntidade";
        EDITAR_TIPO_ENTIDADE = "/atendimento/components/editarTipoEntidade";
        CADASTRAR_TIPO_ARQUIVO = "/atendimento/components/cadastrarTipoArquivo";
        EDITAR_TIPO_ARQUIVO = "/atendimento/components/editarTipoArquivo";
        CADASTRAR_SISTEMA = "/atendimento/components/cadastrarSistema";
        EDITAR_SISTEMA = "/atendimento/components/editarSistema";
        CADASTRAR_PROBLEMA = "/atendimento/components/cadastrarProblema";
        EDITAR_PROBLEMA = "/atendimento/components/editarProblema";
        CADASTRAR_ORIGEM = "/atendimento/components/cadastrarOrigem";
        EDITAR_ORIGEM = "/atendimento/components/editarOrigem";
        CADASTRAR_NIVEL = "/atendimento/components/admin/cadastrarNivel";
        EDITAR_NIVEL = "/atendimento/components/admin/editarNivel";
        CADASTRAR_ENTIDADE = "/atendimento/components/cadastrarEntidade";
        EDITAR_ENTIDADE = "/atendimento/components/editarEntidade";
        CADASTRAR_AREA = "/atendimento/components/admin/cadastrarArea";
        EDITAR_AREA = "/atendimento/components/admin/editarArea";
        MENU_ATENDIMENTO = "/atendimento/components/admin/menuAtendimento";
        CADASTRAR_CHAMADO = "/atendimento/components/cadastrarChamado";
        EDITAR_CHAMADO = "/atendimento/components/editarChamado";
        MODAL_SELECIONAR_USUARIO = "/atendimento/components/selecionarUsuario";
        SELECIONAR_USUARIO = "/atendimento/components/selectUsuario";
        PAGINA_CHAMADO = "/atendimento/pageChamado";
        PAGINA_EDICAO_CHAMADO = "/atendimento/pageEdicaoChamado";
        VER_CHAMADO = "/atendimento/components/verChamado";
    }

    private void rotasCliente() {
        TROCAR_SENHA = "/atendimento/components/trocarSenha";
        MENU_ATENDIMENTO = "/atendimento/components/cliente/menuAtendimento";
        CADASTRAR_CHAMADO = "/atendimento/components/cadastrarChamado";
        EDITAR_CHAMADO = "/atendimento/components/editarChamado";
        PAGINA_CHAMADO = "/atendimento/pageChamado";
        PAGINA_EDICAO_CHAMADO = "/atendimento/pageEdicaoChamado";
        VER_CHAMADO = "/atendimento/components/verChamado";
    }

    public void viewsDaRota() {
        switch (permissoes.role()) {
            case "ANALISTA":
                rotasAnalista();
                break;
            case "ADMINISTRADOR":
                rotasAdministrador();
                break;
            case "CLIENTE":
                rotasCliente();
                break;
            default:
                break;
        }
    }

    public String login() {
        return permissoes.login();
    }

    public String role() {
        return permissoes.role();
    }

    public boolean roleCLIENTE() {
        return role().equals("CLIENTE");
    }

    public boolean roleANALISTA() {
        return role().equals("ANALISTA");
    }

    public boolean roleADMIN() {
        return role().equals("ADMINISTRADOR");
    }

}
