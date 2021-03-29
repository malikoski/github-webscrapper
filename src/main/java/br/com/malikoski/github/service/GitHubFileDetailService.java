package br.com.malikoski.github.service;

import br.com.malikoski.github.api.dto.GithubResponse;
import br.com.malikoski.github.config.ConfigAppProperties;
import br.com.malikoski.github.core.GithubFile;
import br.com.malikoski.github.core.GithubHelper;
import br.com.malikoski.github.core.HttpConnection;
import br.com.malikoski.github.core.ParserHtml;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubFileDetailService {

    private final HttpConnection httpConnection;
    private final ParserHtml parserHtml;
    private final ConfigAppProperties properties;
    private final GithubHelper githubHelper;


    @Async("poolExecutorScrapper")
    public CompletableFuture<List<GithubResponse>> getResultsRepo(String uri) {
        return getFiles(uri, new LinkedList<>())
                .thenApply(githubFiles -> githubFiles.stream()
                        .collect(Collectors.groupingBy(g -> g.getExtension()))
                        .entrySet()
                        .stream()
                        .map(entry -> {
                            return GithubResponse.builder()
                                    .extension(entry.getKey())
                                    .count(entry.getValue().size())
                                    .lines(getTotalLinesByExtension(entry))
                                    .bytes(getTotalBytesByExtension(entry))
                                    .build();
                        }).collect(Collectors.toList())
                );
    }

    private CompletableFuture<List<GithubFile>> getFiles(String uri, final List<GithubFile> githubFiles) {
        return CompletableFuture.supplyAsync(() -> httpConnection.connect(uri).body())
                .thenCompose(content -> {

                    if (isFile(uri)) {
                        githubFiles.add(parserHtml.parseFileDetails(content));
                        return CompletableFuture.completedFuture(githubFiles);
                    }

                    CompletableFuture<List<GithubFile>> files = new CompletableFuture<>(), allFiles = files;
                    var uris = parserHtml.parseUris(content);

                    for (String u : uris) {
                        allFiles = getFiles(u, githubFiles);
                    }
                    files.complete(githubFiles);
                    return allFiles;
                }).whenComplete((files, th) -> {
                    if (th != null) {
                        throw new RuntimeException(th);
                    }
                });
    }


    private boolean isFile(String url) {
        return url.contains(ParserHtml.STRING_BLOB);
    }

    private GithubResponse buildResponse(Map.Entry<String, List<GithubFile>> entry) {
        return GithubResponse.builder()
                .extension(entry.getKey())
                .count(entry.getValue().size())
                .lines(getTotalLinesByExtension(entry))
                .bytes(getTotalBytesByExtension(entry))
                .build();
    }

    private GithubFile setTextAndSize(GithubFile githubFile) {
        var response = httpConnection.connect(githubFile.getUrl()).body();
        final byte[] utf8Bytes = response.getBytes(StandardCharsets.UTF_8);

        var bytes = response.getBytes(StandardCharsets.UTF_8);
        try {
            githubFile.setBytes(utf8Bytes.length);
        } catch (NumberFormatException ex) {
            githubFile.setBytes(0);
        }
        githubFile.setLines(response.lines().count());
        githubFile.setExtension(githubHelper.getExtensionByString(githubFile.getName()).orElse(githubFile.getName()));

        return githubFile;
    }

    private long getTotalLinesByExtension(Map.Entry<String, List<GithubFile>> entry) {
        return entry.getValue().stream()
                .mapToLong(v -> v.getLines())
                .sum();
    }

    private long getTotalBytesByExtension(Map.Entry<String, List<GithubFile>> entry) {
        return entry.getValue().stream()
                .mapToLong(v -> v.getBytes())
                .sum();
    }
}
