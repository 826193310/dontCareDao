package ${info.packName};

<#list info.controllerImportClass as importClass>
import ${importClass};
</#list>

@RestController
public class ${info.className} <#if info.extendsClassName?exists>extends ${info.extendsClassName}</#if>{

    @Autowired
    private ${info.serviceClass} ${info.serviceName};

    @RequestMapping(value="${info.tableInfo.tableName}/add",method=RequestMethod.POST)
    public ${info.respVo}<${info.paramVo}> add(@RequestBody ${info.paramVo} ${info.tableInfo.tableName}) {
        ${info.respVo}<${info.paramVo}> resp = new ${info.respVo}<${info.paramVo}>();
        resp = ${info.serviceName}.add(${info.tableInfo.tableName});
        resp.${info.genericFiledSeter}(${info.tableInfo.tableName});
        return resp;
    }
    <#if info.tableInfo.primaryKey?exists>

    @RequestMapping(value="${info.tableInfo.tableName}/deleteByPrimary/{id}",method=RequestMethod.DELETE)
    public ${info.respVo}<Void> deleteByPrimary(@PathVariable ${info.primaryKeyType} id) {
        ${info.respVo}<Void> resp = new ${info.respVo}<Void>();
        ${info.serviceName}.deleteByPrimary(id);
        return resp;
    }

    @RequestMapping(value="${info.tableInfo.tableName}/updateByPrimary",method=RequestMethod.POST)
    public ${info.respVo}<Void> updateByPrimary(@RequestBody ${info.paramVo} ${info.tableInfo.tableName}) {
        ${info.respVo}<Void> resp = new ${info.respVo}<Void>();
        ${info.serviceName}.updateByPrimary(${info.tableInfo.tableName});
        return resp;
    }
    </#if>

    @RequestMapping(value="${info.tableInfo.tableName}/selectList",method=RequestMethod.GET)
    public <#if (info.respGeneric)>${info.respVo}<List<${info.paramVo}>><#else>List<${info.paramVo}></#if> selectList(${info.paramVo} ${info.tableInfo.tableName}) {
        <#if (info.respGeneric)>
            ${info.respVo}<List<${info.paramVo}>> resp = new ${info.respVo}<List<${info.paramVo}>>();
            resp = ${info.serviceName}.selectList(${info.tableInfo.tableName});
            return resp;
            <#else>
            return ${info.serviceName}.selectList(${info.tableInfo.tableName});
        </#if>

    }
    <#if (info.enablePageHelper)>

    @RequestMapping(value="${info.tableInfo.tableName}/selectPageList",method=RequestMethod.GET)
    public <#if (info.pageRespGeneric)>${info.pageRespVo}<${info.paramVo}><#else>PageInfo<${info.paramVo}></#if> selectPageList(${info.paramVo} ${info.tableInfo.tableName}, int currentPage, int pageSize) {
        <#if (info.pageRespGeneric)>
        ${info.pageRespVo}<${info.paramVo}> pageResp = ${info.serviceName}.selectPageList(${info.tableInfo.tableName}, currentPage, pageSize);
        return pageResp;
        <#else>
        PageInfo<${info.paramVo}> page =  ${info.serviceName}.selectPageList(${info.tableInfo.tableName}, currentPage, pageSize);
        return page;
        </#if>
    }
    </#if>

    <#if info.primaryKeyType?exists>
    @RequestMapping(value="${info.tableInfo.tableName}/findById/{id}",method=RequestMethod.GET)
    public <#if (info.respGeneric)>${info.respVo}<${info.paramVo}><#else>${info.paramVo}</#if> findByPrimary(@PathVariable("id") ${info.primaryKeyType} id) {
        return ${info.serviceName}.findByPrimary(id);
    }
    </#if>
}