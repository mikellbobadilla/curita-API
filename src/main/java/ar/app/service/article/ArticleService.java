package ar.app.service.article;

import ar.app.dto.article.ArticleRequest;
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

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final SectionRepository sectionRepository;
    private final ProviderRepository providerRepository;
    private final MapperObject mapper;

    public Page<ArticleModel> getAll(int page, int size) {

        --page;

        if (page < 0) {
            throw new RuntimeException("Page cannot be less than 1");
        }

        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findAll(pageable);
    }

    public ArticleModel create(ArticleRequest request) throws ProviderNotFountException, SectionNotFoundException {

        if (request.providerId() == null || request.sectionId() == null)
            throw new RuntimeException("Provider or Section cannot be null");

        ProviderModel provider = providerRepository.findById(request.providerId())
                .orElseThrow(() -> new ProviderNotFountException("Provider not found"));

        SectionModel section = sectionRepository.findById(request.sectionId())
                .orElseThrow(() -> new SectionNotFoundException("Section not found"));

        /* Todo: Check if the barcode exists */
        if (articleRepository.existsByBarcode(request.barcode())) {
            throw new RuntimeException("Barcode already exists");
        }

        ArticleModel model = mapper.mapData(ArticleModel.class, request);
        model.setProvider(provider);
        model.setSection(section);
        // Todo: Need to implement ArticleResponse
        return articleRepository.saveAndFlush(model);
    }

    public ArticleModel getBy(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public void update(Long id, ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException, IllegalAccessException {

        if (request.providerId() == null || request.sectionId() == null)
            throw new RuntimeException("Provider or Section cannot be null");

        ProviderModel provider = providerRepository.findById(request.providerId())
                .orElseThrow(
                        () -> new ProviderNotFountException("Cannot find provider with id: " + request.providerId()));
        SectionModel section = sectionRepository.findById(request.sectionId())
                .orElseThrow(() -> new SectionNotFoundException("Cannot find section with id: " + request.sectionId()));

        if (articleRepository.existsByBarcodeAndIdNot(request.barcode(), id)) {
            throw new RuntimeException("Barcode already exists");
        }

        ArticleModel model = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        mapper.mapData(model, request);
        model.setProvider(provider);
        model.setSection(section);
        /* Todo: Need to implement ArticleResponse */
        articleRepository.saveAndFlush(model);
    }

    public ArticleModel partialUpdate(Long id, ArticleRequest request) throws IllegalAccessException {
        
        ArticleModel model = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        if (articleRepository.existsByBarcodeAndIdNot(request.barcode(), id)){
            throw new RuntimeException("Barcode already exists");
        }
        
        mapper.mapData(model, request);
        
        return articleRepository.saveAndFlush(model);
    }

    public void deleteBy(Long id) {
        if (!articleRepository.existsById(id))
            throw new RuntimeException("Article not found");

        articleRepository.deleteById(id);
    }
}
