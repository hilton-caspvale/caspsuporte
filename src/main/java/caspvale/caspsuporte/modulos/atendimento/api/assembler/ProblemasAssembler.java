/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.ProblemasModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspProblemas;
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
public class ProblemasAssembler {
     private ModelMapper modelMapper;

    public ProblemasModel toModel(CaspProblemas caspProblemas) {
        return modelMapper.map(caspProblemas, ProblemasModel.class);
    }

    public List<ProblemasModel> toCollectionModel(List<CaspProblemas> problemas) {
        return problemas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspProblemas toEntity(ProblemasModel problemasModel) {
        return modelMapper.map(problemasModel, CaspProblemas.class);
    }

    public List<CaspProblemas> toCollectionEntity(List<ProblemasModel> problemasModel) {
        return problemasModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
