package ar.app.service.provider;

import ar.app.dto.provider.PageProviders;
import ar.app.dto.provider.ProviderRequest;
import ar.app.dto.provider.ProviderResponse;
import ar.app.exception.provider.ProviderException;
import ar.app.exception.provider.ProviderNotFountException;
import ar.app.model.provider.ProviderModel;
import ar.app.repository.provider.ProviderRepository;
import ar.app.utils.MapperObject;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProviderService {

    private final ProviderRepository repository;
    private final MapperObject mapper;

    /* Todo: Add custom exception */
    public PageProviders getAll(int page, int size) {
        --page;
        if (page < 0) {
            throw new RuntimeException("Page cannot be less than 1");
        }

        Pageable pageable = PageRequest.of(page, size);
        return pageMapper(repository.findAll(pageable));
    }

    public ProviderResponse create(ProviderRequest request) throws ProviderException {

        if (repository.existsByName(request.name())) {
            throw new ProviderException("Provider exists");
        }

        ProviderModel provider = mapper.mapData(ProviderModel.class, request);

        return mapper.mapData(ProviderResponse.class, repository.saveAndFlush(provider));
    }

    /* Todo: Change type return */
    public ProviderModel getBy(Long id) throws ProviderNotFountException {
        return repository.findById(id)
                .orElseThrow(() -> new ProviderNotFountException("Provider not found!"));
    }

    public void update(Long id, ProviderRequest request) throws IllegalAccessException, ProviderNotFountException {
        ProviderModel provider = repository.findById(id)
                .orElseThrow(() -> new ProviderNotFountException("Provider not found"));
        repository.saveAndFlush(mapper.mapData(provider, request));
    }

    public ProviderResponse partialUpdate(Long id, ProviderRequest request) throws IllegalAccessException, ProviderNotFountException {
        ProviderModel provider = repository.findById(id)
                .orElseThrow(() -> new ProviderNotFountException("Provider not found"));
        provider = mapper.mapData(provider, request);
        repository.saveAndFlush(provider);
        return mapper.mapData(ProviderResponse.class, provider);
    }

    public void deleteBy(Long id) throws ProviderNotFountException {
        if (!repository.existsById(id))
            throw new ProviderNotFountException("Provider not found");

        repository.deleteById(id);
    }

    /* Private Methods */
    private PageProviders pageMapper(Page<ProviderModel> models) {
        List<ProviderModel> content = models.getContent();

        return PageProviders.builder()
                .content(content.stream().map(model -> mapper.mapData(ProviderResponse.class, model)).toList())
                .pageNumber(models.getPageable().getPageNumber() + 1)
                .pageSize(models.getPageable().getPageSize())
                .totalPages(models.getTotalPages())
                .totalElements(models.getTotalElements())
                .build();
    }
}
