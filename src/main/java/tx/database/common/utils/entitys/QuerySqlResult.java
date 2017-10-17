package tx.database.common.utils.entitys;

import java.util.List;
import java.util.Map;
/**
 * 查询结果集的实体
 * 注意: 不管是什么数据库查询出来的列名称都会进行小写比如oracle的列和mysql的列全部都会小写
 * @author 张尽
 * @email  zhangjin0908@hotmail.com
 * @qq     854363956
 * @date 2017年10月17日 上午10:01:46
 */
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
