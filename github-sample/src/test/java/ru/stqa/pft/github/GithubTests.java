package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("3ec067f49adc4fc763f58b3c2fcee63955fabc3e");
        RepoCommits commits = github.repos()
                .get(new Coordinates.Simple("AnastasiiaBocharnikova", "new_repository")).commits();

        for (RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
