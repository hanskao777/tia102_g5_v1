-- //////////////////////////////////// DATABASE 設定 ///////////////////////////////////////////
-- ! 移除 DATABASE --------------------------------------------------------
DROP DATABASE IF EXISTS tia102_g5;
-- ! 移除 DATABASE --------------------------------------------------------

-- 建立 DATABASE tia102_g5 ------------------------------------------------
CREATE DATABASE tia102_g5;
USE tia102_g5; 
-- 建立 DATABASE tia102_g5 ------------------------------------------------

-- 設定自增主鍵 -----------------------------------------------------------
-- 初始值 = 1，取值範圍是 1~65535
SET AUTO_INCREMENT_OFFSET = 1;

-- 每次遞增的量 = 1
SET AUTO_INCREMENT_INCREMENT = 1;
-- 設定自增主鍵 -----------------------------------------------------------
-- //////////////////////////////////// DATABASE 設定 ///////////////////////////////////////////


-- //////////////////////////////////// 建立表格 ////////////////////////////////////////////////
-- 會員相關 ---------------------------------------------------------------
CREATE TABLE GeneralMember(
	memberID                   INT           AUTO_INCREMENT    COMMENT "會員ID",
    memberAccount              VARCHAR(255)  NOT NULL          COMMENT "帳號(E-mail)",
    memberPassword             VARCHAR(255)  NOT NULL          COMMENT "密碼",
    memberName                 VARCHAR(255)  NOT NULL          COMMENT "姓名",
    memberPhone                VARCHAR(255)  NOT NULL          COMMENT "電話",
    memberNickName             VARCHAR(255)  NOT NULL          COMMENT "暱稱",
    memberAddress              VARCHAR(255)                    COMMENT "地址",
    nationalID                 VARCHAR(255)  NOT NULL          COMMENT "身分證字號",
    gender                     VARCHAR(255)  NOT NULL          COMMENT "性別",
    birthday                   DATE                            COMMENT "生日",
    memberPicture              MEDIUMBLOB                      COMMENT "大頭照",
    memberStatus               INT           NOT NULL          COMMENT "帳號狀態 0:帳號已黑單 1:帳號正常",
    memberCreateTime           DATETIME       
		                                     DEFAULT CURRENT_TIMESTAMP
		                                                       COMMENT "帳號建立時間",
    
    CONSTRAINT pk_GeneralMember PRIMARY KEY (memberID)
) COMMENT "一般會員";

CREATE TABLE PartnerMember(
	partnerID                   INT          AUTO_INCREMENT    COMMENT "廠商ID",
    taxID                       VARCHAR(255) NOT NULL          COMMENT "統一編號",
    partnerName                 VARCHAR(255) NOT NULL          COMMENT "公司名稱",
    partnerHeading              VARCHAR(255) NOT NULL          COMMENT "抬頭",
    partnerAddress              VARCHAR(255) NOT NULL          COMMENT "地址",
    partnerPhone                VARCHAR(255) NOT NULL          COMMENT "連絡電話",
    partnerContactPerson        VARCHAR(255) NOT NULL          COMMENT "聯絡人",
    partnerPassword             VARCHAR(255) NOT NULL          COMMENT "密碼",
    partnerEmail                VARCHAR(255) NOT NULL          COMMENT "電子信箱",
    partnerCreateTime           DATETIME      
		                                     DEFAULT CURRENT_TIMESTAMP
							                                   COMMENT "帳號建立時間",
    partnerAccountStatus        INT          NOT NULL          COMMENT "帳號狀態 0:黑名單 1.使用中 2.申請中",
    
    CONSTRAINT pk_PartnerMember PRIMARY KEY (partnerID)
) COMMENT "廠商會員";

CREATE TABLE Administrator(
	administratorID             INT          AUTO_INCREMENT    COMMENT "管理員ID",
    administratorAccount        VARCHAR(255) NOT NULL          COMMENT "帳號",
    administratorPassword       VARCHAR(255) NOT NULL          COMMENT "密碼",
    administratorCreateTime     DATETIME 
								             DEFAULT CURRENT_TIMESTAMP
								                               COMMENT "帳號建立時間",
    administratorStatus         INT          NOT NULL          COMMENT "帳號狀態 0:帳號正常 1:帳號停用",
    
    CONSTRAINT pk_Administrator PRIMARY KEY (administratorID)
) COMMENT "管理員";

