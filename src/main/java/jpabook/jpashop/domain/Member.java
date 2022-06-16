package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @JsonIgnore
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")//Order의 member필드가 주인, 거울, 읽기전용
    private List<Order> orders = new ArrayList<>();
}
