<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${info.mapperNameSpace}">
    <resultMap id="${info.dtoValueName}Map" type="${info.insertDtoParamType}">
        <#list info.tableInfo.fields as field>
        <result property="${field.name}" column="${field.sourceName}" />
        </#list>
    </resultMap>

    <sql id="baseColumns">
        <#list info.tableInfo.fields as field>${field.sourceName}<#if (info.tableInfo.fields?size == field_index + 1)><#else>, </#if></#list>
    </sql>

    <sql id="equalFiled">
        <where>
            <#list info.tableInfo.fields as field>
                <if test="${field.name} != null"> AND ${field.sourceName} = <#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse></if>
            </#list>
        </where>
    </sql>


    <insert id="insert" parameterType="${info.insertDtoParamType}" <#if info.tableInfo.primaryKey?exists >useGeneratedKeys="true" keyProperty="${info.tableInfo.primaryKey}"</#if>>
        INSERT INTO ${info.tableInfo.tableName} (
            <#list info.fieldsNotContainId as field>${field.sourceName}<#if (info.fieldsNotContainId?size == field_index + 1)><#else>, </#if></#list>
        ) VALUES (
            <#list info.fieldsNotContainId as field><#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse><#if (info.fieldsNotContainId?size == field_index + 1)><#else>, </#if></#list>
        )
    </insert>

    <insert id="insertDynamic" parameterType="${info.insertDtoParamType}" <#if info.tableInfo.primaryKey?exists >useGeneratedKeys="true" keyProperty="${info.tableInfo.primaryKey}"</#if>>
        INSERT INTO ${info.tableInfo.tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list info.fieldsNotContainId as field>
            <if test="${field.name} != null">${field.sourceName},</if>
        </#list>
        </trim>
         VALUES
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list info.fieldsNotContainId as field>
            <if test="${field.name} != null"><#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,</if>
        </#list>
        </trim>
    </insert>

    <#if info.tableInfo.primaryKey?exists >
    <delete id="deleteByPrimary">
        DELETE FROM ${info.tableInfo.tableName} WHERE ${info.tableInfo.primaryKey} = <#noparse>#{id}</#noparse>
    </delete>

    </#if>
    <delete id="deleteByDtoCondition" parameterType="${info.insertDtoParamType}">
        DELETE FROM ${info.tableInfo.tableName} WHERE
        <trim prefix="" suffix="" suffixOverrides="AND" >
            <#list info.tableInfo.fields as field>
            <if test="${field.name} != null">  ${field.sourceName} = <#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse> AND</if>
            </#list>
        </trim>
    </delete>
    <#if info.tableInfo.primaryKey?exists >

    <select id="selectByPrimary" resultMap="${info.dtoValueName}Map" parameterType="${info.insertDtoParamType}" >
        SELECT <include refid="baseColumns" />  FROM ${info.tableInfo.tableName} WHERE ${info.tableInfo.primaryKey} = <#noparse>#{id}</#noparse>
    </select>

    </#if>
    <select id="selectListByDto" resultMap="${info.dtoValueName}Map" parameterType="${info.insertDtoParamType}">
        SELECT <include refid="baseColumns" />  FROM ${info.tableInfo.tableName} <include refid="equalFiled" />
    </select>

    <select id="selectOneByDto" resultMap="${info.dtoValueName}Map" parameterType="${info.insertDtoParamType}">
        SELECT <include refid="baseColumns" /> FROM ${info.tableInfo.tableName} <include refid="equalFiled" />
    </select>

    <select id="countData" resultType="int" parameterType="${info.insertDtoParamType}">
        SELECT count(1) FROM ${info.tableInfo.tableName} <include refid="equalFiled" />
    </select>

    <#if info.tableInfo.primaryKey?exists>
    <update id="updateByPrimary" parameterType="${info.insertDtoParamType}">
        UPDATE ${info.tableInfo.tableName}
        <set>
        <#list info.fieldsNotContainId as field>
            <if test="${field.name} != null">${field.sourceName} = <#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,</if>
        </#list>
        </set>
        WHERE ${info.tableInfo.primaryKey} = <#noparse>#{</#noparse>${info.tableInfo.dtoprimaryKey}<#noparse>}</#noparse>
    </update>
    </#if>
</mapper>