package br.com.algafoodapi.infraestructure.repository;

import br.com.algafoodapi.domain.model.Restaurante;
import br.com.algafoodapi.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> query = entityManager
                .createQuery("from Restaurante", Restaurante.class);// consulta com jpql - faz consulta em objeto
        return query.getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
