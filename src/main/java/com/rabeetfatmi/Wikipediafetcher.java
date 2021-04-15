package com.rabeetfatmi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * In the English language wikipedia, repeatedly clicking on the first link in the main body of an article will eventually lead to the Philosophy page in a large fraction of cases. This phenomena is documented here: https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
 * This program attempts that logic for 100 random pages and tries to answer the following questions using JSoup:
 * What is the longest path that eventually reaches philosophy?
 * Are there other common sinks, like the Philosophy page?
 */
public class Wikipediafetcher {

    private static final String RANDOM_URL = "https://en.wikipedia.org/wiki/Special:Random";
    private static final String PHILOSOPHY_URL = "https://en.wikipedia.org/wiki/Philosophy";

    public static void main(String[] args) throws Exception {
        run("https://en.wikipedia.org/wiki/Simplified_Chinese_characters");
    }

    /**
     * Start the philosophy loop based on the given url
     * @param url
     */
    private static void run(String url) throws IOException {
        int i = 0;
        while (i++ < 100 && !url.equals(PHILOSOPHY_URL)) {
            System.out.println(i + ": " + url);
            Document doc = Jsoup.connect(url).get();
            url = getFirstValidLink(doc);
        }
        System.out.println(i + ": " + url);
    }

    /**
     * Given doc return first non-parenthesized non-etalicized link
     * Should be a valid wiki link (contain wiki - not wikitionary)
     * @param doc jsoup doc
     * @return first non-parenthesized non-etalicized link
     */
    private static String getFirstValidLink(Document doc) {
        Elements linksInsideParagraphs = doc.select("p > a");
        for (Element link : linksInsideParagraphs) {
            if (isValidLink(link)) return link.absUrl("href");
        }
        return null;
    }

    private static boolean isValidLink(Element link) {
        String text = link.toString();
        return !text.contains("Greek") && !text.contains("Latin") && !text.contains("wikitionary");
    }

}
