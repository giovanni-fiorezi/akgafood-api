package br.com.algafoodapi.controller;

import br.com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafoodapi.domain.model.Restaurante;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import br.com.algafoodapi.domain.repository.RestauranteRepository;
import br.com.algafoodapi.domain.service.RestauranteService;
import br.com.algafoodapi.infraestructure.repository.spec.RestauranteComFreteGratisSpec;
import br.com.algafoodapi.infraestructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Optional<Restaurante> r = restauranteRepository.findById(id);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find-by-taxa")
    public ResponseEntity<List<Restaurante>> findByTaxaFrete(@RequestParam BigDecimal taxaInicial,
                                                             BigDecimal taxaFinal) {
        List<Restaurante> byTaxaFrete = restauranteService.findByTaxaFrete(taxaInicial, taxaFinal);
        return ResponseEntity.ok(byTaxaFrete);
    }

    @GetMapping("/find-by-name-and-cozinha")
    public ResponseEntity<List<Restaurante>> findByTaxaFrete(@RequestParam String name,
                                                             Long cozinhaId) {
        List<Restaurante> byNameAndCozinhaId = restauranteService.findByNameContainingAndCozinha(name, cozinhaId);
        return ResponseEntity.ok(byNameAndCozinhaId);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Optional<Restaurante> restaurante) {
        try {
            Optional<Restaurante> res = restauranteRepository.findById(id);
            if (res.isPresent()) {
                BeanUtils.copyProperties(restaurante, res.get(), "id");
                Restaurante res2 =  restauranteRepository.save(res.get());
                return ResponseEntity.ok(res2);
            }
            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        Optional<Restaurante> res = restauranteRepository.findById(restauranteId);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, res);
        return atualizar(restauranteId, res);
    }

    private void merge(Map<String, Object> dadosOrigem, Optional<Restaurante> restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @GetMapping("/first-name")
    public Optional<Restaurante> findByFirstName(String name) {
        return restauranteRepository.findFirstRestauranteByNameContaining(name);
    }

    @GetMapping("/find-test")
    public List<Restaurante> find(String name, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.find(name, taxaInicial, taxaFinal);
    }

    @GetMapping("/count-por-cozinha")
    public int restaurantesCountPorCozinha(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    public List<Restaurante> restaurantesComFreteGratis(String name) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(name);

        return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
    }
}
