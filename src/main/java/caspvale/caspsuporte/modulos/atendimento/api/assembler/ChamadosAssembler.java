
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspChamados;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hilton
 */
@lombok.AllArgsConstructor
@Component
public class ChamadosAssembler {

    private ModelMapper modelMapper;
    
   /* public ChamadosAssembler() {
        this.modelMapper = new ModelMapper();

        PropertyMap<ChamadosModel, CaspChamados> customMapping = new PropertyMap<ChamadosModel, CaspChamados>() {
            @Override
            protected void configure() {
                map().setCaspSistemasList(source.getSistemas());
                // Mapeia o campo "differentFieldName" da classe de origem para o campo "someField" da classe de destino
                // Você pode adicionar mais regras de mapeamento personalizadas aqui
            }
        };

        modelMapper.addMappings(customMapping);
    }*/

    public ChamadosModel toModel(CaspChamados chamado) {
        return modelMapper.map(chamado, ChamadosModel.class);
    }

    public List<ChamadosModel> toCollectionModel(List<CaspChamados> chamado) {
        return chamado.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    public CaspChamados toEntity(ChamadosModel model) {
        return modelMapper.map(model, CaspChamados.class);
    }

    public List<CaspChamados> toCollectionEntity(List<ChamadosModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
