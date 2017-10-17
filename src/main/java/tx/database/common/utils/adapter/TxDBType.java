package tx.database.common.utils.adapter;

import java.util.Map;

import tx.database.common.utils.string.SqlStringUtils;

public enum TxDBType {
	mysql("mysql"),
	oracle("oracle"),
	postgresql("postgresql");
	private String string;
	TxDBType(String string){
		this.string=string;
	}
	public static TxDBType getTxDBType(String txt) {
		if(TxDBType.mysql.string.equals(txt)) {
			return TxDBType.mysql;
		}else if(TxDBType.oracle.string.equals(txt)) {
			return TxDBType.oracle;
		}else if(TxDBType.postgresql.string.equals(txt)) {
			return TxDBType.postgresql;
		}
		return null;
	}
	/**
	 * 获取分页查询的sql
	 * @param sql      分页查询的sql
	 * @param pageSize 每页显示的记录数
	 * @param pageNum  当前页为第几页
	 * @return
	 */
	public String getSelectPagingSql(String sql,long pageSize,long pageNum) {
 		if(TxDBType.mysql.string.equals(string)) {
 			long startPage = (pageSize*pageNum)-(pageSize+1);
 			long endPage = (pageSize*pageNum)-1;
			return String.format("select * from (%s) limit %s,%s", sql,startPage,endPage);
		}else if(TxDBType.oracle.string.equals(string)) {
			long startPage = (pageSize*pageNum)-(pageSize);
 			long endPage = (pageSize*pageNum);
			return String.format("select * from (select __paging__.*,rownum as __rn__  from ( %s ) __paging__ where rownum <=%s ) where __rn__ >=%s",sql,startPage,endPage);
		}else /*if(TxDBType.postgresql.string.equals(string))*/ {
			long startPage = (pageSize*pageNum)-(pageSize+1);
 			long endPage = (pageSize*pageNum)-1;
 			return String.format("select * from (%s) limit %s offset %s", sql,startPage,endPage);
		}
	}
	public String getUpdateSql(String tablename,Map<String,Object> parame) {
		return String.format("update %s %s", tablename,SqlStringUtils.addUpdate(parame.keySet()));
	}
	public String getInsertSql(String tablename,Map<String,Object> parame) {
		return String.format("insert into %s %s", tablename,SqlStringUtils.addInsert(parame.keySet()));
	}
	public String getDeleteSql(String tablename,Map<String,Object> parame) {
		return String.format("delete from %s %s", tablename,SqlStringUtils.addWhere(parame.keySet()));
	}
}
