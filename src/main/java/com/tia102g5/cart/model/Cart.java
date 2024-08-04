package com.tia102g5.cart.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.cartitem.model.CartItem;

@Entity
@Table(name = "cart")
public class Cart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartID", updatable = false)
	private Integer cartID; // 購物車ID

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 會員ID

	@NotNull(message = "購物車總價不能為空")
	@Column(name = "cartTotalPrice")
	private BigDecimal cartTotalPrice; // 購物車總價

	@Column(name = "cartCreateTime")
	private Timestamp cartCreateTime; // 建立時間

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@OrderBy("cartItemID asc")
	private Set<CartItem> cartItems;

	// 構造函數、getter 和 setter 方法
	public Cart() {
		super();
	}

	public Cart(Integer cartID, GeneralMember generalMember, BigDecimal cartTotalPrice, Timestamp cartCreateTime,
			Set<CartItem> cartItems) {
		super();
		this.cartID = cartID;
		this.generalMember = generalMember;
		this.cartTotalPrice = cartTotalPrice;
		this.cartCreateTime = cartCreateTime;
		this.cartItems = cartItems;
	}

	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public BigDecimal getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(BigDecimal cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public Timestamp getCartCreateTime() {
		return cartCreateTime;
	}

	public void setCartCreateTime(Timestamp cartCreateTime) {
		this.cartCreateTime = cartCreateTime;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@PrePersist
	@PreUpdate
	protected void onUpdate() {
		cartCreateTime = new Timestamp(System.currentTimeMillis());
	}
}
