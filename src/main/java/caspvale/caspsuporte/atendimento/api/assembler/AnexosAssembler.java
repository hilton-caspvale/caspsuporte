/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.AnexosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspAnexos;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hilton
 */
//@lombok.AllArgsConstructor
@Component
public class AnexosAssembler {

    private ModelMapper modelMapper;

    private AnexosAssembler() {
        this.modelMapper = new ModelMapper();

        PropertyMap<CaspAnexos, AnexosModel> customMapping = new PropertyMap<CaspAnexos, AnexosModel>() {
            @Override
            protected void configure() {
                map().setIArquivo(source.getIArquivo());
                // Mapeia o campo "differentFieldName" da classe de origem para o campo "someField" da classe de destino
                // Você pode adicionar mais regras de mapeamento personalizadas aqui
            }
        };

        modelMapper.addMappings(customMapping);
    }

    public AnexosModel toModel(CaspAnexos caspAnexo) {
        return modelMapper.map(caspAnexo, AnexosModel.class);
    }

    public List<AnexosModel> toCollectionModel(List<CaspAnexos> caspAnexo) {
        return caspAnexo.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspAnexos toEntity(AnexosModel anexoModel) {
        return modelMapper.map(anexoModel, CaspAnexos.class);
    }

    public List<CaspAnexos> toCollectionEntity(List<AnexosModel> anexoModel) {
        return anexoModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
