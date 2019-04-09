package cn.bj.king.controller;

import cn.bj.king.base.ResponseMessage;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.entity.AccountDO;
import cn.bj.king.service.AccountService;
import cn.bj.king.vo.AccountVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(value = "/account",description = "用户管理接口")
@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 创建账户
     * @param accountDTO
     * @return
     */
    @ApiOperation(value = "创建账户信息",notes = "添加账户")
    @ApiImplicitParam(name = "accountDTO", value = "用户详细实体accountDTO", required = true, dataType = "AccountDTO")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<Integer> createAccount(@RequestBody AccountDTO accountDTO){
        int result=accountService.createAccount(accountDTO);
        return ResponseMessage.build(0,result);
    }

    /**
     * 修改账户
     * @param accountDTO
     * @return
     */
    @PutMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<Integer> updateAccount(@PathVariable()Integer id, @RequestBody AccountDTO accountDTO){
        accountDTO.setId(id);
        boolean result=accountService.updateAccount(accountDTO);
        return ResponseMessage.build(0,result);
    }
    /**
     * 删除账户
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<Integer> deleteAccount(@PathVariable("id")Integer id){
        boolean result=accountService.deleteAccount(id);
        return ResponseMessage.build(0,result);
    }


    /**
     * 通过ID查询账户信息
     * @param id
     * @return
     */
    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<AccountVO> findById(@PathVariable("id")Integer id){
        return ResponseMessage.build(0,accountService.findById(id));

    }
    /**
     * 通过ID参数查询账户信息
     * @param id
     * @return
     */
    @GetMapping(value = "queryById",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<AccountVO> queryById(@Param("id") Integer id){
        return ResponseMessage.build(0,accountService.findById(id));

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageInfo queryByPage(@RequestParam(value = "pageNum",defaultValue = "0",required = false) int pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize){
        return accountService.findAllAccount(pageNum,pageSize);
    }
}
