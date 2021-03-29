package br.com.malikoski.github.core;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GithubFile {

    private String name;
    private String extension;
    private String url;

    @Builder.Default
    private List<GithubFile> files = new ArrayList<>();

    private long bytes;

    private long lines;

    public void addChilds(List<GithubFile> files) {
        this.files.addAll(files);
    }

    public void addChild(GithubFile file) {
        files.add(file);
    }
}
