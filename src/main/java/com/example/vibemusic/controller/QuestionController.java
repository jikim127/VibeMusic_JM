package com.example.vibemusic.controller;


import com.example.vibemusic.domain.Question;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.QuestionDTO;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import com.example.vibemusic.service.AnswerService;
import com.example.vibemusic.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Log4j2
@Transactional
public class QuestionController {

    private final QuestionService questionService;

    private final AnswerService answerService;

//    @GetMapping("/questionRead")
//    public void qRead(Long qno, Model model) {
//        log.info("qno=============> : {}", qno);
//        QuestionDTO questionDTO = questionService.read1Quest(qno);
//
//        model.addAttribute("dto", questionDTO);
//    }

@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
@GetMapping({"/questionRead","/questionModify"})
    public void qRead(@RequestParam Long qno, Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser) {
        log.info("qno=============> : {}", qno);
        QuestionDTO questionDTO = questionService.read1Quest(qno);

        model.addAttribute("dto", questionDTO);
    }


    @GetMapping("/questions")
    public String list(Pageable pageable, Model model, PageRequestDTO pageRequestDTO) {

        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        return "questions";
    }

    @GetMapping("/userinfo2")
    public ResponseEntity<Map<String, String>> getUserInfo(Authentication authentication) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName()); // 사용자 아이디를 가져옴

        return ResponseEntity.ok(userInfo);
    }

//    @GetMapping("/myQuestions")
//    public String myQuestions(PageRequestDTO pageRequestDTO, Model model, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser) {
//        // 현재 로그인한 사용자의 아이디를 가져옵니다.
//        String username = authenticatedUser.getUsername();
//
//        // 사용자가 작성한 글을 가져오는 서비스 메서드를 호출합니다.
//        PageResponseDTO<QuestionDTO> myQuestions = questionService.getMyQuestions(pageRequestDTO, username);
//
//        model.addAttribute("myQuestions", myQuestions);
//
//        return "/myQuestions"; // 내가 쓴 글 목록을 보여줄 뷰 이름
//    }



//    @GetMapping("/questions")
//    public void list(PageRequestDTO pageRequestDTO, Model model, Pageable pageable) {
//        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
//        model.addAttribute("qList", responseDTO);
//    }


//    @GetMapping("/questions") // DTO포함해야 표까지 정상적인 출력 가능
//    public String list(Pageable pageable, Model model, PageRequestDTO pageRequestDTO) {
//
//        // Set the number of items per page
//        int pageSize = 12;
//        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);
//
//        // Retrieve a Page of news items using the NewsService
//        Page<Question> questionPage = questionService.list(pageable);
//
//        // Add the Page of news items to the model
//        model.addAttribute("questionPage", questionPage);
//
//        // Ensure page number is not less than 1
//        if (pageRequestDTO.getPage() < 1) {
//            pageRequestDTO.setPage(1);
//        }
//
//        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
//        //DTO때문에 충돌 일어남
//        model.addAttribute("qList", responseDTO);
//
////         Return the view name for rendering
//        return "questions";  // questions.html
//
//    }

//    @PreAuthorize("#QuestionDTO.getQWriter() == principal.username")
    @PostMapping("/questionModify")
    public String modQ(
            @Valid QuestionDTO questionDTO,       //수정할 게시글의 데이터를 담고 있는 DTO
            BindingResult bindingResult,    //데이터 유효성 검사 결과를 저장하는 객체
            PageRequestDTO pageRequestDTO,  //페이지 요청에 관련된 데이터를 담고 있는 DTO
            RedirectAttributes redirectAttributes) {
//              //리다이렉트 시에 속성(attribute)를 전달하기 위한 객체

        log.info("question modify post.." + questionDTO);

        if(bindingResult.hasErrors()){

            String link = pageRequestDTO.getLink();
            log.info("got link");


            redirectAttributes.addAttribute("qno",questionDTO.getQno());//수정할 게시글의 식별번호(qno)를 리다이렉트 파라미터로 추가

            return "redirect:/questionModify?"+link;
        }

        questionService.modQuest(questionDTO);

        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("qno", questionDTO.getQno());

        return "redirect:/questionRead";


    }

