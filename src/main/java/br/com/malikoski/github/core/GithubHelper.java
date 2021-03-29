package br.com.malikoski.github.core;

import br.com.malikoski.github.config.ConfigAppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GithubHelper {

    public static final String BLOB_PATH = "/blob/";
    public static final String TREE_PATH = "/tree/";
    public static final String SLASH = "/";
    public static final String DOT = ".";

    private final ConfigAppProperties properties;
    private final HttpConnection httpConnection;

    public String createGithubUrl(String owner, String repo) {
        return properties.getUrlBase() + SLASH + owner + SLASH + repo;
    }

    public Optional<String> getExtensionByString(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public boolean isPathFolder(String url) {
        return url.contains(TREE_PATH);
    }

    public String getFilaname(String uri) {
        return uri.substring(uri.lastIndexOf(SLASH), uri.length());
    }

    public String getExtension(String uri) {
        var indexSlash = uri.lastIndexOf(SLASH);
        return uri.lastIndexOf(".", indexSlash) == -1
                ? uri.substring(indexSlash)
                : uri.substring(uri.indexOf(DOT, indexSlash), uri.length());
    }
}
