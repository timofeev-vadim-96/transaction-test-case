package ru.gnivc.olegtransactions.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gnivc.olegtransactions.model.TestEntity;

@Service
@RequiredArgsConstructor
public class DaoService {
    @PersistenceContext
    private final EntityManager em;

    public void save(TestEntity entity) {
        em.persist(entity);
    }
}
