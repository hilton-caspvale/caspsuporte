package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.SituacoesModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspSituacoes;
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
public class SituacoesAssembler {
     private ModelMapper modelMapper;

    public SituacoesModel toModel(CaspSituacoes caspSituacoes) {
        return modelMapper.map(caspSituacoes, SituacoesModel.class);
    }

    public List<SituacoesModel> toCollectionModel(List<CaspSituacoes> situacoes) {
        return situacoes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspSituacoes toEntity(SituacoesModel situacoesModel) {
        return modelMapper.map(situacoesModel, CaspSituacoes.class);
    }

    public List<CaspSituacoes> toCollectionEntity(List<SituacoesModel> situacoesModel) {
        return situacoesModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
