package test.tx.database.common.utils;

import java.sql.SQLException;
import java.util.ArrayList;
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
		parame.put("autoCommitOnClose", "true");
		TxSessionFactory tsf = new TxSessionFactory(parame);
		session = tsf.getTxSession();
	}
	//简单测试查询是否有效
	@Test
	public void testSelect() throws SQLException {
		QuerySqlResult datas = session.select("select * from hb_sys_users", null);
		System.out.println(datas.getDatas().size());
	}
	 private int number =0;
	 
	synchronized public int getNumber() {
		return number;
	}
	synchronized public void setNumber(int number) {
		this.number = number;
	}
	//测试多线程查询中是否会存在无法释放Session的BUG 
	@Test
	public void testMultithreadingSelect() throws SQLException{
		for(int i=0;i<1000;i++) {
		   Thread  t = new Thread() {
				@Override
				public void run() {
					try {
						 testSelect();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		while(true) {
		}
	}
	@Test
	public void testSave() throws SQLException {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("id", "0");
		data.put("username", "zhang");
		session.save("hb_sys_users",data );
		QuerySqlResult datas = session.select("select * from hb_sys_users", null);
		String username = (String) datas.getDatas().get(0).get("username");
		System.out.println(username);
	}
	@Test
	public void testMultithreadingSave() {
		for(int i=0;i<1000;i++) {
			   Thread  t = new Thread() {
					@Override
					public void run() {
							try {
								Map<String,Object> data = new HashMap<String,Object>();
								data.put("id", "0");
								data.put("username", "zhang"+getNumber());
								setNumber(getNumber()+1);
								session.save("hb_sys_users",data );
								QuerySqlResult datas = session.select("select * from hb_sys_users", null);
								String username = (String) datas.getDatas().get(0).get("username");
								System.out.println(username);
								System.out.println(getNumber());
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
				};
				t.start();
			}
			while(true) {
			}
	}
}
