package br.com.carlos.clothes_store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlos.clothes_store.core.models.ClothesServico;

public interface ClothesRepository extends JpaRepository <ClothesServico, Long> {
    
}
