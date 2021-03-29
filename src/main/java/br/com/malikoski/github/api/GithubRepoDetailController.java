package br.com.malikoski.github.api;

import br.com.malikoski.github.api.dto.GithubResponse;
import br.com.malikoski.github.core.GithubHelper;
import br.com.malikoski.github.service.GitHubFileDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@Slf4j
public class GithubRepoDetailController {

    private final GitHubFileDetailService service;
    private final GithubHelper githubHelper;

    @Cacheable(value = "github-get", key = "#owner.concat('-').concat(#repo)")
    @GetMapping("/{owner}/repo/{repo}")
    public List<GithubResponse> getDetails(@PathVariable("owner") String owner, @PathVariable("repo") String repo) throws IOException, InterruptedException, ExecutionException {
        long start = System.nanoTime();
        log.info("Start fetch repo: owner = {},  repo = {}", owner, repo);

        CompletableFuture<List<GithubResponse>> content = service.getResultsRepo(githubHelper.createGithubUrl(owner, repo));

        CompletableFuture.allOf(content).join();
        var result = content.get();

        long end = System.nanoTime();
        long elapsedTime = end - start;

        long elapsedTimeInSecond = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        log.info("End fetch repo: owner = {},  repo = {} - Time: {} seconds", owner, repo, elapsedTimeInSecond);

        return result;
    }

}
