# dontCareDao
focus your service code,  and dont care anythings about others

dontCareDao 是一个使用 [freemarker](https://freemarker.apache.org/) 处理的自动代码生成工具，旨在通过代码生成，减轻我们工作中的负担， 待完成之后，预计会支持从 controller 到 dao 层的代码生成
旨在通过这个工具，能更加智能地生成 从 controller 到 Dao 层之间的通用逻辑代码，不用关心增加字段后或者一些其它非业务性的问题，开发更加高效，智能，方便

<a href="#feature" target="_self">特点</a><br>
<a href="#use" target="_self">使用</a><br>
<a href="#notice" target="_self">注意事项</a><br>
<a href="#feature" target="_self">后续更多功能</a><br>


<span id = "feature"><h1> 特点</h1></span>

#### 已实现
* 生成 service 层代码
* service 层面自定义自己的返回类
* 支持生成分页方法 （PageHelper形式）
* 支持生成的 DTO 继承自己自定义的类（需要填写自定义继承类的包含字段，一般是表公用字段，创建时间更新时间等）
* 多表批量生成支持（逗号分割表名， 如 t1,t2,t3）
* 支持自定义 Dto 名称，适合当表名称不适合直接变成Dto 名称的时候
#### 未实现
* 生成 Mapper.xml 之后，如果再生成，不会覆盖自己写的xml
* 支持是否集成 swagger ， 如果集成 swagger ，则会在controller 方法生成swagger 注解

#### 待考虑是否需要
* 批量更新 （小数据量）
* 批量 ID 删除 （小数据量）
* 批量插入（小数据量）

<span id="use"><h1> 使用</h1></span>
    目前 非正式版本仅支持在 DontcareApplicationTests 这个 test 类下进行生成, 主要通过核心配置类 GeneratorCodeInfo 生成
列出该类相关参数的说明， 后续可能通过配置文件来配置生成的信息
    
前提：application.yml 配置好数据库信息
    
参考示例：
    如果仅需要生成 Mapper 层面， 参考 DontcareApplicationTests的 generatorMapper 方法
    如果仅需要生成 Mapper,Service 层面， 参考 DontcareApplicationTests的 generatorMapperAndService 方法
    如果仅需要生成 Mapper,Service,  层面， 参考 DontcareApplicationTests的 generatorAll 方法

运行方式：
    以 junit Test 方式运行相关方法即可
    
   
### 生成 dao 层
* TableInfo tableInfo:表信息类， 设置表信息类的属性 tableName 为你需要生成的表 
* String outputPath: 生成文件跟目录，一般spring boot 项目配置到项目的 src/main/java路径下，参考 DontcareApplicationTests 类的 testGenerator 方法中的配置 
* String mapperPath: Mapper 接口生成地址
* String mapperXmlPath: 生成 Mapper.xml 到该包下
* String dtoPath: 生成 Dto 到该包下

### 生成 service 层
* boolean generatorService: 是否生成service 
* String servicePath: 生成 service文件 到该包下 

* String respClass: service 层的返回类，注意需要写返回类的全包名，example：com.su.dontcare.service.entity.Resp
* boolean respGeneric: 返回类是否为泛型
* String genericFiled: 返回类泛型字段 例如 class<T> { private <T> obj} 中的 obj就是泛型字段, 此处设值则为 "obj"

#### 生成分页方法相关属性
* boolean enablePageHelper: 是否生成分页方法
* String pageRespClass: 分页返回接收类， 注意需要写返回类的全包名
* boolean pageRespGeneric: 分页返回类是否为泛型
* PageVoAttr: 分页类相关属性（涉及分页插件的数据组装到返回类等信息）
* String PageVoAttr.listToPageMethod: 组装 page 信息到返回类的方法， 主要是你返回类中，负责把 pageInfo 的信息组装到返回类中， 比如pageSie, pageNum 等

### 生成 controller 层
* boolean generController: 是否生成 controller 层
* String controllerPath: 生成 controller 层到该包下
  

<span id = "notice"><h1> 使用注意事项</h1></span>
* 如果需要生成 Service 层，需要填写 respVo 属性为你返回类的全包名，且需要为泛型
* 目前生成的类名和 service 基本都是以表名为准，后续支持自定义 Dto 名称
* 暂时只支持 mysql 数据库

<span id = "feature"><h1> 后续更多功能</h1></span>
* web 界面支持
* 多选操作
* 自定义配置
