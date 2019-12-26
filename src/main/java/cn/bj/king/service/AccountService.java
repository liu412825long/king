package cn.bj.king.service;

import cn.bj.king.dto.AccountDTO;
import cn.bj.king.entity.AccountDO;
import cn.bj.king.vo.AccountVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountService {

    int createAccount(AccountDTO accountDTO);

    int batchCreateAccount(List<AccountDTO> list);

    PageInfo findAllAccount(int pageNum, int pageSize);

    AccountVO findById(Integer id);

    boolean updateAccount(AccountDTO accountDTO);

    Boolean deleteAccount(Integer id);
}
