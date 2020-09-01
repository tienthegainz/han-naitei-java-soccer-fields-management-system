package app.info;

import app.model.FieldType;
import org.springframework.beans.BeanUtils;

public class FieldTypeInfo {

    private Integer id;

    private String name;

    private String description;

    private String photoURL;

    public FieldTypeInfo() {
    }


    public FieldTypeInfo(Integer id, String name, String description, String photoURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
    }

    public FieldTypeInfo(FieldType fieldType) {
        this.id = fieldType.getId();
        this.name = fieldType.getName();
        this.description = fieldType.getDescription();
        this.photoURL = fieldType.getPhotoURL();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public FieldType toFieldType() {
        FieldType fieldType = new FieldType();

        BeanUtils.copyProperties(this, fieldType);

        return fieldType;
    }
}
