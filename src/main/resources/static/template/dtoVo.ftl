package ${info.packName};


public class ${info.className} {

<#list info.tableInfo.fields as field>
    private ${field.type} ${field.name};
</#list>
}