CREATE TABLE MemberCoupon(
	memberCouponID              INT          AUTO_INCREMENT    COMMENT "會員優惠券ID",
	memberID                    INT          NOT NULL          COMMENT "會員ID",
	couponTypeID                INT          NOT NULL          COMMENT "優惠券類型ID",
    memberCouponExpirationDate  DATE         NOT NULL          COMMENT "有效期限",
    memberCouponStatus          INT          NOT NULL          COMMENT "使用狀態 0:未使用 1:已使用",
    memberCouponCreateTime      DATETIME
		                                     DEFAULT CURRENT_TIMESTAMP
		                                                       COMMENT "建立時間",

    CONSTRAINT pk_MemberCoupon PRIMARY KEY (memberCouponID)
) COMMENT "會員優惠券";
-- 會員相關 ---------------------------------------------------------------

-- 場館相關 ---------------------------------------------------------------
CREATE TABLE Venue(
	venueID                     INT          AUTO_INCREMENT    COMMENT "場館ID",
	venueName                   VARCHAR(255) NOT NULL          COMMENT "場館名稱",
	venuePhone                  VARCHAR(255) NOT NULL          COMMENT "電話",
	venueContactPerson          VARCHAR(255) NOT NULL          COMMENT "聯絡人",
	venueAddress                VARCHAR(255) NOT NULL          COMMENT "地址",
	venueLocation               VARCHAR(255) NOT NULL          COMMENT "地區 如北區、中區、南區",

	CONSTRAINT pk_venueID PRIMARY KEY (venueID)
) COMMENT "場館";

CREATE TABLE VenueArea(
	venueAreaID                 INT          AUTO_INCREMENT    COMMENT "場館區域ID",
	venueID                     INT          NOT NULL          COMMENT "場館ID",
	venueAreaName               VARCHAR(255) NOT NULL          COMMENT "區域代號 如NA, NB, NC, MA, MB, MC, SA, SB, SC",
	
    CONSTRAINT pk_areaID PRIMARY KEY (venueAreaID)
) COMMENT "場館區域";

CREATE TABLE Seat(
	seatID                      INT          AUTO_INCREMENT     COMMENT "座位ID",
    venueID                     INT          NOT NULL           COMMENT "場館ID",
	venueAreaID                 INT          NOT NULL           COMMENT "場館區域ID",
	seatName                    VARCHAR(255) NOT NULL           COMMENT "座位代號 如NA001, NA002, NA003, NA004",
	seatRow                     INT          NOT NULL           COMMENT "行",
	seatNumber                  INT          NOT NULL           COMMENT "號",
    
	CONSTRAINT pk_seatID PRIMARY KEY (seatID)
) COMMENT "座位";

CREATE TABLE VenueRental(
	venueRentalID                INT          AUTO_INCREMENT     COMMENT "場地申請資料ID",
	venueID                      INT          NOT NULL           COMMENT "場館ID",
	partnerID                    INT          NOT NULL           COMMENT "廠商會員ID",
	activityName                 VARCHAR(255) NOT NULL           COMMENT "活動名稱",
	proposal                     LONGBLOB                        COMMENT "企劃書",
	venueRentalStatus            INT          NOT NULL           COMMENT "申請狀態 0:不通過 1:通過 3:審核中 4:取消中 5:已取消",
	venueRentalStartDate         DATE         NOT NULL           COMMENT "租用開始日期",
	venueRentalEndDate           DATE         NOT NULL           COMMENT "租用結束日期",
	venueRentalCreateTime        DATETIME
		                                      DEFAULT CURRENT_TIMESTAMP
		                                                         COMMENT "申請建立時間",
	venueRentalCode              VARCHAR(255) NOT NULL           COMMENT "場地申請編號",
    
	CONSTRAINT pk_venueRentalID PRIMARY KEY (venueRentalID)
) COMMENT "場地申請資料";

CREATE TABLE VenueTimeSlot(
	venueTimeSlotID              INT           AUTO_INCREMENT     COMMENT "場館時段ID",
	venueRentalID                INT                              COMMENT "場地申請資料ID",
	venueTimeSlotDate            DATE          NOT NULL           COMMENT "日期",
	venueTimeSlot                INT           NOT NULL           COMMENT "時段 1:早 2:午 3:晚",
	venueTimeSlotStatus          INT           NOT NULL           COMMENT "時段狀態 0: 未被租借 1: 已被租借 2: 不提供租借 3: 活動",
    
	CONSTRAINT pk_venueTimeSlotID PRIMARY KEY (venueTimeSlotID)
) COMMENT "場館時段";

