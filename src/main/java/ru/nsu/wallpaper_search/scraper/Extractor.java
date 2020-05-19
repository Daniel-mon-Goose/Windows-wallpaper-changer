package ru.nsu.wallpaper_search.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Extractor {
    private static final String USERAGENT = "Mozilla/5.0 (Android 8.1.0; Mobile; rv:61.0) Gecko/61.0 Firefox/61.0";
    private static final String REFERRER = "http://www.google.com";
    private static final String YANDEX = "https://yandex.ru/images/search?text=%s&isize=eq&iw=%s&ih=%s";

    private static Document getDoc(String link) throws IOException {
        return Jsoup.connect(link).userAgent(USERAGENT).referrer(REFERRER).get();
    }

    public static Document accessPicturesPage(String resolution, String theme) throws IOException {
        var nums = resolution.split("x");
        String page = String.format(YANDEX, theme, nums[0], nums[1]);
        return getDoc(page);
    }
}
