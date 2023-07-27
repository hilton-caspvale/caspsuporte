package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.ChamadosInputModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.ChamadosModel;
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
public class ChamadosInputAssembler {
    
    private ModelMapper modelMapper;
    
    public ChamadosInputAssembler() {
        this.modelMapper = new ModelMapper();
        
        PropertyMap<ChamadosInputModel, ChamadosModel> customMapping = new PropertyMap<ChamadosInputModel, ChamadosModel>() {
            @Override
            protected void configure() {
                map().setCaspSistemasList(source.getSistemas());
                map().setCaspProblemasList(source.getProblemas());
                // Mapeia o campo "differentFieldName" da classe de origem para o campo "someField" da classe de destino
                // Você pode adicionar mais regras de mapeamento personalizadas aqui
            }
        };
        
        modelMapper.addMappings(customMapping);
    }
    
    public ChamadosInputModel toModel(ChamadosModel chamado) {
        return modelMapper.map(chamado, ChamadosInputModel.class);
    }
    
    public List<ChamadosInputModel> toCollectionModel(List<ChamadosModel> chamado) {
        return chamado.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
    public ChamadosModel toEntity(ChamadosInputModel model) {
        return modelMapper.map(model, ChamadosModel.class);
    }
    
    public List<ChamadosModel> toCollectionEntity(List<ChamadosInputModel> model) {
        return model.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
