package com.refactoring.rxo.entity.dict;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.refactoring.rxo.mybatis.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 数据字典业务
 */
@TableName("dictionary_business")
public class DictionaryBusiness extends BaseEntity<DictionaryBusiness> {

    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_CODE = "code";


    private Integer version;


    /**
     * 业务名称
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String name;


    /**
     * 业务编码
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String code;


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


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
