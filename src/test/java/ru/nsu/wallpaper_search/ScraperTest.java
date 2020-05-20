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
import java.util.concurrent.TimeUnit;

public class ScraperTest {
    private void printResult(List<PicCell> pics) {
        pics.forEach(cell -> {
            System.out.println(cell.thumb);
            cell.originals.forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    public void jsoupWebsiteTest() throws IOException {
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
    }

    @Test
    public void accessPage() throws IOException {
        var doc = Extractor.accessPicturesPage("1920x1080", "muffin");
    }

    @Test
    public void accessPics() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        var pics = new Scraper().respondWithQuery("1920x1080", "zelda");

        Assert.assertNotEquals(pics.size(), 0);
        printResult(pics);
    }

    @Test
    public void invalidResolution() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        var pics = new Scraper().respondWithQuery("10x1000", "memes");

        Assert.assertEquals(pics.size(), 0);
    }

    @Test
    public void inconsistentRequest() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        var pics = new Scraper().respondWithQuery("1920x1080", "аавауаупвпивам");
        Assert.assertNotEquals(pics.size(), 0);
        printResult(pics);
    }

    @Test
    public void cyrillicRequest() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        var pics = new Scraper().respondWithQuery("1920x1080", "автокары");

        Assert.assertNotEquals(pics.size(), 0);
        printResult(pics);
    }
}
