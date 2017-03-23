package com.msxf.paycore.dal.online.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* ${comment!""}
* @desc	使用代码生成器生成.
* @date 	${.now?string("yyyy/MM/dd")}
*/
@Entity
@Table(name = "${tableName}")
public class ${className} extends BaseEntity {
<#setting number_format="#">
<#list columns as column>

    <#assign autograph = "private " + column.propertyType + " " + column.propertyName + " ;">
    <#if column.propertyName != "id">
    /** ${column.columnComment} */ <#if column.propertyType == "java.util.Date">${"\n\t"}@javax.persistence.Temporal(javax.persistence.TemporalType<#if column.columnType == "DATE" || column.columnType == "date">.DATE<#else>.TIMESTAMP</#if>)</#if>
    @Column(name = "${column.columnName}"<#if column.columnSize != 0> ,length = ${column.columnSize}</#if><#if column.isNullable == "NO"> ,nullable = false</#if><#if column.columnKey == "UNI"> ,unique = true</#if>)
    ${autograph}
    </#if>
</#list>

<#list columns as column>
    <#if column.propertyName != "id">
    /** ${column.columnComment} */
    public void set${column.propertyName?cap_first} (${column.propertyType} ${column.propertyName}){
        this.${column.propertyName} = ${column.propertyName} ;
    }

    /** ${column.columnComment} */
    public ${column.propertyType} get${column.propertyName?cap_first} (){
        return this.${column.propertyName} ;
    }

    </#if>
</#list>
}