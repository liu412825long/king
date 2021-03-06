package cn.bj.king.config;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"cn.bj.king.mapper.slave"},
        sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class ReadDataSourceConfig {

    @Autowired
    Environment environment;


    @Bean(name = "slaveDataSource")
    @Qualifier("slaveDataSource")
    public DataSource slaveDataSource(@Qualifier("slaveHikariConfig")HikariConfig hikariConfig) {
        System.out.println("实例化从库");
        HikariDataSource hikariDataSource=new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    /**
     * 配置连接池信息
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean("slaveHikariConfig")
    public HikariConfig slaveHikariConfig(){
        HikariConfig hikariConfig=new HikariConfig();
        return hikariConfig;
    }

    /**
     * SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/slave/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.bj.king.entity");
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
