package com.refactoring.rxo.entity.dict;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.refactoring.rxo.mybatis.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 数据字典项目
 */
@TableName("dictionary_item")
public class DictionaryItem extends BaseEntity<DictionaryItem> {

    public static final String PROPERTY_DICT_ID = "dictId";
    public static final String PROPERTY_ITEM_NAME = "itemName";
    public static final String PROPERTY_ITEM_CODE = "itemCode";
    public static final String PROPERTY_ITEM_SORT = "itemSort";

    public DictionaryItem(String typeId) {
        this.typeId = typeId;
    }

    private Integer version;


    /**
     * 字典类型ID
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String typeId;


    /**
     * 字典项目名称
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String name;


    /**
     * 字典项目值
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String code;


    /**
     * 排序
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private Integer sort;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
