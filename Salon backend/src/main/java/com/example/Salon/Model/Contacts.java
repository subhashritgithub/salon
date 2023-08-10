package com.example.Salon.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contact_id;
    @ManyToOne
    private Users user_id;


    @Column(name = "user_message")
    private  String message;


}
