package nz.co.vincens.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;
import org.junit.Before;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(RankerController.class)
public class RankerControllerTest {

    @MockBean private TaskService taskService;
    // TODO: 28/09/2017 will need to set this mock up when taskService uses it (on other branch)
    // @MockBean private UserService userService;
    @Autowired private MockMvc mockMvc;

    @Test
    public void allAvailable() throws Exception {
        // TODO: 28/09/2017 for later fix ids (on other branch)
        Task task = new Task(0, "Test task", "testing", new Attribute(1, 1, 1, 1), new Date(), Group
                .BUSINESS_AND_DEVELOPMENT, Status.DRAFT, 1, null);


        given(taskService.getTask(0)).willReturn(task);

        // TODO: 28/09/2017 need correct date-time format (for later)
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm a z").create();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rank/" + 1)
                .content(gson.toJson(task)).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // TODO: 28/09/2017 assert more things (based off of what userService will return)
    }

    @Test
    public void requireFiveAvailability() throws Exception {
        // TODO: 28/09/2017 for later fix ids (on other branch)
        Task task = new Task(0, "Test task", "testing", new Attribute(1, 1, 5, 1), new Date(), Group
                .BUSINESS_AND_DEVELOPMENT, Status.DRAFT, 1, null);

        given(taskService.getTask(0)).willReturn(task);

        // TODO: 28/09/2017 need correct date-time format (for later)
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm a z").create();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rank/" + 1)
                .content(gson.toJson(task)).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // TODO: 28/09/2017 assert more things (based off of what userService will return)
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(result.getResponse().getContentAsString());
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        final boolean[] valid = {true};
        jsonArray.forEach(teamMember -> {
            int availability = teamMember.getAsJsonObject().get("weightings")
                    .getAsJsonObject().get("Business and Development")
                    .getAsJsonObject().get("availability")
                    .getAsInt();
            valid[0] = valid[0] && availability == 5;
        });
        assertTrue(valid[0]);
    }

}