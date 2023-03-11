package br.com.algafoodapi.domain.service;

import br.com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafoodapi.domain.exception.ExceptionGeral;
import br.com.algafoodapi.domain.model.Cidade;
import br.com.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listar() {
        try {
            return cidadeRepository.findAll();
        } catch (Exception e) {
            throw new EntidadeNaoEncontradaException(String.format("Ocorreu um erro na listagem das cidades"));
        }
    }

    public Cidade buscarPorId(Long id) {
        try {
            Optional<Cidade> opt = cidadeRepository.findById(id);
            if (opt.isEmpty()) {
                throw new EntidadeNaoEncontradaException(String.format("O id %d não foi encontrado", id));
            }
            return opt.get();
        } catch (Exception e) {
            throw new EntidadeNaoEncontradaException(String.format("Ocorreu um erro"));
        }
    }

    public Cidade buscarPorNome(String nome) {
        try {
            Optional<Cidade> opt = cidadeRepository.findByNome(nome);
            if (opt.isEmpty()) {
                throw new EntidadeNaoEncontradaException(String.format("A cidade %d não foi encontrada.", nome));
            }
            return opt.get();
        } catch (Exception e) {
            throw new EntidadeNaoEncontradaException(String.format("Ocorreu um erro"));
        }
    }

    public Cidade salvar(Cidade cidade) {
        try {
            Optional<Cidade> opt = cidadeRepository.findByNome(cidade.getNome());

            Long estadoId = cidade.getEstado().getId();

//            cidade.setEstado(estado);

            if(opt.isPresent()) {
                throw new Exception(String.format("Cidade já foi cadastrada!"));
            }
            return cidadeRepository.save(opt.get());
        } catch (Exception e) {
            throw new ExceptionGeral("Erro ao salvar uma cidade");
        }
    }

    public Cidade atualizar(Cidade cidade) {
        try {
            Optional<Cidade> opt = cidadeRepository.findById(cidade.getId());
            if(opt.isEmpty()) {
                throw new Exception(String.format("O id %d não foi encontrado.", cidade.getId()));
            }
            return cidadeRepository.save(opt.get());
        } catch (Exception e) {
            throw new ExceptionGeral("Erro ao atualizar uma cidade");
        }
    }

    public void excluir(Long id) {
        try {
            Optional<Cidade> opt = cidadeRepository.findById(id);
            if(opt.isEmpty()) {
                throw new Exception(String.format("O id %d não foi encontrado.", id));
            }
            cidadeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ExceptionGeral("Erro ao excluir uma cidade");
        }
    }

}
