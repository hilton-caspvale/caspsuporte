
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.ChamadosInputModel;
import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
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
public class ChamadosInputAssembler {

    private ModelMapper modelMapper;

    public ChamadosInputModel toModel(ChamadosModel chamado) {
        return modelMapper.map(chamado, ChamadosInputModel.class);
    }

    public List<ChamadosInputModel> toCollectionModel(List<ChamadosModel> chamado) {
        return chamado.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    public ChamadosModel toEntity(ChamadosInputModel model) {
        return modelMapper.map(model, ChamadosModel.class);
    }

    public List<ChamadosModel> toCollectionEntity(List<ChamadosInputModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
