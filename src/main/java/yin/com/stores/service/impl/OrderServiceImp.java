package yin.com.stores.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yin.com.stores.dto.OrderResponse;
import yin.com.stores.dto.OrderProductDTO;
import yin.com.stores.dto.request.OrderRequest;
import yin.com.stores.exception.AppException;
import yin.com.stores.exception.ErrorCode;
import yin.com.stores.mapper.OrderMapper;
import yin.com.stores.mapper.OrderProductMapper;
import yin.com.stores.model.*;
import yin.com.stores.repository.*;
import yin.com.stores.service.interf.OrderService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImp implements OrderService {
    OrderRepository orderRepository;
    OrderProductRepository orderProductRepository;
    OrderProductMapper orderProductMapper;
    StoreRepository storeRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    OrderMapper orderMapper;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority(#storeId) and hasRole('EMPLOYEE')")
    public OrderResponse createOrder(String storeId, String userId, OrderRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Check if the storeId is in the authorities
        boolean hasStoreAccess = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals(storeId));

        if (!hasStoreAccess) {
            throw new AccessDeniedException("Access denied for store ID: " + storeId);
        }

        Store store = storeRepository.findByStoreIdAndDeletedFalse(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        Order order = new Order();
        orderRepository.save(order);
        order.setStore(store);
        order.setUser(user);
        order.setOrder_date(LocalDate.now());
        for(OrderProductDTO op : dto.getOrderProducts()){
            String productId = op.getProductId();
            Product product = productRepository.findByProductIdAndDeletedFalse(productId)
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            op.setPrice(product.getPrice());
            op.setOrderId(order.getOrderId());
            orderProductRepository.save(orderProductMapper.toOrderProduct(op));
        }
        List<OrderProduct> orderProducts = dto.getOrderProducts().stream().map(orderProductMapper::toOrderProduct).collect(Collectors.toList());
        order.setOrderProducts(orderProducts);
        order.setTotal_price(calculateTotalPrice(dto.getOrderProducts()));
        orderRepository.save(order);
        OrderResponse orderResponse = orderMapper.toOrderResponse(order);
        orderResponse.setUserId(userId);
        orderResponse.setStoreId(storeId);
        orderResponse.setOrderProducts(dto.getOrderProducts());
        return orderResponse;

    }

    private Double calculateTotalPrice(List<OrderProductDTO> orderProductDTOS){
        return orderProductDTOS.stream().mapToDouble(
                op -> op.getPrice() * op.getQuantity()
        ).sum();

    }
}
