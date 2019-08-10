package com.su.dontcare.service;

import com.su.dontcare.service.entity.FieldInfo;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
import com.su.dontcare.service.entity.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private GenerCodeService codeService;
    /**
    *
    *@Description: 生成单表文件
    *@Param: tableName
    *@Author: guanzhou.su
    *@Date: 2019/8/9
    *@return:
     *
    **/
    public void generatorBySingleTable(GeneratorCodeInfo codeInfo){
        // 获取表的信息
        TableInfo tableInfo = getTabelInfo(codeInfo.getTableInfo().getTableName());
        codeInfo.setTableInfo(tableInfo);
        codeService.generCode(codeInfo);
    }


    /**
     *
     *@Description: 根据表名称获取表字段信息
     *@Param: [tableName]
     *@Author: guanzhou.su
     *@Date: 2019/8/9
     *@return: com.su.dontcare.service.entity.TableInfo
     *
     **/
    public TableInfo getTabelInfo(String tableName) {

        String sql = "select * from " + tableName + " limit 0";
        Connection con = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet resultSet = null;
        ResultSetMetaData metaData;
        java.sql.DatabaseMetaData dma;
        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);
            metaData = resultSet.getMetaData();
            DatabaseMetaData dbmd= con.getMetaData();
            ResultSet pks = dbmd.getPrimaryKeys(null, null, tableName);
            ResultSetMetaData pkmd = pks.getMetaData();
            String primaryKey = null;
            while(pks.next()){
                primaryKey = pks.getString("COLUMN_NAME");
            }
            TableInfo info = new TableInfo();
            int columnCount = metaData.getColumnCount();
            List<FieldInfo> fields = new ArrayList<>();
            info.setTableName(metaData.getTableName(1));
            for (int i = 1; i <= columnCount; i++) {
                FieldInfo fieldInfo = new FieldInfo();
                String columnName = metaData.getColumnName(i);
                if (primaryKey != null && primaryKey.equals(columnName)) {
                    fieldInfo.setName(metaData.getColumnName(i));
                    fieldInfo.setType(metaData.getColumnTypeName(i));
                    fieldInfo.setPrimaryKey(true);
                    fields.add(fieldInfo);
                    continue;
                }

                fieldInfo.setName(metaData.getColumnName(i));
                fieldInfo.setType(metaData.getColumnTypeName(i));
                fields.add(fieldInfo);
            }
            info.setFields(fields);
            return info;
        } catch(Exception ex) {
          ex.printStackTrace();
        } finally {
            try {
                con.close();
                resultSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
