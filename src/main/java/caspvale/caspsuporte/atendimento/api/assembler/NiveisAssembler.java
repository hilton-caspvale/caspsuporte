/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.NiveisModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspNiveis;
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
public class NiveisAssembler {
     private ModelMapper modelMapper;

    public NiveisModel toModel(CaspNiveis caspNiveis) {
        return modelMapper.map(caspNiveis, NiveisModel.class);
    }

    public List<NiveisModel> toCollectionModel(List<CaspNiveis> niveis) {
        return niveis.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspNiveis toEntity(NiveisModel niveisModel) {
        return modelMapper.map(niveisModel, CaspNiveis.class);
    }

    public List<CaspNiveis> toCollectionEntity(List<NiveisModel> niveisModel) {
        return niveisModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