CREATE TABLE SeatStatus(
	seatStatusID                 INT           AUTO_INCREMENT     COMMENT "座位狀態ID",
	activityTimeSlotID           INT           NOT NULL           COMMENT "活動時段ID",
	seatID                       INT           NOT NULL           COMMENT "座位ID",
	seatStatus                   INT           NOT NULL           COMMENT "座位狀態 0: 空位 1: 已經購買 2: 廠商保留位 3: 不可使用(目前沒有用到這個資料，考慮未來可能會加入這個狀態)",
    
	CONSTRAINT pk_seatStatusID PRIMARY KEY (seatStatusID)
) COMMENT "座位狀態";

CREATE TABLE ActivityAreaPrice(
	activityAreaPriceID          INT           AUTO_INCREMENT     COMMENT "活動區域價格ID",
	venueAreaID                  INT           NOT NULL           COMMENT "區域ID",
	activityID                   INT           NOT NULL           COMMENT "活動ID",
	activityAreaPrice            DECIMAL(7,2)  NOT NULL           COMMENT "活動區域價格",
    
	CONSTRAINT pk_activityAreaPriceID PRIMARY KEY (activityAreaPriceID)
) COMMENT "活動區域價格";
-- 場館相關 ---------------------------------------------------------------

-- 活動相關 ---------------------------------------------------------------
CREATE TABLE Activity(
	activityID                   INT           AUTO_INCREMENT     COMMENT "活動ID",
    partnerID                    INT           NOT NULL           COMMENT "廠商ID",
    venueID                      INT           NOT NULL           COMMENT "場館ID",
    venueRentalID                INT           NOT NULL           COMMENT "申請資料ID",
    activityName                 VARCHAR(255)  NOT NULL           COMMENT "名稱",
    activityContent              TEXT          NOT NULL           COMMENT "內容",
    activityCreateTime           DATETIME 
		                                       DEFAULT CURRENT_TIMESTAMP 
                                               ON UPDATE CURRENT_TIMESTAMP
								                                  COMMENT "建立時間",
    activityPostTime             DATE          NOT NULL           COMMENT "排程時間",
    activityTag                  VARCHAR(255)  NOT NULL           COMMENT "類型標籤",
    activityStatus               INT           NOT NULL           COMMENT "活動設定狀態 0:未設定 1:已設定",
    ticketSetStatus              INT           NOT NULL           COMMENT "票券設定狀態 0:未設定 1:已設定",
    sellTime                     DATE          NOT NULL           COMMENT "起售日",
    
    CONSTRAINT pk_Activity PRIMARY KEY (activityID)
) COMMENT "活動";

CREATE TABLE ActivityPicture(
	activityPictureID            INT           AUTO_INCREMENT     COMMENT "活動圖片",
    activityID                   INT           NOT NULL           COMMENT "活動ID",
    activityPicture              MEDIUMBLOB                       COMMENT "活動圖片",
    
    CONSTRAINT pk_ActivityPicture PRIMARY KEY (activityPictureID)
) COMMENT "活動圖片";

CREATE TABLE ActivityCollection(
	activityCollectionID         INT           AUTO_INCREMENT     COMMENT "活動收藏ID",
    memberID                     INT           NOT NULL           COMMENT "會員ID",
    activityID                   INT           NOT NULL           COMMENT "活動ID",
    activityCollectionTime       DATETIME
		                                       DEFAULT CURRENT_TIMESTAMP
											   ON UPDATE CURRENT_TIMESTAMP
		                                                          COMMENT "活動收藏時間",
        
	CONSTRAINT pk_ActivityCollection PRIMARY KEY (activityCollectionID)
) COMMENT "活動收藏";

CREATE TABLE ActivityTimeSlot(
	activityTimeSlotID           INT           AUTO_INCREMENT     COMMENT "時段ID",
    activityID                   INT           NOT NULL           COMMENT "活動ID",
    activityTimeSlotDate         DATE          NOT NULL           COMMENT "日期",
    activityTimeSlot             INT           NOT NULL           COMMENT "時段 1:早 2:午 3:晚",
    activityTimeSlotSeatAmount   INT           NOT NULL           COMMENT "時段剩餘座位數",
    
    CONSTRAINT pk_ActivityTimeSlot PRIMARY KEY (activityTimeSlotID)
) COMMENT "活動時段";
-- 活動相關 ---------------------------------------------------------------

