package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

//일정 Entity
@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //일정 ID

    @Column(nullable = false)
    private String title; //제목

    @Column(columnDefinition = "LongText")
    private String contents; //내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //작성자 (연관 관계)

    public Schedule() {
    }

    public Schedule(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    //제목 수정
    public void setTitle(String title) {
        this.title = title;
    }

    //내용 수정
    public void setContents(String contents) {
        this.contents = contents;
    }

    //작성자 설정
    public void setUser(User user) {
        this.user = user;
    }

}
