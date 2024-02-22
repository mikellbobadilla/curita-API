package ar.app.model.section;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sections")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 25, nullable = false, unique = true)
    private String name;
    @Column(length = 50)
    private String observation;
}
