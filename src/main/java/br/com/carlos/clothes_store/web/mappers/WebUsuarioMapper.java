package br.com.carlos.clothes_store.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.carlos.clothes_store.core.models.Usuario;
import br.com.carlos.clothes_store.web.dtos.UsuarioCadastroForm;
import br.com.carlos.clothes_store.web.dtos.UsuarioEdicaoForm;

@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toModel(UsuarioCadastroForm form);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toModel(UsuarioEdicaoForm form);

    UsuarioEdicaoForm toForm(Usuario model);
    
}