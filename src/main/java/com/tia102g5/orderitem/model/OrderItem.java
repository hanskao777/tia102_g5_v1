package com.tia102g5.orderitem.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.tia102g5.commodity.model.Commodity;
import com.tia102g5.orders.model.Orders;

@Entity
@Table(name = "orderItem")
public class OrderItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderItemID", updatable = false)
	private Integer orderItemID; // 訂單明細ID

	@ManyToOne
	@JoinColumn(name = "orderID", referencedColumnName = "orderID")
	private Orders orders; // 訂單ID

	@ManyToOne
	@JoinColumn(name = "commodityID", referencedColumnName = "commodityID")
	private Commodity commodity; // 商品ID

	@NotNull(message = "商品下訂價格不能為空")
	@Column(name = "commodityOrderPrice")
	private BigDecimal commodityOrderPrice; // 商品下訂價格

	@NotNull(message = "商品數量不能為空")
	@Column(name = "orderItemQuantity")
	private Integer orderItemQuantity; // 數量

	@NotNull(message = "單一商品總價不能為空")
	@Column(name = "orderItemTotalPrice")
	private BigDecimal orderItemTotalPrice; // 單一商品架總價

	@Column(name = "orderItemCreateTime", updatable = false, insertable = false)
	private Timestamp orderItemCreateTime; // 建立時間

	// 構造函數、getter 和 setter 方法
	public OrderItem() {
		super();
	}

	public OrderItem(Integer orderItemID, Orders orders, Commodity commodity, BigDecimal commodityOrderPrice,
			Integer orderItemQuantity, BigDecimal orderItemTotalPrice, Timestamp orderItemCreateTime) {
		super();
		this.orderItemID = orderItemID;
		this.orders = orders;
		this.commodity = commodity;
		this.commodityOrderPrice = commodityOrderPrice;
		this.orderItemQuantity = orderItemQuantity;
		this.orderItemTotalPrice = orderItemTotalPrice;
		this.orderItemCreateTime = orderItemCreateTime;
	}

	public Integer getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(Integer orderItemID) {
		this.orderItemID = orderItemID;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public BigDecimal getCommodityOrderPrice() {
		return commodityOrderPrice;
	}

	public void setCommodityOrderPrice(BigDecimal commodityOrderPrice) {
		this.commodityOrderPrice = commodityOrderPrice;
	}

	public Integer getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(Integer orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public BigDecimal getOrderItemTotalPrice() {
		return orderItemTotalPrice;
	}

	public void setOrderItemTotalPrice(BigDecimal orderItemTotalPrice) {
		this.orderItemTotalPrice = orderItemTotalPrice;
	}

	public Timestamp getOrderItemCreateTime() {
		return orderItemCreateTime;
	}

	public void setOrderItemCreateTime(Timestamp orderItemCreateTime) {
		this.orderItemCreateTime = orderItemCreateTime;
	}

	@PrePersist
	protected void onCreate() {
		orderItemCreateTime = new Timestamp(System.currentTimeMillis());
	}
}

