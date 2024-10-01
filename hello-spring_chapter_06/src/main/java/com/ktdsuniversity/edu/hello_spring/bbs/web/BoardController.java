package com.ktdsuniversity.edu.hello_spring.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;
import com.ktdsuniversity.edu.hello_spring.common.beans.FileHandler;

@Controller
public class BoardController {
	
	@Autowired
	private FileHandler fileHandler;
	
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
	public String doCreateNewBoard(WriteBoardVO writeBoardVO, @RequestParam MultipartFile file ) {
		boolean isCreate = this.boardService.createNewBoard(writeBoardVO);
		System.out.println("게시글 등록 결과: " + isCreate);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/view/{id}")
	public String getOneBoard(@PathVariable int id, Model model) {
		BoardVO board = this.boardService.getOneBoard(id, true);
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
			model.addAttribute("boardVO", modifyBoardVO);
			return "board/boardmodify";
		}
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
	
	@GetMapping("board/file/download/{id}")
	public ResponseEntity<Resource> doDownloadFile(@PathVariable int id){
		
		// 1. 다운로드 할 파일의 이름을 알기 위해 게시글을 조회한다.
		BoardVO boardVO = this.boardService.getOneBoard(id, false);
	
		return this.fileHandler
				.downloadFile(boardVO.getFileName(), boardVO.getOriginFileName());

	}
	
	
	
	
	
	
	
	
	
	
	
}