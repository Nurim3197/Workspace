package homework.bbs.dao;

import java.util.List;

import homework.bbs.vo.BoardVO;
import homework.bbs.vo.WriteBoardVO;

public interface BoardDao {
	
	public int selectBoardAllCount();
	
	public List<BoardVO> selectAllBoard();
	
	public int insertNewBoard(WriteBoardVO writeBoardVO);
	
	public BoardVO selectOneBoard(int id);
	
	public int addViewCnt(int id);
	
}