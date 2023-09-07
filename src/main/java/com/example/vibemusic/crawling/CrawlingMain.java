package com.example.vibemusic.crawling;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CrawlingMain {

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

        Parser parser = new AutoDetectParser();
        ParseContext context = new ParseContext(); // 이 부분을 추가하여 컨텍스트를 사용할 수도 있습니다. (선택사항)

        for (File mp3File : mp3Files) {
            try (InputStream stream = new FileInputStream(mp3File)) {
                Metadata metadata = new Metadata();
                parser.parse(stream, new BodyContentHandler(), metadata, context);

                // Extracting metadata
                String title = metadata.get("title");
                String artist = metadata.get("artist");
                String genre = metadata.get("genre");
                String duration = metadata.get("duration");

                System.out.println("File: " + mp3File.getName());
                System.out.println("Title: " + title);
                System.out.println("Artist: " + artist);
                System.out.println("Genre: " + genre);
                System.out.println("Duration: " + duration);
                System.out.println("--------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

