package ru.nsu.wallpaper_search.scraper;

import ru.nsu.wallpaper_search.tools.PicCell;

import java.io.IOException;
import java.util.List;

public class Scraper {
    public List<PicCell> respondWithQuery(String size, String theme) throws IOException {
        var doc = Extractor.accessPicturesPage(size, theme);
        var result = Extractor.getPictures(doc);
        result.addAll(Extractor.getPictures(doc));

        return result;
    }
}
