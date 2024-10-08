package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;
import com.ktdsuniversity.edu.hello_spring.common.beans.FileHandler;
import com.ktdsuniversity.edu.hello_spring.common.vo.StoreResultVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private FileHandler fileHandler;
	
	
	// apllication.yml 파일에서 "app.multipart.base-dir"설정 값을 가져온다.
	
	// Value아래에 있는 baseDirectory에 할당한다고 보면된다.
	@Value("${app.multipart.base-dir}")
	private String baseDirectory;
	
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
		
		// 파일 업로드 처리
		MultipartFile file = writeBoardVO.getFile();
		
		StoreResultVO storeResultVO = this.fileHandler.storeFile(file);
		
		if (storeResultVO != null) {
			writeBoardVO.setFileName(storeResultVO.getObfuscatedFileName());
			writeBoardVO.setOriginFileName(storeResultVO.getOriginFileName());
		}
		
		int result = this.boardDao.insertNewBoard(writeBoardVO);
		return result > 1;
	}
	
	@Override
	public BoardVO getOneBoard(int id, boolean isAdd) {
		if(isAdd) {
		boolean isUpdate = this.boardDao.addViewCnt(id) > 0;
			if(!isUpdate) {
				System.out.println("id : " + id + "는 존재하지 않습니다.");
				return null;
			}
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
		
		// 기존의 파일을 삭제하기 위해서 업데이트하기 전 게시글의 정보를 조회한다.
		BoardVO boardVO = this.boardDao.selectOneBoard(modifyBoardVO.getId());
		
		MultipartFile file = modifyBoardVO.getFile();
		
		StoreResultVO storeREsultVO = this.fileHandler.storeFile(file);
		if(storeREsultVO != null) {
			modifyBoardVO.setFileName(storeREsultVO.getObfuscatedFileName());
			modifyBoardVO.setOriginFileName(storeREsultVO.getOriginFileName());
		}
		
		int result = this.boardDao.updateOneBoard(modifyBoardVO);
		if (result > 0) {
			
			this.fileHandler.deleteFile(boardVO.getFileName());
		}
		return result > 0;
	}
	
	@Override
	public boolean deleteOneBoard(int id) {
		
		BoardVO boardVO= this.boardDao.selectOneBoard(id);
		
		int result = this.boardDao.deleteOneBoard(id);
		
		if (result > 0) {
			this.fileHandler.deleteFile(boardVO.getFileName());
		}
		return result > 0;
	}
}






