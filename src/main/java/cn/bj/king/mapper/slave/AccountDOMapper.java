package cn.bj.king.mapper.slave;

import cn.bj.king.entity.AccountDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    AccountDO selectByPrimaryKey(Integer id);

    AccountDO selectByUsername(@Param("username") String username,@Param("password")String password);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);

    int batchInsert(List<AccountDO> list);

    int deleteById(Integer id);

    List<AccountDO> findByPage();

    int selectCountByCondition();
}