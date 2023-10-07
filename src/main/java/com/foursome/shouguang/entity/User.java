package com.foursome.shouguang.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String gender;

    private String userrole;

    private String phone;

    private String address;

    private String idpicpath;

    private Date birthday;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creationdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifydate;


}
