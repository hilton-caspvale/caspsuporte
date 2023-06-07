/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.OrigensModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspOrigens;
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
public class OrigensAssembler {
     private ModelMapper modelMapper;

    public OrigensModel toModel(CaspOrigens caspOrigens) {
        return modelMapper.map(caspOrigens, OrigensModel.class);
    }

    public List<OrigensModel> toCollectionModel(List<CaspOrigens> origens) {
        return origens.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspOrigens toEntity(OrigensModel origensModel) {
        return modelMapper.map(origensModel, CaspOrigens.class);
    }

    public List<CaspOrigens> toCollectionEntity(List<OrigensModel> origensModel) {
        return origensModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
