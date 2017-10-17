package tx.database.common.utils;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public enum C3P0Properties {
	/**------------------------------------------------------------------------------------------------
	 *if you english  is very good , please see http://www.mchange.com/projects/c3p0/#acquireIncrement 
	 **------------------------------------------------------------------------------------------------*/
	
	
	//连接池在无空闲连接可用时一次性创建的新数据库连接数,default : 3
	acquireIncrement("acquireIncrement"),
	//连接池在获得新连接失败时重试的次数，如果小于等于0则无限重试直至连接获得成功。 default : 30（建议使用）
	acquireRetryAttempts("acquireRetryAttempts"),
	//两次连接中间隔时间，单位毫秒，连接池在获得新连接时的间隔时间。default : 1000  单位ms（建议使用）
	acquireRetryDelay("acquireRetryDelay"),
	//连接池在回收数据库连接时是否自动提交事务。如果为false，则会回滚未提交的事务，如果为true，则会自动提交事务。default : false（不建议使用）
	autoCommitOnClose("autoCommitOnClose"),
	//用来配置测试连接的一种方式。配置一个表名，连接池根据这个表名创建一个空表，并且用自己的测试sql语句在这个空表上测试数据库连接这个表只能由c3p0来使用，用户不能操作，同时用户配置的preferredTestQuery 将会被忽略。
	automaticTestTable("automaticTestTable"),
	//如果为true，则当连接获取失败时自动关闭数据源，除非重新启动应用程序。所以一般不用。default : false（不建议使用）
	breakAfterAcquireFailure("breakAfterAcquireFailure"),
	//配置当连接池所有连接用完时应用程序getConnection的等待时间。为0则无限等待直至有其他连接释放或者创建新的连接，不为0则当时间到的时候如果仍没有获得连接，则会抛出SQLException。其实就是acquireRetryAttempts*acquireRetryDelay。default : 0（与其他两个，有重复，选择其中两个都行）
	checkoutTimeout("checkoutTimeout"),
	//用来定制Connection的管理，比如在Connection acquire 的时候设定Connection的隔离级别，或者在Connection丢弃的时候进行资源关闭，就可以通过继承一个AbstractConnectionCustomizer来实现相关方法，配置的时候使用全类名。有点类似监听器的作用。 default : null
	connectionCustomizerClassName("connectionCustomizerClassName"),
	//连接池用来支持automaticTestTable和preferredTestQuery测试的类，必须是全类名，就像默认的那样，可以通过实现UnifiedConnectionTester接口或者继承AbstractConnectionTester来定制自己的测试方法   default :  com.mchange.v2.c3p0.impl.DefaultConnectionTester
	connectionTesterClassName("connectionTesterClassName"),
	//ontextClassLoaderSource应当设置一个caller或library或为空。（默认是一个caller）如果使用c3p0的ClassLoader将它设置为一个library，那么需要重部署的客户端将不再包含引用。 default: caller
	contextClassLoaderSource("contextClassLoaderSource"),
	/**
	 *Default: if configured with a named config, the config name, otherwise the pool's "identity token"
	 *Every c3p0 pooled data source is given a dataSourceName, which serves two purposes. It helps users find DataSources via C3P0Registry, and it is included in the name of JMX mBeans in order to help track and distinguish between multiple c3p0 DataSources even across application or JVM restarts. dataSourceName defaults to the pool's configuration name, if a named config was used, or else to an "identity token" (an opaque, guaranteed unique String associated with every c3p0 DataSource). You may update this property to any name you find convenient. dataSourceName is not guaranteed to be unique — for example, multiple DataSource created from the same named configuration will share the same dataSourceName. But if you are going to make use of dataSourceName, you will probably want to ensure that all pooled DataSources within your JVM do have unique names.
	 */
	dataSourceName("dataSourceName"),
	//如果为true并且unreturnedConnectionTimeout设为大于0的值，当所有被getConnection出去的连接unreturnedConnectionTimeout时间到的时候，就会打印出堆栈信息。只能在debug模式下适用，因为 打印堆栈信息会减慢getConnection的速度 default : false
	debugUnreturnedConnectionStackTraces("debugUnreturnedConnectionStackTraces"),
	//Java类的完全限定名称的JDBC驱动程序的使用。
	driverClass("driverClass"),
	/**
	 * Default: an empty java.util.Map
	 * A java.util.Map (raw type) containing the values of any user-defined configuration extensions defined for this DataSource.
	 */
	extensions("extensions"),
	/**
	 * Default: null
	 * DataSources that will be bound by JNDI and use that API's Referenceable interface to store themselves may specify a URL from which the class capable of dereferencing a them may be loaded. If (as is usually the case) the c3p0 libraries will be locally available to the JNDI service, leave this set as null.
	 */
	factoryClassLocation("factoryClassLocation"),
	//这个配置强烈不建议为true。default : false（不建议使用）
	forceIgnoreUnresolvedTransactions("forceIgnoreUnresolvedTransactions"),
	/**
	 * Default: false
	 * Setting this to true forces Connections to be checked-in synchronously, which under some circumstances may improve performance. Ordinarily Connections are checked-in asynchronously so that clients avoid any overhead of testing or custom check-in logic. However, asynchronous check-in contributes to thread pool congestion, and very busy pools might find clients delayed waiting for check-ins to complete. Expanding numHelperThreads can help manage Thread pool congestion, but memory footprint and switching costs put limits on practical thread pool size. To reduce thread pool load, you can set forceSynchronousCheckins to true. Synchronous check-ins are likely to improve overall performance when testConnectionOnCheckin is set to false and no slow work is performed in a ConnectionCustomizer's onCheckIn(...) method. If Connections are tested or other slow work is performed on check-in, then this setting will cause clients to experience the overhead of that work on Connection.close(), which you must trade-off against any improvements in pool performance. 
	 */
	forceSynchronousCheckins("forceSynchronousCheckins"),
	/**
	 * Default: false
	 * Setting the parameter driverClass causes that class to preload and register with java.sql.DriverManager. However, it does not on its own ensure that the driver used will be an instance of driverClass, as DriverManager may (in unusual cases) know of other driver classes which can handle the specified jdbcUrl. Setting this parameter to true causes c3p0 to ignore DriverManager and simply instantiate driverClass directly.
	 */
	forceUseNamedDriverClass("forceUseNamedDriverClass"),
	//每900秒检查所有连接池中的空闲连接
	idleConnectionTestPeriod("idleConnectionTestPeriod"),
	//连接池初始化时创建的连接数,default : 3，取值应在minPoolSize与maxPoolSize之间
	initialPoolSize("initialPoolSize"),
	//要传递给我们的JDBC驱动程序建立连接的连接URL。
	jdbcUrl("jdbcUrl"),
	/**
	 * Default: 0
	 * Seconds before c3p0's thread pool will try to interrupt an apparently hung task. Rarely useful. Many of c3p0's functions are not performed by client threads, but asynchronously by an internal thread pool. c3p0's asynchrony enhances client performance directly, and minimizes the length of time that critical locks are held by ensuring that slow jdbc operations are performed in non-lock-holding threads. If, however, some of these tasks "hang", that is they neither succeed nor fail with an Exception for a prolonged period of time, c3p0's thread pool can become exhausted and administrative tasks backed up. If the tasks are simply slow, the best way to resolve the problem is to increase the number of threads, via numHelperThreads. But if tasks sometimes hang indefinitely, you can use this parameter to force a call to the task thread's interrupt() method if a task exceeds a set time limit. [c3p0 will eventually recover from hung tasks anyway by signalling an "APPARENT DEADLOCK" (you'll see it as a warning in the logs), replacing the thread pool task threads, and interrupt()ing the original threads. But letting the pool go into APPARENT DEADLOCK and then recover means that for some periods, c3p0's performance will be impaired. So if you're seeing these messages, increasing numHelperThreads and setting maxAdministrativeTaskTime might help. maxAdministrativeTaskTime should be large enough that any resonable attempt to acquire a Connection from the database, to test a Connection, or to destroy a Connection, would be expected to succeed or fail within the time set. Zero (the default) means tasks are never interrupted, which is the best and safest policy under most circumstances. If tasks are just slow, allocate more threads. If tasks are hanging forever, try to figure out why, and maybe setting maxAdministrativeTaskTime can help in the meantime.
	 */
	maxAdministrativeTaskTime("maxAdministrativeTaskTime"),
	//配置连接的生存时间，超过这个时间的连接将由连接池自动断开丢弃掉。当然正在使用的连接不会马上断开，而是等待它close再断开。配置为0的时候则不会对连接的生存时间进行限制。default : 0 单位 s（不建议使用）
	maxConnectionAge("maxConnectionAge"),
	//连接的最大空闲时间，如果超过这个时间，某个数据库连接还没有被使用，则会断开掉这个连接。如果为0，则永远不会断开连接,即回收此连接。default : 0 单位 s
	maxIdleTime("maxIdleTime"),
	//这个配置主要是为了快速减轻连接池的负载，比如连接池中连接数因为某次数据访问高峰导致创建了很多数据连接，但是后面的时间段需要的数据库连接数很少，需要快速释放，必须小于maxIdleTime。其实这个没必要配置，maxIdleTime已经配置了。default : 0 单位 
	maxIdleTimeExcessConnections("maxIdleTimeExcessConnections"),
	//连接池中拥有的最大连接数，如果获得新连接时会使连接总数超过这个值则不会再获取新连接，而是等待其他连接释放，所以这个值有可能会设计地很大,default : 15
	maxPoolSize("maxPoolSize"),
	//配置PreparedStatement缓存,连接池为数据源缓存的PreparedStatement的总数。由于PreparedStatement属于单个Connection,所以这个数量应该根据应用中平均连接数乘以每个连接的平均PreparedStatement,来计算。同时maxStatementsPerConnection的配置无效。default : 0（不建议使用）
	maxStatements("maxStatements"),
	//连接池为数据源单个Connection缓存的PreparedStatement数，这个配置比maxStatements更有意义，因为它缓存的服务对象是单个数据连接，,如果设置的好，肯定是可以提高性能的。为0的时候不缓存。default : 0（看情况而论）
	maxStatementsPerConnection("maxStatementsPerConnection"),
	//连接池保持的最小连接数,default : 3
	minPoolSize("minPoolSize"),
	//c3p0是异步操作的，缓慢的JDBC操作通过帮助进程完成。扩展这些操作可以有效的提升性能 通过多线程实现多个操作同时被执行。Default: 3
	numHelperThreads("numHelperThreads"),
	//当用户调用getConnection()时使root用户成为去获取连接的用户。主要用于连接池连接非c3p0的数据源时。Default: null
	overrideDefaultUser("overrideDefaultUser"),
	//与overrideDefaultUser参数对应使用的一个参数。Default: null
	overrideDefaultPassword("overrideDefaultPassword"),
	//要传递给我们的JDBC驱动程序建立连接的连接密码。
	password("password"),
	//义所有连接测试都执行的测试语句。在使用连接测试的情况下这个一显著提高测试速度。注意：测试的表必须在初始数据源的时候就存在。Default: null
	preferredTestQuery("preferredTestQuery"),
	//默认为false的布尔值。若将它设置为true，那么c3p0的线程将使用c3p0库中的AccessControlContext，而不是可能和客户端程序相关联从而阻止垃圾回收的 AccessControlContext。
	privilegeSpawnedThreads("privilegeSpawnedThreads"),
	//用户修改系统配置参数执行前最多等待300秒。Default: 300
	propertyCycle("propertyCycle"),
	/**
	 * Default: 0
	 * If set to a value greater than 0, the statement cache will track when Connections are in use, and only destroy Statements when their parent Connections are not otherwise in use. Although closing of a Statement while the parent Connection is in use is formally within spec, some databases and/or JDBC drivers, most notably Oracle, do not handle the case well and freeze, leading to deadlocks. Setting this parameter to a positive value should eliminate the issue. This parameter should only be set if you observe that attempts by c3p0 to close() cached statements freeze (usually you'll see APPARENT DEADLOCKS in your logs). If set, this parameter should almost always be set to 1. Basically, if you need more than one Thread dedicated solely to destroying cached Statements, you should set maxStatements and/or maxStatementsPerConnection so that you don't churn through Statements so quickly. 
	 */
	statementCacheNumDeferredCloseThreads("statementCacheNumDeferredCloseThreads"),
	//如果为true，则在close的时候测试连接的有效性。default : false（不建议使用）
	testConnectionOnCheckin("testConnectionOnCheckin"),
	//性能消耗大。如果为true，在每次getConnection的时候都会测试，为了提高性能,尽量不要用。default : false（不建议使用）
	testConnectionOnCheckout("testConnectionOnCheckout"),
	//为0的时候要求所有的Connection在应用程序中必须关闭。如果不为0，则强制在设定的时间到达后回收Connection，所以必须小心设置，保证在回收之前所有数据库操作都能够完成。这种限制减少Connection未关闭情况的不是很适用。建议手动关闭。default : 0 单位 s（不建议使用）debugUnreturnedConnectionStackTraces:如果为true并且unreturnedConnectionTimeout设为大于0的值，当所有被getConnection出去的连接unreturnedConnectionTimeout时间到的时候，就会打印出堆栈信息。只能在debug模式下适用，因为打印堆栈信息会减慢getConnection的速度default : false（不建议使用）
	unreturnedConnectionTimeout("unreturnedConnectionTimeout"),
	//要传递给我们的JDBC驱动程序以建立连接的连接用户名。
	user("user");
	private String string;
	C3P0Properties(String string){
		this.string = string;
	}
	/**
	 * 根据字符串获取对应的枚举类型
	 * @param string  字符串
	 * @return  获取到的枚举类型
	 * @throws PropertyVetoException  如果没有获取到类型将抛出异常
	 */
	public static C3P0Properties getC3P0Properties(String string) throws PropertyVetoException {
		if(C3P0Properties.acquireIncrement.string.equals(string)) {
			return acquireIncrement;
		}else if(C3P0Properties.acquireRetryAttempts.string.equals(string)) {
			return acquireRetryAttempts;
		}else if(C3P0Properties.acquireRetryDelay.string.equals(string)) {
			return acquireRetryDelay;
		}else if(C3P0Properties.autoCommitOnClose.string.equals(string)) {
			return autoCommitOnClose;
		}else if(C3P0Properties.automaticTestTable.string.equals(string)) {
			return automaticTestTable;
		}else if(C3P0Properties.breakAfterAcquireFailure.string.equals(string)) {
			return breakAfterAcquireFailure;
		}else if(C3P0Properties.checkoutTimeout.string.equals(string)) {
			return checkoutTimeout;
		}else if(C3P0Properties.connectionCustomizerClassName.string.equals(string)) {
			return connectionCustomizerClassName;
		}else if(C3P0Properties.connectionTesterClassName.string.equals(string)) {
			return connectionTesterClassName;
		}else if(C3P0Properties.contextClassLoaderSource.string.equals(string)) {
			return contextClassLoaderSource;
		}else if(C3P0Properties.dataSourceName.string.equals(string)) {
			return dataSourceName;
		}else if(C3P0Properties.debugUnreturnedConnectionStackTraces.string.equals(string)) {
			return debugUnreturnedConnectionStackTraces;
		}else if(C3P0Properties.driverClass.string.equals(string)) {
			return driverClass;
		}else if(C3P0Properties.extensions.string.equals(string)) {
			//暂时不考虑c3p0的extensions属性配置
			//cpds.setExtensions(null);
		}else if(C3P0Properties.factoryClassLocation.string.equals(string)) {
			return factoryClassLocation;
		}else if(C3P0Properties.forceIgnoreUnresolvedTransactions.string.equals(string)) {
			return forceIgnoreUnresolvedTransactions;
		}else if(C3P0Properties.forceSynchronousCheckins.string.equals(string)) {
			return forceSynchronousCheckins;
		}else if(C3P0Properties.forceUseNamedDriverClass.string.equals(string)) {
			return forceUseNamedDriverClass;
		}else if(C3P0Properties.idleConnectionTestPeriod.string.equals(string)) {
			return idleConnectionTestPeriod;
		}else if(C3P0Properties.initialPoolSize.string.equals(string)) {
			return initialPoolSize;
		}else if(C3P0Properties.jdbcUrl.string.equals(string)) {
			return jdbcUrl;
		}else if(C3P0Properties.maxAdministrativeTaskTime.string.equals(string)) {
			return maxAdministrativeTaskTime;
		}else if(C3P0Properties.maxConnectionAge.string.equals(string)) {
			return maxConnectionAge;
		}else if(C3P0Properties.maxIdleTime.string.equals(string)) {
			return maxIdleTime;
		}else if(C3P0Properties.maxIdleTimeExcessConnections.string.equals(string)) {
			return maxIdleTimeExcessConnections;
		}else if(C3P0Properties.maxPoolSize.string.equals(string)) {
			return maxPoolSize;
		}else if(C3P0Properties.maxStatements.string.equals(string)) {
			return maxStatements;
		}else if(C3P0Properties.maxStatementsPerConnection.string.equals(string)) {
			return maxStatementsPerConnection;
		}else if(C3P0Properties.minPoolSize.string.equals(string)) {
			return minPoolSize;
		}else if(C3P0Properties.numHelperThreads.string.equals(string)) {
			return numHelperThreads;
		}else if(C3P0Properties.overrideDefaultUser.string.equals(string)) {
			return overrideDefaultUser;
		}else if(C3P0Properties.overrideDefaultPassword.string.equals(string)) {
			return overrideDefaultPassword;
		}else if(C3P0Properties.password.string.equals(string)) {
			return password;
		}else if(C3P0Properties.preferredTestQuery.string.equals(string)) {
			return preferredTestQuery;
		}else if(C3P0Properties.privilegeSpawnedThreads.string.equals(string)) {
			return privilegeSpawnedThreads;
		}else if(C3P0Properties.propertyCycle.string.equals(string)) {
			return propertyCycle;
		}else if(C3P0Properties.statementCacheNumDeferredCloseThreads.string.equals(string)) {
			return statementCacheNumDeferredCloseThreads;
		}else if(C3P0Properties.testConnectionOnCheckin.string.equals(string)) {
			return testConnectionOnCheckin;
		}else if(C3P0Properties.testConnectionOnCheckout.string.equals(string)) {
			return testConnectionOnCheckout;
		}else if(C3P0Properties.unreturnedConnectionTimeout.string.equals(string)) {
			return unreturnedConnectionTimeout;
		}else if(C3P0Properties.user.string.equals(string)) {
			return user;
		}
		throw new PropertyVetoException("当前属性无法设置,请不要配置此属性!", null);
	}
	/**
	 * 设置ComboPooledDataSource里面的属性的值 
	 * @param value  要设置的值
	 * @param cpds   设置的对象
	 * @throws PropertyVetoException 
	 */
	public void invokingSetPropertie(String value,ComboPooledDataSource cpds) throws PropertyVetoException {
		if(C3P0Properties.acquireIncrement.string.equals(string)) {
			cpds.setAcquireIncrement(Integer.parseInt(value));
		}else if(C3P0Properties.acquireRetryAttempts.string.equals(string)) {
			cpds.setAcquireRetryAttempts(Integer.parseInt(value));
		}else if(C3P0Properties.acquireRetryDelay.string.equals(string)) {
			cpds.setAcquireRetryDelay(Integer.parseInt(value));
		}else if(C3P0Properties.autoCommitOnClose.string.equals(string)) {
			cpds.setAutoCommitOnClose("true".equals(value)?true : false);
		}else if(C3P0Properties.automaticTestTable.string.equals(string)) {
			cpds.setAutomaticTestTable(value);
		}else if(C3P0Properties.breakAfterAcquireFailure.string.equals(string)) {
			cpds.setBreakAfterAcquireFailure("true".equals(value)?true : false);
		}else if(C3P0Properties.checkoutTimeout.string.equals(string)) {
			cpds.setCheckoutTimeout(Integer.parseInt(value));
		}else if(C3P0Properties.connectionCustomizerClassName.string.equals(string)) {
			cpds.setConnectionCustomizerClassName(value);
		}else if(C3P0Properties.connectionTesterClassName.string.equals(string)) {
			cpds.setConnectionTesterClassName(value);
		}else if(C3P0Properties.contextClassLoaderSource.string.equals(string)) {
			cpds.setContextClassLoaderSource(value);
		}else if(C3P0Properties.dataSourceName.string.equals(string)) {
			cpds.setDataSourceName(value);
		}else if(C3P0Properties.debugUnreturnedConnectionStackTraces.string.equals(string)) {
			cpds.setDebugUnreturnedConnectionStackTraces("true".equals(value)?true : false);
		}else if(C3P0Properties.driverClass.string.equals(string)) {
			cpds.setDriverClass(value);
		}else if(C3P0Properties.extensions.string.equals(string)) {
			//暂时不考虑c3p0的extensions属性配置
			//cpds.setExtensions(null);
		}else if(C3P0Properties.factoryClassLocation.string.equals(string)) {
			cpds.setFactoryClassLocation(value);
		}else if(C3P0Properties.forceIgnoreUnresolvedTransactions.string.equals(string)) {
			cpds.setForceIgnoreUnresolvedTransactions("true".equals(value)?true : false);
		}else if(C3P0Properties.forceSynchronousCheckins.string.equals(string)) {
			cpds.setForceSynchronousCheckins("true".equals(value)?true : false);
		}else if(C3P0Properties.forceUseNamedDriverClass.string.equals(string)) {
			cpds.setForceUseNamedDriverClass("true".equals(value)?true : false);
		}else if(C3P0Properties.idleConnectionTestPeriod.string.equals(string)) {
			cpds.setIdleConnectionTestPeriod(Integer.parseInt(value));
		}else if(C3P0Properties.initialPoolSize.string.equals(string)) {
			cpds.setInitialPoolSize(Integer.parseInt(value));
		}else if(C3P0Properties.jdbcUrl.string.equals(string)) {
			cpds.setJdbcUrl(value);
		}else if(C3P0Properties.maxAdministrativeTaskTime.string.equals(string)) {
			cpds.setMaxAdministrativeTaskTime(Integer.parseInt(value));
		}else if(C3P0Properties.maxConnectionAge.string.equals(string)) {
			cpds.setMaxConnectionAge(Integer.parseInt(value));
		}else if(C3P0Properties.maxIdleTime.string.equals(string)) {
			cpds.setMaxIdleTime(Integer.parseInt(value));
		}else if(C3P0Properties.maxIdleTimeExcessConnections.string.equals(string)) {
			cpds.setMaxIdleTimeExcessConnections(Integer.parseInt(value));
		}else if(C3P0Properties.maxPoolSize.string.equals(string)) {
			cpds.setMaxPoolSize(Integer.parseInt(value));
		}else if(C3P0Properties.maxStatements.string.equals(string)) {
			cpds.setMaxStatements(Integer.parseInt(value));
		}else if(C3P0Properties.maxStatementsPerConnection.string.equals(string)) {
			cpds.setMaxStatementsPerConnection(Integer.parseInt(value));
		}else if(C3P0Properties.minPoolSize.string.equals(string)) {
			cpds.setMinPoolSize(Integer.parseInt(value));
		}else if(C3P0Properties.numHelperThreads.string.equals(string)) {
			cpds.setNumHelperThreads(Integer.parseInt(value));
		}else if(C3P0Properties.overrideDefaultUser.string.equals(string)) {
			cpds.setOverrideDefaultUser(value);
		}else if(C3P0Properties.overrideDefaultPassword.string.equals(string)) {
			cpds.setOverrideDefaultPassword(value);
		}else if(C3P0Properties.password.string.equals(string)) {
			cpds.setPassword(value);
		}else if(C3P0Properties.preferredTestQuery.string.equals(string)) {
			cpds.setPreferredTestQuery(value);
		}else if(C3P0Properties.privilegeSpawnedThreads.string.equals(string)) {
			cpds.setPrivilegeSpawnedThreads("true".equals(value)?true : false);
		}else if(C3P0Properties.propertyCycle.string.equals(string)) {
			cpds.setPropertyCycle(Integer.parseInt(value));
		}else if(C3P0Properties.statementCacheNumDeferredCloseThreads.string.equals(string)) {
			cpds.setStatementCacheNumDeferredCloseThreads(Integer.parseInt(value));
		}else if(C3P0Properties.testConnectionOnCheckin.string.equals(string)) {
			cpds.setTestConnectionOnCheckin("true".equals(value)?true : false);
		}else if(C3P0Properties.testConnectionOnCheckout.string.equals(string)) {
			cpds.setTestConnectionOnCheckout("true".equals(value)?true : false);
		}else if(C3P0Properties.unreturnedConnectionTimeout.string.equals(string)) {
			cpds.setUnreturnedConnectionTimeout(Integer.parseInt(value));
		}else if(C3P0Properties.user.string.equals(string)) {
			cpds.setUser(value);
		}
	}
}
