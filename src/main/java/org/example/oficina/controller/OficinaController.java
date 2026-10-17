package org.example.oficina.controller;

import jakarta.validation.Valid;
import org.example.oficina.model.CategoriaOficina;
import org.example.oficina.model.Oficina;
import org.example.oficina.repository.OficinaRepository;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("oficinas")
public class OficinaController {

    private final OficinaRepository repository;

    public OficinaController (OficinaRepository repository){
        this.repository = repository;
    }


    // 1 - listar
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("oficinas", repository.getTodos());
        return "oficinas";
    }

    // 2 - Exibir formulário vazio
    @GetMapping("/novo")
    public String exibir(Model model) {
        model.addAttribute("postForm", new Oficina());
        model.addAttribute("categorias", CategoriaOficina.values());
        return "form";
    }

    // 3 - Postar informações
    @PostMapping
    public String postar(@Valid @ModelAttribute("postForm") Oficina oficina, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("categorias", CategoriaOficina.values());
            return "form";
        }
        repository.adicionar(oficina);
        return "redirect:/oficinas";
    }

    // 4 - buscar por id
    @GetMapping("/{id}")
    public String buscarId(@PathVariable Long id, Model model){
        model.addAttribute("detalhes", repository.buscarId(id));
        return "detalhes";
    }

    // 5 - puxar as oficinas publicadas
    @GetMapping("/publicadas")
    public String publicadas(Model model){
        model.addAttribute("publicadas", repository.listarPublicadas());
        model.addAttribute("contarPublicadas", repository.contarPublicadas());
        return "publicadas";
    }

    // 6 - Metodo para publicar
    @PostMapping("/{id}/publicar")
    public String publicar(@PathVariable Long id){
        repository.buscarId(id).publicar();
        return "redirect:/oficinas/" + id;
    }

    // 7 - Metodo para cancelar
    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id){
        repository.buscarId(id).cancelar();
        return "redirect:/oficinas/" + id;
    }

    // 8 - Metodo para encerrar
    @PostMapping("/{id}/encerrar")
    public String encerrar(@PathVariable Long id){
        repository.buscarId(id).encerrar();
        return "redirect:/oficinas/" + id;
    }

    // 9 - Metodo para inscrever aluno
    @PostMapping("/{id}/inscrever")
    public String inscrever(@PathVariable Long id){
        repository.buscarId(id).inscrever();
        return "redirect:/oficinas/" + id;
    }
}
