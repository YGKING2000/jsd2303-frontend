package com.example.baking.security;

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
    private Long id;
    private String nickName;
    private String imgUrl;
    private Integer isAdmin;
    public CustomUserDetails(Long id, String nickName, String imgUrl, Integer isAdmin, String username, 
                             String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickName = nickName;
        this.imgUrl = imgUrl;
        this.isAdmin = isAdmin;
    }
}
