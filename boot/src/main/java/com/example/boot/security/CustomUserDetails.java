package com.example.boot.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className CustomUserDetails
 * @date 2023/05/31 10:25
 */
// 因为父类中有构造方法，此时添加@Data会让当前类添加构造方法，此方法可能会和父类的冲突所以报错
@Getter
@Setter
public class CustomUserDetails extends User {
    private Integer id;
    private String nickname;
    public CustomUserDetails(Integer id, String nickname, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickname = nickname;
    }
}
