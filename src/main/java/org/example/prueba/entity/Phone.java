package org.example.prueba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private String cityCode;
    private String contryCode;
}
