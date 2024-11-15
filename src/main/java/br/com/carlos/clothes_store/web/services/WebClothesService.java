package br.com.carlos.clothes_store.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlos.clothes_store.core.exceptions.ServicoNaoEncontradoException;
import br.com.carlos.clothes_store.core.models.ClothesServico;
import br.com.carlos.clothes_store.core.repositories.ClothesRepository;
import br.com.carlos.clothes_store.web.dtos.ClothesServicoForm;
import br.com.carlos.clothes_store.web.mappers.WebClothesMapper;

@Service
public class WebClothesService {
    
    @Autowired
    private WebClothesMapper mapper;

    @Autowired
    private ClothesRepository repository;

    public List<ClothesServico> mostrarTodos() {

        return repository.findAll();
    }

    public ClothesServico cadastrar(ClothesServicoForm form) {

        var model = mapper.toModel(form);
        return repository.save(model);
    }

    public ClothesServico buscarPorId(Long id) {

        var mensagem = String.format("Serviço com ID %d não foi encontrado", id);

        return repository.findById(id)
        .orElseThrow(() -> new ServicoNaoEncontradoException(mensagem));
    }

    public ClothesServico editar(Long id, ClothesServicoForm form) {

        var servicoEncontrado = buscarPorId(id);
        var model = mapper.toModel(form);

        model.setId(servicoEncontrado.getId());

        return repository.save(model);
    }

    public void excluir(Long id) {

        var servicoEncontrado = buscarPorId(id);
        repository.delete(servicoEncontrado);
    }
}
