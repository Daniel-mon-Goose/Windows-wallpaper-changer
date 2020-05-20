package ru.nsu.wallpaper_search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.wallpaper_search.scraper.Extractor;
import ru.nsu.wallpaper_search.scraper.Scraper;
import ru.nsu.wallpaper_search.tools.PicCell;

import java.io.IOException;
import java.util.List;

public class ScraperTest {
    private void printResult(List<PicCell> pics) {
        pics.forEach(cell -> {
            System.out.println(cell.thumb);
            cell.originals.forEach(System.out::println);
            System.out.println();
        });
    }

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

    @Test
    public void accessPics() throws IOException {
        var heyListen = new Scraper();
        var pics = heyListen.respondWithQuery("1920x1080", "memes");

        Assert.assertNotEquals(pics.size(), 0);
        printResult(pics);
    }
}