-- 票券相關 ---------------------------------------------------------------
CREATE TABLE BookTicket(
	bookTicketID                 INT            AUTO_INCREMENT     COMMENT "票券訂單ID",
    memberID                     INT            NOT NULL           COMMENT "會員ID(買家)",
    activityID                   INT            NOT NULL           COMMENT "活動ID",
    activityTimeSlotID           INT            NOT NULL           COMMENT "時段ID",
    memberCouponID               INT            NOT NULL           COMMENT "會員優惠券ID",
    bookTime                     DATETIME 
                                                DEFAULT CURRENT_TIMESTAMP 
						                        ON UPDATE CURRENT_TIMESTAMP 
									                               COMMENT "訂購日期",
    ticketQuantity               INT            NOT NULL           COMMENT "數量",
    totalPrice                   DECIMAL(7,2)   NOT NULL           COMMENT "總金額",
    
    CONSTRAINT pk_BookTicket PRIMARY KEY (BookTicketID)
) COMMENT "票券訂單";

CREATE TABLE Ticket(
	ticketID                     INT            AUTO_INCREMENT     COMMENT "票券ID",
    memberID                     INT            NOT NULL           COMMENT "會員(擁有者)",
    seatStatusID                 INT            NOT NULL           COMMENT "座位狀態ID",
    activityAreaPriceID          INT            NOT NULL           COMMENT "活動區域價格ID",
    bookTicketID                 INT            NOT NULL           COMMENT "票券訂單ID",
    activityTimeSlotID           INT            NOT NULL           COMMENT "時段ID",
    
    CONSTRAINT pk_Ticket PRIMARY KEY (ticketID)
) COMMENT "票券";

CREATE TABLE CouponType(
	couponTypeID                 INT            AUTO_INCREMENT     COMMENT "優惠券類型ID",
    couponTypeName               VARCHAR(255)   NOT NULL           COMMENT "優惠券類型名稱",
    couponTypeRegulation         TEXT           NOT NULL           COMMENT "使用規則",
    couponTypeDiscount           DECIMAL(7,2)   NOT NULL           COMMENT "折扣數",
    
    CONSTRAINT pk_CouponType PRIMARY KEY (couponTypeID)
) COMMENT "優惠券";
-- 票券相關 ---------------------------------------------------------------

-- 社群相關 ---------------------------------------------------------------
CREATE TABLE Article (
	articleID	                 INT            AUTO_INCREMENT     COMMENT "文章ID",
    articleCategory              VARCHAR(100)   NOT NULL           COMMENT "文章類別",
	articleTitle	             VARCHAR(100)   NOT NULL           COMMENT "文章標題",
	memberID		             INT            NOT NULL           COMMENT "會員ID",
    articleContent               TEXT           NOT NULL           COMMENT "文章內容",
    boardID                      INT            NOT NULL           COMMENT "各板ID",
    articleStatus                INT            NOT NULL           COMMENT "文章狀態 0.不顯示 1.顯示",
	articleCreateTime            DATETIME 
					                            DEFAULT CURRENT_TIMESTAMP 
					                            ON UPDATE CURRENT_TIMESTAMP 
					                                               COMMENT "文章建立時間",
                          
	CONSTRAINT pk_Article PRIMARY KEY (articleID)
) COMMENT "文章";

CREATE TABLE Message (
	messageID	                 INT            AUTO_INCREMENT     COMMENT "留言ID",
	memberID	                 INT            NOT NULL           COMMENT "會員ID",
	articleID		             INT            NOT NULL           COMMENT "文章ID",
    messageContent               TEXT           NOT NULL           COMMENT "留言內容",
    messageStatus                INT            NOT NULL           COMMENT "留言狀態 0.不顯示 1.顯示",
    messageCreateTime            DATETIME 
					                            DEFAULT CURRENT_TIMESTAMP 
					                            ON UPDATE CURRENT_TIMESTAMP 
							                                       COMMENT "留言時間",
                          
	CONSTRAINT pk_Message PRIMARY KEY (messageID)
) COMMENT "文章留言";

CREATE TABLE ArticleCollection (
	articleCollectionID          INT            AUTO_INCREMENT     COMMENT "文章收藏ID",
	memberID	                 INT            NOT NULL           COMMENT "會員ID",
	articleID		             INT            NOT NULL           COMMENT "文章ID",
    collectionCreateTime         DATETIME 
					                            DEFAULT CURRENT_TIMESTAMP 
					                            ON UPDATE CURRENT_TIMESTAMP 
							                                       COMMENT "收藏時間",
                             
	CONSTRAINT pk_ArticleCollection PRIMARY KEY (articleCollectionID)
) COMMENT "文章收藏";

CREATE TABLE Board (
	boardID                      INT            AUTO_INCREMENT     COMMENT "各板ID",
	boardName	                 VARCHAR(100)   NOT NULL           COMMENT "各板名稱",
    
	CONSTRAINT pk_Board PRIMARY KEY (boardID)    
) COMMENT "文章各板";

