package com.example.demo.resource;

import com.example.demo.domain.Contato;
import com.example.demo.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/contatos")
public class ContatoResource {

    @Autowired
    ContatoService contatoservice;

    @GetMapping
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> lista = contatoservice.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Contato contato = contatoservice.find(id);
        map.put("Contato", contato);
        return ResponseEntity.ok().body(map);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Contato contato) {
        Map<String, Object> map = new HashMap<>();
        contato = contatoservice.insert(contato);
        map.put("Contato foi criado status", "Sucesso!");
        map.put("Contato", contato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).body(contato);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody  Contato contato, @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        contato.setId(id);
        contato = contatoservice.update(contato);
        map.put("Contato foi atualizado status", "Sucesso!");
        map.put("Contato", contato);
        return ResponseEntity.ok().body(contato);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        contatoservice.delete(id);
        map.put("Contato foi deletado id", id);
        return ResponseEntity.ok().body(map);
    }
}
