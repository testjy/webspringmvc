package kr.or.nextit.member.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;

@Controller
@RequestMapping("/member")
// @SessionAttributes("member")
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@ModelAttribute("searchTypeMap")
	// model.addAttribute("searchTypeMap",searchTypeMap);을 어노테이션으로 처리
	public Map<String, String> getSearchType() {
		Map<String, String> searchTypeMap = new LinkedHashMap<>();
		searchTypeMap.put("id", "아이디");
		searchTypeMap.put("name", "이름");
		return searchTypeMap;

	}

	// 포스트 메소드로 요청이 들어올때만 기동됨
	@RequestMapping(value = "/memberList")
	public String memberList(HttpServletRequest request,
			// required=true 를 주면, 해당 파라미터가 반드시 들어가야 하는식으로 제어 가능 .
			// defaultValue 속성을 통해 default값 적용 가능
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) throws Exception {

		request.setCharacterEncoding("utf-8");

		// String searchType = request.getParameter("searchType");
		// String searchWord = request.getParameter("searchWord");

		Map<String, Object> paramMap = new HashMap<>();

		if (StringUtils.isNotBlank(searchWord)) {

			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);

		}

		// 회원목록 조회
		List<Member> memberList = memberService.getMemberList(paramMap);

		model.addAttribute("memberList", memberList);

		// request.setAttribute("memberList", memberList);
		// 항상 뷰의 이름으로 줄것
		return "member/memberList";

	}

	@RequestMapping("/memberView")
	public String memberView(@RequestParam(value = "mem_id", required = true) String mem_id, Model model

	) throws Exception {
		Member member = memberService.getMember(mem_id);
		model.addAttribute("member", member);
		return "member/memberView";
	}

	@RequestMapping(value = "/memberForm")
	public String memberForm(HttpSession session, Member member,
			@RequestParam(value = "mem_id", required = false) String mem_id, Model model) throws Exception {

		// if(member!=null) {

		// member = (Member)session.getAttribute("member");

		// }

		model.addAttribute("member", member);

		if (StringUtils.isNotBlank(mem_id)) {

			member = memberService.getMember(mem_id);

		}

		model.addAttribute("member", member);

		return "member/memberForm";

	}

	@RequestMapping(value = "/memberInsert", method = RequestMethod.POST)
	public String memberInsert(@ModelAttribute("member") Member member, Model model, SessionStatus sessionStatus) {

		String viewPage = "common/message";
		boolean isError = false;
		String message = "정상적으로 회원가입 되었습니다.";
		// 유효성 검증.
		if (member.getMem_id() == null || member.getMem_id().isEmpty()) {
			isError = true;
			message = "아이디를 입력하세요.";
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			isError = true;
			message = "이름을 입력하세요.";
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);

			// return "member/memberForm";
			return "redirect:/member/memberForm?type=I";
			// model.addAttribute("member", member);

		}

		try {
			if (!isError) {

				int updCnt = memberService.insertMember(member);

				if (updCnt == 0) {
					// 에러
					message = "회원등록에 실패하였습니다.";
					isError = true;
				}
			}
		} catch (Exception e) {
			// 에러
			e.printStackTrace();
			message = "회원등록에 실패하였습니다.";
			isError = true;
		}

		sessionStatus.setComplete();

		// response.sendRedirect(request.getContextPath() + "/member/memberList.do");
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberList");

		return viewPage;

	}

	@RequestMapping(value = "/memberUpdate")
	public String updateMember(// @ModelAttribute("member")
			Member member, Model model) throws Exception {

		String viewPage = "common/message";

		boolean isError = false;
		String message = "정상 수정되었습니다.";

		/*
		 * if(StringUtils.isBlank(member.getMem_id())) { isError = true; message =
		 * "아이디를 입력해주세요."; } if(StringUtils.isBlank(member.getMem_name())) { isError =
		 * true; message = "이름을 입력해주세요."; }
		 */

		if (!isError) {

			try {

				int updCnt = memberService.updateMember(member);

				if (updCnt == 0) {
					message = "회원정보 수정 실패하였습니다.";
					isError = true;
				}
			} catch (Exception e) {
				message = "회원정보 수정 실패하였습니다.";
				isError = true;
				throw e;
			}
		}

		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberView?mem_id=" + member.getMem_id());

		return viewPage;

	}

	@RequestMapping("/memberDelete")
	public String deleteMember(@RequestParam(value = "mem_id") String mem_id, Model model) throws Exception {

		boolean isError = false;
		String viewPage = "common/message";
		String message = "정상 삭제되었습니다.";

		try {
			int updCnt = memberService.deleteMember(mem_id);
			if (updCnt == 0) {
				message = "회원삭제 실패하였습니다.";
				isError = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "회원삭제 실패하였습니다.";
			isError = true;
			throw e;

		}

		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberList");

		return viewPage;

	}

	@RequestMapping("/memberExists")
	@ResponseBody 
//	이 어노테이션이 존재하지 않으면, 해당 리턴값의 jsp파일을 찾지만, 이걸 붙여주면 그렇게 인지하지 않는다.
	public Map<String, Object> memberExists(@RequestParam(value = "mem_id", required = true) String mem_id

	) throws Exception {
		Member member = memberService.getMember(mem_id);
		
		Map<String, Object> resultMap = new HashMap<>();
		
//		resultMap.put("msg", "성공");
//		resultMap.put("member", member);
		

		if (member != null) {
			resultMap.put("result", "true");

		} else {
			resultMap.put("result", "false");

		}

		return resultMap ;
	}

}
