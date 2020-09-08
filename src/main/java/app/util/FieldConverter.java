package app.util;

import app.info.FieldInfo;
import app.model.Field;
import app.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FieldConverter implements Converter<String, Field> {

    private final FieldService fieldService;

    @Autowired
    public FieldConverter(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @Override
    public Field convert(String id) {
        try {
            FieldInfo fieldInfo = fieldService.findField(Integer.parseInt(id));
            return fieldInfo.toField();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
