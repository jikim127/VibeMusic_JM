package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.dto.MemberJoinDTO;
import com.example.vibemusic.dto.MemberLoginDTO;
import com.example.vibemusic.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void loginGET(String errorCode, String logout){
        log.info("-------login get-------");
        log.info("-------logout : {}", logout);

        if(logout != null){
            log.info("user logout.......................");
        }
    }

    @PostMapping("/login")
    public String loginPOST(MemberLoginDTO memberLoginDTO, RedirectAttributes redirectAttributes){
        log.info("login Post...........");
        log.info("memberSecurityDTO : {}", memberLoginDTO);

        try {
            memberService.login(memberLoginDTO);
        }catch (MemberService.MidExistException e){
            redirectAttributes.addFlashAttribute("error","mid");
            return "redirect:/member/login";
        }
        return "redirect:/member/index";
    }

    @GetMapping("/join")
    public void joinGET() {
        log.info("join get....");
    }

    @PostMapping("/join")
    public String joinGET(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        log.info("join post..........");
        log.info("memberJoinDTO : {}", memberJoinDTO);

        try{
            memberService.join(memberJoinDTO);
        }catch (MemberService.MidExistException e){

            redirectAttributes.addFlashAttribute("error","mid");
            return "redirect:/member/join";
        }
        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/member/login";
    }

    @GetMapping("/modify")
    public void modifyGET(Principal principal, Model model) {
        log.info("-------------modifyGET 진입-------------");

        String username = principal.getName();

        log.info("name : {}", username);

        Member member = memberService.findByUsername(username);

        if(member != null){
            model.addAttribute("name", member.getName());
            model.addAttribute("email", member.getEmail());
            model.addAttribute("phone", member.getPhone());
            model.addAttribute("address", member.getAddress());
            model.addAttribute("birthDate", member.getBirthDate());
        }

    }

    @PostMapping("/modify")
    public String modifyPOST(MemberLoginDTO memberLoginDTO, RedirectAttributes redirectAttributes) {
        log.info("modifyPOST 진입..................");

        log.info("memberLoginDTO.getName() : {}", memberLoginDTO.getName());

        try {
            memberService.modifyInformation(memberLoginDTO);
            log.info("회원 정보 변경 하기 성공~!");
        } catch (MemberService.MidExistException e){
            log.info("까뷔 실패");
            redirectAttributes.addFlashAttribute("error", "회원가입 실패");
            return "redirect:/member/modify";
        }
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/logout";
    }

}
