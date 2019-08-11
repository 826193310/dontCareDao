package com.su.dontcare.service;

import com.su.dontcare.Enum.DataBaseTypeEnum;
import com.su.dontcare.constant.YmlPropertiesConst;
import com.su.dontcare.service.entity.FieldInfo;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
import com.su.dontcare.service.entity.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private GenerCodeService codeService;

    @Autowired
    YmlPropertiesConst ymlPropertiesConst;
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

        Connection con = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        ResultSetMetaData metaData;
        java.sql.DatabaseMetaData dma;
        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();

            DatabaseMetaData dbmd= con.getMetaData();
            ResultSet pks = dbmd.getPrimaryKeys(null, null, tableName);
            rs = dbmd.getColumns(null, "%", tableName, "%");

            ResultSetMetaData pkmd = pks.getMetaData();
            String primaryKey = null;
            while(pks.next()){
                //pks.
                primaryKey = pks.getString("COLUMN_NAME");
            }
            TableInfo info = new TableInfo();
            List<FieldInfo> fields = new ArrayList<>();

            while(rs.next()){
                FieldInfo fieldInfo = new FieldInfo();
                //列名
                String columnName = rs.getString("COLUMN_NAME");
                String typeName = rs.getString("TYPE_NAME");
                String remarks = rs.getString("REMARKS");
                fieldInfo.setName(columnName);
                fieldInfo.setType(typeName);
                fieldInfo.setCommons(remarks);
                if (primaryKey != null && primaryKey.equals(columnName)) {
                    fieldInfo.setPrimaryKey(true);
                }
                fields.add(fieldInfo);
            }

            info.setTableName(getTableName(tableName));
            info.setDriverClass(ymlPropertiesConst.driverClass);
            info.setFields(fields);
            return info;
        } catch(Exception ex) {
          ex.printStackTrace();
          System.exit(0);
        } finally {
            try {
                con.close();
                rs.close();
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getTableName(String tableName) {
        Connection con = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        ResultSetMetaData metaData;
        java.sql.DatabaseMetaData dma;
        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();
            String sql = setSelectSqlByDataBaseType(tableName);
            ResultSet resultSet = stmt.executeQuery(sql);
            return resultSet.getMetaData().getTableName(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                con.close();
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String setSelectSqlByDataBaseType(String table) {
        return DataBaseTypeEnum.findSql(ymlPropertiesConst.driverClass).replace("TABLE", table);
    }
}
