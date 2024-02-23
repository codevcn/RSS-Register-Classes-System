package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "BlockOfMajor")
public class BlockOfMajor {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ToString.Exclude
    @OneToMany(mappedBy = "majorBlock")
    @JsonManagedReference
    private Set<CreditDetail> creditDetails;
}
