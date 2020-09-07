package app.service;

import app.info.FieldInfo;
import app.model.Field;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FieldService {

    FieldInfo findField(Integer id);

    boolean createField(FieldInfo field);

    boolean updateField(FieldInfo field);

    FieldInfo findByName(String name);

//    List<FieldInfo> searchFields(String key);
    Page<FieldInfo> searchFields(String key, FieldInfo fieldInfo);

    List<FieldInfo> loadFields();

    Page<FieldInfo> paginate(FieldInfo fieldInfo);
}