CREATE TABLE Heart (
	heartID                      INT            AUTO_INCREMENT     COMMENT "點讚ID",
	memberID	                 INT            NOT NULL           COMMENT "會員ID",    
    articleID		             INT            NOT NULL           COMMENT "文章ID",
    heartCreateTime              DATETIME 
					                            DEFAULT CURRENT_TIMESTAMP 
					                                               COMMENT "點讚時間",
                          
	CONSTRAINT pk_Heart PRIMARY KEY (heartID)
) COMMENT "文章點讚";

CREATE TABLE ArticleImg (
	articleImgID                 INT            AUTO_INCREMENT     COMMENT "文章圖片ID",
	articleID	         		 INT            NOT NULL           COMMENT "文章ID",    
    articlePic		     		 MEDIUMBLOB                        COMMENT "圖片",
    articleImgCreateTime  		 DATETIME 
												DEFAULT CURRENT_TIMESTAMP 
												ON UPDATE CURRENT_TIMESTAMP 
												                   COMMENT "圖片建立時間",
                          
	CONSTRAINT pk_ArticleImg PRIMARY KEY (articleImgID)
) COMMENT "文章圖片";

CREATE TABLE Prosecute (
	prosecuteID           		 INT            AUTO_INCREMENT      COMMENT "檢舉ID",
	memberID	        		 INT            NOT NULL            COMMENT "檢舉人ID",    
    articleID		      		 INT                                COMMENT "被檢舉文章ID",
    prosecuteReason       		 VARCHAR(255)   NOT NULL            COMMENT "被檢舉原因",
    messageID	          		 INT                                COMMENT "被檢舉留言ID", 
    prosecuteCreateTime   		 DATE           NOT NULL            COMMENT "檢舉日期", 
    prosecuteStatus       		 INT            NOT NULL            COMMENT "檢舉狀態 0: 正常 1: 不顯示",
    
	CONSTRAINT pk_Prosecute PRIMARY KEY (prosecuteID)
) COMMENT "檢舉";
-- 社群相關 ---------------------------------------------------------------

-- 商城相關 ---------------------------------------------------------------
CREATE TABLE Commodity(
    commodityID                  INT            AUTO_INCREMENT      COMMENT "商品ID",
    commodityName         		 VARCHAR(255)   NOT NULL            COMMENT "商品名稱",
    commodityPrice        		 DECIMAL(7,2)   NOT NULL            COMMENT "商品價格",
    commodityStock        		 INT            NOT NULL            COMMENT "商品庫存",
    commodityContent      		 TEXT           NOT NULL            COMMENT "商品內容",
    activityID            		 INT            NOT NULL            COMMENT "活動ID",
    partnerID             		 INT            NOT NULL            COMMENT "廠商ID",
    commodityStatus       		 INT            NOT NULL            COMMENT "商品狀態 0:下架 1:在售",
    commodityPostTime     		 DATE           NOT NULL            COMMENT "上架時間",
    commodityRemoveTime   		 DATE                               COMMENT "下架時間",
    commodityUpdateTime   		 DATETIME              
                                                DEFAULT CURRENT_TIMESTAMP 
					                            ON UPDATE CURRENT_TIMESTAMP 
																    COMMENT "更新時間",
    commodityCreateTime          DATETIME
                                                DEFAULT CURRENT_TIMESTAMP
                                                                    COMMENT "建立時間",
                                     
    CONSTRAINT pk_Commodity PRIMARY KEY (commodityID)
) COMMENT "商品";

CREATE TABLE CommodityPicture(
	commodityPictureID           INT           AUTO_INCREMENT       COMMENT "商品圖片",
    commodityID                  INT           NOT NULL             COMMENT "商品ID",
    commodityPicture             INT                                COMMENT "商品圖片",
    
    CONSTRAINT pk_CommodityPicture PRIMARY KEY (commodityPictureID)
) COMMENT "商品圖片";

