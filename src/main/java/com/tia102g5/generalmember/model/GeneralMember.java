package com.tia102g5.generalmember.model;



import java.sql.Date;
import java.util.Arrays;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.tia102g5.activitycollection.model.ActivityCollection;
import com.tia102g5.article.model.Article;
import com.tia102g5.articleCollection.model.ArticleCollection;
import com.tia102g5.bookticket.model.BookTicket;
import com.tia102g5.cart.model.Cart;
import com.tia102g5.heart.model.Heart;
import com.tia102g5.membercoupon.model.MemberCoupon;
import com.tia102g5.message.model.Message;
import com.tia102g5.orders.model.Orders;
import com.tia102g5.prosecute.model.Prosecute;
import com.tia102g5.ticket.model.Ticket;


@Entity
@Table(name = "generalmember")
public class GeneralMember implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberID", updatable = false) // 會員ID
	private Integer memberID;

	@NotEmpty(message="帳號: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "格式須符合有@")
	@Column(name = "memberAccount") // "帳號(E-mail)"
	private String memberAccount;

	@NotEmpty(message="密碼: 請勿空白")
	@Column(name = "memberPassword") // "密碼"
	private String memberPassword;

	
	@NotEmpty(message="姓名: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "姓名: 只能是中、英文字母")
	@Column(name = "memberName") // "姓名"
	private String memberName;

	
	@NotEmpty(message="電話: 請勿空白")
	@Column(name = "memberPhone") // "電話"
	private String memberPhone;

	
	@NotEmpty(message="暱稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "暱稱: 只能是中、英文字母、數字和")
	@Column(name = "memberNickName") // "暱稱"
	private String memberNickName;

	
	@NotEmpty(message="地址: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$", message = "地址: 只能是中、英文字母、數字")
	@Column(name = "memberAddress") // "地址"
	private String memberAddress;

	
	@NotEmpty(message="身分證字號: 請勿空白")
	@Column(name = "nationalID") // "身分證字號"
	private String nationalID;

	@NotEmpty(message="性別: 請勿空白")
	@Column(name = "gender") // "性別"
	private String gender;
	
//	@NotEmpty(message="生日: 請勿空白")
//	@Temporal(TemporalType.DATE)
	@Column(name = "birthday") // "生日"
	private Date birthday;
	
	
	@Column(name = "memberPicture", columnDefinition = "mediumblob") // "大頭照"
	private byte[] memberPicture;
	

	@Column(name = "memberStatus") // "帳號狀態 0:帳號已黑單 1:帳號正常"
	private Integer memberStatus = 1;
	
//	@Temporal(TemporalType.DATE)
	@Column(name = "memberCreateTime", updatable = false, insertable = false) // "帳號建立時間"
	private Date memberCreateTime;
	
	
	
	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
	@OrderBy("memberCouponID asc")
	private Set<MemberCoupon> memberCoupons;
	
	
	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
	@OrderBy("cartID asc")
	private Set<Cart> carts;
	
	
//	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
//	@OrderBy("articleCollectionID asc")
//	private Set<ArticleCollection> articleCollections;
//	
//	
//	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
//	@OrderBy("articleID asc")
//	private Set<Article> articles;
//	
//	
//	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
//	@OrderBy("heartID asc")
//	private Set<Heart> hearts;
//	
//	
//	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
//	@OrderBy("messageID asc")
//	private Set<Message> messages;
	
	
	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
	@OrderBy("activityCollectionID asc")
	private Set<ActivityCollection> activityCollections;
	
	
	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
	@OrderBy("orderID asc")
	private Set<Orders> orders;
	
	
	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
	@OrderBy("ticketID asc")
	private Set<Ticket> tickets;
	
	
	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
	@OrderBy("bookTicketID asc")
	private Set<BookTicket> bookTickets;
	
	
//	@OneToMany(mappedBy = "generalMember", cascade = CascadeType.ALL)
//	@OrderBy("prosecuteID asc")
//	private Set<Prosecute> prosecutes;


	public GeneralMember() {
		super();
	}


	public Integer getMemberID() {
		return memberID;
	}


	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}


	public String getMemberAccount() {
		return memberAccount;
	}


	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}


	public String getMemberPassword() {
		return memberPassword;
	}


	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}


	public String getMemberNickName() {
		return memberNickName;
	}


	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}


	public String getMemberAddress() {
		return memberAddress;
	}


	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}


	public String getNationalID() {
		return nationalID;
	}


	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public byte[] getMemberPicture() {
		return memberPicture;
	}


	public void setMemberPicture(byte[] memberPicture) {
		this.memberPicture = memberPicture;
	}


	public Integer getMemberStatus() {
		return memberStatus;
	}


	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}


	public Date getMemberCreateTime() {
		return memberCreateTime;
	}


	public void setMemberCreateTime(Date memberCreateTime) {
		this.memberCreateTime = memberCreateTime;
	}


	public Set<MemberCoupon> getMemberCoupons() {
		return memberCoupons;
	}


	public void setMemberCoupons(Set<MemberCoupon> memberCoupons) {
		this.memberCoupons = memberCoupons;
	}


	public Set<Cart> getCarts() {
		return carts;
	}


	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}


