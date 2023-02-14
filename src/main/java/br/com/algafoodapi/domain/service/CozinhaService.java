package br.com.algafoodapi.domain.service;

import br.com.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.algafoodapi.domain.model.Cozinha;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public Cozinha salvar(Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

    public void excluir(Long id) {
        try {
            repository.remover(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de codigo %s nao pode ser removida," +
                    "pois esta em uso", id));
        }
    }
}
