package ar.app.entities;

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
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 25, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String observation;
}
