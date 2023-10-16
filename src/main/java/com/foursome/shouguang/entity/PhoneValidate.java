package com.foursome.shouguang.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhoneValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phone;

    private String validate;

}
