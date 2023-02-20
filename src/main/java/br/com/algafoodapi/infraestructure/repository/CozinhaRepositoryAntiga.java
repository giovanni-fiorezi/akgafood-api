//package br.com.algafoodapi.infraestructure.repository;
//
//import br.com.algafoodapi.domain.model.Cozinha;
//import br.com.algafoodapi.domain.repository.CozinhaRepository;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//public class CozinhaRepositoryImpl implements CozinhaRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Cozinha> listar() {
//        TypedQuery<Cozinha> query = entityManager
//                .createQuery("from Cozinha", Cozinha.class);// consulta com jpql - faz consulta em objeto
//        return query.getResultList();
//    }
//
//    @Override
//    public List<Cozinha> buscarPorName(String name) {
//        return entityManager.createQuery("from Cozinha where name = :name", Cozinha.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
//
//    @Override
//    public Cozinha buscar(Long id) {
//        return entityManager.find(Cozinha.class, id);
//    }
//
//    @Transactional
//    @Override
//    public Cozinha salvar(Cozinha cozinha) {
//        return entityManager.merge(cozinha);
//    }
//
//    @Transactional
//    @Override
//    public void remover(Long id) {
//        buscar(id);
//        entityManager.remove(id);
//    }
//
//}
