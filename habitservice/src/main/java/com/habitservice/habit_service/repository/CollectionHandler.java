package com.habitservice.habit_service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CollectionHandler {
    private final MongoTemplate mongoTemplate;
    public <T> Object save(T object, String...dbName) {
        if (object == null) throw new RuntimeException("Saving Object is Null");
        if (dbName != null && dbName.length > 0) {
            return mongoTemplate.save(object, dbName.toString());
        }else {
            return mongoTemplate.save(object);
        }
    }
    public <T> List<T> saveAll(List<T> objectsToSave) {
        Assert.notNull(objectsToSave, "List of Object Should Not Null");
        return (List<T>) mongoTemplate.insertAll(objectsToSave);
    }

    public <T> List<T> findAllDocuments(Class<T> clazz) {
        return mongoTemplate.findAll(clazz);
    }
    public <T> List<T> findDocumentByField(String fieldName, Object object, Class<T> clazz) {
        Query query = new Query(Criteria.where(fieldName).is(object));
        return mongoTemplate.find(query, clazz);
    }
    public <T>List<T> findDocumentsWithSingleFieldQueries(String fieldName, List<String> messageIds, Class<T> clazz) {
        Query query = new Query(Criteria.where(fieldName).in(messageIds));
        return mongoTemplate.find(query, clazz);
    }

    public <T>List<T> findDocumentsWithMultipleFieldQueries(List<String> fields, List<String> messageIds, Class<T> clazz) {
        if (fields.size() != messageIds.size()) throw new IllegalArgumentException("Field size and value size is not same");

        List<Criteria> criteriaList = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            criteriaList.add(Criteria.where(fields.get(i)).is(messageIds.get(i)));
        }

        Query query = new Query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));

        return mongoTemplate.find(query, clazz);
    }
}