CREATE TABLE Orders(
    orderID               		 INT            AUTO_INCREMENT      COMMENT "訂單ID",
    memberID              		 INT            NOT NULL            COMMENT "會員ID",
    recipient           		 VARCHAR(255)   NOT NULL            COMMENT "收件人姓名",
    recipientPhone      		 VARCHAR(255)   NOT NULL            COMMENT "收件人電話",
    recipientEmail      		 VARCHAR(255)   NOT NULL            COMMENT "收件人E-mail",
    recipientAddress     		 VARCHAR(255)   NOT NULL            COMMENT "收件地址",
    memberCouponID               INT                                COMMENT "會員優惠券ID",
    actualAmount        	     DECIMAL(7,2)   NOT NULL            COMMENT "實付金額",
    orderStatus          	     INT            NOT NULL            COMMENT "訂單狀態 0:取消 1:未出貨 2:已出貨 3:完成訂單 4:退貨中 5:完成退貨",
    payStatus           		 INT            NOT NULL            COMMENT "支付狀態",
    payTime         		     DATETIME
											    DEFAULT CURRENT_TIMESTAMP 
					                                                COMMENT "付款時間",
    shipTime             		 DATETIME                           COMMENT "出貨時間",
                                  
    CONSTRAINT pk_Orders PRIMARY KEY (orderID)
) COMMENT "訂單";

CREATE TABLE OrderItem(
    orderItemID           		 INT            AUTO_INCREMENT      COMMENT "訂單明細ID",
    orderID               		 INT            NOT NULL            COMMENT "訂單ID",
    commodityID          		 INT            NOT NULL            COMMENT "商品ID",
    commodityOrderPrice          DECIMAL(7,2)   NOT NULL            COMMENT "商品下訂價格",
    orderItemQuantity            INT            NOT NULL            COMMENT "商品數量",
    orderItemTotalPrice          DECIMAL(7,2)   NOT NULL            COMMENT "單一商品總價",
    orderItemCreateTime   		 DATETIME
                                                DEFAULT CURRENT_TIMESTAMP
                                                                    COMMENT "建立時間",
                            
    CONSTRAINT pk_OrderItem PRIMARY KEY (orderItemID)
) COMMENT "訂單明細";


CREATE TABLE Cart(
    cartID                		 INT            AUTO_INCREMENT      COMMENT "購物車ID",
    memberID              		 INT            NOT NULL            COMMENT "會員ID",
    cartTotalPrice               DECIMAL(7,2)   NOT NULL            COMMENT "購物車總價",
    cartCreateTime        		 DATETIME
											    DEFAULT CURRENT_TIMESTAMP
                                                                    COMMENT "建立時間",
                        
    CONSTRAINT pk_Cart PRIMARY KEY (cartID)
) COMMENT "購物車";

CREATE TABLE CartItem(
	cartItemID					INT				AUTO_INCREMENT		COMMENT "購物車明細ID",
    cartID						INT				NOT NULL			COMMENT "購物車ID",
    commodityID					INT				NOT NULL			COMMENT "商品ID",
    checkedQuantity			    INT				NOT NULL			COMMENT "購買商品數量",
    
    CONSTRAINT pk_CartItem PRIMARY KEY (cartItemID)
) COMMENT "購物車明細";

-- 商城相關 ---------------------------------------------------------------

-- 最新消息相關 -----------------------------------------------------------
CREATE TABLE News(
    newsID                		 INT            AUTO_INCREMENT      COMMENT "消息ID",
    administratorID      		 INT            NOT NULL            COMMENT "管理員ID",
    newsTitle             		 VARCHAR(255)   NOT NULL            COMMENT "標題",
    newsContent         		 TEXT           NOT NULL            COMMENT "內容",
    newsStatus          		 INT            NOT NULL            COMMENT "狀態 0:隱藏 1:正常顯示 2:置頂",
    newsCreateTime      		 DATETIME
		                                        DEFAULT CURRENT_TIMESTAMP 
					                            ON UPDATE CURRENT_TIMESTAMP 
					                                                COMMENT "發布時間",
                                     
    CONSTRAINT pk_News PRIMARY KEY (newsID)
) COMMENT "最新消息";

CREATE TABLE Announcement(
    announcementID         		 INT            AUTO_INCREMENT      COMMENT "公告ID",
    administratorID       		 INT            NOT NULL            COMMENT "管理員ID",
    announcementTitle     		 VARCHAR(255)   NOT NULL            COMMENT "標題",
    announcementContent    		 TEXT           NOT NULL            COMMENT "內容",
    announcementStatus     		 INT            NOT NULL            COMMENT "狀態 0:隱藏 1:正常顯示 2:置頂",
    announcementCreateTime 		 DATETIME
												DEFAULT CURRENT_TIMESTAMP 
					                            ON UPDATE CURRENT_TIMESTAMP 
					                                                COMMENT "發布時間",
                                     
    CONSTRAINT pk_News PRIMARY KEY (announcementID)
) COMMENT "公告";
-- 最新消息相關 -----------------------------------------------------------
-- //////////////////////////////////// 建立表格 ////////////////////////////////////////////////


