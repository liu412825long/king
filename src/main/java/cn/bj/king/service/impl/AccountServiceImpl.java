package cn.bj.king.service.impl;

import cn.bj.king.constants.RedisExpireConstants;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.entity.AccountDO;
import cn.bj.king.enums.ErrorCodeEnum;
import cn.bj.king.exception.GlobalException;
import cn.bj.king.mapper.slave.AccountDOMapper;
import cn.bj.king.service.AccountService;
import cn.bj.king.util.*;
import cn.bj.king.vo.AccountVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private static Logger logger= LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDOMapper accountDOMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int createAccount(AccountDTO accountDTO) {
        logger.info("创建account账户信息。");
        //对密码进行加密
        String password=accountDTO.getPassword();
        accountDTO.setPassword(MD5Coder.encode(password));
        return accountDOMapper.insertSelective(TypeConverter.convert(accountDTO, AccountDO.class));
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
        log.info("--------"+log.isDebugEnabled());
        log.info("--------"+logger.isDebugEnabled());
        AccountVO accountVO=TypeConverter.convert(accountDOMapper.selectByPrimaryKey(id),AccountVO.class);
        return accountVO;
    }

    @Override
    public boolean updateAccount(AccountDTO accountDTO) {
        AccountDO accountDO=TypeConverter.convert(accountDTO,AccountDO.class);
        return accountDOMapper.updateByPrimaryKeySelective(accountDO)>0;
    }

    @Override
    public boolean deleteAccount(Integer id) {
        return accountDOMapper.deleteById(id)>0;
    }

    @Override
    public String login(String username, String password) {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new GlobalException(ErrorCodeEnum.PARAMETERS_IS_NULL);
        }
        password=MD5Coder.encode(password);
        AccountDO accountDO=accountDOMapper.selectByUsername(username,password);
        if(accountDO==null){
            throw new GlobalException(ErrorCodeEnum.ACCOUNT_NOT_EXIST);
        }
        AccountVO accountVO= TypeConverter.convert(accountDO,AccountVO.class);
        //生成一个token
        String accountJson= JSON.stringify(accountVO);
        String token= TokenUtil.createToken();
        redisUtil.set(token,accountJson, RedisExpireConstants.ACCOUNT_EXPIRE);
        return token;
    }


}
