package com.refactoring.rxo.entity.dict;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.refactoring.rxo.datasource.mybatis.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



/**
* 数据字典类型
*/
@TableName("dictionary_type")
public class DictionaryType  extends BaseEntity<DictionaryType> {

    public static final String PROPERTY_CODE = "code";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_BUSINESS_ID = "businessId";

	public DictionaryType() {
	}

	public DictionaryType(String businessId) {
		this.businessId = businessId;
	}

	private Integer version;


	/**
	* 类型编码
	*/
        @TableField(strategy = FieldStrategy.NOT_NULL)
	private String code;


	/**
	* 类型名称
	*/
        @TableField(strategy = FieldStrategy.NOT_NULL)
	private String name;


	/**
	* 所属业务ID
	*/
        @TableField(strategy = FieldStrategy.NOT_NULL)
	private String businessId;


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessId(){
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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
