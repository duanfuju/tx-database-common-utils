package tx.database.common.utils.entitys;

public class QuerySqlResultTableColumn {
	//如果此列为只读，则返回 true。 
	private boolean readOnly;
	//如果此列自动递增，则返回 true。这类列通常为键，而且始终是只读的。
	private boolean autoIncrement;
	//列类型的名称
	private String columnTypeName;
	//获取列的名称
	private String columnName;
	//获取列的别名
	private String columnLabel;
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnLabel() {
		return columnLabel;
	}
	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}
	
}
