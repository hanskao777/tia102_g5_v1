package com.tia102g5.cartitem.model;

import com.tia102g5.cartitem.model.CartItem;
import com.tia102g5.cartitem.model.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartItemsByCartID(Integer cartID) {
        return cartItemRepository.findByCartID(cartID);
    }

    @Transactional
    public void updateCartItemQuantity(Integer cartItemID, Integer newQuantity) {
        CartItem item = cartItemRepository.findById(cartItemID)
            .orElseThrow(() -> new RuntimeException("CartItem not found"));
        item.setCheckedQuantity(newQuantity);
        cartItemRepository.save(item);
    }

    @Transactional
    public void deleteCartItem(Integer cartItemID) {
        cartItemRepository.deleteByCartItemID(cartItemID);
    }
}