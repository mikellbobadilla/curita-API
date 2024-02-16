package ar.app.services.provider;

import ar.app.dto.provider.ProviderRequest;
import ar.app.dto.provider.ProviderResponse;
import ar.app.entities.ProviderEntity;
import ar.app.repositories.ProviderRepository;
import ar.app.utils.MapperObject;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProviderService {

    private final ProviderRepository repository;

    public Page<ProviderEntity> getAll(int page, int size) {
        --page;
        if (page < 0) {
            throw new RuntimeException("Page cannot be less than 1");
        }

        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public ProviderResponse create(ProviderRequest request) throws IllegalAccessException {

        MapperObject<ProviderEntity, ProviderRequest> mapper = new MapperObject<>();
        ProviderEntity provider = mapper.mapData(new ProviderEntity(), request);

        MapperObject<ProviderResponse, ProviderEntity> mapperResponse = new MapperObject<>();
        return mapperResponse.mapData(new ProviderResponse(), repository.saveAndFlush(provider));
    }

    public ProviderEntity getBy(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Provider not found!"));
    }

    public void update(Long id, ProviderRequest request) {
        ProviderEntity provider = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        provider.setName(request.name());
        provider.setAddress(request.address());
        provider.setEmail(request.email());
        provider.setContactProvider(request.contactProvider());
        provider.setPhone(request.phone());
        provider.setCuil(request.cuil());
        provider.setStartOperations(request.startOperations());
        provider.setObservation(request.observation());

        repository.saveAndFlush(provider);
    }

    public void deleteBy(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Provider not found");

        repository.deleteById(id);
    }
}
