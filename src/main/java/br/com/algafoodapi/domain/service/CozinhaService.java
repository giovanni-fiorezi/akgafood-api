package br.com.algafoodapi.domain.service;

import br.com.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafoodapi.domain.model.Cozinha;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public Optional<Cozinha> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Cozinha> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void excluir(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de codigo %s nao pode ser removida," +
                    "pois esta em uso", id));
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não foi encontrada", id));
        }
    }

    public Cozinha buscarOuFalhar(Long idCozinha) {
        return repository.findById(idCozinha).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de cozinha com código %d", idCozinha)));
    }

}
