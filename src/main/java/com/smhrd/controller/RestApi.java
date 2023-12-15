package com.smhrd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.smhrd.entity.QNA;
import com.smhrd.entity.Tbl_Deep;
import com.smhrd.entity.Tbl_Payment;
import com.smhrd.entity.Tbl_Rating;
import com.smhrd.entity.Tbl_Request;
import com.smhrd.entity.Tbl_User;
import com.smhrd.entity.Tbl_answer;
import com.smhrd.mapper.AdminMapper;
import com.smhrd.repository.AnswerRepository;
import com.smhrd.repository.RatingRepository;
import com.smhrd.repository.RequestRepository;
import com.smhrd.repository.UserRepository;

@RestController
public class RestApi {

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private UserRepository uPo;

	@Autowired
	private AnswerRepository anpo;
	
	@Autowired
	private RatingRepository rapo;

	// 회원가입시 데이터베이스 저장 api
	@RequestMapping("/join")
	public Map<String, Object> join(Tbl_User user, String userEmail) {

		System.out.println("서버요청 들어왔냐?");
		System.out.println(user.getJoinedAt());
		if (user.getUserRole().equals("사용자")) {
			user.setUserToken(1);
		}

		uPo.save(user);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", user);

		return resultMap;

	}



	@PostMapping("/joinMedic")
	public ResponseEntity<String> uploadImage2(@RequestParam("imageFileName") String imageFileName,
			@RequestBody Tbl_User user) {
		user.setUserLoginType("Nomal");
		try {
			if (!imageFileName.equals("")) {
				// 이미지를 서버에 저장하는 로직
				String filePath = "/home/ubuntu/CMS/join/" + imageFileName;
				saveImageToFile2(user.getUserAuth(), filePath); // 이미지 저장 방식 변경

				// 데이터베이스에 이미지 파일명을 저장하는 로직
				user.setUserAuth(imageFileName); // 이미지 파일명 설정
			}
			uPo.save(user);

			return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 유저 개인정보 변경
	   @PostMapping(value = "/updateUser", produces = "application/json; charset=UTF-8")
	   public Map<String, Object> mypageuser(@RequestBody Tbl_User user) {
	      // 여기서 userEmail을 사용하여 필요한 작업 수행 가능
	      String userEmail = user.getUserEmail();

	      System.out.println("수정할거 들어왔냐?");

	      Map<String, Object> resultMap = new HashMap<>();

	      try {
	         // 데이터베이스에서 사용자 정보 가져오기
	         Tbl_User euser = uPo.findByUserEmail(userEmail);

	         if (euser != null) {
	            // 업데이트할 필드 설정
	            euser.setUserNick(user.getUserNick());
	            euser.setUserPhone(user.getUserPhone());

	            // 업데이트된 사용자 정보 저장
	            uPo.save(euser);
	            resultMap.put("result", "success");
	         } else {
	            resultMap.put("result", "fail");
	            resultMap.put("error", "유저를 찾을 수 없습니다.");
	         }
	      } catch (Exception e) {
	         resultMap.put("result", "fail");
	         resultMap.put("error", e.getMessage());
	         e.printStackTrace();
	      }

	      return resultMap;
	   }
	   
	   
	   // 의대생 개인정보 변경
	   @PostMapping(value = "/updateMedic", produces = "application/json; charset=UTF-8")
	   public Map<String, Object> mypagemedic(@RequestBody Tbl_User user) {
	      // 여기서 userEmail을 사용하여 필요한 작업 수행 가능
	      String userEmail = user.getUserEmail();

	      System.out.println("수정할거 들어왔냐?");

	      Map<String, Object> resultMap = new HashMap<>();

	      try {
	         // 데이터베이스에서 사용자 정보 가져오기
	         Tbl_User euser = uPo.findByUserEmail(userEmail);

	         if (euser != null) {
	            // 업데이트할 필드 설정
	            euser.setUserPw(user.getUserPw());
	            euser.setUserPhone(user.getUserPhone());

	            // 업데이트된 사용자 정보 저장
	            uPo.save(euser);
	            resultMap.put("result", "success");
	         } else {
	            resultMap.put("result", "fail");
	            resultMap.put("error", "유저를 찾을 수 없습니다.");
	         }
	      } catch (Exception e) {
	         resultMap.put("result", "fail");
	         resultMap.put("error", e.getMessage());
	         e.printStackTrace();
	      }

	      return resultMap;
	   }
	
	
	

	@RequestMapping("/joinCheck")
	public Map<String, Object> joinCheck(Tbl_User user) {
		System.out.println(user.getUserEmail());

		Tbl_User count = uPo.findByUserEmail(user.getUserEmail());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (count == null || count.getUserEmail() == null) {
			resultMap.put("result", "0");
			System.out.println("0번");
			return resultMap;
		} else {
			resultMap.put("result", "1");
			System.out.println("1번");
			return resultMap;
		}

	}

	//
//	@PostMapping("/RestApiServerTestQna")
//	public CompletableFuture<Map<String, Object>> test2(@RequestParam String user_email) {
//		return CompletableFuture.supplyAsync(() -> {
//			Map<String, Object> resultMap = new HashMap<>();
//
//			// QNA 정보 조회
//			List<QNA> qna = adminMapper.qna_detail(user_email);
//			if (qna != null) {
//				resultMap.put("qna", qna);
//			} else {
//				resultMap.put("qna", "데이터 없음");
//			}
//
//			System.out.println(resultMap);
//			return resultMap;
//		});
//	}

	// 회원가입 조회 api
//		@RequestMapping(value = "/joinCheck", produces = "application/json; charset=UTF-8")
//		public Map<String, Object> joinCheck() {
//
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap.put("result", adminMapper.high_rating());
//
//			System.out.println(resultMap);
//
//			return resultMap;
//		}

	// 로그인 api
	@RequestMapping(value = "/userInfo", produces = "application/json; charset=UTF-8")
	public Map<String, Object> test1(String dataid, String datapw) {

		System.out.println("로그인요청");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 데이터베이스에서 사용자 정보 조회
		Tbl_User user = uPo.findByUserEmail(dataid);

		if (user != null && user.getUserPw().equals(datapw) && user.getApprovedAt() != null) {
			// 사용자 정보가 있고 비밀번호가 일치하는 경우
			resultMap.put("result", user);
			List<QNA> qna = adminMapper.qnaNull(dataid);
			resultMap.put("qna", qna);
			List<QNA> qnaOk = adminMapper.qnaNotNull(dataid);
			resultMap.put("ansOk", qnaOk);
		} else if (user == null || !user.getUserPw().equals(datapw)) {
			// 사용자 정보가 없거나 비밀번호가 일치하지 않는 경우
			resultMap.put("result", "loginFail");
		} else if (user != null && user.getUserPw().equals(datapw) && user.getApprovedAt() == null){
			resultMap.put("result", "NotApprove");
		}

		return resultMap;

	}

	// 간편로그인 api
	@RequestMapping(value = "/userKaNaInfo", produces = "application/json; charset=UTF-8")
	public Map<String, Object> userKaNaInfo(Tbl_User user) {
		Map<String, Object> resultMap = new HashMap<>();

		System.out.println("유저카/네이메일 조회" + user.getUserEmail());
		// 데이터베이스에서 사용자 정보 조회
		Tbl_User info = uPo.findByUserEmail(user.getUserEmail());

		if (info != null) {
			resultMap.put("result", info);
			System.out.println("정보 가져옴.");
		} else {
			resultMap.put("result", null); // 사용자 정보 없음을 나타내는 값
			System.out.println("정보 없음.");
		}

		return resultMap;
	}

	// 게시판 리스트 추출 api
	@RequestMapping(value = "/qnaContent", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> getImage(String user_email) {

		try {

			List<QNA> qnaList = adminMapper.qnaNull(user_email);

			List<QNA> resList = new ArrayList<>();

			Map<String, Object> imageMap = new HashMap<>();

			for (int i = 0; i < qnaList.size(); i++) {

				QNA qna = new QNA();
				qna = qnaList.get(i);

				if (qna.getReq_img() != null) {
					byte[] fileContent = FileUtils
							.readFileToByteArray(new File("/home/ubuntu/CMS/img/" + qna.getReq_img()));
					String encodedString = Base64.getEncoder().encodeToString(fileContent);
					qna.setReq_img_url(encodedString);
				} else {
					qna.setReq_img_url(null);
				}

				resList.add(qna);

			}
			imageMap.put("qnaContent", resList);
			System.out.println("durl");
			System.out.println(imageMap);

			return new ResponseEntity<>(imageMap, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// 의대생 상담요청 리스트 추출 api
	@RequestMapping(value = "/MedicQnaContent", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> getImage1() {

		try {

			List<QNA> qnaList = adminMapper.MedicQnaNull();
			

			List<QNA> resList = new ArrayList<>();

			Map<String, Object> imageMap = new HashMap<>();
			

			System.out.println("씨발년아"+qnaList.size());
			
			for (int i = 0; i < qnaList.size(); i++) {
				QNA qna = new QNA();
				System.out.println("포문");
				qna = qnaList.get(i);
				System.out.println(i);
				System.out.println(qna);
				if (qna.getReq_img() != null) {
					
				byte[] fileContent = FileUtils
						.readFileToByteArray(new File("/home/ubuntu/CMS/Deep/" + qna.getDeep_img()));
				String encodedString = Base64.getEncoder().encodeToString(fileContent);
				
				byte[] fileContent2 = FileUtils
						.readFileToByteArray(new File("/home/ubuntu/CMS/img/" + qna.getReq_img()));
				String encodedString2 = Base64.getEncoder().encodeToString(fileContent2);

					
					System.out.println("이푸");
				qna.setDeep_img_url(encodedString);
				qna.setReq_img_url(encodedString2);
				
				System.out.println(encodedString + encodedString2);

				} else {
					System.out.println("엘수");
					qna.setDeep_img_url(null);
					qna.setReq_img_url(null);
				}

				
				resList.add(qna);

			}
			imageMap.put("qnaContent", resList);

			return new ResponseEntity<>(imageMap, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// 의대생 상담완료 리스트 추출 api
	@RequestMapping(value = "/MedicQnAnswerContent", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> getImage2(@RequestParam String user_email) {

		try {

			List<QNA> qnaList = adminMapper.MedocQnaNotNull(user_email);

			List<QNA> resList = new ArrayList<>();

			Map<String, Object> imageMap = new HashMap<>();

			for (int i = 0; i < qnaList.size(); i++) {
				QNA qna = new QNA();
				System.out.println("포문");
				qna = qnaList.get(i);
				System.out.println(i);
				System.out.println(qna);
				if (qna.getReq_img() != null) {
					
				byte[] fileContent = FileUtils
						.readFileToByteArray(new File("/home/ubuntu/CMS/Deep/" + qna.getDeep_img()));
				String encodedString = Base64.getEncoder().encodeToString(fileContent);
				
				byte[] fileContent2 = FileUtils
						.readFileToByteArray(new File("/home/ubuntu/CMS/img/" + qna.getReq_img()));
				String encodedString2 = Base64.getEncoder().encodeToString(fileContent2);

					
					System.out.println("이푸");
				qna.setDeep_img_url(encodedString);
				qna.setReq_img_url(encodedString2);
				
				System.out.println(encodedString + encodedString2);

				} else {
					System.out.println("엘수");
					qna.setDeep_img_url(null);
					qna.setReq_img_url(null);
				}

				
				resList.add(qna);

			}
			imageMap.put("qnaContent", resList);

			return new ResponseEntity<>(imageMap, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	// 유저 상담완료 리스트 추출 api
		@RequestMapping(value = "/userQnAnswerContent", produces = "application/json; charset=UTF-8")
		public ResponseEntity<Map<String, Object>> getImage3(@RequestParam String user_email) {

			try {

				List<QNA> qnaList = adminMapper.UserQnaNotNull(user_email);

				List<QNA> resList = new ArrayList<>();

				Map<String, Object> imageMap = new HashMap<>();

				for (int i = 0; i < qnaList.size(); i++) {
					QNA qna = new QNA();
					System.out.println("포문");
					qna = qnaList.get(i);
					System.out.println(i);
					System.out.println(qna);
					if (qna.getReq_img() != null) {
						
					byte[] fileContent = FileUtils
							.readFileToByteArray(new File("/home/ubuntu/CMS/Deep/" + qna.getDeep_img()));
					String encodedString = Base64.getEncoder().encodeToString(fileContent);
					
					byte[] fileContent2 = FileUtils
							.readFileToByteArray(new File("/home/ubuntu/CMS/img/" + qna.getReq_img()));
					String encodedString2 = Base64.getEncoder().encodeToString(fileContent2);

						
						System.out.println("이푸");
					qna.setDeep_img_url(encodedString);
					qna.setReq_img_url(encodedString2);
					
					System.out.println(encodedString + encodedString2);

					} else {
						System.out.println("엘수");
						qna.setDeep_img_url(null);
						qna.setReq_img_url(null);
					}

					
					resList.add(qna);

				}
				imageMap.put("qnaContent", resList);

				return new ResponseEntity<>(imageMap, HttpStatus.OK);
			} catch (Exception e) {

				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}

	@RequestMapping("/answer")
	public Map<String, Object> answer(Tbl_answer answer) {

		String user_email = answer.getAnsUserEmail();
		String req_idx = Integer.toString(answer.getReqIdx());
		
		System.out.println("요청 들어옴" + answer);
		anpo.save(answer);
		adminMapper.answerPay(user_email, req_idx);
		adminMapper.updateUserShare(user_email);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", answer);

		return resultMap;

	}
	
	@RequestMapping("/rating")
	public Map<String, Object> rating(Tbl_Rating rating) {
		
		System.out.println("평가 요청 들어옴" + rating);
		rapo.save(rating);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", rating);

		return resultMap;

	}
	
	@RequestMapping("/ratingSel")
	public int ratingSel(String ansIdx) {
	    System.out.println("평가 했니? 들어옴 " + ansIdx);

	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("ansIdx", Integer.parseInt(ansIdx));

	    int cnt = adminMapper.ratingSel(paramMap);

	    if (cnt > 0) {
	        return 1;
	    } else {
	        return 0;
	    }
	}

	// 결제
	@RequestMapping("/payment")
	public Map<String, Object> payment(@RequestBody Tbl_Payment tblPayment) {
		System.out.println(tblPayment.getUserEmail());
		System.out.println("결제 들어옴");
		adminMapper.pay_insert(tblPayment);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", tblPayment);

		return resultMap;

	}

	@RequestMapping("/deleteWrite")
	public int deleteWrite(@RequestBody Tbl_User user) {

		System.out.println("질문 작성 뒤로가기 요청 들어옴");
		int deleteCnt = adminMapper.WriteDelete(user.getUserEmail());

		int resultMap = 0;

		if (deleteCnt > 0) {
			resultMap = 1;
		} else {
			resultMap = 0;
		}

		return resultMap;

	}

	@RequestMapping("/minusToken")
	public int minusToken(String user_email) {
		System.out.println("토큰사용" + user_email);
		int resultMap = adminMapper.minusToken(user_email);

		return resultMap;
	}

	@PostMapping("/uploadImage2")
	public ResponseEntity<String> uploadImage2(@RequestParam("imageFileName") String imageFileName,
			@RequestBody Tbl_Request tblRequest) {
		try {
			if (!imageFileName.equals("")) {
				// 이미지를 서버에 저장하는 로직
				String filePath = "/home/ubuntu/CMS/img/" + imageFileName;
				saveImageToFile2(tblRequest.getReqImg(), filePath); // 이미지 저장 방식 변경

				// 데이터베이스에 이미지 파일명을 저장하는 로직
				tblRequest.setReqImg(imageFileName); // 이미지 파일명 설정
			}
			adminMapper.req_insert(tblRequest);

			return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void saveImageToFile2(String base64Image, String filePath) throws IOException {
		byte[] imageBytes = Base64.getDecoder().decode(base64Image);
		try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
			fos.write(imageBytes);
		}
	}

	@PostMapping("/uploadDeep")
	public ResponseEntity<String> uploadDeep(@RequestParam("imageFileName") String imageFileName,
			@RequestBody Tbl_Deep tblDeep) {

		System.out.println("업로드 딥러닝 이미지");
		try {
			// 이미지를 서버에 저장하는 로직
			String filePath = "/home/ubuntu/CMS/Deep/" + imageFileName;
			saveImageToFile2(tblDeep.getDeepImg(), filePath); // 이미지 저장 방식 변경

			// 데이터베이스에 이미지 파일명을 저장하는 로직
			tblDeep.setDeepImg(imageFileName); // 이미지 파일명 설정
			adminMapper.deep_insert(tblDeep);

			return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

//@RequestMapping(value = "/getImage", produces = "application/json; charset=UTF-8")
//public ResponseEntity<Map<String, Object>> getImage(HttpServletRequest request, String user_email) {
//	try {
//
//		
//		request.getParameter("user_email");
//		request.getParameter("user_email1");
//		request.getParameter("user_email2");
//		request.getParameter("user_email3");
//		request.getParameter("user_email4");
//		
//		MultipartRequest mtprq = (MultipartRequest) request.getParameter("user_file");
//		
//		List<QNA> qnaList = adminMapper.qnaNull(user_email);
//		System.out.println(qnaList.size());
//
//		List<QNA> resList = new ArrayList<>();
//
//		Map<String, Object> imageMap = new HashMap<>();
//
//		for (int i = 0; i < qnaList.size(); i++) {
//
//			QNA qna = new QNA();
//			qna = qnaList.get(i);
//			String imgUrl = "/Users/smhrd/Desktop/" + qna.getReq_img();
//			qna.setReq_img_url(imgUrl);
//
//			resList.add(qna);
//
//			// imageMap.put(qna.getUser_email()+(i+1), imgUrl);
//
//		}
//		imageMap.put("qnaContent", resList);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
//
//		return new ResponseEntity<>(imageMap, headers, HttpStatus.OK);
//	} catch (Exception e) {
//
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//	// 이미지 경로가 없는 경우에는 에러 응답
//
//}
