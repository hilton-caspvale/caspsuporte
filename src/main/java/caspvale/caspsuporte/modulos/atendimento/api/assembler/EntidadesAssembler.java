/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspEntidades;
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
public class EntidadesAssembler {

    private ModelMapper modelMapper;

    public EntidadesModel toModel(CaspEntidades entidade) {
        return modelMapper.map(entidade, EntidadesModel.class);
    }

    public List<EntidadesModel> toCollectionModel(List<CaspEntidades> entidade) {
        return entidade.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    public CaspEntidades toEntity(EntidadesModel model) {
        return modelMapper.map(model, CaspEntidades.class);
    }

    public List<CaspEntidades> toCollectionEntity(List<EntidadesModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
