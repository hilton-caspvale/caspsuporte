/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspSistemas;
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
public class SistemasAssembler {

    private ModelMapper modelMapper;

    public SistemasModel toModel(CaspSistemas sistema) {
        return modelMapper.map(sistema, SistemasModel.class);
    }

    public List<SistemasModel> toCollectionModel(List<CaspSistemas> sistema) {
        return sistema.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
     public CaspSistemas toEntity(SistemasModel model) {
        return modelMapper.map(model, CaspSistemas.class);
    }

    public List<CaspSistemas> toCollectionEntity(List<SistemasModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
