package ru.nsu.wallpaper_search.scraper;

import ru.nsu.wallpaper_search.tools.PicCell;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class Scraper {
    public List<PicCell> respondWithQuery(String size, String theme) throws IOException {
        theme = theme.trim();

        var doc = Extractor.accessPicturesPage(size, theme);
        var result = Extractor.getPictures(doc);
        result.addAll(Extractor.getPictures(doc));

        doc = Extractor.accessPicturesPage(size, theme.concat(" wallpaper"));
        result.addAll(Extractor.getPictures(doc));

        if (result.isEmpty() && doc.text().contains("автоматические")) {
            throw new ConnectException("Scraping blocked");
        }

        return result;
    }
}
