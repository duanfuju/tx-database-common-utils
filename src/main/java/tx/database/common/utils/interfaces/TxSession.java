package tx.database.common.utils.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tx.database.common.utils.Transactional;
import tx.database.common.utils.entitys.QuerySqlResult;
/**
 * 用来管理数据库操作的类
 * @author 张尽
 * @email  zhangjin0908@hotmail.com
 * @qq     854363956
 * @date 2017年10月12日 下午11:32:18
 */
public interface TxSession {
	/**
	 * 更新或保存数据,如果数据库ID字段存在则更新,如果不存在则创建
	 * @param tablename
	 * @param datas
	 * @throws SQLException 
	 */
	void save(String tablename,Map<String,Object> datas ) throws SQLException;
	/**
	 * 
	 * @param sql           要查询的数据集的sql
	 * @param parames       要替换的参数,防止SQL注入
	 * @param pageSize      每页显示的记录数
	 * @param pageNum       当前页为第几页
	 * @return
	 * @throws SQLException
	 */
	QuerySqlResult selectPaging(String sql,Map<String,Object> parames,long pageSize,long pageNum )throws SQLException;
	/**
	 * 查询数据
	 * @param sql       要查询的数据集的sql
	 * @param parames   要替换的参数,防止SQL注入
	 * @return
	 * @throws SQLException 
	 */
	QuerySqlResult select(String sql,Map<String,Object> parames ) throws SQLException;
	/**
	 * 根据ID删除当前的数据
	 * @param tablename      数据库的表名称
	 * @param id             要删除的ID
	 * @return
	 * @throws SQLException 
	 */
	int delete(String tablename,String id) throws SQLException;
	/**
	 * 根据参数删除数据
	 * @param tablename      数据库表的名称
	 * @param parame         要删除的参数
	 * @return
	 * @throws SQLException 
	 */
	int delete(String tablename,Map<String,Object> parame) throws SQLException;
	/**
	 * 获取一个数据库连接,当前线程的connection连接
	 * @return  获取数据库的数据库连接,用完后不用关闭这个Connection对象
	 * @throws SQLException 
	 */
	Connection getConnection() throws SQLException;
	/**
	 * 开启Sql事物
	 * @return  返回事物,就算重复获取也是在同一个事物中
	 * @throws SQLException
	 */
	Transactional openTransactional() throws SQLException;
	
}
