package com.act.repomanagementsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bank bank;
    @ManyToOne
    private ProductType productType;


    private String latestBranch;
    private String description;
//    @URL(message = "Invalid URL format")
    private String link;
    private String cicd;
    private String readme;
    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    private String receivedFrom;

    @PrePersist
    protected void onCreate() {
        receivedDate = LocalDateTime.now();
    }

}
