/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.UsuariosClientesModel;
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
public class UsuariosClientesAssembler {
      private ModelMapper modelMapper;

    public UsuariosClientesModel toModel(CaspUsuarios caspUsuarios) {
        return modelMapper.map(caspUsuarios, UsuariosClientesModel.class);
    }

    public List<UsuariosClientesModel> toCollectionModel(List<CaspUsuarios> usuarios) {
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspUsuarios toEntity(UsuariosClientesModel usuariosClientesModel) {
        return modelMapper.map(usuariosClientesModel, CaspUsuarios.class);
    }

    public List<CaspUsuarios> toCollectionEntity(List<UsuariosClientesModel> usuariosClientesModel) {
        return usuariosClientesModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
