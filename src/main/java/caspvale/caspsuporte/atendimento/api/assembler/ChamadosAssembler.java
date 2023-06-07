
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspChamados;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hilton
 */
@lombok.AllArgsConstructor
@Component
public class ChamadosAssembler {

    private ModelMapper modelMapper;

    public ChamadosModel toModel(CaspChamados chamado) {
        return modelMapper.map(chamado, ChamadosModel.class);
    }

    public List<ChamadosModel> toCollectionModel(List<CaspChamados> chamado) {
        return chamado.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    public CaspChamados toEntity(ChamadosModel model) {
        return modelMapper.map(model, CaspChamados.class);
    }

    public List<CaspChamados> toCollectionEntity(List<ChamadosModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
