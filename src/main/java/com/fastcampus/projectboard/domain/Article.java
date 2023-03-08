package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 auto-increment 사용하겠다
    private Long id;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; // 해시태그

    // 실무에선 양방향 바인딩을 일부러 풀고 하는 경우가 많음
    @ToString.Exclude // 이쪽에서 toString 흐름을 끊어줌
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    // 기본생성자는 평소엔 오픈하지 않을거기에 protected 접근제어자 가짐
    protected Article() {}

    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // factory method
    // 의도를 전달 -> 도메인 아티클을 생성하고자 할땐 아래 인자들로 가이드 하는 것
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

    // 뼈대인 skeleton code부터 만들어보기(equals&hashcode overriding 통해)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
          // boiler-plate 정리
//        if (o == null || getClass() != o.getClass()) return false;
//        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}