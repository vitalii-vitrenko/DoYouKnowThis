package com.vitrenko.doyouknowthis.testcommons;

import com.vitrenko.doyouknowthis.domain.entity.DomainEntity;
import org.springframework.test.annotation.IfProfileValue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IfProfileValue(name = "integration-tests", value = "true")
public abstract class IntegrationTest extends DatabaseAwareTest {

    @PersistenceContext
    private EntityManager entityManager;

    private EntityFactory entities = new EntityFactory();

    public EntityFactory entities() {
        return entities;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public <T extends DomainEntity> void assertPersistentEntity(T entity) {
        Long id = entity.getId();
        Object foundEntity = entityManager.find(entity.getClass(), id);

        assertNotNull("Entity with given id should be persistent", foundEntity);
        assertEquals("Given entity should be equals to persistent", entity, foundEntity);
    }

    protected <T extends DomainEntity> void persist(T... entities) {
        for (T entity : entities) {
            entityManager.persist(entity);
        }
        entityManager.flush();
    }

    protected <T extends DomainEntity> void persist(Collection<T> entities) {
        persist(entities.toArray(new DomainEntity[entities.size()]));
    }
}
