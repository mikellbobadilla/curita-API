package ar.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 25, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String address;
    @Column(length = 50)
    private String email;
    @Column(length = 25)
    private String contactProvider;
    @Column(length = 25, nullable = false)
    private String phone;
    @Column(length = 25, nullable = false, unique = true)
    private String cuil;
    @Column(nullable = false)
    private Date startOperations;
    @Column(length = 50)
    private String observation;
}
