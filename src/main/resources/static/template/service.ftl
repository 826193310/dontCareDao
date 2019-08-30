package ${info.packName};

<#list info.serviceImportClass as importClass>
import ${importClass};
</#list>

@Service
public class ${info.className} <#if info.extendsClassName?exists>extends ${info.extendsClassName}</#if>{

    @Autowired
    private ${info.mapperClass} ${info.mapperName};

    @Transactional
    public ${info.respVo}<${info.listSearchVo}> add(${info.listSearchVo} ${info.dtoValueName}) {
        ${info.mapperName}.insertDynamic(${info.dtoValueName});
        ${info.respVo}<${info.listSearchVo}> resp = new ${info.respVo}<${info.listSearchVo}>();
        resp.${info.genericFiledSeter}(${info.dtoValueName});
        return resp;
    }
    <#if info.tableInfo.primaryKey?exists>

    @Transactional
    public ${info.respVo}<Void> deleteByPrimary(${info.primaryKeyType} id) {
        ${info.respVo}<Void> resp = new ${info.respVo}<Void>();
        ${info.mapperName}.deleteByPrimary(id);
        return resp;
    }

    @Transactional
    public ${info.respVo}<Void> updateByPrimary(${info.listSearchVo} ${info.dtoValueName}) {
        ${info.respVo}<Void> resp = new ${info.respVo}<Void>();
        ${info.mapperName}.updateByPrimary(${info.dtoValueName});
        return resp;
    }
    </#if>

    public <#if (info.respGeneric)>${info.respVo}<List<${info.listSearchVo}>><#else>List<${info.listSearchVo}></#if> selectList(${info.listSearchVo} ${info.dtoValueName}) {
        List<${info.listSearchVo}> list = ${info.mapperName}.selectListByDto(${info.dtoValueName});
        <#if (info.respGeneric)>
        ${info.respVo}<List<${info.listSearchVo}>> resp = new ${info.respVo}<List<${info.listSearchVo}>>();
        resp.${info.genericFiledSeter}(list);
        return resp;
        <#else>
        return list;
        </#if>
    }
    <#if (info.enablePageHelper)>

    public <#if (info.pageRespGeneric)>${info.pageRespVo}<${info.listSearchVo}><#else>PageInfo<${info.listSearchVo}></#if> selectPageList(${info.listSearchVo} ${info.dtoValueName}, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<${info.listSearchVo}> list = ${info.mapperName}.selectListByDto(${info.dtoValueName});
        PageInfo<${info.listSearchVo}> page = new PageInfo<${info.listSearchVo}>(list);
        <#if (info.pageRespGeneric)>
        ${info.pageRespVo}<${info.listSearchVo}> pageResp = new ${info.pageRespVo}<${info.listSearchVo}>();
        pageResp.${info.pageVoAttr.listToPageMethod}(page);
        return pageResp;
        <#else>
        return page;
        </#if>
    }
    </#if>

    <#if info.primaryKeyType?exists>
    public <#if (info.respGeneric)>${info.respVo}<${info.listSearchVo}><#else>${info.listSearchVo}</#if> findByPrimary(${info.primaryKeyType} id) {
        ${info.listSearchVo} respVo = ${info.mapperName}.selectByPrimary(id);
         <#if (info.respGeneric)>
         ${info.respVo}<${info.listSearchVo}> resp = new ${info.respVo}<${info.listSearchVo}>();
         resp.${info.genericFiledSeter}(respVo);
         return resp;
         <#else>
         return respVo;
         </#if>
    }
    </#if>
}