-- //////////////////////////////////// 建立 FK /////////////////////////////////////////////////
-- 會員相關 ---------------------------------------------------------------
ALTER TABLE MemberCoupon
	ADD CONSTRAINT fk_MemberCoupon_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_MemberCoupon__CouponType_couponTypeID
	FOREIGN KEY (couponTypeID) REFERENCES  CouponType (couponTypeID);
-- 會員相關 ---------------------------------------------------------------

-- 場館相關 ---------------------------------------------------------------
ALTER TABLE VenueArea
	ADD CONSTRAINT fk_VenueArea_Venue_vueueID
	FOREIGN KEY (venueID) REFERENCES Venue (venueID);

ALTER TABLE Seat
	ADD CONSTRAINT fk_Seat_VenueArea_venueAreaID
	FOREIGN KEY (venueAreaID) REFERENCES VenueArea (venueAreaID),
    ADD CONSTRAINT fk_Seat_Venue_venueID
    FOREIGN KEY (venueID) REFERENCES Venue (venueID);

ALTER TABLE VenueRental
	ADD CONSTRAINT fk_VenueRental_Venue_venueID
	FOREIGN KEY (venueID) REFERENCES Venue (venueID),
	ADD CONSTRAINT fk_VenueRental_PartnerMember_partnerID
	FOREIGN KEY (partnerID) REFERENCES PartnerMember (partnerID);

ALTER TABLE VenueTimeSlot
	ADD CONSTRAINT fk_VenueTimeSlot_VenueRental_venueRentalID
	FOREIGN KEY (venueRentalID) REFERENCES VenueRental (venueRentalID);

ALTER TABLE SeatStatus
	ADD CONSTRAINT fk_SeatStatus_ActivityTimeSlot_activityTimeSlotID
	FOREIGN KEY (activityTimeSlotID) REFERENCES ActivityTimeSlot (activityTimeSlotID),
	ADD CONSTRAINT fk_SeatStatus_Seat_seatID
	FOREIGN KEY (seatID) REFERENCES Seat (seatID);

ALTER TABLE ActivityAreaPrice
	ADD CONSTRAINT fk_ActivityAreaPrice_VenueArea_venueAreaID
	FOREIGN KEY (venueAreaID) REFERENCES VenueArea (venueAreaID),
	ADD CONSTRAINT fk_ActivityAreaPrice_Activity_activityID
	FOREIGN KEY (activityID) REFERENCES Activity (activityID);
-- 場館相關 ---------------------------------------------------------------

-- 活動相關 ---------------------------------------------------------------
ALTER TABLE Activity
	ADD CONSTRAINT fk_Activity_PartnerMember_partnerID
	FOREIGN KEY (partnerID) REFERENCES PartnerMember (partnerID),
	ADD CONSTRAINT fk_Activity_Venue_venueID
	FOREIGN KEY (venueID) REFERENCES Venue (venueID),
	ADD CONSTRAINT fk_Activity_VenueRental_venueRentalID
	FOREIGN KEY (venueRentalID) REFERENCES VenueRental (venueRentalID);
    
ALTER TABLE ActivityPicture
	ADD CONSTRAINT fk_ActivityPicture_Activity_activityID
    FOREIGN KEY (activityID) REFERENCES Activity (activityID);

ALTER TABLE ActivityCollection
	ADD CONSTRAINT fk_ActivityCollection_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_ActivityCollection_Activity_activityID
	FOREIGN KEY (activityID) REFERENCES Activity (activityID);

ALTER TABLE ActivityTimeSlot
	ADD CONSTRAINT fk_ActivityTimeSlot_Activity_activityID
	FOREIGN KEY (activityID) REFERENCES Activity (activityID);
-- 活動相關 ---------------------------------------------------------------

-- 票券相關 ---------------------------------------------------------------
ALTER TABLE BookTicket
	ADD CONSTRAINT fk_BookTicket_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_BookTicket_Activity_activityID
	FOREIGN KEY (activityID) REFERENCES Activity (activityID),
	ADD CONSTRAINT fk_BookTicket_ActivityTimeSlot_activityTimeSlotID
	FOREIGN KEY (activityTimeSlotID) REFERENCES ActivityTimeSlot (activityTimeSlotID),
    ADD CONSTRAINT fk_BookTicket_MemberCoupon_memberCouponID
    FOREIGN KEY (memberCouponID) REFERENCES MemberCoupon (memberCouponID);

