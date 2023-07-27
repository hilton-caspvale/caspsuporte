
package caspvale.caspsuporte.modulos.atendimento.api.controller;

import caspvale.caspsuporte.modulos.atendimento.api.assembler.UsuariosClientesAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.model.UsuariosClientesModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.service.TiposUsuariosService;
import caspvale.caspsuporte.modulos.atendimento.domain.service.UsuariosClientesService;
import caspvale.caspsuporte.modulos.atendimento.common.Rotas;
import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/clientes")
public class UsuariosClientesController {      

    private final UsuariosClientesAssembler clientesAssembler;
    private final UsuariosClientesService clienteService;
    private final Permissoes permissoes;

    public UsuariosClientesController(UsuariosClientesService clienteService, UsuariosClientesAssembler clientesAssembler, Permissoes permissoes) {
        this.clienteService = clienteService;
        this.clientesAssembler = clientesAssembler;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listarClientes(@RequestParam(value = "entidades", required = false, defaultValue = "0") int entidades,
            @RequestParam(value = "areas", required = false, defaultValue = "0") int areas,
            @RequestParam(value = "sistemas", required = false, defaultValue = "0") int sistemas,
            @RequestParam(value = "exibir", required = false, defaultValue = "false") String exibir) {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(clientesAssembler.toCollectionModel(clienteService.clientesPorFiltros(entidades, areas, sistemas, exibir)));
    }

    @GetMapping("/{iUsuario}")
    public ResponseEntity<?> usuarioCliente(@PathVariable @Valid Integer iUsuario) {
         permissoes.restritoAoCliente();
        return ResponseEntity.ok(clientesAssembler.toModel(clienteService.buscarOuFalhar(iUsuario)));
    }

    @PostMapping
    public ResponseEntity<?> adicionarCliente(@Valid @RequestBody UsuariosClientesModel usuariosClientesModel) {
         permissoes.restritoAoCliente();
        if (usuariosClientesModel.getIUsuario() != null) {
            throw new NegocioException("O código do usuário não pode ser informado para novos cadastros!");
        }
        CaspUsuarios usuarioNovo = clienteService.novoCliente(clientesAssembler.toEntity(usuariosClientesModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesAssembler.toModel(usuarioNovo));
    }

    @PutMapping("/{iUsuario}")
    public ResponseEntity editarCliente(@Valid @RequestBody UsuariosClientesModel usuariosClientesModel, @PathVariable Integer iUsuario) {
         permissoes.restritoAoCliente();
        CaspUsuarios caspUsuariosAtual = clienteService.buscarOuFalhar(iUsuario);
        if (usuariosClientesModel.getIUsuario() == null || caspUsuariosAtual.getIUsuario() != iUsuario) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspUsuarios usuarioParaEditar = clientesAssembler.toEntity(usuariosClientesModel); 
        CaspUsuarios caspUsuariosEditado = clienteService.editarCliente(usuarioParaEditar,caspUsuariosAtual);   
        UsuariosClientesModel clienteEditado = clientesAssembler.toModel(caspUsuariosEditado);
        return ResponseEntity.ok(clienteEditado);
    }

    @DeleteMapping("/{iUsuario}")
    public ResponseEntity deletarCliente(@PathVariable Integer iUsuario) {
         permissoes.restritoAoCliente();
        clienteService.deletarCliente(iUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientesAssembler.toModel(new CaspUsuarios()));
    }

    
}
