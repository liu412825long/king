package cn.bj.king.service.impl;

import cn.bj.king.dto.AccountDTO;
import cn.bj.king.entity.AccountDO;
import cn.bj.king.mapper.slave.AccountDOMapper;
import cn.bj.king.service.AccountService;
import cn.bj.king.util.TypeConverter;
import cn.bj.king.vo.AccountVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static Logger logger= LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDOMapper accountDOMapper;

    @Override
    @Transactional(value = "slaveTransactionManager",rollbackFor = Exception.class)
    public int createAccount(AccountDTO accountDTO) {
        logger.info("创建account账户信息。");
        int result=accountDOMapper.insertSelective(TypeConverter.convert(accountDTO, AccountDO.class));
        String[] nickName=accountDTO.getNickname().split(",");
        System.out.println(nickName);
        return result;
    }

    @Override
    public int batchCreateAccount(List<AccountDTO> list) {
        logger.info("批量创建account.");
        return accountDOMapper.batchInsert(TypeConverter.convert(list,AccountDO.class));
    }

    @Override
    public PageInfo findAllAccount(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<AccountDO> accountDOS= accountDOMapper.findByPage();
        PageInfo<AccountDO> pageInfo = new PageInfo<>(accountDOS);
        return pageInfo;
    }

    @Override
    public AccountVO findById(Integer id) {
        logger.info("查询id为{}的账户信息",id);
        AccountVO accountVO=TypeConverter.convert(accountDOMapper.selectByPrimaryKey(id),AccountVO.class);
        String name=null;
        name.indexOf("d");
        return accountVO;
    }

    @Override
    public boolean updateAccount(AccountDTO accountDTO) {
        AccountDO accountDO=TypeConverter.convert(accountDTO,AccountDO.class);
        return accountDOMapper.updateByPrimaryKeySelective(accountDO)>0;
    }

    @Override
    public Boolean deleteAccount(Integer id) {
        String name=null;
        name.indexOf("d");
        return accountDOMapper.deleteById(id)>0;
    }
}
