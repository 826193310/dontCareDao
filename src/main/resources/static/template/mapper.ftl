<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${info.mapperNameSpace}">

    <sql id="baseColumns">
        <#list info.tableInfo.fields as field>${field.name}<#if (info.tableInfo.fields?size == field_index + 1)><#else>, </#if></#list>
    </sql>

    <sql id="equalFiled">
        <where>
            <#list info.tableInfo.fields as field>
                <if test="${field.name} != null"> AND ${field.name} = <#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse></if>
            </#list>
        </where>
    </sql>

    <insert id="insert" parameterType="${info.insertDtoParamType}">
        INSERT INTO ${info.tableInfo.tableName} (
            <#list info.fieldsNotContainId as field>${field.name}<#if (info.fieldsNotContainId?size == field_index + 1)><#else>, </#if></#list>
        ) VALUES (
            <#list info.fieldsNotContainId as field><#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse><#if (info.fieldsNotContainId?size == field_index + 1)><#else>, </#if></#list>
        )
    </insert>

    <insert id="insertDynamic" parameterType="${info.insertDtoParamType}">
        INSERT INTO ${info.tableInfo.tableName} (
        <#list info.fieldsNotContainId as field>
            <if test="${field.name} != null">${field.name}<#if (info.fieldsNotContainId?size == field_index + 1)></if><#else>,</if></#if>
        </#list>
        ) VALUES (
        <#list info.fieldsNotContainId as field>
            <if test="${field.name} != null"><#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse><#if (info.fieldsNotContainId?size == field_index + 1)></if><#else>,</if></#if>
        </#list>
        )
    </insert>

    <#if info.tableInfo.primaryKey?exists >
    <delete id="deleteByPrimary">
        DELETE FROM ${info.tableInfo.tableName} WHERE ${info.tableInfo.primaryKey} = <#noparse>#{id}</#noparse>
    </delete>

    </#if>
    <#if info.tableInfo.primaryKey?exists >
    <select id="selectByPrimary" resultType="${info.insertDtoParamType}" parameterType="${info.insertDtoParamType}" >
        SELECT <include refid="baseColumns" />  FROM ${info.tableInfo.tableName} WHERE ${info.tableInfo.primaryKey} = <#noparse>#{id}</#noparse>
    </select>

    </#if>
    <select id="selectListByDto" resultType="${info.insertDtoParamType}" parameterType="${info.insertDtoParamType}">
        SELECT <include refid="baseColumns" />  FROM ${info.tableInfo.tableName} <include refid="equalFiled" />
    </select>

    <select id="selectOneByDto" resultType="${info.insertDtoParamType}" parameterType="${info.insertDtoParamType}">
        SELECT <include refid="baseColumns" /> FROM ${info.tableInfo.tableName} <include refid="equalFiled" />
    </select>

    <select id="countData" resultType="int" parameterType="${info.insertDtoParamType}">
        SELECT count(1) FROM ${info.tableInfo.tableName} <include refid="equalFiled" />
    </select>

    <#if info.tableInfo.primaryKey?exists>
    <update id="updateByPrimary" parameterType="${info.insertDtoParamType}">
        UPDATE ${info.tableInfo.tableName}
        <#list info.fieldsNotContainId as field>
            <set><if test="${field.name} != null">${field.name} = <#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,</if></set>
        </#list>
        WHERE ${info.tableInfo.primaryKey} = <#noparse>#{</#noparse>${info.tableInfo.primaryKey}<#noparse>}</#noparse>
    </update>
    </#if>
</mapper>