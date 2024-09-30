package homework.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import homework.bbs.service.BoardService;
import homework.bbs.vo.BoardListVO;
import homework.bbs.vo.BoardVO;
import homework.bbs.vo.WriteBoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public String viewBoardList(Model model) {
		
		BoardListVO boardListVO = this.boardService.getAllBoard();
		
		model.addAttribute("boardListVO", boardListVO);
		
		return "board/boardlist";
	}
	
	@GetMapping("/board/write")
	public String viewBoardWritePage() {
		return "board/boardwrite";
	}
	
	@PostMapping("/board/write")
	public String doCreateNewBoard(WriteBoardVO writeBoardVO) {
		boolean isCreate = this.boardService.createNewBoard(writeBoardVO);
		System.out.println("게시글 등록 결과: " + isCreate);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/viewone")
	// 교재에서는 도메인에 ?id=1처럼 번호로 받아오는 방법을 제시했음
	// 파라미터에 @RequestParam를 붙이면 된다.
	// 그리고, modelAndView객체를 만들고, setViewName("board/biewone")으로 이어주고
	// addObject("boardVO", board)로 객체 바인딩?해주면 되는 것으로 보임
	
	// 근데 REqusetParam은 그렇다고 치고, modelView대신 Model model을 파라미터로 받아와서 addAttribute하면 안되나?
	public String viewOneBoard(Model model) {
		BoardVO board = this.boardService.getOneBoard(3);
		if(board == null) {
			return "/board/notfound";
		}
		model.addAttribute("boardVO", board);
		return "/board/viewone";
	}
}
