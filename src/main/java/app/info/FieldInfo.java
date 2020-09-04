package app.info;

import app.model.Field;
import app.model.FieldType;
import org.springframework.beans.BeanUtils;

public class FieldInfo {

    private Integer id;

    private String name;

    private String address;

    private Integer area;

    private Integer price;

    private FieldType fieldType;

    public FieldInfo() {
    }

    public FieldInfo(Integer id, String name, String address, Integer area, Integer price, FieldType fieldType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.area = area;
        this.price = price;
        this.fieldType = fieldType;
    }

    public FieldInfo(Field field) {
        this.id = field.getId();
        this.name = field.getName();
        this.address = field.getAddress();
        this.area = field.getArea();
        this.price = field.getPrice();
        this.fieldType = field.getFieldType();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public Field toField() {
        Field field = new Field();

        BeanUtils.copyProperties(this, field);
        return field;
    }
}


