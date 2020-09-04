package app.dao;

import app.model.Field;

import java.util.List;

public interface FieldDAO extends BaseDAO<Integer, Field> {

    Field findByName(String name);

    List<Field> searchFields(String key);

    List<Field> loadFields();
}
