package app.service;

import app.info.FieldTypeInfo;
import app.model.FieldType;

import java.util.List;

public interface FieldTypeService {

    FieldTypeInfo findFieldType(int id);

    boolean createFieldType(FieldTypeInfo fieldType);

    boolean updateFieldType(FieldTypeInfo fieldType);

    boolean deleteFieldType(int id);

    FieldTypeInfo findByName(String name);

    List<FieldTypeInfo> loadFieldTypes();
}
