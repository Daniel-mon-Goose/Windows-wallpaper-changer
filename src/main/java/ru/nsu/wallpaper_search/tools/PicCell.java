package ru.nsu.wallpaper_search.tools;

import java.util.ArrayList;
import java.util.List;

public class PicCell {
    private String thumb;
    private List<String> originals;

    public PicCell(String thumb) {
        this.thumb = thumb;
        originals = new ArrayList<>();
    }

    public String getThumb() {
        return thumb;
    }

    public List<String> getOriginals() {
        return originals;
    }

    public void addOriginal(String link) {
        originals.add(link);
    }
}
