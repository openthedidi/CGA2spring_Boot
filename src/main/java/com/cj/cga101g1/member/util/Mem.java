package com.cj.cga101g1.member.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Entity
@Table(name = "mem")
@Getter
@Setter
public class Mem extends MemCore  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memNo;
//    @NotNull(message = "MemAccount不可為null")
//    @NotBlank(message = "MemAccount不可是空白")
    private String memAccount;
//    @NotNull(message = "Password不可為null")
//    @NotBlank(message = "Password不可是空白")
    private String memPassword;
    private Integer memStatus;
    private Integer memVrfed;
    private Date memNoVrftime;
    private String memName;

    @Pattern(regexp="^09[0-9]{8}",message="手機格式不正確")
    private String memMobile;
    private String memCity;
    private String memDist;
    private String memAdd;
    @Email
    @NotNull(message = "Email需填寫")
    private String memEmail;
    @Column(name = "MemBirth")
    private Date memBirth;
    @Column(name = "MemJoinTime")
    private Date memJoinTime;
    @Column(name = "CreditcardNo")
    private String creditcardNo;
    @Column(name = "CreditcardDate")
    private String creditcardDate;
    @Column(name = "CreditcardSecurityNo")
    private String creditcardSecurityNo;
    @Column(name = "BankAccount")
    private String bankAccount;
    @Column(name = "BankAccountOwner")
    private String bankAccountOwner;
    @Column(name = "UserStatus")
    private Integer userStatus;
    @Column(name = "MyPic")
    private byte[] myPic;
    @Column(name = "IsMute")
    private Integer isMute;


    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public String getMemAccount() {
        return memAccount;
    }

    public void setMemAccount(String memAccount) {
        this.memAccount = memAccount;
    }

    public String getMemPassword() {
        return memPassword;
    }

    public void setMemPassword(String memPassword) {
        this.memPassword = memPassword;
    }

    public Integer getMemStatus() {
        return memStatus;
    }

    public void setMemStatus(Integer memStatus) {
        this.memStatus = memStatus;
    }

    public Integer getMemVrfed() {
        return memVrfed;
    }

    public void setMemVrfed(Integer memVrfed) {
        this.memVrfed = memVrfed;
    }

    public Date getMemNoVrftime() {
        return memNoVrftime;
    }

    public void setMemNoVrftime(Date memNoVrftime) {
        this.memNoVrftime = memNoVrftime;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemMobile() {
        return memMobile;
    }

    public void setMemMobile(String memMobile) {
        this.memMobile = memMobile;
    }

    public String getMemCity() {
        return memCity;
    }

    public void setMemCity(String memCity) {
        this.memCity = memCity;
    }

    public String getMemDist() {
        return memDist;
    }

    public void setMemDist(String memDist) {
        this.memDist = memDist;
    }

    public String getMemAdd() {
        return memAdd;
    }

    public void setMemAdd(String memAdd) {
        this.memAdd = memAdd;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public Date getMemBirth() {
        return memBirth;
    }

    public void setMemBirth(Date memBirth) {
        this.memBirth = memBirth;
    }

    public Date getMemJoinTime() {
        return memJoinTime;
    }

    public void setMemJoinTime(Date memJoinTime) {
        this.memJoinTime = memJoinTime;
    }

    public String getCreditcardNo() {
        return creditcardNo;
    }

    public void setCreditcardNo(String creditcardNo) {
        this.creditcardNo = creditcardNo;
    }

    public String getCreditcardDate() {
        return creditcardDate;
    }

    public void setCreditcardDate(String creditcardDate) {
        this.creditcardDate = creditcardDate;
    }

    public String getCreditcardSecurityNo() {
        return creditcardSecurityNo;
    }

    public void setCreditcardSecurityNo(String creditcardSecurityNo) {
        this.creditcardSecurityNo = creditcardSecurityNo;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccountOwner() {
        return bankAccountOwner;
    }

    public void setBankAccountOwner(String bankAccountOwner) {
        this.bankAccountOwner = bankAccountOwner;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public byte[] getMyPic() {
        return myPic;
    }

    public void setMyPic(byte[] myPic) {
        this.myPic = myPic;
    }

    public Integer getIsMute() {
        return isMute;
    }

    public void setIsMute(Integer isMute) {
        this.isMute = isMute;
    }

    /**
     *     根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        System.out.println("role000888"+roles);
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role+"role"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.memPassword;
    }

    @Override
    public String getUsername() {
        return this.memAccount;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
