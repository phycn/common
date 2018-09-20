package cn.puhy.dynamicdatasource.controller;

import cn.puhy.dynamicdatasource.DataSources;
import cn.puhy.dynamicdatasource.RoutingDataSource;
import cn.puhy.dynamicdatasource.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PUHY
 * 2018-09-20 23:13
 */
@RestController
public class TestController {

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/test")
    @RoutingDataSource(DataSources.SLAVE_DB)
    public String test(@RequestParam("id") int id) {
        return accountMapper.getAccountName(id);
    }
}