//    @GetMapping("/questionModify")
//    public String qModify(@RequestParam Long qno, Model model, PageRequestDTO pageRequestDTO) {
//        log.info("qno=============> : {}", qno);
//        QuestionDTO questionDTO = questionService.read1Quest(qno);
//
//        model.addAttribute("dto", questionDTO);
//        return "questionModify"; // question_modify.html 뷰 이름
//    }

    @PostMapping("/questionRemove")
    public String removeQ(@RequestParam Long qno, RedirectAttributes redirectAttributes){
        //bno: 삭제할 게시글의 식별번호(Long 타입)입니다.
        //리다이렉트 시에 속성(attribute)를 전달
        log.info("remove post" + qno);

        questionService.removeQuest(qno); //boardService.remove(qno) 메서드를 호출하여 해당 식별번호의 게시글을 삭제
        redirectAttributes.addFlashAttribute("result","removed"); //삭제 성공을 나타내는 속성을 리다이렉트 속성에 추가


        return "redirect:/questions";
        //게시글 목록 페이지로 리다이렉트합니다.
    }
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    @GetMapping("/questionRegister")
    public void registerQuestGET(@AuthenticationPrincipal MemberSecurityDTO authenticatedUser){

        log.info("=============================등록GET============================");

    }

    @PostMapping("/questionRegister")
    public String registerQuest(
            @Valid QuestionDTO questionDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        // @Valid : 어노테이션을 사용하여 boardDTO 매개변수의 값이 유효한지 검증하여 유효성 검사를 통과한 데이터만 처리됨
        // bindingResult: 유효성 검사 결과를 저장하는 객체입니다. 만약 boardDTO의 유효성 검사에서 오류가 발생하면 해당 오류 정보가 이 객체에 저장됩니다.
        // RedirectAttributes : 리다이렉트 시 데이터를 전달하기 위한 객체입니다. 리다이렉트된 페이지에서 데이터를 받을 수 있습니다. 성공 메시지를 리다이렉트로 전달합니다.

        log.info("---------------------------------quest POST register--------------------------");

        log.info("questionDTO : {}", questionDTO.getQTitle());


        if(bindingResult.hasErrors()){ //유효성 검사 결과를 확인하여 오류가 있는지 체크함
            log.info("has errors");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // 오류 정보를 redirectAttributes에 추가하여 다음 페이지로 전달합니다.

            return "redirect:/questionRegister";
        }

        log.info(questionDTO);

        Long qno =questionService.registerQuest(questionDTO);


        redirectAttributes.addFlashAttribute("result", qno);
        return "redirect:/questions";
         }


//    @GetMapping("/questions") //무시
//    public String list(Pageable pageable, Model model, PageRequestDTO pageRequestDTO) {
//        // Set the number of items per page
//        int pageSize = 5;
//        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);
//
//        // Retrieve a Page of news items using the NewsService
//        Page<Question> questionPage = questionService.list(pageable);
//
//        // Ensure page number is not less than 1
//        if (pageRequestDTO.getPage() < 1) {
//            pageRequestDTO.setPage(1);
//        }
//
//        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
//
//        // Update the pageSize to match the actual number of items in the responseDTO
//        pageSize = responseDTO.getDtoList().size();
//        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);
//
//        // Retrieve a Page of news items using the updated pageSize
//        questionPage = questionService.list(pageable);
//
//        // Add the Page of news items to the model
//        model.addAttribute("questionPage", questionPage);
//        model.addAttribute("qList", responseDTO);
//
//        // Return the view name for rendering
//        return "questions";  // questions.html
//    }





}

