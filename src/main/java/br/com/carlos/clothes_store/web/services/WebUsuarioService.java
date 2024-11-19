package br.com.carlos.clothes_store.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import br.com.carlos.clothes_store.core.enums.TipoUsuario;
import br.com.carlos.clothes_store.core.exceptions.SenhaIncorretaException;
import br.com.carlos.clothes_store.core.exceptions.SenhasNaoConferemException;
import br.com.carlos.clothes_store.core.exceptions.UsuarioNaoEncontradoException;
import br.com.carlos.clothes_store.core.exceptions.UsuarioJaCadastradoException;

import br.com.carlos.clothes_store.core.models.Usuario;
import br.com.carlos.clothes_store.core.repositories.UsuarioRepository;
import br.com.carlos.clothes_store.web.dtos.AlterarSenhaForm;
import br.com.carlos.clothes_store.web.dtos.UsuarioCadastroForm;
import br.com.carlos.clothes_store.web.dtos.UsuarioEdicaoForm;
import br.com.carlos.clothes_store.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    @Autowired
    private WebUsuarioMapper mapper;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public List<Usuario> buscarTodos() {
        
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroForm form) {

        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        if(!senha.equals(confirmacaoSenha)) {

            var mensagem = "os campos não conferem";

            var fieldError = new FieldError(form.getClass().getName(),
             "confirmacaoSenha",
              form.getConfirmacaoSenha(),
               false,
                null,
                 null,
                  mensagem);

                  throw new SenhasNaoConferemException(mensagem, fieldError);
        }

        var model = mapper.toModel(form);

        var senhaHash = passwordEncoder.encode(model.getSenha());

        model.setSenha(senhaHash); 
        model.setTipoUsuario(TipoUsuario.ADMIN);
        validarCamposUnicos(model);

        return repository.save(model);
    }

    public Usuario buscarPorId(Long id) {

        var mensagem = String.format("usuario com o ID %d não foi encontrado", id);

        return repository.findById(id)
        .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
    }

    public Usuario buscarPorEmail(String email) {

        var mensagem = String.format("usuario com o Email %s não foi encontrado", email);

        return repository.findByEmail(email)
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

    public void alterarSenha(AlterarSenhaForm form, String email) {

        var usuario = buscarPorEmail(email);

        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        var senhaAtual = usuario.getSenha();
        var senhaAntiga = form.getSenha();

        if(!senha.equals(confirmacaoSenha)) {

            var mensagem = "os campos não conferem";

            var fieldError = new FieldError(form.getClass().getName(),
             "confirmacaoSenha",
              form.getConfirmacaoSenha(),
               false,
                null,
                 null,
                  mensagem);

                  throw new SenhasNaoConferemException(mensagem, fieldError);
        }

        if(!passwordEncoder.matches(senhaAntiga, senhaAtual)) {

            var mensagem = "A senha antiga está incorreta";

            var fieldError = new FieldError(form.getClass().getName(),
             "senhaAntiga",
              senhaAntiga,
               false,
                null,
                 null,
                  mensagem);

                  throw new SenhaIncorretaException(mensagem, fieldError);
        }

        var novaSenhaHash = passwordEncoder.encode(senha);
        usuario.setSenha(novaSenhaHash);
        
        repository.save(usuario);
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