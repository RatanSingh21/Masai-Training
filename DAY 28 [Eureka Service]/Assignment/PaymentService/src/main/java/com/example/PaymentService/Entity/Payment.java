package com.example.PaymentService.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "payment_service_table")
public class Payment {

    @Id
    private Long orderId;

    private String orderStatus;

    @JsonIgnore
    private String served;
}
