package homework.bbs.service;

import homework.bbs.vo.BoardListVO;
import homework.bbs.vo.BoardVO;
import homework.bbs.vo.WriteBoardVO;

public interface BoardService {

	public BoardListVO getAllBoard();
	
	public boolean createNewBoard(WriteBoardVO writeBoardVO);
	
	public BoardVO getOneBoard(int id);
	
//	public boolean addViewCnt(int id);
}
