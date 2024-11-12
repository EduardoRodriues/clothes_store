package br.com.carlos.clothes_store.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.carlos.clothes_store.core.models.ClothesServico;
import br.com.carlos.clothes_store.web.dtos.ClothesServicoForm;

@Mapper(componentModel = "spring")
public interface WebClothesMapper {
    
    @Mapping(target = "id", ignore = true)
    ClothesServico toModel(ClothesServicoForm form);

    ClothesServicoForm toForm(ClothesServico model);
}
