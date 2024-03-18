package com.example.aptProject.controller;

import com.example.aptProject.entity.Users;
import com.example.aptProject.service.LocationService;
import com.example.aptProject.service.MyRegionService;
import com.example.aptProject.service.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private LocationService locationCodeService;
	@Autowired
	private UsersService uSvc;
	@Autowired
	private MyRegionService mSvc;

	@GetMapping("/register")
	public String registerForm(Model model) {
		List<String> firstNames = locationCodeService.getAllFirstNames();
		model.addAttribute("firstNames", firstNames);
		return "user/register";
	}

	@GetMapping("/register/secondNames")
	@ResponseBody
	public List<String> getSecondNamesByFirstName(@RequestParam("firstName") String firstName) {
		List<String> secondNames = locationCodeService.getSecondNamesByFirstName(firstName);
		return secondNames;
	}

	@PostMapping("/register")
	public String registerProc(Model model,
							   String uid, String pwd, String pwd2, String uname, String email, String firstName, String secondName) {

		if (uSvc.getUserByUid(uid) != null) {
			model.addAttribute("msg", "사용자 ID가 중복되었습니다.");
			model.addAttribute("url", "/apt/user/register");
			return "common/alertMsg";
		}
		if (pwd.equals(pwd2) && pwd != null) {
			System.out.println("1111111111111111111111111111111111");
			Users user = new Users(uid, pwd, uname, email);
			System.out.println("22222222222222222222222222222");
			uSvc.registerUser(user);
			System.out.println("33333333333333333333333333");
			mSvc.registerUser(user, firstName, secondName);
			System.out.println("4444444444444444444444444444");

			model.addAttribute("msg", "등록을 마쳤습니다. 로그인하세요.");
			System.out.println("5555555555555555555555555555555");
			model.addAttribute("url", "/apt/user/login");
			System.out.println("6666666666666666666666666666666");
			return "common/alertMsg";
		} else {
			model.addAttribute("msg", "패스워드 입력이 잘못되었습니다.");
			model.addAttribute("url", "/apt/user/register");
			return "common/alertMsg";
		}

	}

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login")
	public String loginProc(String uid, String pwd, HttpSession session, Model model) {
		int result = uSvc.login(uid, pwd);
		switch (result) {
			case UsersService.CORRECT_LOGIN:
				Users user = uSvc.getUserByUid(uid);
				session.setAttribute("sessUid", uid);
				session.setAttribute("sessUname", user.getUname());
				session.setAttribute("email", user.getEmail());

			// 환영 메세지
			log.info("Info Login: {}, {}", uid, user.getUname());
			model.addAttribute("msg", user.getUname()+"님 환영합니다.");
			model.addAttribute("url", "/apt/map");
			break;

		case UsersService.USER_NOT_EXIST:
			model.addAttribute("msg", "ID가 없습니다. 회원가입 페이지로 이동합니다.");
			model.addAttribute("url", "/apt/user/register");
			break;

		case UsersService.WRONG_PASSWORD:
			model.addAttribute("msg", "패스워드 입력이 잘못되었습니다. 다시 입력하세요.");
			model.addAttribute("url", "/apt/user/login");
		}
		return "common/alertMsg";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}

	@GetMapping("/update")
	public String updateForm(HttpSession session, Model model) {
		String sessUid = (String) session.getAttribute("sessUid");
		if (sessUid != null) {
			Users user = uSvc.getUserByUid(sessUid);
			model.addAttribute("user", user);
		} else {

			return "redirect:/user/login";
		}

		return "user/update";
	}
	@PostMapping("/update")
	public String updateProc(Users user, HttpSession session, Model model) {
		String sessUid = (String) session.getAttribute("sessUid");

		if (sessUid != null && sessUid.equals(user.getUid())) {
			uSvc.updateUser(user);

			model.addAttribute("msg", "회원 정보가 업데이트되었습니다.");
			model.addAttribute("url", "/apt/user/update");
		} else {

			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/apt/user/login");
		}

		return "redirect:/user/logout";

	}

	@GetMapping("/register/firstNames")
	public List<String> getAllFirstNames() {
		return locationCodeService.getAllFirstNames();
	}





}



