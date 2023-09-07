package com.example.vibemusic.service;

import com.example.vibemusic.domain.EventBoard;
import com.example.vibemusic.dto.EventBoardDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.repository.EventBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class EventBoardServiceImpl implements EventBoardService{

    private final ModelMapper modelMapper;
    private final EventBoardRepository eventBoardRepository;

    @Override
    public Long register(EventBoardDTO eventBoardDTO) {

        EventBoard eventBoard = modelMapper.map(eventBoardDTO, EventBoard.class);

        Long ebno = eventBoardRepository.save(eventBoard).getEbno();

        return ebno;
    }

    @Override
    public EventBoardDTO select(Long ebno) {

        Optional<EventBoard> s = eventBoardRepository.findById(ebno);

        EventBoard eventBoard = s.orElseThrow();

        EventBoardDTO eventBoardDTO = modelMapper.map(eventBoard, EventBoardDTO.class);

        log.info("eventBoardDTO=======>"+eventBoardDTO);

        return eventBoardDTO;
    }

    @Override
    public void modify(EventBoardDTO eventBoardDTO) {

        Optional<EventBoard> m = eventBoardRepository.findById(eventBoardDTO.getEbno());

        EventBoard eventBoard = m.orElseThrow();

        eventBoard.change(eventBoardDTO.getTitle(), eventBoardDTO.getContent());

        eventBoardRepository.save(eventBoard);
    }

    @Override
    public void remove(Long ebno) {

        eventBoardRepository.deleteById(ebno);
    }

    @Override
    public PageResponseDTO<EventBoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("ebno");

        Page<EventBoard> result = eventBoardRepository.searchAll(types, keyword, pageable);

//        log.info("-----------------------------------------------");
//        log.info("getTotalPages = " + result.getTotalPages());
//        log.info("getSize = " + result.getSize());
//        log.info("getTotalElements = " + result.getTotalElements());
//        result.getContent().forEach(eventBoard -> log.info(eventBoard));
//        log.info("------------------------------------------------");

        List<EventBoardDTO> dtoList = result.getContent().stream()
                .map(eventBoard -> modelMapper.map(eventBoard, EventBoardDTO.class)).collect(Collectors.toList());

        PageResponseDTO<EventBoardDTO> pageResponseDTO =
                new PageResponseDTO<>(pageRequestDTO, dtoList, (int) result.getTotalElements());

        return pageResponseDTO;
    }

//    @Override
//    public Page<EventBoard> Elist(Pageable pageable) {
//        return eventBoardRepository.findAll(pageable);
//    }

    @Transactional
    @Override
    public void eViewCount(Long ebno) {
        EventBoard eventBoard = eventBoardRepository.findById(ebno)
                .orElseThrow(() -> new IllegalArgumentException("News not found with ebno: " + ebno));

        int eViewCount = eventBoard.getEViewCount();
        eventBoard.setEViewCount(eViewCount + 1);

        eventBoardRepository.save(eventBoard);
    }

}
