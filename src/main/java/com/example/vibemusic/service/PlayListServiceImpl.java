package com.example.vibemusic.service;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.repository.MemberRepository;
import com.example.vibemusic.repository.MusicRepository;
import com.example.vibemusic.repository.PlayListRepository;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;
    private final MusicRepository musicRepository;
    private final MemberRepository memberRepository;

    /**
     * *********** PlayList 생성 부분 ***************
     * PlayList 목록 보여주기
     */
    public List<PlayList> getPlaylist(@AuthenticationPrincipal MemberSecurityDTO authenticatedUser) {
        Member member = memberRepository.findByMid(authenticatedUser.getMid());
        log.info("===member=== : {}",member);
        return playListRepository.findByMember_Mid(member);
    }

    /**
     PlayList 추가하기
     */
    public PlayList addPlaylist(@RequestParam String plName, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser) {
        Member member = memberRepository.findByMid(authenticatedUser.getMid());

        PlayList playListItem = PlayList.builder()
                .member(member)
                .plName(plName)
                .build();

        playListRepository.save(playListItem);

        return playListItem;
    }

    /**
     PlayList 목록 삭제하기
     */
    public void removePlaylist(Long plNo) {

        playListRepository.deleteById(plNo);
    }

    /**
     * *********** PlayList에 노래담기 부분 ***************
     곡 목록보기
     */
//    public List<Music> getMusicsInPlayList(Long plNo){
//        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
//        return playList.getMusics();
//    }

    /**
     한곡 추가하기
     */
    public void addMusicToPlayList(Long plno, Long no) {
        log.info("plno : {}", plno);
        log.info("no : {}", no);

        Optional<PlayList> playListById = playListRepository.findById(plno);
        PlayList playList = playListById.orElseThrow();

        Optional<Music> byId = musicRepository.findById(no);
        Music music = byId.orElseThrow();

        log.info("playList.getMusics() : {}" , playList.getMusics().add(music));
        log.info("music.getNo() : {}", music.getNo());

        playListRepository.save(playList);
    }

    /**
     여러곡 추가하기
     */
//    public void addAllToPlayList(Long plNo, List<Long> nos) {
//        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
//        List<Music> musicList = new ArrayList<>();
//            //nos.add();
//
//        playList.getMusics().addAll(musicList);
//        playListRepository.save(playList);
//    }

    /**
     곡 삭제하기
     */
    public void removeMusicFromPlayist(Long plNo, Long no) {
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        Music music = musicRepository.findById(no).orElseThrow(EntityNotFoundException::new);

        playList.removeMusic(music);
        playListRepository.save(playList);
    }
}