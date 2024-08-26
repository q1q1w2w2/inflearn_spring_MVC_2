package community.demo.web;

import community.demo.domain.Member;
import community.demo.service.MemberService;
import community.demo.web.form.MemberJoinForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberJoinForm", new MemberJoinForm());
        return "member/join";
    }

    @PostMapping("/join")
    public String joinMember(@ModelAttribute MemberJoinForm form, Model model) {
        log.info("*** join post 요청 ***");
        log.info("memberJoinForm = {}", form);

        Member member = new Member(form.getName(), form.getLoginId(), form.getPassword(), form.getEmail());
        memberService.save(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "member/login";
    }
}
