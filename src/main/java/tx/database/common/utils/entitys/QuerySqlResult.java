package tx.database.common.utils.entitys;

import java.util.List;
import java.util.Map;

public class QuerySqlResult {
	private List<QuerySqlResultTableColumn> querySqlResultTableColumns; 
	private List<Map<String,Object>> datas;
	public List<QuerySqlResultTableColumn> getQuerySqlResultTableColumns() {
		return querySqlResultTableColumns;
	}
	public void setQuerySqlResultTableColumns(List<QuerySqlResultTableColumn> querySqlResultTableColumns) {
		this.querySqlResultTableColumns = querySqlResultTableColumns;
	}
	public List<Map<String, Object>> getDatas() {
		return datas;
	}
	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
}
