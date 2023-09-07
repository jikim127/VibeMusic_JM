//package com.example.vibemusic.service;
//
//import com.example.vibemusic.domain.Member;
//import com.example.vibemusic.domain.Music;
//import com.example.vibemusic.domain.PlayList;
//import com.example.vibemusic.repository.MemberRepository;
//import com.example.vibemusic.repository.MusicRepository;
//import com.example.vibemusic.repository.PlayListRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityNotFoundException;
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Slf4j
//public class PlayListServiceTests {
//
//    @Autowired
//    private PlayListRepository playListRepository;
//
//    @Autowired
//    private MusicRepository musicRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test  //PlayList 만듬
//    public void addPlaylistTest() {
//
//        Optional<Member> jimin = memberRepository.findById("jimin");
//
//        Member member = jimin.orElseThrow();
//
//        log.info("member : {}", member);
//        PlayList playList = PlayList.builder()
//                .plName("JM's Choice"+1)
//                .member(member)
//                .build();
//
//        PlayList save = playListRepository.save(playList);
//        log.info("save : {}", save);
//    }
//
//    public void addMusicToPlayList(Long plNo, Long no) {
//        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
//        Music music = musicRepository.findById(no).orElseThrow(EntityNotFoundException::new);
//
//        playList.getMusics().add(music);
//        playListRepository.save(playList);
//    }
//
//    @Test  //PlayList 안에 한곡 넣기
//    public void addMusicToPlayListTest() {
////        Long plNo = 1L; // 플레이리스트 번호
////        Long no = 1L; // 곡 번호
//
//        Optional<PlayList> byPlayListId = playListRepository.findById(1L);
//        Optional<Music> byMusicId = musicRepository.findById(3L);
//
//        PlayList playList = byPlayListId.orElseThrow();
//        Music music = byMusicId.orElseThrow();
//
//        playList.getMusics().add(music);
//        playListRepository.save(playList);
//
//        for(int i = 0; i<2; i++){
//            log.info("뭐라도 찍어줘 : {}",playList.getMusics().get(i).getM_title());
//        }
//    }
//
//    //플레이리스트에서 한곡 빼기
//    @Transactional
//    @Test
//    public void removeMusicToPlayListTest() {
//
//        Optional<PlayList> byId = playListRepository.findById(1L);
//        PlayList playList = byId.orElseThrow();
//
//        Optional<Music> byId1 = musicRepository.findById(3L);
//        Music music = byId1.orElseThrow();
//
//        playList.removeMusic(music);
//        playList.getMusics().remove(music);
////        for(int i = 0; i<2; i++) {
////            log.info("playList : {}", playList.getMusics().get(i).getM_title());
////        }
//        playListRepository.delete(playList);
//
//    }
//
//
//    @Test  //PlayList 안에 여러곡 선택 후 넣기
//    public void addAllToPlayListTest() {
//        Long plNo = 1L; // 플레이리스트 번호
//        List<Long> nos = Arrays.asList(1L, 2L, 3L); // 곡 번호들
//
//        addAllToPlayList(plNo, nos);
//    }
//    public void addAllToPlayList(Long plNo, List<Long> nos) {
//        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
//        List<Music> musicList = musicRepository.findAllById(nos);
//
//        playList.getMusics().addAll(musicList);
//        playListRepository.save(playList);
//    }
//
//
//}
