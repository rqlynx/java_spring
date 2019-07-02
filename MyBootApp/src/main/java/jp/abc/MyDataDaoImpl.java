package jp.abc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MyDataDaoImpl implements MyDataDao<MyData> {

    private EntityManager entityManager;

    public MyDataDaoImpl() {
        super();
    }
    public MyDataDaoImpl(EntityManager entityManager) {
        this();
        this.entityManager = entityManager;
    }

    @Override
    public List<MyData> getAll() {
        Query query = entityManager.createQuery("from MyData");
        List<MyData> list = query.getResultList();
        entityManager.close();
        return list;
    }

}