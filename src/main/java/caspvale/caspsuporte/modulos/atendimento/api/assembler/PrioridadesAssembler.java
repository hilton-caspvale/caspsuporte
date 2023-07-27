/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.PrioridadesModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspPrioridades;
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
public class PrioridadesAssembler {

    private ModelMapper modelMapper;

    public PrioridadesModel toModel(CaspPrioridades prioridade) {
        return modelMapper.map(prioridade, PrioridadesModel.class);
    }

    public List<PrioridadesModel> toCollectionModel(List<CaspPrioridades> prioridade) {
        return prioridade.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
     public CaspPrioridades toEntity(PrioridadesModel model) {
        return modelMapper.map(model, CaspPrioridades.class);
    }

    public List<CaspPrioridades> toCollectionEntity(List<PrioridadesModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
