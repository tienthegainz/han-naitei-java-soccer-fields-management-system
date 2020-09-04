package app.service.impl;

import app.info.FieldInfo;
import app.model.Field;
import app.service.FieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class FieldServiceImpl extends BaseServiceImpl implements FieldService {
    private static final Logger logger = Logger.getLogger(FieldServiceImpl.class);

    @Override
    public Field findField(Integer id) {
        try {
            return getFieldDAO().findById(id, false);
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean createField(FieldInfo fieldInfo) {
        try {
            getFieldDAO().saveOrUpdate(fieldInfo.toField());
            logger.info("Created new Field ");
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean updateField(FieldInfo fieldInfo) {
        try {
            Field field = getFieldDAO().findById(fieldInfo.getId(), true);
            BeanUtils.copyProperties(fieldInfo.toField(), field);
            getFieldDAO().saveOrUpdate(field);
            logger.info(String.format("Updated Field having id: %d", fieldInfo.getId()));
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public Field findByName(String name) {
        try {
            Field field = getFieldDAO().findByName(name);
            logger.info(String.format("Find Field  with name %s", name));
            return field;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<Field> searchFields(String key) {
        try {
            List<Field> fields = getFieldDAO().searchFields(key);
            logger.info(String.format("Search Field with: %s", key));
            return fields;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<Field> loadFields() {
        try {
            List<Field> fields = getFieldDAO().loadFields();
            logger.info("Get Field as list");
            return fields;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
