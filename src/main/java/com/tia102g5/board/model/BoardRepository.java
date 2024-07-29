// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.board.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from board where boardID =?1", nativeQuery = true)
	void deleteByBoardID(int boardID);

	//● (自訂)條件查詢
	@Query(value = "from Board where boardID=?1 and boardName like?2 order by boardID")
	List<Board> findByOthers(int boardID , String boardName );
}