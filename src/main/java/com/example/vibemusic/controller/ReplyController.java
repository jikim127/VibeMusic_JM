package com.example.vibemusic.controller;

import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.ReplyDTO;
import com.example.vibemusic.service.ReplyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Slf4j
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @ApiOperation(value = "Replies POST", notes = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        log.info("no : {}", replyDTO.getNo());

        Map<String, Long> resultMap = new HashMap<>();

        Long rno = replyService.register(replyDTO);

        resultMap.put("rno",rno);

        return resultMap;
    }

    @ApiOperation(value = "Replies of Music", notes = "Get 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value = "/list/{no}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("no") Long no, PageRequestDTO pageRequestDTO){
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfMusic(no,pageRequestDTO);

        return responseDTO;
    }

    @ApiOperation(value = "Read Reply", notes = "GET 방식으로 특정 댓글 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    @ApiOperation(value = "Delete Reply", notes = "DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno){
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno",rno);
        return resultMap;
    }

    @ApiOperation(value = "Modify Reply", notes = "Put 방식으로 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Map<String, Long> remove(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }



}
