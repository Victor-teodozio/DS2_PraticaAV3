package com.example.demo.services;

import com.example.demo.domain.Contato;
import com.example.demo.repositories.ContatoRepository;
import com.example.demo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato find(Integer id) {
        Optional<Contato> contato = contatoRepository.findById((id));
        return contato.orElseThrow(() -> new ObjectNotFoundException("CONTATO COM ID IGUAL À ID NÃO FOI ENCONTRADO ".replace("ID", Integer.toString(id))));
    }

    public Contato insert(Contato contato) {
        contato.setId(null);
        return contatoRepository.save(contato);
    }

    public Contato update(Contato contato) {
        find(contato.getId());
        return contatoRepository.save(contato);
    }

    public void delete(Integer id) {
        find(id);
        contatoRepository.deleteById(id);
    }

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }
}
