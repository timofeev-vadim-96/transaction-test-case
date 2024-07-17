package ru.gnivc.olegtransactions.beans;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gnivc.olegtransactions.model.TestEntity;
import ru.gnivc.olegtransactions.provider.KafkaSendMessage;
import ru.gnivc.olegtransactions.service.DaoService;

@Component
public class SomeClass {
    private final DaoService daoSerivce;

    private final KafkaSendMessage kafkaSendMessage;

    private final SomeMethod someMethod;

    private final SomeClass someClass;

    @PersistenceContext
    private final EntityManager em;

    public SomeClass(DaoService daoSerivce, KafkaSendMessage kafkaSendMessage, SomeMethod someMethod, @Lazy SomeClass someClass, EntityManager em) {
        this.daoSerivce = daoSerivce;
        this.kafkaSendMessage = kafkaSendMessage;
        this.someMethod = someMethod;
        this.someClass = someClass;
        this.em = em;
    }

    @Transactional
    public void a() throws Exception {
        em.persist(new TestEntity("first entity"));

        //если так, то сохранится лишь одна сущность из первого метода
        someClass.run();
        //если так, то сохранятся обе сущности, т.к. вторая транзакция не сработала
        run();
    }

    //
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void run() throws Exception {
        kafkaSendMessage.send("some data");
        daoSerivce.save(new TestEntity("second entity"));
        someMethod.doMethod("something");
    }
}
