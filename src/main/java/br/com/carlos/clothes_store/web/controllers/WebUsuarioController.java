package br.com.carlos.clothes_store.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.carlos.clothes_store.web.services.WebUsuarioService;
import jakarta.validation.Valid;
import br.com.carlos.clothes_store.web.dtos.UsuarioCadastroForm;
import br.com.carlos.clothes_store.web.dtos.UsuarioEdicaoForm;
import br.com.carlos.clothes_store.web.dtos.AlterarSenhaForm;
import br.com.carlos.clothes_store.web.dtos.FlashMessage;
import br.com.carlos.clothes_store.core.exceptions.ValidacaoException;

@Controller
@RequestMapping("admin/usuarios")
public class WebUsuarioController {

    @Autowired
    private WebUsuarioService service;

    @GetMapping
    public ModelAndView mostrarTodos() {

        var modelAndView = new ModelAndView("admin/usuario/lista");
        modelAndView.addObject("lista", service.buscarTodos());

        return modelAndView;
    } 

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {

        var modelAndView = new ModelAndView("admin/usuario/formCadastro");
        modelAndView.addObject("formCadastro", new UsuarioCadastroForm());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("formCadastro") UsuarioCadastroForm form,
    BindingResult result,
    RedirectAttributes attrs) {

        if(result.hasErrors()) {
            return "admin/usuario/formCadastro";
        }

        try{
            service.cadastrar(form);
            attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "usuario cadastrado com sucesso!"));
        } catch(ValidacaoException e) {
            result.addError(e.getFieldError());
            return "admin/usuario/formCadastro";
        }

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {

        var modelAndView = new ModelAndView("admin/usuario/formEdicao");
        modelAndView.addObject("formEdicao", service.buscarPorId(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@Valid @ModelAttribute("formEdicao") UsuarioEdicaoForm form,
    @PathVariable Long id,
    BindingResult result,
    RedirectAttributes attrs) {

        if(result.hasErrors()) {
            return "admin/usuario/formEdicao";
        }

        try{
            service.editar(id, form);
            attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "usuario editado com sucesso!"));
        } catch(ValidacaoException e) {
            result.addError(e.getFieldError());
            return "admin/usuario/formEdicao";
        }

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {

        service.excluir(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço excluido com sucesso!"));

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/alterar-senha")
    public ModelAndView alterarSenha() {

        var modelAndView = new ModelAndView("admin/usuario/alterarSenha");
        modelAndView.addObject("alterarSenhaForm", new AlterarSenhaForm());

        return modelAndView;
    }

    @PostMapping("/alterar-senha")
    public String editar(@Valid @ModelAttribute("alterarSenhaForm") AlterarSenhaForm form,
    BindingResult result,
    RedirectAttributes attrs,
    Principal principal) {

        if(result.hasErrors()) {
            return "admin/usuario/alterar-senha";
        }

        try{
            service.alterarSenha(form, principal.getName());
            attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "senha alterada com sucesso!"));
        } catch(ValidacaoException e) {
            result.addError(e.getFieldError());
            return "admin/usuario/alterar-senha";
        }

        return "redirect:/admin/usuarios";
    }
    
}