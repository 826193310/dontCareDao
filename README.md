# dontCareDao
focus your service code,  and dont care anythings about others

dontCareDao 是一个使用 [freemarker](https://freemarker.apache.org/) 处理的自动代码生成工具，旨在通过代码生成，减轻我们工作中的负担， 待完成之后，预计会支持从 controller 到 dao 层的代码生成
旨在通过这个工具，能更加智能地生成 从 controller 到 Dao 层之间的通用逻辑代码，不用关心增加字段后或者一些其它非业务性的问题，开发更加高效，智能，方便

# 特点

* 生成 Mapper.xml 之后，如果再生成，不会覆盖自己写的xml
* 支持生成的 DTO 继承自己自定义的类
* service 层面自定义自己的返回类
* 支持是否集成 swagger ， 如果集成 swagger ，则会在controller 方法生成swagger 注解

# 后续更多功能
* web 界面支持
* 多选操作
* 自定义配置
