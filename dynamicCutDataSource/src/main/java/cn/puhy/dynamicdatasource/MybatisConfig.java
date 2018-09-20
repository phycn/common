package cn.puhy.dynamicdatasource;

import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author PUHY
 * 2018-09-20 21:45
 */
@Configuration
@MapperScan(basePackages = {"cn.puhy.dynamicdatasource.mapper"})  // 这里需要替换为实际的路径
public class MybatisConfig {

    @Autowired
    @Qualifier(DataSources.MASTER_DB)
    private DataSource masterDB;

    @Autowired
    @Qualifier(DataSources.SLAVE_DB)
    private DataSource slaveDB;

    /**
     * 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDB);

        // 配置多数据源
        Map<Object, Object> dsMap = Maps.newHashMap();
        dsMap.put(DataSources.MASTER_DB, masterDB);
        dsMap.put(DataSources.SLAVE_DB, slaveDB);
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }
}
