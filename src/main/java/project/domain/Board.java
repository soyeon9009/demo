package project.domain;

import java.util.Date;
//외장 라이브러리 (gradle로 다운로드한 롬북이 외장 라이브러리)
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import javax.persistance.*;
import java.util.Date;

//롬복에 있는 Getter라는 메서드를 통해
//히딘에 있는 클래스 Board는
//자동으로 getter,setter메서드가 생겅됨을 암시함(@어노테이션)

@Getter
@Setter
//메서드 getter,setter를 만들어 쓰지 않아도 getter,setter사용이 가능함.
@ToString
//@Entity
public class Board{
    //식별코드
//    @Id
//    @GeneratedValue
    private Long seq;

    private String title;

//    @Column(updatable = false)
    private String writer;

    private String content;

//    @Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
    private Date createDate;

//    @Column(insertable = false, updatable = false, columnDefinition = "number default 0")
    private Long cnt;

    //원래는 setter,getter라는 메서드가 있어야 private 필드값에 데이터를 넣을 수 있지만,
    //(gradle 에서 설치) 롬복이라는 라이브러리로 자동 getter,setter 메소드 생성
}
