package br.com.algafoodapi.controller;

import br.com.algafoodapi.domain.model.Cidade;
import br.com.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("")
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable (value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.buscarPorId(id));
    }

    @GetMapping("/")
    public ResponseEntity<Cidade> buscarPorNome(@RequestParam (value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
        Cidade cid = cidadeService.salvar(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cid);
    }

    @PutMapping
    public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade) {
        Cidade cid = cidadeService.atualizar(cidade);
        return ResponseEntity.status(HttpStatus.OK).body(cid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        cidadeService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cidade excluída com sucesso.");
    }

}
