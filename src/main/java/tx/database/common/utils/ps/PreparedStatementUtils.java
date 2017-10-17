package tx.database.common.utils.ps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tx.database.common.utils.entitys.QuerySqlResult;
import tx.database.common.utils.entitys.QuerySqlResultTableColumn;


public class PreparedStatementUtils {
	/**
	 * 封装查询Sql并关闭流对象
	 * @param connection   connection对象
	 * @param sql          要查询的sql
	 * @param parame       参数
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(Connection connection,String sql,Map<String, Object> parame) throws SQLException {
		PreparedStatement ps = null;
		List<String> str = SqlStringUtils.findPlaceholder(sql);
		for(String k : str) {
			sql=SqlStringUtils.replaceAll(sql, k, "?");
		}
		ps = connection.prepareStatement(sql);
		for(int i=0;i<str.size();i++) {
			ps.setObject(i+1, parame.get(str.get(i)));
		}
		return ps.executeQuery();
	}
	/**
	 * 执行sql返回影响行数
	 * @param connection
	 * @param sql
	 * @param parame
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(Connection connection,String sql,Map<String, Object> parame) throws SQLException {
		PreparedStatement ps = null;
		try {
			List<String> str = SqlStringUtils.findPlaceholder(sql);
			for(String k : str) {
				sql=SqlStringUtils.replaceAll(sql, k, "?");
			}
			ps = connection.prepareStatement(sql);
			for(int i=0;i<str.size();i++) {
				ps.setObject(i+1, parame.get(str.get(i)));
			}
			return ps.executeUpdate();
		} finally {
			if(ps!=null)
				ps.close();
		}
	}
	
	public static QuerySqlResult fnResultSetToQuerySqlResult(ResultSet rs) throws SQLException {
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		ResultSetMetaData rsmd = rs.getMetaData() ;
	    int columnCount = rsmd.getColumnCount();
	    String[] colNames = new String[columnCount];
	    List<QuerySqlResultTableColumn> querySqlResultTableColumns = new ArrayList<QuerySqlResultTableColumn>();
	    for(int i = 0; i < colNames.length; i++) {
	    	QuerySqlResultTableColumn querySqlResultTableColumn = new QuerySqlResultTableColumn();
	         colNames[i] = rsmd.getColumnLabel(i+1).toLowerCase();
	         querySqlResultTableColumn.setColumnName(rsmd.getColumnName(i+1).toLowerCase());
	         querySqlResultTableColumn.setColumnLabel(colNames[i]);
	         querySqlResultTableColumn.setAutoIncrement(rsmd.isAutoIncrement(i+1));
	         querySqlResultTableColumn.setColumnTypeName(rsmd.getColumnTypeName(i+1).toLowerCase());
	         querySqlResultTableColumn.setReadOnly(rsmd.isReadOnly(i+1));
	         querySqlResultTableColumns.add(querySqlResultTableColumn);
	     }
	    while(rs.next()) {
	         Map<String,Object> data = new HashMap<String,Object>();
	         for(int i = 1; i <= columnCount; ++i) {
	        	 data.put(colNames[i - 1], rs.getObject(i));
	         }
	         datas.add(data);
	   }
	    QuerySqlResult qsr = new QuerySqlResult();
	    qsr.setDatas(datas);
	    qsr.setQuerySqlResultTableColumns(querySqlResultTableColumns);
	    return qsr;
	}
}
