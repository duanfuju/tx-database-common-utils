package tx.database.common.utils.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlStringUtils {
	/**
	 * 给sql添加where条件
	 * @param key 
	 * @return
	 */
	public static String addWhere(Set<String> key) {
		String sql =" where ";
		for(String k :key) {
			sql += String.format("%s=${%s} and ", k,k);
		}
		return sql.substring(0, sql.length()-4);
	}
	/**
	 * 给sql生成插入数据的sql语句
	 * @param key 
	 * @return
	 */
	public static String addInsert(Set<String> key) {
		String sql="(";
		for(String k :key) {
			sql +=String.format("%s,", k);
		}
		sql=sql.substring(0, sql.length()-1)+") values (";
		for(String k :key) {
			sql +=String.format("${%s},", k);
		}
		return sql.substring(0, sql.length()-1)+")";
	}
	public static String addUpdate(Set<String> key) {
		String sql=" set ";
		for(String k :key) {
			sql +=String.format("%s=${%s},", k,k);
		}
		return sql.substring(0, sql.length()-1)+" where id=${id}";
	}
	/**
	 * 获取当前sql中特殊的字符
	 * @param txt  要获取的sql
	 * @return
	 */
	public static List<String> findPlaceholder(String txt){
		return findRegex(txt,"\\$\\{[a-z0-9_]+\\}");
	}
	/**
	 * 替换全部字符串不包含正则表达式
	 * @param txt        文本
	 * @param replace    要替换的字符
	 * @param newreplace 新的字符
	 * @return
	 */
	public static String replaceAll(String txt,String replace,String newreplace) {
		while(txt.indexOf(replace)!=-1) {
			txt=txt.replace(replace, newreplace);
		}
		return txt;
	}
	/**
	 * 根据正则表达式获取符合正则表达式的内容
	 * @param content  要查找的内容
	 * @param regex    正则表达式对象
	 * @return
	 */
	public static List<String> findRegex(String content,String regex) {
		List<String> result = new ArrayList<String>();
		Pattern r = Pattern.compile(regex);
	    Matcher m = r.matcher(content);
	    while(m.find()) {
	    	result.add(m.group());
	    }
	    return result;
	}
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("admin");
		set.add("test");
		System.out.println(SqlStringUtils.addUpdate(set));
	}
}
