package br.com.algafoodapi.controller;

import br.com.algafoodapi.domain.model.Cozinha;
import br.com.algafoodapi.domain.repository.CozinhaRepository;
import br.com.algafoodapi.domain.service.CozinhaService;
import br.com.algafoodapi.model.CozinhasXmlWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private CozinhaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Optional<Cozinha> cozinha = repository.findById(id);
        return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Optional<Cozinha>> findByName(@RequestParam("name") String name) {
        Optional<Cozinha> byName = repository.findByName(name);
        return ResponseEntity.ok(byName);
    }

    @GetMapping("/find-by-name-contain")
    public ResponseEntity<List<Cozinha>> findByNameContaining(@RequestParam("name") String name) {
        List<Cozinha> byName = service.findByNameContaining(name);
        return ResponseEntity.ok(byName);
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
        Cozinha c = service.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
                                             @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = repository.findById(id);

        if (cozinhaAtual.isPresent()) {
//        cozinhaAtual.setName(cozinha.getName());
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id"); //copia os dados de um objeto para o outro
            Cozinha coz = service.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(coz);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Cozinha> cozinha = repository.findById(id);
        if (cozinha.isPresent()) {
            service.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }
}
