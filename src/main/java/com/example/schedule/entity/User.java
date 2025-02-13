package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

//사용자 Entity
@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //사용자 ID

    @Column(nullable = false, unique = true) //필수, 유일 조건
    private String name; //사용자명

    @Column(nullable = false, unique = true) //필수, 유일 조건
    private String email; //이메일

    @Column(nullable = false) //필수 조건
    private String password; //비밀번호

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //사용자 정보 수정
    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //비밀번호 변경
    public void updatePassword(String password) {
        this.password = password;
    }

    //비밀번호 조회
    public String getPassword() {
        return this.password;
    }
}






