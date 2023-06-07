/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
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
public class AreasAssembler {

    private ModelMapper modelMapper;

    public AreasModel toModel(CaspAreas caspAreas) {
        return modelMapper.map(caspAreas, AreasModel.class);
    }

    public List<AreasModel> toCollectionModel(List<CaspAreas> areas) {
        return areas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspAreas toEntity(AreasModel areasModel) {
        return modelMapper.map(areasModel, CaspAreas.class);
    }

    public List<CaspAreas> toCollectionEntity(List<AreasModel> areasModel) {
        return areasModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
