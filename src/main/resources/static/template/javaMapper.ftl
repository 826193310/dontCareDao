package ${info.packName};

<#list importClasses as importClass>
import ${importClass};
</#list>

public interface ${info.className} {

    int insert(${dtoName} ${info.tableInfo.tableName});

    /**
    *
    * 动态插入处理
    *
    **/
    int insertDynamic(${dtoName} ${info.tableInfo.tableName});

    <#if primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 删除数据
    *
    **/
    int deleteByPrimary(${primaryKeyType} id);

    </#if>
    <#if primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 获取数据
    *
    **/
    ${dtoName} selectByPrimary(${primaryKeyType} id);

    </#if>
    /**
    *
    * 根据 dto 获取列表数据
    *
    **/
    List<${dtoName}> selectListByDto(${dtoName} ${info.tableInfo.tableName});

    /**
    *
    * 根据 dto 获取单条记录
    *
    **/
    ${dtoName} selectOneByDto(${dtoName} ${info.tableInfo.tableName});

    <#if primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 更新数据
    *
    **/
    int updateByPrimary(${dtoName} ${info.tableInfo.tableName});

    </#if>
}