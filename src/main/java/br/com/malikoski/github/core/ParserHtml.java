package br.com.malikoski.github.core;

import br.com.malikoski.github.config.ConfigAppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParserHtml {

    public static final String STRING_BLOB = "/blob/";
    public static final String MEGABYTE = "MB";
    public static final String KYLOBYTE = "KB";
    public static final String TITLE_FILE_MODE = "title=\"File mode\"";
    public static final String LINES = "lines";

    private final ConfigAppProperties properties;
    private final HttpConnection httpConnection;
    private final GithubHelper githubHelper;


    public List<String> parseUris(String contentHtml) {

        List<String> uris = new ArrayList<>();
        var dirs = contentHtml.split("octicon-file-directory");

        dirs[0] = null;

        if (dirs.length > 1) {
            var files = dirs[dirs.length - 1].split("octicon-file ");
            dirs[dirs.length - 1] = files[0];
            files[0] = null;

            for (int i = 1; i < dirs.length; i++) {
                dirs[i] = dirs[i].substring(dirs[i].indexOf("href=\""));
                dirs[i] = dirs[i].substring(dirs[i].indexOf("\"", 1) + 2);
                dirs[i] = dirs[i].substring(0, dirs[i].indexOf(">") - 1);
                uris.add(properties.getUrlBase() + "/" + dirs[i]);
            }

            for (int i = 1; i < files.length; i++) {
                files[i] = files[i].substring(files[i].indexOf("href=\""));
                files[i] = files[i].substring(files[i].indexOf("\"", 1) + 2);
                files[i] = files[i].substring(0, files[i].indexOf(">") - 1);
                uris.add(properties.getUrlBase() + "/" + files[i]);
            }
        } else {
            var files = contentHtml.split("octicon-file ");
            if (files.length > 1) {
                files[0] = null;

                for (int i = 1; i < files.length; i++) {
                    files[i] = files[i].substring(files[i].indexOf("href=\""));
                    files[i] = files[i].substring(files[i].indexOf("\"", 1) + 2);
                    files[i] = files[i].substring(0, files[i].indexOf(">") - 1);
                    uris.add(properties.getUrlBase() + "/" + files[i]);
                }
            }

        }

        return uris;
    }


    public GithubFile parseFileDetails(String content) {

        var indexName = content.indexOf("final-path");
        var name = content.substring(content.indexOf(">", indexName) + 1, content.indexOf("<", indexName));
        var extension = name.substring(name.lastIndexOf("."));

        String textLinesAndBytes = content.substring(content.indexOf("text-mono f6"));

        textLinesAndBytes = textLinesAndBytes.substring(textLinesAndBytes.indexOf(">") + 1)
                .substring(0, textLinesAndBytes.indexOf("</div>"));

        if (textLinesAndBytes.contains("file-info-divider")) {
            textLinesAndBytes = textLinesAndBytes.replace("<span class=\"file-info-divider\"></span>", "|");
        }

        var githubFile = transformLinesAndBytes(textLinesAndBytes);
        githubFile.setName(name);
        githubFile.setExtension(extension);

        return githubFile;
    }


    private GithubFile transformLinesAndBytes(String content) {

        var values = content.split(Pattern.quote("|"));
        var githubFile = GithubFile.builder().build();

        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].trim();
            if (values[i].contains(TITLE_FILE_MODE)) {
                continue;
            }
            if (values[i].contains(LINES)) {
                String value = values[i].substring(0, values[i].indexOf(' '));
                githubFile.setLines(Long.valueOf(value));
            } else {
                long multiplier = 1L;
                String value = values[i].substring(0, values[i].indexOf(' '));
                String unit = values[i].substring(values[i].indexOf(' ') + 1, values[i].indexOf('\n'));
                switch (unit) {
                    case MEGABYTE:
                        multiplier = multiplier * 1024L * 1024L;
                    case KYLOBYTE:
                        multiplier = multiplier * 1024L;
                        break;
                    default:
                        multiplier = 1;
                        break;
                }
                githubFile.setBytes((multiplier * Double.valueOf(value).longValue()));
            }
        }
        return githubFile;
    }
}
