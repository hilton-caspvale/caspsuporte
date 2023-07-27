/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.AreasUsuariosModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAreas;
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
public class AreasUsuariosAssembler {

    private ModelMapper modelMapper;

    public AreasUsuariosModel toModel(CaspAreas caspAreas) {
        return modelMapper.map(caspAreas, AreasUsuariosModel.class);
    }

    public List<AreasUsuariosModel> toCollectionModel(List<CaspAreas> areas) {
        return areas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspAreas toEntity(AreasUsuariosModel areasUsuariosModel) {
        return modelMapper.map(areasUsuariosModel, CaspAreas.class);
    }

    public List<CaspAreas> toCollectionEntity(List<AreasUsuariosModel> areasUsuariosModel) {
        return areasUsuariosModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
