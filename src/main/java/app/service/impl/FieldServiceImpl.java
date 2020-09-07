package app.service.impl;

import app.info.FieldInfo;
import app.model.Field;
import app.service.FieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class FieldServiceImpl extends BaseServiceImpl implements FieldService {
    private static final Logger logger = Logger.getLogger(FieldServiceImpl.class);

    @Override
    public FieldInfo findField(Integer id) {
        try {
            Field field = getFieldDAO().findById(id, false);
            return new FieldInfo(field);
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
    public FieldInfo findByName(String name) {
        try {
            Field field = getFieldDAO().findByName(name);
            logger.info(String.format("Find Field  with name %s", name));
            return new FieldInfo(field);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

//    @Override
//    public List<FieldInfo> searchFields(String key) {
//        try {
//            List<Field> fields = getFieldDAO().searchFields(key);
//            logger.info(String.format("Search Field with: %s", key));
//            return fields.stream().map(FieldInfo::new).collect(Collectors.toList());
//        } catch (Exception e) {
//            logger.error(e);
//            return null;
//        }
//    }

    @Override
    public Page<FieldInfo> searchFields(String key, FieldInfo fieldInfo) {
        try {
            Page<Field> fields = getFieldDAO().searchFields(key, fieldInfo.getPageable());
            logger.info(String.format("Search Field with: %s", key));
            return fields.map(field -> {
                FieldInfo model = new FieldInfo();
                BeanUtils.copyProperties(field, model);
                return model;
            });
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<FieldInfo> loadFields() {
        try {
            List<Field> fields = getFieldDAO().loadFields();
            logger.info("Get Field as list");
            return fields.stream().map(FieldInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public Page<FieldInfo> paginate(FieldInfo fieldInfo) {
        try {
            Page<Field> fields = getFieldDAO().paginateField(fieldInfo.getPageable());
            return fields.map(field -> {
                FieldInfo model = new FieldInfo();
                BeanUtils.copyProperties(field, model);
                return model;
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
