package br.com.malikoski.github.api;


import br.com.malikoski.github.api.dto.GithubResponse;
import br.com.malikoski.github.core.GithubHelper;
import br.com.malikoski.github.core.ParserHtml;
import br.com.malikoski.github.service.GitHubFileDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = {GithubRepoDetailController.class})
@AutoConfigureMockMvc
public class GithubRepoDetailControllerTest {


    static final String URL_GITHUB_BASE = "https://github.com/";
    static final String GITHUB_API = "/api/github/";
    static final String OWNERNAME = "ownername";
    static final String REPONAME = "reponame";
    static final String URL_GITHUP_OWNER_REPO_PATH = URL_GITHUB_BASE + OWNERNAME +  "/" + REPONAME;
    public static final String JAVA_EXTENSION = "java";
    public static final String PROPERTIES_EXTENSION = "properties";
    public static final String XML_EXTENSION = "xml";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubFileDetailService gitHubFileDetailService;

    @MockBean
    private ThreadPoolExecutor threadPoolExecutor;

    @MockBean
    private GithubHelper githubHelper;

    @MockBean
    private ParserHtml parserHtml;

    @Test
    @DisplayName("Should return success response")
    public void successTest() throws Exception {

        List<GithubResponse> response = createDefaultResponse();
        var endpoint = GITHUB_API + OWNERNAME + "/repo/" + REPONAME;

        BDDMockito.given(githubHelper.createGithubUrl(any(),any())).willReturn(URL_GITHUP_OWNER_REPO_PATH);

        BDDMockito.given(gitHubFileDetailService.getStaticsFilesFromRepo(URL_GITHUP_OWNER_REPO_PATH))
                .willReturn(CompletableFuture.completedFuture(response));

        String json = new ObjectMapper().writeValueAsString(response);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].extension").value(response.get(0).getExtension()))
                .andExpect(jsonPath("[0].count").value(response.get(0).getCount()))
                .andExpect(jsonPath("[0].bytes").value(response.get(0).getBytes()))
                .andExpect(jsonPath("[0].lines").value(response.get(0).getLines()))

                .andExpect(jsonPath("[1].extension").value(response.get(1).getExtension()))
                .andExpect(jsonPath("[1].count").value(response.get(1).getCount()))
                .andExpect(jsonPath("[1].bytes").value(response.get(1).getBytes()))
                .andExpect(jsonPath("[1].lines").value(response.get(1).getLines()))

                .andExpect(jsonPath("$[2].extension").value(response.get(2).getExtension()))
                .andExpect(jsonPath("$[2].count").value(response.get(2).getCount()))
                .andExpect(jsonPath("$[2].bytes").value(response.get(2).getBytes()))
                .andExpect(jsonPath("$[2].lines").value(response.get(2).getLines()));
    }

    private List<GithubResponse> createDefaultResponse() {

        return  Arrays.asList(
                GithubResponse.builder().extension(JAVA_EXTENSION)
                        .count(20)
                        .bytes(200000l)
                        .lines(10000l)
                        .build(),
                GithubResponse.builder().extension(PROPERTIES_EXTENSION)
                        .count(2)
                        .bytes(20l)
                        .lines(15l)
                        .build(),

                GithubResponse.builder().extension(XML_EXTENSION)
                        .count(1)
                        .bytes(2000l)
                        .lines(700l)
                        .build()
        );
    }


}
