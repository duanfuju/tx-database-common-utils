package tx.database.common.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import tx.database.common.utils.adapter.TxDBType;
import tx.database.common.utils.entitys.QuerySqlResult;
import tx.database.common.utils.interfaces.TxSession;
import tx.database.common.utils.ps.PreparedStatementUtils;
/**
 * TxSession的实现类,用来操作数据库
 * @author 张尽
 * @email  zhangjin0908@hotmail.com
 * @qq     854363956
 * @date 2017年10月13日 上午10:07:39
 */
public class TxSessionImp implements TxSession {
	private ThreadLocal<Connection> threadlocal = new ThreadLocal<Connection>();
	private ThreadLocal<Transactional> threadlocalTransactional = new ThreadLocal<Transactional>();
	private TxSessionFactory sessionfactory;
	private TxDBType dbt;
	public TxSessionImp(TxSessionFactory sessionfactory,TxDBType dbt) {
		this.sessionfactory=sessionfactory;
		this.dbt = dbt;
	}
	
	@Override
	public void save(String tablename, Map<String, Object> data) throws SQLException {
		QuerySqlResult qrs = select(String.format("selelct 1 from %s where id=${id}", tablename),data);
		if(qrs.getDatas().size() == 0) {
			String sql = dbt.getInsertSql(tablename, data);
			PreparedStatementUtils.executeUpdate(getConnection(), sql, data);
		}else {
			String sql = dbt.getUpdateSql(tablename, data);
			PreparedStatementUtils.executeUpdate(getConnection(), sql, data);
		}
	}
	@Override
	public QuerySqlResult select(String sql, Map<String, Object> parames) throws SQLException {
		ResultSet rs = PreparedStatementUtils.executeQuery(getConnection(), sql, parames);
		return PreparedStatementUtils.fnResultSetToQuerySqlResult(rs);
	}
	@Override
	public int delete(String tablename, String id) throws SQLException {
		Map<String,Object> parame = new HashMap<String,Object>();
		parame.put("id", id);
		String sql = dbt.getDeleteSql(tablename, parame);
		return PreparedStatementUtils.executeUpdate(getConnection(), sql, parame);
	}
	@Override
	public int delete(String tablename, Map<String, Object> parame) throws SQLException {
		String sql = dbt.getDeleteSql(tablename, parame);
		return PreparedStatementUtils.executeUpdate(getConnection(), sql, parame);
	}
	@Override
	public Connection getConnection() throws SQLException {
		if(threadlocal.get() == null || threadlocal.get().isClosed()) {
			Connection connection = sessionfactory.getComboPooledDataSource().getConnection();
			threadlocal.set(connection);
			return connection;
		}else {
			return threadlocal.get();
		}
	}
	private Transactional getTransactional(Connection connection) {
		if(threadlocalTransactional.get() == null) {
			Transactional transactional = new Transactional(connection);
			threadlocalTransactional.set(transactional);
			return transactional;
		}else {
			return threadlocalTransactional.get();
		}
	}
	@Override
	public Transactional openTransactional() throws SQLException {
		if(getConnection().getAutoCommit()) {
			getConnection().setAutoCommit(false);
		}
		return getTransactional(getConnection());
	}

	@Override
	public QuerySqlResult selectPaging(String sql, Map<String, Object> parames, long pageSize, long pageNum) throws SQLException {
		String s = dbt.getSelectPagingSql(sql, pageSize, pageNum);
		ResultSet rs = PreparedStatementUtils.executeQuery(getConnection(), s, parames);
		return PreparedStatementUtils.fnResultSetToQuerySqlResult(rs);
	}

}
