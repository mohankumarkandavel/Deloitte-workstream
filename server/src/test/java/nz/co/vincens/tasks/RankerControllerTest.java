package nz.co.vincens.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(RankerController.class)
public class RankerControllerTest {

    @MockBean private TaskService taskService;
    // TODO: 28/09/2017 assert more things (based off of what userService will return)
    @MockBean private UserService userService;
    @Autowired private MockMvc mockMvc;

    @Test
    public void allAvailable() throws Exception {
        Task task = new Task(1, "Test task", "testing", new Attribute(1, 1, 1, 1), new Date(), Group
                .BUSINESS_AND_DEVELOPMENT, Status.DRAFT, 1, null);


        given(taskService.getTask(1)).willReturn(task);

        // TODO: 28/09/2017 need correct date-time format (for later)
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm a z").create();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rank/" + 1)
                .content(gson.toJson(task)).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void requireFiveAvailability() throws Exception {
        Task task = new Task(1, "Test task", "testing", new Attribute(1, 1, 5, 1), new Date(), Group
                .BUSINESS_AND_DEVELOPMENT, Status.DRAFT, 1, null);

        given(taskService.getTask(1)).willReturn(task);

        // TODO: 28/09/2017 need correct date-time format (for later)
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm a z").create();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rank/" + 1)
                .content(gson.toJson(task)).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(result.getResponse().getContentAsString());
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        boolean valid = true;
        for (JsonElement teamMember: jsonArray) {
            int availability = teamMember.getAsJsonObject().get("weightings")
                    .getAsJsonObject().get("Business and Development")
                    .getAsJsonObject().get("availability")
                    .getAsInt();
            valid = valid && availability == 5;
        }

        assertTrue(valid);
    }

}