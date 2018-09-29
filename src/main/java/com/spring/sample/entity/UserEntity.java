//package com.spring.sample.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//
//@Entity
//@Table(name = "user")
//@Data
//@NoArgsConstructor
//public class UserEntity implements UserDetails {
//    private static final long serialVersionUID = 1L;
//
//    public enum Authority {USER, ADMIN};
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @NotNull
//    private String username;
//
//    @NotNull
//    private String password;
//
//    @NotNull
//    private String email;
//
//    private Data createdAt;
//
//    private Data updatedAt;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Authority authority;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(authority.toString()));
//        return authorities;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
