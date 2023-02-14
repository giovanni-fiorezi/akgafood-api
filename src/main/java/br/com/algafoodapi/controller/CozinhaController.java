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

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private CozinhaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return repository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(repository.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = repository.buscar(id);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
        Cozinha c = service.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
                                             @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = repository.buscar(id);

        if (cozinhaAtual != null) {
//        cozinhaAtual.setName(cozinha.getName());
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id"); //copia os dados de um objeto para o outro
            repository.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Cozinha cozinha = repository.buscar(id);
        if (cozinha != null) {
            service.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }
}
