表名称： ${tableInfo.tableName}

表字段信息：
<#list tableInfo.fields as field>
    ${field_index}
    ${field.name}
    ${field.type}
    ${field.primaryKey?string("true","flase")}
</#list>