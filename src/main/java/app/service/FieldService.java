package app.service;

import app.info.FieldInfo;
import app.model.Field;

import java.util.List;

public interface FieldService {

    Field findField(Integer id);

    boolean createField(FieldInfo field);

    boolean updateField(FieldInfo field);

    Field findByName(String name);

    List<Field> searchFields(String key);

    List<Field> loadFields();
}
