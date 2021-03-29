package br.com.malikoski.github;

import br.com.malikoski.github.core.GithubHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
@EnableAsync
public class ReadGithubRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadGithubRepoApplication.class, args);
    }

    @Autowired
    private GithubHelper githubHelper;
}
