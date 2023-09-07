package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ChartServiceImpl implements ChartService{


    private final MusicRepository musicRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MusicDTO> DanceGenre(Long no) {
        List<Music> danceMusics = musicRepository.findBymGenre("dance");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        // Sorting musicDTO list based on mPlayCount in ascending order
        musicDTO.sort(Comparator.comparingInt(MusicDTO::getMPlayCount).reversed());

        return musicDTO;
    }

    @Override
    public List<MusicDTO> BalladeGenre(Long no) {
        List<Music> balladeMusics = musicRepository.findBymGenre("Ballade");

        List<MusicDTO> musicDTO = balladeMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        // Sorting musicDTO list based on mPlayCount in ascending order
        musicDTO.sort(Comparator.comparingInt(MusicDTO::getMPlayCount).reversed());

        return musicDTO;
    }

    @Override
    public List<MusicDTO> HipHopGenre(Long no) {
        List<Music> hiphopMusics = musicRepository.findBymGenre("hiphop");

        List<MusicDTO> musicDTO = hiphopMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        // Sorting musicDTO list based on mPlayCount in ascending order
        musicDTO.sort(Comparator.comparingInt(MusicDTO::getMPlayCount).reversed());

        return musicDTO;
    }

    @Override
    public List<MusicDTO> PopGenre(Long no) {
        List<Music> popMusics = musicRepository.findBymGenre("pop");

        List<MusicDTO> musicDTO = popMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        // Sorting musicDTO list based on mPlayCount in ascending order
        musicDTO.sort(Comparator.comparingInt(MusicDTO::getMPlayCount).reversed());

        return musicDTO;
    }


    @Override
    public List<Music> getAllMusicSortedByPlayCount() {
        Sort sortByPlayCountDesc = Sort.by(Sort.Direction.DESC, "mPlayCount");
        return musicRepository.findAll(sortByPlayCountDesc);
    }


    @Override
    public List<MusicDTO> RandomDanceGenre(Long no) {

//        Music randomMusic = musicRepository.findById(no).orElse(null);

        List<Music> danceMusics = musicRepository.findBymGenre("dance");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

//        if (randomMusic != null) {
//            MusicDTO randomMusicDTO = modelMapper.map(randomMusic, MusicDTO.class);
//            musicDTO.add(randomMusicDTO);
//        }

        return musicDTO;
    }
    @Override
    public List<MusicDTO> RandomHipHopGenre(Long no) {

//        Music randomMusic = musicRepository.findById(no).orElse(null);

        List<Music> danceMusics = musicRepository.findBymGenre("hiphop");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

//        if (randomMusic != null) {
//            MusicDTO randomMusicDTO = modelMapper.map(randomMusic, MusicDTO.class);
//            musicDTO.add(randomMusicDTO);
//        }

        return musicDTO;
    }
    @Override
    public List<MusicDTO> RandomPopGenre(Long no) {

//        Music randomMusic = musicRepository.findById(no).orElse(null);

        List<Music> danceMusics = musicRepository.findBymGenre("Pop");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

//        if (randomMusic != null) {
//            MusicDTO randomMusicDTO = modelMapper.map(randomMusic, MusicDTO.class);
//            musicDTO.add(randomMusicDTO);
//        }

        return musicDTO;
    }

    @Override
    public List<MusicDTO> RandomBalladeGenre(Long no) {

//        Music randomMusic = musicRepository.findById(no).orElse(null);

        List<Music> danceMusics = musicRepository.findBymGenre("Ballade");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, com.example.vibemusic.dto.MusicDTO.class))
                .collect(Collectors.toList());

//        if (randomMusic != null) {
//            com.example.vibemusic.dto.MusicDTO randomMusicDTO = modelMapper.map(randomMusic, com.example.vibemusic.dto.MusicDTO.class);
//            musicDTO.add(randomMusicDTO);
//        }

        return musicDTO;
    }
}
