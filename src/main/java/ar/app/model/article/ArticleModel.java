package ar.app.model.article;

import ar.app.model.provider.ProviderModel;
import ar.app.model.section.SectionModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Data 
@Builder
public class ArticleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 40, nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "INT CHECK(stock >= 0)")
    private Integer stock;
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2) CHECK(price >= 0.00)")
    private BigDecimal price;
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2) CHECK(cost >= 0.00)")
    private BigDecimal cost;
    @Column(length = 20, unique = true)
    private String barcode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "provider_id", nullable = false)
    private ProviderModel provider;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "section_id", nullable = false)
    private SectionModel section;

    @Column(length = 50)
    private String observation;
}
