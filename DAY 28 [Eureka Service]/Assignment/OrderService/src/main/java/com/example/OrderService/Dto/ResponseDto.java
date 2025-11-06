package com.example.OrderService.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    public Long orderId;
    private String productName;
    private String status;
    private String serverBy;

}
