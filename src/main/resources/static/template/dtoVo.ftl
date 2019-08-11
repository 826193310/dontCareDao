package ${info.packName};

<#list info.dtoImportClass as importClass>
    import ${importClass};
</#list>

public class ${info.className} {

<#list info.tableInfo.fields as field>

    // ${field.commons}
    private ${field.javaType} ${field.name};
</#list>

<#list gsters as gster>

    public  ${gster.javaType} ${gster.getterName}() {
        return ${gster.name};
    }

    public void ${gster.setterName}(${gster.javaType} ${gster.name}) {
        this.${gster.name} = ${gster.name};
    }
</#list>



}