package test.tx.database.common.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tx.database.common.utils.TxSessionFactory;
import tx.database.common.utils.entitys.QuerySqlResult;
import tx.database.common.utils.interfaces.TxSession;

public class TestTxSession {
	private TxSession session ;
	@Before
	public void onInit() throws Exception {
		Map<String,String> parame = new HashMap<String,String>(); 
		parame.put("DBType", "mysql");
		parame.put("jdbcUrl", "jdbc:mysql://localhost:3306/honeybee?useUnicode=true&characterset=utf-8&allowMultiQueries=true");
		parame.put("user", "honeybee");
		parame.put("password", "honeybee");
		parame.put("driverClass", "com.mysql.jdbc.Driver");
		parame.put("minPoolSize", "5");
		parame.put("maxPoolSize", "30");
		parame.put("initialPoolSize", "10");
		parame.put("maxIdleTime", "60");
		parame.put("minPoolSize", "5");
		TxSessionFactory tsf = new TxSessionFactory(parame);
		session = tsf.getTxSession();
	}
	@Test
	public void testSelect() throws SQLException {
		QuerySqlResult datas = session.select("select * from hb_sys_users", null);
		System.out.println(datas.getDatas().size());
	}
}
