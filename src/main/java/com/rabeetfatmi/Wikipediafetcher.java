package com.rabeetfatmi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * In the English language wikipedia, repeatedly clicking on the first link in the main body of an article will eventually lead to the Philosophy page in a large fraction of cases. This phenomena is documented here: https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
 * This program attempts that logic for 100 random pages and tries to answer the following questions using JSoup:
 * What is the longest path that eventually reaches philosophy?
 * Are there other common sinks, like the Philosophy page?
 */
public class Wikipediafetcher {

    private static final String RANDOM_URL = "https://en.wikipedia.org/wiki/Special:Random";

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect(RANDOM_URL).get();
        System.out.println(doc.select("p").get(0));
    }
}
