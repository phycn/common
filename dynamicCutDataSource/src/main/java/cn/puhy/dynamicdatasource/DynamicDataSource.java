package cn.puhy.dynamicdatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author PUHY
 * 2018-09-20 21:48
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}
