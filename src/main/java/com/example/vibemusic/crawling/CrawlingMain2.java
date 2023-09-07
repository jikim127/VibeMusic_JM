package com.example.vibemusic.crawling;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;

public class CrawlingMain2 {

    public static void main(String[] args) {
        File directory = new File("C:\\Users\\Admin\\Downloads\\Music");
        extractMp3Metadata(directory);
    }

    public static void extractMp3Metadata(File directory) {
        if (!directory.isDirectory()) {
            System.out.println("Invalid directory path.");
            return;
        }

        File[] mp3Files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

        if (mp3Files == null || mp3Files.length == 0) {
            System.out.println("No mp3 files found in the directory.");
            return;
        }

        for (File mp3File : mp3Files) {
            try {
                AudioFile audioFile = AudioFileIO.read(mp3File);
                Tag tag = audioFile.getTag();

                // Extracting metadata
                String title = tag.getFirst(FieldKey.TITLE);
                String artist = tag.getFirst(FieldKey.ARTIST);
                String album = tag.getFirst(FieldKey.ALBUM);
                String genre = tag.getFirst(FieldKey.GENRE);
                MP3AudioHeader audioHeader = (MP3AudioHeader) audioFile.getAudioHeader();
                String duration = audioHeader.getTrackLengthAsString();

                System.out.println("File: " + mp3File.getName());
                System.out.println("Title: " + title);
                System.out.println("Artist: " + artist);
                System.out.println("Album: " + album);
                System.out.println("Genre: " + genre);
                System.out.println("Duration: " + duration);
                System.out.println("--------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
