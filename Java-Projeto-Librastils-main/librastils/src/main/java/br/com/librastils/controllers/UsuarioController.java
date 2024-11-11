package br.com.librastils.controllers;

import br.com.librastils.Service.UsuarioService;
import br.com.librastils.models.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        var aux = service.criar(usuario);
        return ResponseEntity.ok(aux);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscar(@PathVariable String cpf) {
        var aux = service.buscarUsuarioPorCpf(cpf);
        return ResponseEntity.ok(aux);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Usuario> atualizar(@PathVariable String cpf, @RequestBody Usuario usuario) {
        var aux = service.update(usuario, cpf);
        return ResponseEntity.ok(aux);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> apagar(@PathVariable String cpf) {
        service.delete(cpf);
        return ResponseEntity.noContent().build();
    }
}
