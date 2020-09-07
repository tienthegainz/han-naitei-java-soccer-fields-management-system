package app.service.impl;

import app.info.FieldTypeInfo;
import app.model.FieldType;
import app.service.FieldTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FieldTypeServiceImpl extends BaseServiceImpl implements FieldTypeService {
    private static final Logger logger = Logger.getLogger(FieldTypeServiceImpl.class);

    @Override
    public FieldTypeInfo findFieldType(int id) {
        try {
            FieldType fieldType = getFieldTypeDAO().findById(id, false);
            return new FieldTypeInfo(fieldType);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean createFieldType(FieldTypeInfo fieldTypeInfo) {
        try {
            getFieldTypeDAO().saveOrUpdate(fieldTypeInfo.toFieldType());
            logger.info("Created new Field Type");
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean updateFieldType(FieldTypeInfo fieldTypeInfo) {
        String[] ignoreProperties = {"id"};

        try {
            FieldType fieldType = getFieldTypeDAO().findById(fieldTypeInfo.getId(), true);
            BeanUtils.copyProperties(fieldTypeInfo.toFieldType(), fieldType, ignoreProperties);
            getFieldTypeDAO().saveOrUpdate(fieldType);
            logger.info(String.format("Updated Field Type having id: %d", fieldTypeInfo.getId()));
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean deleteFieldType(int id) {
        try {
            FieldType fieldType = getFieldTypeDAO().findById(id, true);
            getFieldTypeDAO().delete(fieldType);
            logger.info(String.format("Delete Field Type having id: %d", id));
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public FieldTypeInfo findByName(String name) {
        try {
            FieldType fieldType = getFieldTypeDAO().findByName(name);
            logger.info(String.format("Find Field Type with name %s", name));
            return new FieldTypeInfo(fieldType);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<FieldTypeInfo> loadFieldTypes() {
        try {
            List<FieldType> fieldTypes = getFieldTypeDAO().loadFieldTypes();
            logger.info("Get Field Types list");
            return fieldTypes.stream().map(FieldTypeInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
