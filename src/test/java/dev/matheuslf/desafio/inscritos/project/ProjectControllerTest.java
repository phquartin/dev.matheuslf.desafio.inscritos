package dev.matheuslf.desafio.inscritos.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class Config {
        @Bean
        ProjectService projectService() {
            return Mockito.mock(ProjectService.class);
        }
    }

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException Error, Project request MUST HAVE name!")
    void errorWhenRequestDoNotHaveName() throws Exception {
        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"desc\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException Error, Project request name CANT HAVE less than 3 chars!")
    void errorWhenRequestHaveANameWith3Chars() throws Exception {
        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"ab\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException Error, Project request name CANT HAVE more than 100 chars!")
    void errorWhenRequestHaveANameWith101Chars() throws Exception {

        String text = "a".repeat(101);
        String json =
        """
        {
          "name": "%s"
        }
        """.formatted(text);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException Error, errorWhenRequestHaveANullName")
    void errorWhenRequestHaveANullName() throws Exception {

        String json =
                """
                {
                  "name": null
                }
                """;

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException Error, errorWhenRequestHaveAnEmptyName")
    void errorWhenRequestHaveAnEmptyName() throws Exception {

        String json =
                """
                {
                  "name": ""
                }
                """;

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException Error, errorWhenRequestHaveAnEmptySpacedName")
    void errorWhenRequestHaveAnEmptySpacedName() throws Exception {

        String json =
                """
                {
                  "name": "           "
                }
                """;

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    @DisplayName("Valid Request, expect 201 response")
    void validRequest() throws Exception {

        String json =
                """
                {
                  "name": "valid"
                }
                """;

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

}