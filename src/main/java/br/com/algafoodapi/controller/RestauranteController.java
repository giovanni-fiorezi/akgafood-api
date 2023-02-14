package br.com.algafoodapi.controller;

import br.com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafoodapi.domain.model.Cozinha;
import br.com.algafoodapi.domain.model.Restaurante;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import br.com.algafoodapi.domain.repository.RestauranteRepository;
import br.com.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante r = restauranteRepository.buscar(id);
        if (r != null) {
            return ResponseEntity.ok().body(r);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
