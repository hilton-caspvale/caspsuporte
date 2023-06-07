/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.TiposUsuariosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspTiposUsuarios;
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
public class TiposUsuariosAssembler {

    private ModelMapper modelMapper;

    public TiposUsuariosModel toModel(CaspTiposUsuarios tiposUsuarios) {
        return modelMapper.map(tiposUsuarios, TiposUsuariosModel.class);
    }

    public List<TiposUsuariosModel> toCollectionModel(List<CaspTiposUsuarios> tiposUsuarios) {
        return tiposUsuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
     public CaspTiposUsuarios toEntity(TiposUsuariosModel model) {
        return modelMapper.map(model, CaspTiposUsuarios.class);
    }

    public List<CaspTiposUsuarios> toCollectionEntity(List<TiposUsuariosModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}