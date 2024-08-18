package com.tia102g5.message.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tia102g5.message.model.Message;


public class MessageRowMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int i) throws SQLException {
		
		Message message = new Message();
		
		message.setMessageID(rs.getInt("messageID"));
		message.setMemberIDRM(rs.getInt("memberID"));
		message.setArticleIDRM(rs.getInt("articleID"));
		message.setMemberNameRM(rs.getString("memberName"));
		message.setMessageContent(rs.getString("messageContent"));
		message.setMessageCreateTimeRM(rs.getTimestamp("messageCreateTime"));
		
		return message;
	}

}