ALTER TABLE Ticket
	ADD CONSTRAINT fk_Ticket_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_Ticket_SeatStatus_seatStatusID
	FOREIGN KEY (seatStatusID) REFERENCES SeatStatus (seatStatusID),
	ADD CONSTRAINT fk_Ticket_ActivityAreaPrice_activityAreaPriceID
	FOREIGN KEY (activityAreaPriceID) REFERENCES ActivityAreaPrice (activityAreaPriceID),
	ADD CONSTRAINT fk_Ticket_BookTicket_bookTicketID
	FOREIGN KEY (bookTicketID) REFERENCES BookTicket (bookTicketID),
	ADD CONSTRAINT fk_Ticket_ActivityTimeSlot_activityTimeSlotID
	FOREIGN KEY (activityTimeSlotID) REFERENCES ActivityTimeSlot (activityTimeSlotID);
-- 票券相關 ---------------------------------------------------------------

-- 社群相關 ---------------------------------------------------------------
ALTER TABLE Article 
	ADD CONSTRAINT fk_Article_GeneralMember_memberID 
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT  fk_Article_Board_boardID
	FOREIGN KEY (boardID) REFERENCES Board (boardID);

ALTER TABLE Message 
	ADD CONSTRAINT fk_Message_GeneralMember_memberID 
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID), 
	ADD CONSTRAINT fk_Message_Article_articleID
	FOREIGN KEY (articleID) REFERENCES Article (articleID);

ALTER TABLE ArticleCollection
	ADD CONSTRAINT fk_ArticleCollection_GeneralMember_memberID 
	FOREIGN KEY (memberID)  REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_ArticleCollection_Article_articleID
	FOREIGN KEY (articleID) REFERENCES Article (articleID);

ALTER TABLE Heart
	ADD CONSTRAINT fk_Heart_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_Heart_Article_articleID
	FOREIGN KEY (articleID) REFERENCES Article (articleID);

ALTER TABLE ArticleImg
	ADD CONSTRAINT fk_ArticleImg_Article_articleID
	FOREIGN KEY (articleID) REFERENCES Article (articleID)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Prosecute
	ADD CONSTRAINT fk_Prosecute_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_Prosecute_Article_articleID
	FOREIGN KEY (articleID) REFERENCES Article (articleID),
	ADD CONSTRAINT fk_Prosecute_Message_messageID
	FOREIGN KEY (messageID) REFERENCES Message (messageID);
-- 社群相關 ---------------------------------------------------------------

-- 商城相關 ---------------------------------------------------------------
ALTER TABLE Commodity
	ADD CONSTRAINT fk_Commodity_Activity_activityID
	FOREIGN KEY (activityID) REFERENCES Activity (activityID),
	ADD CONSTRAINT fk_Commodity_PartnerMember_partnerID
	FOREIGN KEY (partnerID) REFERENCES PartnerMember (partnerID);
    
ALTER TABLE CommodityPicture
	ADD CONSTRAINT fk_CommodityPicture_Commodity_activityID
	FOREIGN KEY (commodityID) REFERENCES Commodity (commodityID);

ALTER TABLE Orders
	ADD CONSTRAINT fk_Orders_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember (memberID),
	ADD CONSTRAINT fk_Orders_MemberCoupon_memberCouponID
	FOREIGN KEY (memberCouponID) REFERENCES MemberCoupon (memberCouponID);

ALTER TABLE OrderItem
	ADD CONSTRAINT fk_OrderItem_Orders_orderID
	FOREIGN KEY (orderID) REFERENCES Orders (orderID),
	ADD CONSTRAINT fk_OrderItem_Commodity_commodityID
	FOREIGN KEY (commodityID) REFERENCES Commodity (commodityID);

ALTER TABLE Cart
	ADD CONSTRAINT fk_Cart_GeneralMember_memberID
	FOREIGN KEY (memberID) REFERENCES GeneralMember(memberID);
    
ALTER TABLE CartItem
	ADD CONSTRAINT fk_CartItem_Cart_cartID
    FOREIGN KEY (cartID) REFERENCES Cart (cartID),
    ADD CONSTRAINT fk_CartItem_Commodity_commodityID
    FOREIGN KEY (commodityID) REFERENCES Commodity (commodityID);
-- 商城相關 ---------------------------------------------------------------

-- 最新消息相關 -----------------------------------------------------------
ALTER TABLE News
	ADD CONSTRAINT fk_News_Administrator_administratorID
	FOREIGN KEY (administratorID) REFERENCES Administrator(administratorID);

ALTER TABLE Announcement
	ADD CONSTRAINT fk_Announcement_Administrator_administratorID
	FOREIGN KEY (administratorID) REFERENCES Administrator(administratorID);
-- 最新消息相關 -----------------------------------------------------------
-- //////////////////////////////////// 建立 FK /////////////////////////////////////////////////