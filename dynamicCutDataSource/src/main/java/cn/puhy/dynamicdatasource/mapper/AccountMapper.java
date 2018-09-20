package cn.puhy.dynamicdatasource.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author PUHY
 * 2018-09-20 22:37
 */
public interface AccountMapper {

    @Select({"select accountName from account where id = #{id}"})
    String getAccountName(int id);
}
