package ${info.packName};

<#--<#list info.dtoImportClass as importClass>
import ${importClass};
</#list>-->

@Service
public class ${info.className} {

    @Autowired
    private ${info.mapperClass} ${info.mapperName};

    public <#if (info.respGeneric)>${info.respVo}<List<${info.listSearchVo}>><#else>List<${info.listSearchVo}></#if> selectList(${info.listSearchVo} ${info.tableInfo.tableName}) {
        List<${info.listSearchVo}> list = ${info.mapperName}.selectListByDto(${info.tableInfo.tableName});
        ${info.respVo}<List<${info.listSearchVo}>> resp = new ${info.respVo}<List<${info.listSearchVo}>>();
        resp.${info.genericFiledSeter}(list);
        return resp;
    }

}