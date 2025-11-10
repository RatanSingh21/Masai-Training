package in.ratansgh.OrderServiceNew.service;

import in.ratansgh.OrderServiceNew.client.PaymentFeignClient;
import in.ratansgh.OrderServiceNew.dto.CreateOrderRequest;
import in.ratansgh.OrderServiceNew.dto.OrderDTO;
import in.ratansgh.OrderServiceNew.dto.PaymentDTO;
import in.ratansgh.OrderServiceNew.entity.Order;
import in.ratansgh.OrderServiceNew.exception.OrderNotFoundException;
import in.ratansgh.OrderServiceNew.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private final PaymentFeignClient feignClient;
    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    public Order createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        return repository.save(order);
    }

    // REST TEMPLATE
    public OrderDTO getOrderWithPaymentRestTemplate(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        String url = paymentServiceUrl + "/" + id;

        PaymentDTO payment;
        try {
            payment = restTemplate.getForObject(url, PaymentDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            payment = new PaymentDTO(id, "NOT_FOUND");
        }

        return toOrderDTO(order, payment);
    }

    public OrderDTO getOrderWithPaymentWebClient(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        PaymentDTO payment = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(PaymentDTO.class)
                .onErrorResume(WebClientResponseException.NotFound.class, ex ->
                        Mono.just(new PaymentDTO(id, "NOT_FOUND"))
                )
                .defaultIfEmpty(new PaymentDTO(id, "NOT_FOUND"))
                .block();

        return toOrderDTO(order, payment);
    }

    // FEIGN
    public OrderDTO getOrderWithPaymentFeign(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        PaymentDTO payment;
        try {
            payment = feignClient.getPaymentStatus(id);
        } catch (FeignException.NotFound e) {
            payment = new PaymentDTO(id, "NOT_FOUND");
        }
        return toOrderDTO(order, payment);
    }

    private OrderDTO toOrderDTO(Order order, PaymentDTO payment) {
        return new OrderDTO(order.getId(), order.getProductName(), order.getQuantity(), payment.getStatus());
    }
}
