package community.demo.web;

import community.demo.domain.Member;
import community.demo.service.LoginService;
import community.demo.service.MemberService;
import community.demo.web.form.LoginForm;
import community.demo.web.form.MemberJoinForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberJoinForm", new MemberJoinForm());
        return "member/join";
    }

    @PostMapping("/join")
    public String joinMember(@Validated @ModelAttribute MemberJoinForm form, BindingResult bindingResult) {
        log.info("*** join post 요청 ***");
        log.info("memberJoinForm = {}", form);
        log.info("bindingResult = {}", bindingResult);

        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        Member member = new Member(form.getName(), form.getLoginId(), form.getPassword(), form.getEmail());
        memberService.save(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "/") String redirectURL, @Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        log.info("*** login post ***");
        log.info("memberLoginForm = {}", form);
        log.info("bindingResult = {}", bindingResult);

        if (bindingResult.hasErrors()) {
            return "member/login";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("loginMember = {}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member/login";
        }

        // 세션 있으면 있는 세션 반환, 없으면 신규 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("*** logout post ***");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
