/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.assembler;

import caspvale.caspsuporte.atendimento.api.model.UsuariosConfigModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuariosConfig;
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
public class UsuariosConfigAssembler {

    private ModelMapper modelMapper;

    public UsuariosConfigModel toModel(CaspUsuariosConfig usuario) {
        return modelMapper.map(usuario, UsuariosConfigModel.class);
    }

    public List<UsuariosConfigModel> toCollectionModel(List<CaspUsuariosConfig> usuario) {
        return usuario.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
