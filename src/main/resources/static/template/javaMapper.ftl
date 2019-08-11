package ${info.packName};

<#list info.mapperImportClass as importClass>
import ${importClass};
</#list>

public interface ${info.className} {

    int insert(${info.dtoName} ${info.tableInfo.tableName});

    /**
    *
    * 动态插入处理
    *
    **/
    int insertDynamic(${info.dtoName} ${info.tableInfo.tableName});

    <#if info.primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 删除数据
    *
    **/
    int deleteByPrimary(${info.primaryKeyType} id);

    </#if>
    <#if info.primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 获取数据
    *
    **/
    ${info.dtoName} selectByPrimary(${info.primaryKeyType} id);

    </#if>
    /**
    *
    * 根据 dto 获取列表数据
    *
    **/
    List<${info.dtoName}> selectListByDto(${info.dtoName} ${info.tableInfo.tableName});

    /**
    *
    * 根据 dto 获取单条记录
    *
    **/
    ${info.dtoName} selectOneByDto(${info.dtoName} ${info.tableInfo.tableName});

    <#if info.primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 更新数据
    *
    **/
    int updateByPrimary(${info.dtoName} ${info.tableInfo.tableName});

    </#if>
}