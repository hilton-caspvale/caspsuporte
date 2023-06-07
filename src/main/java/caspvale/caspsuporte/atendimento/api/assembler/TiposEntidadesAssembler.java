/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.TiposEntidadesModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspTiposEntidades;
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
public class TiposEntidadesAssembler {

    private ModelMapper modelMapper;

    public TiposEntidadesModel toModel(CaspTiposEntidades tiposEntidades) {
        return modelMapper.map(tiposEntidades, TiposEntidadesModel.class);
    }

    public List<TiposEntidadesModel> toCollectionModel(List<CaspTiposEntidades> tiposEntidades) {
        return tiposEntidades.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
     public CaspTiposEntidades toEntity(TiposEntidadesModel model) {
        return modelMapper.map(model, CaspTiposEntidades.class);
    }

    public List<CaspTiposEntidades> toCollectionEntity(List<TiposEntidadesModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
