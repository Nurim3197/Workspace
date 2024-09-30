package com.ktdsuniversity.edu.hello_spring.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;

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
	
	@GetMapping("/board/view/{id}")
	public String getOneBoard(@PathVariable int id, Model model) {
		BoardVO board = this.boardService.getOneBoard(id);
		model.addAttribute("boardVO", board);
		
		return "board/viewone";
	}
	
	@GetMapping("/board/modifyboard/{id}")
	public String modifyBoard(@PathVariable int id) {
		return "board/modifyboard";
	}
	
	@PostMapping("/board/modifyboard/{id}")
	public String modifyBoard(@PathVariable int id, ModifyBoardVO modifyBoardVO, Model model) {
		modifyBoardVO.setId(id);
		
		boolean isUpdate = this.boardService.updateOneBoard(modifyBoardVO);
		
		if(!isUpdate) {
			// TODO 사용자가 작성했던 내용을 JSP에 그대로 보내준다.
			model.addAttribute("boardVO", modifyBoardVO);
			return "board/boardmodify";
		}
		// TODO 성공적으로 수정했다면, 수정한 게시글의 상세 조회 페이지로 이동시킨다.
		return "redirect:/board/viewone/" + id;
	}
	
	@GetMapping("/board/delete/{id}")
	public String deleteOneBoard(@PathVariable int id) {
		boolean isDelete = this.boardService.deleteOneBoard(id);
		
		if (!isDelete) {
			System.out.println("실패하였습니다.");
			return "redirect:/board/view/" + id;
		}
		else {
			System.out.println("삭제 성공했습니다.");
		}
		return "redirect:/board/list";
	}
	
}