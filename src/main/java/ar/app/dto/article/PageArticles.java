package ar.app.dto.article;

import java.util.List;

import lombok.Builder;

@Builder
public record PageArticles(
        List<ArticleResponse> content,
        Integer pageNumber,
        Integer pageSize,
        Integer totalPages,
        Long totalElements
) {
}
