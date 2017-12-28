/*package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.web.servlet.Controller;

public class BoardDeleteController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		// 로그인 여부 확인
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("LOGIN_USER");
		
		if(member == null) {
			return "redirect:/login/loginForm.do";
		}
				
		
		String viewPage = "common/message";
		
		boolean isError = false;
		String message = "정상 삭제되었습니다.";
		String locationURL = "/board/boardList.do";
		
		try {
			
			String boSeqNo = request.getParameter("bo_seq_no");
			Map<String, Object> paramMap = new HashMap<>();
			
			if(boSeqNo != null) {
				
				paramMap.put("bo_seq_no", Integer.parseInt(boSeqNo));		
				paramMap.put("upd_user", member.getMem_id());	// 로그인 사용자 정보
				
				BoardService boardService = BoardServiceImpl.getInstance();
				
				int updCnt = boardService.deleteBoard(paramMap);
				
				if(updCnt == 0) {
					isError = true;
					message = "삭제 실패하였습니다.";
				}
			}else {
				isError = true;
				message = "삭제 대상건이 없습니다.";
			}			
			
		}catch(Exception e) {
			isError = true;
			message = "삭제 실패하였습니다.";
		}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", locationURL);
		
		return viewPage;
	}
}
*/