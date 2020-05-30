package ru.nsu.wallpaper_search;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.wallpaper_search.scraper.Extractor;
import ru.nsu.wallpaper_search.scraper.Scraper;
import ru.nsu.wallpaper_search.tools.PicCell;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class ScraperTest {
    private void printResult(List<PicCell> pics) {
        pics.forEach(cell -> {
            System.out.println(cell.getThumb());
            cell.getOriginals().forEach(System.out::println);
            System.out.println();
        });
    }

    private int scrapingAutomatedVersion(String size, String theme) throws IOException, InterruptedException {
        var pics = new Scraper().respondWithQuery(size, theme);
        printResult(pics);

        return pics.size();
    }

    @Test
    public void jsoupWebsiteTest() throws IOException {
        Document doc = Extractor.getDoc("https://wallhaven.cc/", false);

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
    public void accessPics() throws IOException, InterruptedException {
        try {
            int size = scrapingAutomatedVersion("1920x1080", "zelda");
            Assert.assertNotEquals(0, size);
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void invalidResolution() throws IOException, InterruptedException {
        try {
            int size = scrapingAutomatedVersion("10x1000", "memes");
            Assert.assertEquals(0, size);
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void inconsistentRequest() throws IOException, InterruptedException {
        try {
            int size = scrapingAutomatedVersion("1920x1080", "аавауаупвпивам");
            Assert.assertNotEquals(0, size);
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void cyrillicRequest() throws IOException, InterruptedException {
        try {
            int size = scrapingAutomatedVersion("1600x900", "автокары");
            Assert.assertNotEquals(0, size);
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
        }
    }
}
