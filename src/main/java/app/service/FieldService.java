package app.service;

import app.info.FieldInfo;
import app.model.Field;

import java.util.List;

public interface FieldService {

    FieldInfo findField(Integer id);

    boolean createField(FieldInfo field);

    boolean updateField(FieldInfo field);

    FieldInfo findByName(String name);

    List<FieldInfo> searchFields(String key);

    List<FieldInfo> loadFields();
}
