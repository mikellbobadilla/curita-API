package ar.app.controllers.provider;

import ar.app.dto.provider.ProviderRequest;
import ar.app.dto.provider.ProviderResponse;
import ar.app.entities.ProviderEntity;
import ar.app.services.provider.ProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/providers")
@AllArgsConstructor
public class ProviderController {

    private final ProviderService service;

    @GetMapping
    ResponseEntity<Page<ProviderEntity>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.getAll(page, size), HttpStatus.OK);
    }
 
    @PostMapping
    ResponseEntity<ProviderResponse> create(@RequestBody @Valid ProviderRequest request) throws IllegalAccessException {

        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProviderEntity> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.getBy(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProviderRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        System.out.println("Id: " + id);
        System.out.println("Request: " + request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /* Todo: Implementar este servicio */
    /*
    @RestController
@RequestMapping("/api/resource/{resourceId}")
public class ResourceController {

    @Autowired
    private Validator validator;

    @PatchMapping
    public ResponseEntity<?> updateResource(@PathVariable Long resourceId, @RequestBody Map<String, Object> patchData) {
        // Obtener el recurso de la base de datos
        Resource existingResource = resourceService.findById(resourceId);

        // Convertir el Map de datos de parche a un objeto Resource
        Resource updatedResource = convertPatchDataToResource(patchData, existingResource);

        // Validar el objeto Resource actualizado dinámicamente
        Set<ConstraintViolation<Resource>> violations = validator.validate(updatedResource);

        if (!violations.isEmpty()) {
            // Construir una respuesta de error con los mensajes de validación
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Resource> violation : violations) {
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        // Actualizar el recurso en la base de datos
        resourceService.save(existingResource);

        return ResponseEntity.ok("Recurso actualizado correctamente");
    }

    private Resource convertPatchDataToResource(Map<String, Object> patchData, Resource existingResource) {
        // Crear un nuevo objeto Resource con los campos actualizados de patchData
        Resource updatedResource = new Resource();

        // Iterar sobre los campos del patchData y asignarlos al objeto Resource
        for (Map.Entry<String, Object> entry : patchData.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            // Utilizar reflexión para asignar el valor al campo correspondiente en el objeto Resource
            try {
                Field field = Resource.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(updatedResource, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Manejar errores si el campo no existe en el objeto Resource
                e.printStackTrace();
            }
        }

        return updatedResource;
    }
}

    * */
}
