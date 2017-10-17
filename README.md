# tx-database-common-utils
用来处理数据库访问的工具类

##### 如果你要使用tx-database-common-utils工具类来进行访问,那么你设计的数据库必须遵循一下原则<br/>
1. 数据库中表中的组件必须是与业务无关的ID建议使用32位的MD5(UUID)
2. 当前组件并未实现缓存操作,属于直接JDBC操作数据库,暂时支持mysql,oracle,postgresql数据库
3. 由于组件是个人时间开发,无发精确进行测试,可能会有BUG使用的时候请先进行测试

##### 第一步-> 初始化连接池
`Map<String,String> parame = new HashMap<String,String>();`</br>
`parame.put("DBType", "mysql");`</br>
`parame.put("jdbcUrl", "jdbc:mysql://localhost:3306/honeybee?useUnicode=true&characterset=utf-8&allowMultiQueries=true");`</br>
`parame.put("user", "honeybee");`</br>
`parame.put("password", "honeybee");`</br>
`parame.put("driverClass", "com.mysql.jdbc.Driver");`</br>
`parame.put("minPoolSize", "5");`</br>
`parame.put("maxPoolSize", "30");`</br>
`parame.put("initialPoolSize", "10");`</br>
`parame.put("maxIdleTime", "60");`</br>
`parame.put("minPoolSize", "5");`</br>
`parame.put("autoCommitOnClose", "true");`</br>
`TxSessionFactory tsf = new TxSessionFactory(parame);`</br>
`TxSession session = tsf.getTxSession();`</br>
##### 第二步-> 查询SQL操作
`QuerySqlResult datas = session.select("select * from hb_sys_users", null);`
`System.out.println(datas.getDatas().size());` <br/>
tx.database.common.utils.entitys.QuerySqlResult对象是对查询结果集的封装它拥有两个属性 querySqlResultTableColumns 和 datas,datas表示查询出来的结果集,querySqlResultTableColumns表示当前结果集的列的信息,datas里面如果列的值为空的话就无法获取列名的信息了在querySqlResultTableColumns中就可以精准的控制
