package com.example.boot.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UserLoginDTO
 * @date 2023/05/30 09:52
 */
@Data
public class UserLoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
