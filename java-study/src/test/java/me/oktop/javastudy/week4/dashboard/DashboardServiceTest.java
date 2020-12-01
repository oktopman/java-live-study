package me.oktop.javastudy.week4.dashboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DashboardServiceTest {

    @Value("${token.value}")
    private String token;

    @Test
    @DisplayName("repository issue의 댓글 가져오기")
    void get_github_repo_issue() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();
        GHRepository ghRepository = gitHub.getRepository("oktopman/java-live-study");
        GHIssue issue = ghRepository.getIssue(2);
        List<GHIssueComment> comments = issue.getComments();
        assertThat(comments.get(0).getBody()).isEqualTo("댓글2입니다~");
    }


}