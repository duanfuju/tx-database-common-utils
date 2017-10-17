package tx.database.common.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Transactional {
	private Connection connection;
	public Transactional(Connection connection) {
		this.connection = connection;
	}
	public void commit() throws SQLException {
		connection.commit();
		connection.close();
	}
	public void rollback() throws SQLException {
		connection.rollback();
		connection.close();
	}
}
