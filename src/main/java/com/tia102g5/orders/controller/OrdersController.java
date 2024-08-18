package com.tia102g5.orders.controller;

import com.tia102g5.orders.model.Orders;
import com.tia102g5.orders.model.OrdersService;
import com.tia102g5.cart.model.Cart;
import com.tia102g5.orderitem.model.OrderItem;
import com.tia102g5.orderitem.model.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@Validated
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderItemService orderItemService;
    
    
    @GetMapping("/complete")
    public String showOrderComplete(@RequestParam(required = false) Integer orderID, Model model) {
    	
    	if(orderID == null) {
    		return "redirect:error";
    	}
        Orders order = ordersService.getOrderById(orderID);
        if (order == null) {
            return "redirect:/error";
        }
        model.addAttribute("order", order);
        return "front-end/mall/mallComplete";
    }
    
//    @GetMapping("/view")
//	public String viewOrder(@RequestParam Integer orderID, Model model) {
//        Orders order = ordersService.getOrderById(orderID);
//		model.addAttribute("order", order);
//		return "/front-end/mall/mallCart";
//	}
    
    @GetMapping("/test")
	public String viewCart(Model model) {
    	
      return "front-end/mall/mallComplete";
	}
    
    
    
    
    
    
    
    
    
    
    
    
}
    
    
    
    
    
    
    
    
    
    
    

//    // Orders related endpoints
//    @GetMapping
//    public ResponseEntity<List<Orders>> getAllOrders() {
//        return ResponseEntity.ok(ordersService.getAllOrders());
//    }
//
//    @GetMapping("/{orderID}")
//    public ResponseEntity<Orders> getOrderById(@PathVariable Integer orderID) {
//        return ordersService.getOrderById(orderID)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/member/{memberID}")
//    public ResponseEntity<List<Orders>> getOrdersByMemberID(@PathVariable Integer memberID) {
//        return ResponseEntity.ok(ordersService.getOrdersByMemberID(memberID));
//    }
//
//    @PostMapping
//    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
//        return ResponseEntity.ok(ordersService.createOrder(order));
//    }
//
//    @PutMapping("/{orderID}")
//    public ResponseEntity<Orders> updateOrder(@PathVariable Integer orderID, @RequestBody Orders order) {
//        order.setOrderID(orderID);
//        return ResponseEntity.ok(ordersService.updateOrder(order));
//    }
//
//    @DeleteMapping("/{orderID}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderID) {
//        ordersService.deleteOrder(orderID);
//        return ResponseEntity.ok().build();
//    }
//
//    // OrderItem related endpoints
//    @GetMapping("/{orderID}/items")
//    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderID(@PathVariable Integer orderID) {
//        return ResponseEntity.ok(orderItemService.getOrderItemsByOrderID(orderID));
//    }
//
//    @PostMapping("/{orderID}/items")
//    public ResponseEntity<OrderItem> addOrderItem(@PathVariable Integer orderID, @RequestBody OrderItem orderItem) {
//        Orders order = ordersService.getOrderById(orderID)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        orderItem.setOrders(order);
//        return ResponseEntity.ok(orderItemService.createOrderItem(orderItem));
//    }
//
//    @PutMapping("/items/{orderItemID}")
//    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Integer orderItemID, @RequestBody OrderItem orderItem) {
//        orderItem.setOrderItemID(orderItemID);
//        return ResponseEntity.ok(orderItemService.updateOrderItem(orderItem));
//    }
//
//    @DeleteMapping("/items/{orderItemID}")
//    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer orderItemID) {
//        orderItemService.deleteOrderItem(orderItemID);
//        return ResponseEntity.ok().build();
//    }
