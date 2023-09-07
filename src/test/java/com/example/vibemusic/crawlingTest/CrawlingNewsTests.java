package com.example.vibemusic.crawlingTest;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.repository.NewsRepository;
import com.example.vibemusic.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class CrawlingNewsTests {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;


    @Test
    public void crawlingNews1() {
        try {
            Long count = 1L; // 초기값 설정
            for (int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements titles = doc.select("div.w_content_sublist div.w_news_list li a div.w_nwl_text h3.nwl_title");

                log.info("title : {}", titles);

                for (Element title : titles) {
                    String newsText = title.text();

                    // 생성한 News 객체에 값을 설정
                    News news = new News();
                    news.setNTitle(newsText);
                    // 다른 필요한 정보들도 설정

                    newsRepository.save(news);
                    count++;  // count 증가
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void crawlingNews2() {
        try {
            Long count = 1L; // 초기값 설정
            for (int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements contents = doc.select("div.w_content_sublist div.w_news_list li a div.nwl_text");

                log.info("content : {}", contents);

                for (Element content : contents) {
                    String newsText = content.text();

                    Optional<News> byId = newsRepository.findById(count);
                    News news = byId.orElseThrow();

                    news.setNContent(newsText);
                    // 다른 필요한 정보들도 설정

                    newsRepository.save(news);
                    count++;  // count 증가
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void crawlingNews3() {
        try {
            Long count = 1L; // 초기값 설정
            for (int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements images = doc.select("div.w_content_sublist div.w_news_list li a div.w_nwl_image img.lazy.nwl_image");

                if (images != null) {
                    String imgSrc = images.attr("data-src");
                    System.out.println("Image URL: " + imgSrc);
                } else {
                    System.out.println("Image not found on the page.");
                }

                for (Element image : images) {
                    String imgUrl = image.attr("data-src");

                    Optional<News> byId = newsRepository.findById(count);
                    News news = byId.orElseThrow();

                    news.setNImage(imgUrl);

                    newsRepository.save(news);
                    count++;  // count 증가
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void crawlingNews4() {
        try {
            Long count = 1L; // 초기값 설정
            for (int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements dateNtimes = doc.select("div.w_content_sublist div.w_news_list li a div.w_nwl_text div.w_nwl_info div.nwl_subtext");

                if (dateNtimes != null) {
                    String nRegDate = dateNtimes.text(); // 날짜 정보 추출
                    System.out.println("nRegDate ==========> " + nRegDate);
                } else {
                    System.out.println("Date not found on the page.");
                }

                for (Element dateNtime : dateNtimes) {
                    String dateString = dateNtime.text();

                    Optional<News> byId = newsRepository.findById(count);
                    News news = byId.orElseThrow();

                    news.setNRegDate(dateString);
                    // 다른 필요한 정보들도 설정

                    newsRepository.save(news);
                    count++;  // count 증가
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void crawlingNewsDetail1() {

        try{
            Long count = 1L; // 초기값 설정
            for(int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements newsDetails = doc.select("main.w_content div.w_content_sublist div.w_ct_middle div.w_ctm_left div.w_news_list ul li.news_list_menu a.nwl_link");

                log.info("newsDetails : {}", newsDetails);

                if (newsDetails != null) {
                    String newsDetailHref = newsDetails.attr("href");
                    System.out.println("FullNews URL: " + newsDetailHref);
                } else {
                    System.out.println("Full news not found on the page.");
                }

                for (Element newsDetail : newsDetails) {
                    String newsDetailUrl = "https://ent.sbs.co.kr" + newsDetail.attr("href");

                    Optional<News> byId = newsRepository.findById(count);
                    News news = byId.orElseThrow();

                    news.setNNewsLinks(newsDetailUrl);

                    newsRepository.save(news);
                    count++;  // count 증가
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void crawlingNewsDetail2() {
        List<News> newsList = newsRepository.findAll();

        for (News news : newsList) {
            String newsLink = news.getNNewsLinks();
            String newsContent = crawlNewsContent(newsLink);

            if (newsContent != null && !newsContent.isEmpty()) {
                news.setNFullNews(newsContent);
                newsRepository.save(news);
            }
        }
    }

    private String crawlNewsContent(String newsLink) {
        try {
            Document doc = Jsoup.connect(newsLink).get();
            Elements paragraphs = doc.select("div.w_ctma_text p");

            StringBuilder newsContentBuilder = new StringBuilder();

            for (Element paragraph : paragraphs) {
                newsContentBuilder.append(paragraph.text());
                newsContentBuilder.append("<br>");
            }

            String fullNews = newsContentBuilder.toString();
            return fullNews;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}