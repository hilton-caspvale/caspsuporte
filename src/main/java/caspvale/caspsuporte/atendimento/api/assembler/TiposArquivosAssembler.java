/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.TiposArquivosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspTiposArquivos;
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
public class TiposArquivosAssembler {
     private ModelMapper modelMapper;

    public TiposArquivosModel toModel(CaspTiposArquivos caspTiposArquivos) {
        return modelMapper.map(caspTiposArquivos, TiposArquivosModel.class);
    }

    public List<TiposArquivosModel> toCollectionModel(List<CaspTiposArquivos> tiposArquivos) {
        return tiposArquivos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspTiposArquivos toEntity(TiposArquivosModel tiposArquivosModel) {
        return modelMapper.map(tiposArquivosModel, CaspTiposArquivos.class);
    }

    public List<CaspTiposArquivos> toCollectionEntity(List<TiposArquivosModel> tiposArquivosModel) {
        return tiposArquivosModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
