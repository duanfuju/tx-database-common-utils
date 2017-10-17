package tx.database.common.utils.monitors;

import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0States {
	private ComboPooledDataSource cpds;
	public C3P0States(ComboPooledDataSource cpds) {
		this.cpds = cpds;
	}
	/**
	 * 获取当前连接池最大的连接数
	 * @return
	 */
	public int getMaxPoolSize() {
		return cpds.getMaxPoolSize();
	}
	/**
	 * 获取当前连接池最小连接数
	 * @return
	 */
	public int getMinPoolSize() {
		return cpds.getMinPoolSize();
	}
	/**
	 * 获取正在使用的连接数
	 * @return 
	 * @throws SQLException 
	 */
	public int  getNumBusyConnections() throws SQLException {
		return cpds.getNumBusyConnections();
	}
	/**
	 * 空闲连接数 
	 * @return
	 * @throws SQLException
	 */
	public int  getNumIdleConnections() throws SQLException {
		return cpds.getNumIdleConnections();
	}
	/**
	 * 获取总连接数
	 * @return
	 * @throws SQLException
	 */
	public int getNumConnections() throws SQLException {
		return cpds.getNumConnections();
	}
	/**
	 * 获取用户的URL地址
	 * @return
	 */
	public String  getJdbcUrl() {
		return cpds.getJdbcUrl();
	}
	/**
	 * 获取用户的名称
	 * @return
	 */
	public String  getUser() {
		return cpds.getUser();
	}
	/**
	 * 获取驱动的Class名称
	 * @return
	 */
	public String getDriverClass() {
		return cpds.getDriverClass();
	}
}
