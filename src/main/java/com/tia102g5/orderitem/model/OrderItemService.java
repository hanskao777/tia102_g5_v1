package com.tia102g5.orderitem.model;

import com.tia102g5.orderitem.model.OrderItem;
import com.tia102g5.orderitem.model.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Integer orderItemID) {
        return orderItemRepository.findById(orderItemID);
    }

    public List<OrderItem> getOrderItemsByOrderID(Integer orderID) {
        return orderItemRepository.findByOrders_OrderID(orderID);
    }

    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public void deleteOrderItem(Integer orderItemID) {
        orderItemRepository.deleteByOrderItemID(orderItemID);
    }
}