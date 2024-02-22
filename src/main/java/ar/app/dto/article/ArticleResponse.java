package ar.app.dto.article;

import ar.app.dto.provider.ProviderResponse;
import ar.app.dto.section.SectionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ArticleResponse(
                Long id,
                String name,
                Integer stock,
                String price,
                String cost,
                String barcode,
                ProviderResponse provider,
                SectionResponse section,
                String observation) {

        public ArticleResponse withProviderAndSection(ProviderResponse newProvider, SectionResponse newSection) {

                return new ArticleResponse(id, name, stock, price, cost, barcode, newProvider, newSection, observation);
        }
}
