package ${info.packName};

<#list info.controllerImportClass as importClass>
import ${importClass};
</#list>

@RestController
public class ${info.className} <#if info.extendsClassName?exists>extends ${info.extendsClassName}</#if>{

    @Autowired
    private ${info.serviceClass} ${info.serviceName};

    @RequestMapping(value="${info.pathName}/add",method=RequestMethod.POST)
    public ${info.respVo}<${info.paramVo}> add(@RequestBody ${info.paramVo} ${info.pathName}) {
        ${info.respVo}<${info.paramVo}> resp = new ${info.respVo}<${info.paramVo}>();
        resp = ${info.serviceName}.add(${info.pathName});
        resp.${info.genericFiledSeter}(${info.pathName});
        return resp;
    }
    <#if info.tableInfo.primaryKey?exists>

    @RequestMapping(value="${info.pathName}/deleteByPrimary/{id}",method=RequestMethod.DELETE)
    public ${info.respVo}<Void> deleteByPrimary(@PathVariable ${info.primaryKeyType} id) {
        ${info.respVo}<Void> resp = new ${info.respVo}<Void>();
        ${info.serviceName}.deleteByPrimary(id);
        return resp;
    }

    @RequestMapping(value="${info.pathName}/updateByPrimary",method=RequestMethod.POST)
    public ${info.respVo}<Void> updateByPrimary(@RequestBody ${info.paramVo} ${info.pathName}) {
        ${info.respVo}<Void> resp = new ${info.respVo}<Void>();
        ${info.serviceName}.updateByPrimary(${info.pathName});
        return resp;
    }
    </#if>

    @RequestMapping(value="${info.pathName}/selectList",method=RequestMethod.GET)
    public <#if (info.respGeneric)>${info.respVo}<List<${info.paramVo}>><#else>List<${info.paramVo}></#if> selectList(${info.paramVo} ${info.pathName}) {
        <#if (info.respGeneric)>
            ${info.respVo}<List<${info.paramVo}>> resp = new ${info.respVo}<List<${info.paramVo}>>();
            resp = ${info.serviceName}.selectList(${info.pathName});
            return resp;
            <#else>
            return ${info.serviceName}.selectList(${info.pathName});
        </#if>

    }
    <#if (info.enablePageHelper)>

    @RequestMapping(value="${info.pathName}/selectPageList",method=RequestMethod.GET)
    public <#if (info.pageRespGeneric)>${info.pageRespVo}<${info.paramVo}><#else>PageInfo<${info.paramVo}></#if> selectPageList(${info.paramVo} ${info.pathName}, int currentPage, int pageSize) {
        <#if (info.pageRespGeneric)>
        ${info.pageRespVo}<${info.paramVo}> pageResp = ${info.serviceName}.selectPageList(${info.pathName}, currentPage, pageSize);
        return pageResp;
        <#else>
        PageInfo<${info.paramVo}> page =  ${info.serviceName}.selectPageList(${info.pathName}, currentPage, pageSize);
        return page;
        </#if>
    }
    </#if>

    <#if info.primaryKeyType?exists>
    @RequestMapping(value="${info.pathName}/findById/{id}",method=RequestMethod.GET)
    public <#if (info.respGeneric)>${info.respVo}<${info.paramVo}><#else>${info.paramVo}</#if> findByPrimary(@PathVariable("id") ${info.primaryKeyType} id) {
        return ${info.serviceName}.findByPrimary(id);
    }
    </#if>
}