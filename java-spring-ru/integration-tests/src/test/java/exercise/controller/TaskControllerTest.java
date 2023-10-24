package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Optional;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    Faker faker;
    @Autowired
    TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void beforeEach() {
        this.task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    @Test
    public void testShow() throws Exception {
        String title = task.getTitle();
        String description = task.getDescription();
        //1 создание объекта в БД:
        taskRepository.save(task);
        //2-2 подготовка запроса:
        //MockHttpServletRequest ??
        var request = get("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON);
        //3 выполнение запроса:
        mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDescription()).isEqualTo(description);
    }

    @Test
    public void testCreate() throws Exception {
        taskRepository.save(task);

        String title = task.getTitle();
        String description = task.getDescription();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDescription()).isEqualTo(description);
    }

    @Test
    public void testUpdate() throws Exception {
        //1 создание объекта в БД:
        taskRepository.save(task);
        //2-1 создание объекта сравнения:
        HashMap<String, String> data = new HashMap<>();
        data.put("title", "test");
        data.put("description", "test description");
        //2-2 подготовка запроса:
        //MockHttpServletRequest ??
        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));
        //3 выполнение запроса:
        mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo("test");
        assertThat(task.getDescription()).isEqualTo("test description");
    }

    @Test
    public void testDelete() throws Exception {
        taskRepository.save(task);

        var request = delete("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk()); //или статус 204 no_content

        Optional<Task> taskAfterDeletion = taskRepository.findById(task.getId());
        assertThat(taskAfterDeletion).isEqualTo(Optional.empty());
    }
}
// END
