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

##### 第三步-> 数据保存或更新
`Map<String,Object> data = new HashMap<String,Object>();`</br>
`data.put("id", "0");`</br>
`data.put("username", "zhang");`</br>
`session.save("hb_sys_users",data );`</br>
TxSession.save 里面有两个参数,第一个要保存的表的名称,根据数据库的表名称,这里区分大小写,完全按照数据库的表的大小写,第二个参数是要保存的数据,这个Map中必须含有一个id的参数,系统会根据这个id去数据库中进行查询,如果这个数据存在数据库中,那么它会更新剩下的所有字段,如果不存在数据库中,那么它就会创建一条数据到数据库中(PS:无论如何,数据都无法更新id字段)

##### 第四步-> 数据删除
`session.delete("hb_sys_users","0");`</br>
这种删除数据的方式是指明表的名称和表id列的值来进行删除,他返回的是当前影响的行数</br>
`Map<String,Object> data = new HashMap<String,Object>();`</br>
`data.put("id", "0");`</br>
`data.put("username", "zhang");`</br>
`session.delete("hb_sys_users",data);`</br>
这种删除数据的方式是根据其他列的属性来进行删除,比如上的这样的用sql表示就是`delete from hb_sys_users where username='zhang' and id='0'` 当然真正的不可能生成这样的sql,因为这样会引起SQL注入,生成的sql就是预编译的sql
