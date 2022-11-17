package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//entity class의 이름을 db 테이블의 name과 동일하게 만든다

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // @Table(name = "user") 없어도 이름이 동일하면 JPA에서 자동으로 Mapping 시켜준다
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "account")
    private String account;

    private String email;

    private String phoneNumber; // DB에서는 phone_number이지만 JPA가 자동으로 맵핑시켜준다.

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}
