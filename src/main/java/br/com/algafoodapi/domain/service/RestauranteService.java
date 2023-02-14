package br.com.algafoodapi.domain.service;

import br.com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafoodapi.domain.model.Cozinha;
import br.com.algafoodapi.domain.model.Restaurante;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import br.com.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe cadastro de cozinha com codigo %d", cozinhaId));
        }
        restaurante.setId(cozinhaId);
        return restauranteRepository.salvar(restaurante);
    }
}
