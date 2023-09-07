package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.service.ChartService;
import com.example.vibemusic.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class ChartController {

    private final MusicService musicService;
    private final MessageSource messageSource;
    private final ChartService chartService;



    @GetMapping("/chart")
    public String displayMusicList(Model model) {
        List<Music> musicList = chartService.getAllMusicSortedByPlayCount();
        model.addAttribute("music", musicList);
        return "chart"; // Assuming you have a Thymeleaf template named "music-list.html"
    }
}
