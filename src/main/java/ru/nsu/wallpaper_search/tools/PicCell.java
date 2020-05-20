package ru.nsu.wallpaper_search.tools;

import java.util.ArrayList;
import java.util.List;

public class PicCell {
    public String thumb;
    public List<String> originals;

    public PicCell(String thumb) {
        this.thumb = thumb;
        originals = new ArrayList<>();
    }

    public void addOriginal(String link) {
        originals.add(link);
    }
}

