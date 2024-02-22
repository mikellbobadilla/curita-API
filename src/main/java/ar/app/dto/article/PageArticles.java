package ar.app.dto.article;

import java.util.List;

public record PageArticles(
        List<ArticleResponse> content,
        Integer pageNumber,
        Integer pageSize,
        Integer totalPages,
        Long totalElements
) {
}
