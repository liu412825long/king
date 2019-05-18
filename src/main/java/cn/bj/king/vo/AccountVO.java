package cn.bj.king.vo;

import cn.bj.king.dto.AccountDTO;
import lombok.Data;

import java.util.Date;

@Data
public class AccountVO {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String nickname;

    private String name;

    private Boolean sex;

    private String qq;

    private String wechat;

    private Date birthday;

    private String address;

    private String status;

    private String icon;

    private Date createTime;

    private Date updateTime;

    private AccountDTO accountDTO;

}
