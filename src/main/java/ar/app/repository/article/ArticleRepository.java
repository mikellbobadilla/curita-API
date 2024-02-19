package ar.app.repository.article;

import ar.app.model.article.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Long> {
}
