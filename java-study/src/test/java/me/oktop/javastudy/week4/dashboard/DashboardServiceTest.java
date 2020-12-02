package me.oktop.javastudy.week4.dashboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DashboardServiceTest {

    @Value("${token.value}")
    private String token;

    @Test
    @DisplayName("repository issue의 댓글 가져오기")
    void get_github_repo_issue() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();
        GHRepository ghRepository = gitHub.getRepository("oktopman/java-live-study");
        Map<String, Integer> participantMap = generateParticipants(ghRepository);

        for (Map.Entry<String, Integer> element : participantMap.entrySet()) {
            double participationPercent = (element.getValue() * 100) / 18.0;
            System.out.println(String.format("참여자 : %s 참여율 : %.2f", element.getKey(), participationPercent));
        }
    }

    private Map<String, Integer> generateParticipants(GHRepository ghRepository) throws IOException {
        Map<String, Integer> participantMap = new HashMap<>();
        for (int i = 1; i <= 2; i++) {
            GHIssue issue = ghRepository.getIssue(i);
            PagedIterable<GHIssueComment> ghIssueComments = issue.listComments();
            String temp = "";
            for (GHIssueComment comment : ghIssueComments) {
                String username = comment.getUser().getLogin();
                if (temp.equals(username))
                    continue;

                temp = username;
                participantMap.put(username, participantMap.getOrDefault(username, 0) + 1);
            }
        }
        return participantMap;
    }

}