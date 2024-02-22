package ar.app.service.article;

import ar.app.dto.article.ArticleRequest;
import ar.app.dto.article.ArticleResponse;
import ar.app.dto.article.PageArticles;
import ar.app.dto.provider.ProviderResponse;
import ar.app.dto.section.SectionResponse;
import ar.app.exception.article.ArticleException;
import ar.app.exception.article.ArticleNotFoundException;
import ar.app.exception.provider.ProviderNotFountException;
import ar.app.exception.section.SectionNotFoundException;
import ar.app.model.article.ArticleModel;
import ar.app.model.provider.ProviderModel;
import ar.app.model.section.SectionModel;
import ar.app.repository.article.ArticleRepository;
import ar.app.repository.provider.ProviderRepository;
import ar.app.repository.section.SectionRepository;
import ar.app.utils.MapperObject;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final SectionRepository sectionRepository;
    private final ProviderRepository providerRepository;
    private final MapperObject mapper;

    public PageArticles getAll(int page, int size) throws ArticleException {

        --page;

        if (page < 0) {
            throw new ArticleException("Page cannot be less than 1");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleModel> articles = articleRepository.findAll(pageable);

        return pageMapper(articles);
    }

    public ArticleResponse create(ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException, ArticleException {

        if (request.providerId() == null || request.sectionId() == null)
            throw new RuntimeException("Provider or Section cannot be null");

        ProviderModel provider = providerRepository.findById(request.providerId())
                .orElseThrow(() -> new ProviderNotFountException("Provider not found"));

        SectionModel section = sectionRepository.findById(request.sectionId())
                .orElseThrow(() -> new SectionNotFoundException("Section not found"));

        if (articleRepository.existsByBarcode(request.barcode())) {
            throw new ArticleException("Barcode already exists");
        }

        ArticleModel model = mapper.mapData(ArticleModel.class, request);
        model.setProvider(provider);
        model.setSection(section);

        return parseResponse(articleRepository.saveAndFlush(model));
    }

    public ArticleResponse getBy(Long id) throws ArticleNotFoundException {
        ArticleModel model = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));
        return parseResponse(model);
    }

    public void update(Long id, ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException, IllegalAccessException, ArticleException {

        if (request.providerId() == null || request.sectionId() == null)
            throw new ArticleException("Provider or Section cannot be null");

        ProviderModel provider = providerRepository.findById(request.providerId())
                .orElseThrow(
                        () -> new ProviderNotFountException("Cannot find provider with id: " + request.providerId()));
        SectionModel section = sectionRepository.findById(request.sectionId())
                .orElseThrow(() -> new SectionNotFoundException("Cannot find section with id: " + request.sectionId()));

        if (articleRepository.existsByBarcodeAndIdNot(request.barcode(), id)) {
            throw new ArticleException("Barcode already exists");
        }

        ArticleModel model = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        mapper.mapData(model, request);
        model.setProvider(provider);
        model.setSection(section);

        articleRepository.saveAndFlush(model);
    }

    public ArticleResponse partialUpdate(Long id, ArticleRequest request)
            throws IllegalAccessException, ArticleException {

        ArticleModel model = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        if (articleRepository.existsByBarcodeAndIdNot(request.barcode(), id)) {
            throw new ArticleException("Barcode already exists");
        }

        mapper.mapData(model, request);

        return parseResponse(articleRepository.saveAndFlush(model));
    }

    public void deleteBy(Long id) throws ArticleNotFoundException {
        if (!articleRepository.existsById(id))
            throw new ArticleNotFoundException("Article not found");

        articleRepository.deleteById(id);
    }

    /* Private Methods */

    private ArticleResponse parseResponse(ArticleModel model) {
        ArticleResponse response = mapper.mapData(ArticleResponse.class, model);

        ProviderResponse providerResponse = mapper.mapData(ProviderResponse.class, model.getProvider());
        SectionResponse sectionResponse = mapper.mapData(SectionResponse.class, model.getSection());

        return response.withProviderAndSection(providerResponse, sectionResponse);
    }

    private PageArticles pageMapper(Page<ArticleModel> models) {

        List<ArticleModel> content = models.getContent();

        List<ArticleResponse> response = content.stream()
                .map(model -> parseResponse(model))
                .toList();

        return PageArticles.builder()
                .content(response)
                .pageNumber(models.getPageable().getPageNumber() + 1)
                .pageSize(models.getPageable().getPageSize())
                .totalPages(models.getTotalPages())
                .totalElements(models.getTotalElements())
                .build();
    }
}
