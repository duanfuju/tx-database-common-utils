package tx.database.common.utils;

import java.beans.PropertyVetoException;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import tx.database.common.utils.adapter.TxDBType;
import tx.database.common.utils.interfaces.TxSession;

public class TxSessionFactory {
	private ThreadLocal<TxSession> threadlocal = new ThreadLocal<TxSession>();
	private ComboPooledDataSource cpds;
	private TxDBType tbdt;
	public TxSessionFactory(Map<String,String> config) throws Exception {
		cpds = new ComboPooledDataSource();
		tbdt = TxDBType.getTxDBType(config.get("DBType"));
		if(tbdt==null) {
			throw new Exception("参数DBType未配置,请配置DBType属性!");
		}else {
			config.remove("DBType");
		}
		for(String key : config.keySet()) {
			try {
				C3P0Properties p =C3P0Properties.getC3P0Properties(key);
				p.invokingSetPropertie(config.get(key), cpds);
			} catch (PropertyVetoException e) {
				throw new RuntimeException("参数:["+key+"]设置失败",e);
			}
		}
	}
	public ComboPooledDataSource getComboPooledDataSource() {
		return cpds;
	}
	
	public TxSession getTxSession() {
		if(threadlocal.get() == null ) {
			TxSessionImp tsi = new TxSessionImp(this,tbdt);
			threadlocal.set(tsi);
			return tsi;
		}else {
			return threadlocal.get();
		}
	}
}
