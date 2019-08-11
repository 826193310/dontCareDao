package ${info.packName};


public class ${info.className} {

<#list info.tableInfo.fields as field>

    // ${field.commons}
    private ${field.javaType} ${field.name};
</#list>
}