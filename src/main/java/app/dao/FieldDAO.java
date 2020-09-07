package app.dao;

import app.model.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FieldDAO extends BaseDAO<Integer, Field> {

    Field findByName(String name);

//    List<Field> searchFields(String key);

    List<Field> loadFields();

    Page<Field> searchFields(String key, Pageable pageable);

    Page<Field> paginateField(Pageable pageable);
}
