package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	//게시글을 가져오는 역할을 Dao에 넣어놨으니 보드Dao를 autowired해줘야한다.
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public BoardListVO getAllBoard() {
		// 게시글 목록 화면에 데이터를 전송해주기 위해서
		// 게시글의 건수와 게시글의 목록을 조회해 반환시킨다.
		
		// 1. 게시글의 건수를 조회한다.
		int boardCount = this.boardDao.selectBoardAllCount();
		
		// 2. 게시글의 목록을 조회한다.
		List<BoardVO> boardList = this.boardDao.selectAllBoard();
		
		// 3. BoardListVO를 만들어서 게시글의 건수와 목록을 할당한다.
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardCnt(boardCount);
		boardListVO.setBoardList(boardList);
		
		// 4. BoardListVO인스턴스를 반환한다.
		return boardListVO;
		 
	}
	@Override
	public boolean createNewBoard(WriteBoardVO writeBoardVO) {
		int result = this.boardDao.insertNewBoard(writeBoardVO);
		return result > 1;
	}
	
	@Override
	public BoardVO getOneBoard(int id) {
		boolean isUpdate = this.boardDao.addViewCnt(id) > 0;
		if(!isUpdate) {
			System.out.println("id : " + id + "는 존재하지 않습니다.");
			return null;
		}
		BoardVO board = this.boardDao.selectOneBoard(id);
		return board;
	}
	
	/*
	@Override
	public boolean addViewCnt(int id) {
		int result = this.boardDao.addViewCnt(id);
		return result == 1;
	}
	*/
	
	@Override
	public boolean updateOneBoard(ModifyBoardVO modifyBoardVO) {
		int result = this.boardDao.updateOneBoard(modifyBoardVO);
		return result > 0;
	}
	
	@Override
	public boolean deleteOneBoard(int id) {
		int result = this.boardDao.deleteOneBoard(id);
		return result > 0;
	}
}






