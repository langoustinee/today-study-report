package com.kakao.lango.moviereview.domain;

import jakarta.persistence.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
//  지연로딩을 통해 생성하기 때문에 movie를 get하지 않은 상태에서 toString을 호출하면 예외가 발생한다.
//  그래서 toString 호출시 movie는 제외하고 호출해야 한다.
@ToString(exclude = "movie")
// @Embeddable은 부모 테이블을 만들 때 이 속성의 값을 포함시켜 생성하도록 해주는 어노테이션이다.
@Embeddable
@Entity
public class MovieImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    // 첨부파일의 이름 중복 방지를 위함.
    private String uuid;

    // 파일 이름
    private String imgName;

    // 하나의 디렉토리에 너무 많은 파일이 저장되지 않아야 한다.
    // 업로드한 날짜 별로 파일을 기록하기 위해 디렉토리 별로 관리한다.
    private String path;

    /*
    * 하나의 Movie가 여러개의 MovieImage를 소유한다.
    * 데이터를 불러올 때 movie를 불러오지 않고 사용할 때 불러오도록 지연 로딩을 설정한다.
    * 외래키의 이름은 movie_mno로 만들어진다.
    * */
    @ManyToOne(fetch = FetchType.LAZY) 
    private Movie movie;

}
