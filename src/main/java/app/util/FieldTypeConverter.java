package app.util;

import app.info.FieldTypeInfo;
import app.model.FieldType;
import app.service.FieldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FieldTypeConverter implements Converter<String, FieldType> {
    @Autowired
    FieldTypeService fieldTypeService;

    @Override
    public FieldType convert(String id) {
        try {
            FieldTypeInfo fieldTypeInfo = fieldTypeService.findFieldType(Integer.parseInt(id));
            return fieldTypeInfo.toFieldType();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
