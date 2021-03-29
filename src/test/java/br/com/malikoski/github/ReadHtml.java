package br.com.malikoski.github;

import lombok.Builder;
import lombok.Data;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReadHtml {

    public static void main(String[] args) throws IOException {

        String urlToRead = "https://github.com/malikoski/playmusic";
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

       // System.out.println(result);

        var filesMap = new LinkedHashMap<Integer, GitHubFile>();

        var pos = 0;
        var key = 0;
        System.out.println(result.length());
        while (pos <= result.length()) {
            var posStartTagLinkFileName = result.indexOf("a class=\"js-navigation-open", pos);
            if (posStartTagLinkFileName == -1)
                break;

            var postEndBracketsStartTagLinkFileName = result.indexOf(">", posStartTagLinkFileName);
            var postEndTagLinkFileName = result.indexOf("</a>", postEndBracketsStartTagLinkFileName);

            key++;
            filesMap.put(key, GitHubFile.builder()
                    .name(result.substring(postEndBracketsStartTagLinkFileName + 1, postEndTagLinkFileName))
                    .build());

//            System.out.println(result.substring(postEndBracketsStartTagLinkFileName + 1, postEndTagLinkFileName));

            pos = postEndTagLinkFileName + 1;
            System.out.println(pos);

        }

        filesMap.forEach((k, v) -> System.out.println(v));


    }

    @Data
    @Builder
    static class GitHubFile {
        private String name;
        private String url;
        private Map<Integer, GitHubFile> files = new LinkedHashMap<>();

        public void addFileChild(Integer key, GitHubFile file) {
            files.put(key, file);
        }
    }


}
