package com.hudini.korea.marusingle.util.crawler;

import android.util.Log;

import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by korea on 2018-03-26.
 * Maru util URL is marumaru.in
 */

public class MaruUtil {
    private static final String URL = "https://marumaru.in/?r=home&m=bbs&bid=manga";
    private static final MaruUtil ourInstance = new MaruUtil();
    private static final String MangaURL = "https://marumaru.in/b/manga/";

    private MaruUtil() {
    }

    public static MaruUtil getInstance() {
        return ourInstance;
    }

    /**
     * @param page non negative int
     * @param tag  enumType MaruTag
     * @return List<MaruItem>
     */
    public List<MaruItem> searchPageList(int page, MaruTag tag) {
        String query_p = "&p=" + page;
        List<MaruItem> maruItems = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL + query_p + tag.getTypeQuery()).get();
            Elements elements = doc.select("div.picbox");
            for (Element ele : elements) {
                String img_url = ele.select(" img").attr("src");
                String cat = ele.select(".cat").text();
                String title = ele.select(" a").text().substring(ele.select(".cat").text().length());
                long uId;
                try {
                    uId = extractUid(ele.select(" a").attr("href"));
                }catch (NumberFormatException e){
                    e.printStackTrace();
                    uId = 0;
                }
                MaruItem maruItem = new MaruItem(uId, title, cat, img_url, null);
                maruItems.add(maruItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return maruItems;
    }

    /**
     * @param uId positive long
     * @return List<MaruContentItem>
     * @throws IOException
     */
    public List<MaruContentItem> searchUid(long uId) {
        List<MaruContentItem> contentItems = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(MangaURL + uId).get();
            Log.d("searchUID","uid : "+uId);
            Elements elements = doc.select("#vContent div").select("a");
            Log.d("searchUID","elements : "+elements.size());
            for (Element ele : elements) {
                if (!ele.attr("target").isEmpty() && ele.attr("class").isEmpty()) {
                    String chapterTitle = ele.text();

                    String chapterUrl = ele.select("a").attr("href");
                    contentItems.add(new MaruContentItem(uId, chapterTitle, chapterUrl));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentItems;
    }

    private long extractUid(String href) {
        int start = href.length();
        for(int i=href.length()-1;i>=0;i--){
            if(href.charAt(i)=='='||href.charAt(i)=='/'){
                break;
            }
            start--;
        }
        Log.d("href", "indexOf" + href.substring(start));
        return Long.parseLong(href.substring(start));
    }
}
