package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false) // ManyToOne 연관관계
    private Article article; // 게시글 (ID)

    @Setter
    @Column(nullable = false, length = 500)
    private String content; // 본문

    @Setter
    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성일시
    @Setter
    @Column(nullable = false, length = 100)
    private String createdBy; // 생성자
    @Setter
    @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일시
    @Setter
    @Column(nullable = false, length = 100)
    private String modifiedBy; // 수정자

    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    // factory method
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
