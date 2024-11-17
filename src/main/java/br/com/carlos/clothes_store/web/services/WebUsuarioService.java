package br.com.carlos.clothes_store.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import br.com.carlos.clothes_store.core.exceptions.SenhasNaoConferemException;
import br.com.carlos.clothes_store.core.exceptions.UsuarioNaoEncontradoException;
import br.com.carlos.clothes_store.core.exceptions.UsuarioJaCadastradoException;

import br.com.carlos.clothes_store.core.models.Usuario;
import br.com.carlos.clothes_store.core.repositories.UsuarioRepository;
import br.com.carlos.clothes_store.web.dtos.UsuarioCadastroForm;
import br.com.carlos.clothes_store.web.dtos.UsuarioEdicaoForm;
import br.com.carlos.clothes_store.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    @Autowired
    private WebUsuarioMapper mapper;

    @Autowired
    private UsuarioRepository repository;
    

    public List<Usuario> buscarTodos() {
        
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroForm form) {

        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        if(!senha.equals(confirmacaoSenha)) {

            var mensagem = "os campos não conferem";

            var fieldError = new FieldError(form.getClass().getName(),
             "os campos não coneferm",
              form.getConfirmacaoSenha(),
               false,
                null,
                 null,
                  mensagem);

                  throw new SenhasNaoConferemException(mensagem, fieldError);
        }

        var model = mapper.toModel(form);
        validarCamposUnicos(model);

        return repository.save(model);
    }

    public Usuario buscarPorId(Long id) {

        var mensagem = String.format("usuario com o ID %d não foi encontrado", id);

        return repository.findById(id)
        .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
    }

    public Usuario editar(Long id, UsuarioEdicaoForm form) {

        var usuario = buscarPorId(id);
        var model = mapper.toModel(form);

        model.setId(usuario.getId());
        model.setTipoUsuario(usuario.getTipoUsuario());
        model.setSenha(usuario.getSenha());

        return repository.save(model);
    }

    public void excluir(Long id) {

        var usuario = buscarPorId(id);
        repository.delete(usuario);
    }

    private void validarCamposUnicos(Usuario usuario) {

        if(repository.isEmailJaCadastrado(usuario.getEmail(), usuario.getId())) {
            var menssagem = "este email já foi cadastrado";

            var fieldError = new FieldError(usuario.getClass().getName(),
             "email", usuario.getEmail(),
              false,
               null,
                null,
                 menssagem);

                 throw new UsuarioJaCadastradoException(menssagem, fieldError);
        }
    }
}