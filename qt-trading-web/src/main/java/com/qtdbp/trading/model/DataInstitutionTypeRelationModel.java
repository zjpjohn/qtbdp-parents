package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据商类型关系
 */
@ApiModel(value = "data_institution_type_relation",description = "数据商类型关系")
public class DataInstitutionTypeRelationModel {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_institution_type_relation.id
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	@ApiModelProperty(hidden=true)
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_institution_type_relation.institution_id
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	@ApiModelProperty(hidden=true)
	private Integer institutionId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_institution_type_relation.data_type
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	@ApiModelProperty(name = "dataType", value = "数据类型", dataType = "Integer")
	private Integer dataType;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_institution_type_relation.id
	 * @return  the value of data_institution_type_relation.id
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_institution_type_relation.id
	 * @param id  the value for data_institution_type_relation.id
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_institution_type_relation.institution_id
	 * @return  the value of data_institution_type_relation.institution_id
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	public Integer getInstitutionId() {
		return institutionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_institution_type_relation.institution_id
	 * @param institutionId  the value for data_institution_type_relation.institution_id
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_institution_type_relation.data_type
	 * @return  the value of data_institution_type_relation.data_type
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	public Integer getDataType() {
		return dataType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_institution_type_relation.data_type
	 * @param dataType  the value for data_institution_type_relation.data_type
	 * @mbggenerated  Fri Jul 24 15:58:25 CST 2015
	 */
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
}