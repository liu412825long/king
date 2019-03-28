package cn.bj.king.controller;

import cn.bj.king.base.ResponseMessage;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.service.AccountService;
import cn.bj.king.service.UserService;
import cn.bj.king.vo.AccountVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过ID查询账户信息
     * @param id
     * @return
     */
    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<AccountVO> findById(@PathVariable("id")Integer id){
        return ResponseMessage.build(0,userService.queryUserById(id));

    }
}
