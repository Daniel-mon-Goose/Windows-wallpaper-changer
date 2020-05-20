package ru.nsu.wallpaper_search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.wallpaper_search.scraper.Extractor;

import java.io.IOException;

public class ScraperTest {
    @Test
    public void jsoupWebsiteTest() {
        try {
            Document doc = Jsoup.connect("https://wallhaven.cc/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();

            Element firstLine = doc.selectFirst("div.more-feat").child(0);

            Assert.assertNotEquals(firstLine.children().size(), 0);

            for (var elem: firstLine.children()) {
                var dat = elem.selectFirst("img").attr("src");
                System.out.println(dat.replaceFirst("th", "w")
                        .replaceFirst("small", "full")
                        .replaceAll("(.{6})\\.jpg", "wallhaven-$1.jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void accessPage() throws IOException {
        try {
            var doc = Extractor.accessPicturesPage("1920x1080", "muffin");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
