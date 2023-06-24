package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.AreasAssembler;
import caspvale.caspsuporte.atendimento.api.assembler.AreasUsuariosAssembler;
import caspvale.caspsuporte.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.atendimento.api.model.AreasUsuariosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.domain.exception.NegocioException;
import caspvale.caspsuporte.atendimento.domain.service.AreasService;
import caspvale.caspsuporte.atendimento.domain.service.UsuariosService;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping("/atendimento/areas")
public class AreasController {

    private final AreasAssembler areasAssembler;
    private final AreasUsuariosAssembler areasUsuariosAssembler;
    private final AreasService areasService;
    private final UsuariosService usuariosService;
    private final Permissoes permissoes;

    public AreasController(AreasAssembler areasAssembler, AreasService areasService,
            AreasUsuariosAssembler areasUsuariosAssembler,
            UsuariosService usuariosService, Permissoes permissoes) {
        this.areasAssembler = areasAssembler;
        this.areasUsuariosAssembler = areasUsuariosAssembler;
        this.areasService = areasService;
        this.usuariosService = usuariosService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(areasAssembler.toCollectionModel(areasService.listar()));
    }

    @GetMapping("/minhas-areas")
    public ResponseEntity<?> minhasAreas(
            @RequestParam(value = "situacao", required = false, defaultValue = "") String situacao) {
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(permissoes.login());
        return ResponseEntity.ok(areasAssembler.toCollectionModel(areasService.areasDoUsuario(situacao, usuario)));
    }

    @GetMapping("/areas-do-usuario")
    public ResponseEntity<?> areasDoUsuario(
            @RequestParam(value = "situacao", required = false, defaultValue = "") String situacao,
            @RequestParam(value = "login", required = true) String login) {
        permissoes.restritoAoCliente();
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(login);
        List<AreasModel> areas = areasAssembler.toCollectionModel(areasService.areasDoUsuario(situacao, usuario));
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/{iArea}")
    public ResponseEntity<?> area(@PathVariable @Valid Integer iArea) {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(areasAssembler.toModel(areasService.buscarOuFalhar(iArea)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody AreasModel areasModel) {
        permissoes.exclusivoAdministrador();
        if (areasModel.getIArea() != null) {
            throw new NegocioException("O código da área não pode ser informado para novos cadastros!");
        }
        CaspAreas areaNova = areasService.novo(areasAssembler.toEntity(areasModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(areasAssembler.toModel(areaNova));
    }

    @PutMapping("/{iArea}")
    public ResponseEntity editar(@Valid @RequestBody AreasModel areasModel, @PathVariable Integer iArea) {
        permissoes.exclusivoAdministrador();
        CaspAreas caspAreaAtual = areasService.buscarOuFalhar(iArea);
        if (areasModel.getIArea() == null || caspAreaAtual.getIArea() != iArea) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspAreas caspAreaEditada = areasAssembler.toEntity(areasModel);
        AreasModel areaEdidata = areasAssembler.toModel(areasService.editar(caspAreaEditada, caspAreaAtual));
        return ResponseEntity.ok(areaEdidata);
    }

    @DeleteMapping("/{iArea}")
    public ResponseEntity deletar(@PathVariable Integer iArea) {
        permissoes.exclusivoAdministrador();
        areasService.deletar(iArea);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(areasAssembler.toModel(new CaspAreas()));
    }

    public List<AreasUsuariosModel> areasComUsuariosAnalistasDoChamado(List<Integer> listaIAreas) {
        return areasUsuariosAssembler.toCollectionModel(areasService.areasComUsuariosAnalistasDoChamado(listaIAreas));
    }
}
