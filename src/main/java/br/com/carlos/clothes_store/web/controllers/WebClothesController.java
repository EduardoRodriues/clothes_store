package br.com.carlos.clothes_store.web.controllers;

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

import br.com.carlos.clothes_store.core.enums.Tamanho;
import br.com.carlos.clothes_store.web.dtos.ClothesServicoForm;
import br.com.carlos.clothes_store.web.dtos.FlashMessage;
import br.com.carlos.clothes_store.web.services.WebClothesService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/servico")
public class WebClothesController {
    
    @Autowired
    private WebClothesService service;

    @GetMapping
    public ModelAndView mostrarTodos() {
        var modelAndView = new ModelAndView("admin/servico/lista");
        modelAndView.addObject("lista", service.mostrarTodos());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("form", new ClothesServicoForm());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ClothesServicoForm form,
     BindingResult result,
      RedirectAttributes attrs) {

        if(result.hasErrors()) {
            return "admin/servico/form";
        }

        service.cadastrar(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço cadastrado com sucesso"));

        return "redirect:/admin/servico";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar() {
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("form", new ClothesServicoForm());

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String cadastrar(@Valid @ModelAttribute("form") ClothesServicoForm form, 
    @PathVariable Long id,
     BindingResult result,
      RedirectAttributes attrs) {

        if(result.hasErrors()) {
            return "admin/servico/form";
        }

        service.editar(id, form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço editado com sucesso"));

        return "redirect:/admin/servico";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {

        service.excluir(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço excluido com sucesso"));

        return "redirect:admin/servico";
    }

    @ModelAttribute("tamanhos")
    public Tamanho[] geTamanhos() {
        return Tamanho.values();
    }
}
