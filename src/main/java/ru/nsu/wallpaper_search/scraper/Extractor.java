package ru.nsu.wallpaper_search.scraper;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.nsu.wallpaper_search.tools.PicCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Extractor {
    private static final String USERAGENT = "Mozilla/5.0 (Android 8.1.0; Mobile; rv:61.0) Gecko/61.0 Firefox/61.0";
    private static final String REFERRER = "http://www.google.com";
    private static final String YANDEX = "https://yandex.ru/images/search?text=%s&isize=eq&iw=%s&ih=%s";

    private static final String SELECTOR = "div.serp-item.serp-item_type_search";
    private static final String HTTPS = "https:";
    private static final String URL = "url";
    private static final String PREVIEW = "preview";
    private static final String SERP = "serp-item";
    private static final String ORIGIN = "origin";
    private static final String THUMB = "thumb";
    private static final String BEM = "data-bem";

    private static final Map<String, String> cookies = new HashMap<>();

    private Extractor() {
        throw new IllegalStateException("Utility class");
    }

    public static Document getDoc(String link, boolean useCookies) throws IOException {
        var launchPad = Jsoup.connect(link).userAgent(USERAGENT).referrer(REFERRER);

        if (!useCookies) {
            return launchPad.get();
        }

        var connection = launchPad.cookies(cookies).execute();
        cookies.putAll(connection.cookies());
        return connection.parse();
    }

    public static Document accessPicturesPage(String resolution, String theme) throws IOException {
        var nums = resolution.split("x");
        String page = String.format(YANDEX, theme, nums[0], nums[1]);
        return getDoc(page, true);
    }

    public static List<PicCell> getPictures(Document picsPage) {
        var result = new ArrayList<PicCell>();
        Elements pics = picsPage.select(SELECTOR);

        for (Element pic: pics) {
            var picData = new JSONObject(pic.attr(BEM)).getJSONObject(SERP);

            var smallPicLink = picData.getJSONObject(THUMB).getString(URL);
            if (!smallPicLink.startsWith(HTTPS)) {
                smallPicLink = HTTPS.concat(smallPicLink);
            }
            var cell = new PicCell(smallPicLink);

            var bigPicPreviews = picData.getJSONArray(PREVIEW);
            for (var preview: bigPicPreviews) {
                String bigPickLink;
                try {
                    bigPickLink = ((JSONObject) preview).getJSONObject(ORIGIN).getString(URL);
                } catch (JSONException e) {
                    bigPickLink = ((JSONObject) preview).getString(URL);
                }
                cell.addOriginal(bigPickLink);
            }
            result.add(cell);
        }

        return result;
    }
}
