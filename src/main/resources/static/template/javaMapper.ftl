package ${info.packName};

<#list info.mapperImportClass as importClass>
import ${importClass};
</#list>

public interface ${info.className} <#if info.extendsClassName?exists>extends ${info.extendsClassName}</#if>{

    int insert(${info.dtoName} ${info.dtoValueName});

    /**
    *
    * 动态插入处理
    *
    **/
    int insertDynamic(${info.dtoName} ${info.dtoValueName});

    <#if info.primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 删除数据
    *
    **/
    int deleteByPrimary(${info.primaryKeyType} id);

    </#if>
    /**
    *
    * 根据 dto 条件批量删除
    *
    **/
    int deleteByDtoCondition(${info.dtoName} ${info.dtoValueName});

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
    List<${info.dtoName}> selectListByDto(${info.dtoName} ${info.dtoValueName});

    /**
    *
    * 根据 dto 获取单条记录
    *
    **/
    ${info.dtoName} selectOneByDto(${info.dtoName} ${info.dtoValueName});

    /**
    *
    * 传入对象，统计匹配的数据条数（适用于增加时判断数据是否有重复）
    *
    **/
    int countData(${info.dtoName} ${info.dtoValueName});

    <#if info.primaryKeyType?exists>
    /**
    *
    * 根据主键 ID 更新数据
    *
    **/
    int updateByPrimary(${info.dtoName} ${info.dtoValueName});

    </#if>
}