package com.smhrd.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.entity.QuestionInfo;
import com.smhrd.entity.Tbl_Admin;
import com.smhrd.entity.Tbl_Request;
import com.smhrd.entity.Tbl_User;
import com.smhrd.mapper.AdminMapper;
import com.smhrd.repository.RequestRepository;
import com.smhrd.repository.UserRepository;



@Controller
public class CmsController {
	
	
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private RequestRepository repo;
	
	@Autowired
	private UserRepository uPo;

	@Autowired
	private Email email;
	

	//로그인페이지
	@RequestMapping("/")
	public String goMain() {
		
		
		return "LogIn";
	}
	
	//메인페이지
	@RequestMapping("/Main")
	public String goHome() {

		
		return "Main";
	}
	
	//질문 전체 조회
	@RequestMapping("/QnA")
	public String goQnA(Model model) {
		
		List<Tbl_Request> request = repo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	
		model.addAttribute("request",request);
		
		return "QnA";
	}
	
	// 질문 상세 조회
	@RequestMapping("/QuestionInfo")
	public String goQuestionInfo(Model model, int req_idx) {
		
		QuestionInfo request = adminMapper.Q_detail(req_idx);
	
		model.addAttribute("request", request);
		
		return "QuestionInfo";
	}
	
	// 게시글 전체 조회
	@RequestMapping("/Post")
	public String goPost() {
		
		return "Post";
	}
	
	// 게시글 작성 페이지
	@RequestMapping("/PostWirte")
	public String goPostWrite() {
		
		return "PostWrite";
	}
	
	// 계정 전체 조회
	   @RequestMapping(value = "/User", method = RequestMethod.GET)
	   public String goManagement(Model model) {
	      List<Tbl_User> user = uPo.findAll(Sort.by(Sort.Direction.DESC, "joinedAt"));
	      model.addAttribute("user",user);
	      
	      return "User";
	   }

	// 비동기(사용자,의대생)
	   @RequestMapping(value = "/User", method = RequestMethod.POST)
	      public @ResponseBody List<Tbl_User> search(String userRole) {
	         
	         System.out.println(userRole);
	         List<Tbl_User> list = adminMapper.search(userRole);
	         
	         System.out.println(list);
	         return list;
	      }
	   
	// 계정 상세 조회
	@RequestMapping("/UserInfo")
	public String detail(String user_email, Model model) {
		
		// 1. 파라미터 수집
		
		// 2. 기능 실행
		Tbl_User user = uPo.findByUserEmail(user_email) ;
		
		
		
		model.addAttribute("user", user);
		
		
		return "UserInfo";
		
	}
	
	//로그인
		@RequestMapping("/login")
		public String login(Tbl_Admin Tbl_admin, HttpServletResponse response, HttpSession session) {
			
			Tbl_admin = adminMapper.login(Tbl_admin);
			
			 String result = null;
			 
			 try {
			 if(Tbl_admin.getAdmin_id() != null) {
				 session.setAttribute("admin", Tbl_admin);
				 
				 System.out.println(Tbl_admin.getAdmin_email());
				 result = "Main";
			 }
			 
			 }
			 catch (Exception e) {
				 
				 String url = "/";
				 response.setContentType("text/html; charset=UTF-8");
				 System.out.println("1");
					PrintWriter writer;
					try {
						writer = response.getWriter();
						writer.println("<script>alert('로그인 정보를 확인해주세요.'); location.href='" + url + "';</script>");
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
			return result;
			
		}
	
	// 로그아웃
	@RequestMapping("/logout")
	public String logout(Tbl_Admin Tbl_admin, HttpSession session) {
			
		session.removeAttribute("admin");
		
		
		return "redirect:/";
	}
	
	
	
	
	// 관리자 비밀번호 수정
	@RequestMapping("/changePw")
	public String changePw(Tbl_Admin admin) {
		
		adminMapper.changePw(admin);
		
		
		return "redirect:/Main";
		
	}
	
	// 사용자계정 정보 수정
	@RequestMapping(value = "accountChange", method = RequestMethod.POST)
	public String accountChange(@RequestParam(value = "check", required = false) String check,Tbl_User user, HttpServletResponse response) throws IOException {
		
		System.out.println(check);
		
		if ("true".equals(check)) {
			Date currentDate = new Date();
			System.out.println(currentDate);
			user.setApprovedAt(currentDate);
			String[] to = {user.getUserEmail()};
			String subject = "Adviser 회원가입이 성공적으로 완료되었습니다!";
			String text = "안녕하세요 "+user.getUserEmail()+"님,\r\n"
					+ "\r\n"
					+ "저희 Adviser에 가입해 주셔서 감사합니다! 회원가입이 성공적으로 완료되었습니다.";

			email.sendMail(to, subject, text);
	    } 
		
		
		adminMapper.accountChange(user);
		 response.setContentType("text/html; charset=UTF-8");
	
		  PrintWriter out = response.getWriter();

		    out.println("<script>alert('Success');</script>");
		    out.println("<script>setTimeout(function() { window.location.href = '/UserInfo?user_email=" + user.getUserEmail() + "'; }, 100);</script>");
		    out.flush();

		    return null;
	}
	
	

		
}
