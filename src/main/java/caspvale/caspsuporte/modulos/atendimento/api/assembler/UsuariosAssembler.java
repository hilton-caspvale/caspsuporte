/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.UsuariosModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
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
public class UsuariosAssembler {

    private ModelMapper modelMapper;

    public UsuariosModel toModel(CaspUsuarios caspUsuarios) {
        return modelMapper.map(caspUsuarios, UsuariosModel.class);
    }

    public List<UsuariosModel> toCollectionModel(List<CaspUsuarios> usuarios) {
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspUsuarios toEntity(UsuariosModel usuariosModel) {
        return modelMapper.map(usuariosModel, CaspUsuarios.class);
    }

    public List<CaspUsuarios> toCollectionEntity(List<UsuariosModel> usuariosModel) {
        return usuariosModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
