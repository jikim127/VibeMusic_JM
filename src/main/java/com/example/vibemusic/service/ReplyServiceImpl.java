package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.ReplyDTO;
import com.example.vibemusic.repository.MusicRepository;
import com.example.vibemusic.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final MusicRepository musicRepository;


    @Override
    public PageResponseDTO<ReplyDTO> replyListWithPaging(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("rno");

        Page<Reply> result = replyRepository.searchAll(types, keyword, pageable);

        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, dtoList, (int)result.getTotalElements());

        return pageResponseDTO;
    }

    @Override
    public ReplyDTO read(Long rno){
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow();
        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public Long register(ReplyDTO replyDTO) {
        Optional<Music> byId = musicRepository.findById(replyDTO.getNo());
        Music music = byId.orElseThrow();
        Reply reply = Reply.builder()
                .music(music)
                .rreplyer(replyDTO.getRreplyer())
                .r_replyText(replyDTO.getR_replyText())
                .build();

        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Optional<Reply> byId = replyRepository.findById(replyDTO.getRno());
        Reply reply = byId.orElseThrow();
        reply.change(replyDTO.getR_replyText());
        replyRepository.save(reply);

    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public Page<Reply> replyListOfMusic(Long no, Pageable pageable) {

        Page<Reply> result = replyRepository.replyListOfMusic(no, pageable);

        return result;
    }

    @Override
    public PageResponseDTO getListOfMusic(Long no, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() -1, pageRequestDTO.getSize(), Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.replyListOfMusic(no, pageable);

        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<ReplyDTO>withAll().pageRequestDTO(pageRequestDTO).dtoList(dtoList).total((int)result.getTotalElements()).build();
    }
}
