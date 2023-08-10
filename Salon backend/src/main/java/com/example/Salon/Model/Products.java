package com.example.Salon.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int Products_id;

    @Column(name = "product_name")
    private String Product_name;

    @Column(name = "product_image")
    private String Product_image;

    @Column(name = "product_description")
    private String Product_description;

}