//	public Set<ArticleCollection> getArticleCollections() {
//		return articleCollections;
//	}
//
//
//	public void setArticleCollections(Set<ArticleCollection> articleCollections) {
//		this.articleCollections = articleCollections;
//	}
//
//
//	public Set<Article> getArticles() {
//		return articles;
//	}
//
//
//	public void setArticles(Set<Article> articles) {
//		this.articles = articles;
//	}
//
//
//	public Set<Heart> getHearts() {
//		return hearts;
//	}
//
//
//	public void setHearts(Set<Heart> hearts) {
//		this.hearts = hearts;
//	}
//
//
//	public Set<Message> getMessages() {
//		return messages;
//	}
//
//
//	public void setMessages(Set<Message> messages) {
//		this.messages = messages;
//	}


	public Set<ActivityCollection> getActivityCollections() {
		return activityCollections;
	}


	public void setActivityCollections(Set<ActivityCollection> activityCollections) {
		this.activityCollections = activityCollections;
	}


	public Set<Orders> getOrders() {
		return orders;
	}


	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}


	public Set<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}


	public Set<BookTicket> getBookTickets() {
		return bookTickets;
	}


	public void setBookTickets(Set<BookTicket> bookTickets) {
		this.bookTickets = bookTickets;
	}


//	public Set<Prosecute> getProsecutes() {
//		return prosecutes;
//	}
//
//
//	public void setProsecutes(Set<Prosecute> prosecutes) {
//		this.prosecutes = prosecutes;
//	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


//	@Override
//	public String toString() {
//		return "GeneralMember [memberID=" + memberID + ", memberAccount=" + memberAccount + ", memberPassword="
//				+ memberPassword + ", memberName=" + memberName + ", memberPhone=" + memberPhone + ", memberNickName="
//				+ memberNickName + ", memberAddress=" + memberAddress + ", nationalID=" + nationalID + ", gender="
//				+ gender + ", birthday=" + birthday + ", memberPicture=" + Arrays.toString(memberPicture)
//				+ ", memberStatus=" + memberStatus + ", memberCreateTime=" + memberCreateTime + ", memberCoupons="
//				+ memberCoupons + ", carts=" + carts + ", articleCollections=" + articleCollections + ", articles="
//				+ articles + ", hearts=" + hearts + ", messages=" + messages + ", activityCollections="
//				+ activityCollections + ", orders=" + orders + ", tickets=" + tickets + ", bookTickets=" + bookTickets
//				+ ", prosecutes=" + prosecutes + "]";
//	}
	
	
	
}
