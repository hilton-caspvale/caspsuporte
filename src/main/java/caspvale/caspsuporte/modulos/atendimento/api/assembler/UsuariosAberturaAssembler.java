/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.assembler;

import caspvale.caspsuporte.modulos.atendimento.api.model.UsuariosAberturaModel;
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
public class UsuariosAberturaAssembler {

    private ModelMapper modelMapper;

    public UsuariosAberturaModel toModel(CaspUsuarios caspUsuarios) {
        return modelMapper.map(caspUsuarios, UsuariosAberturaModel.class);
    }

    public List<UsuariosAberturaModel> toCollectionModel(List<CaspUsuarios> usuarios) {
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CaspUsuarios toEntity(UsuariosAberturaModel usuariosAberturaModel) {
        return modelMapper.map(usuariosAberturaModel, CaspUsuarios.class);
    }

    public List<CaspUsuarios> toCollectionEntity(List<UsuariosAberturaModel> usuariosAberturaModel) {
        return usuariosAberturaModel.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
