package br.com.algafoodapi.domain.service;

import br.com.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.algafoodapi.domain.model.Restaurante;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import br.com.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    /* Faz a query dinamica do nome do restaurante atrelado a id da cozinha */
    public List<Restaurante> findByTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    public List<Restaurante> findByNameContainingAndCozinha(String name, Long cozinhaId) {
        return restauranteRepository.findByNameContainingAndCozinhaId(name, cozinhaId);
    }

    public void excluir(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Restaurante de codigo %s nao pode ser removida," +
                    "pois esta em uso", id));
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeEmUsoException(String.format("Restaurante de código %d não pode ser removida, pois está em uso", id));
        }
    }

}
