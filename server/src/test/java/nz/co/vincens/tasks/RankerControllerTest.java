package nz.co.vincens.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.*;

// @RunWith(SpringRunner.class)
// @WebMvcTest(RankerController.class)
public class RankerControllerTest {

    // @Autowired
    // private MockMvc mockMvc;

    @Test
    public void allAvailable() throws Exception {
        // FIXME: 21/09/2017 Can't inject TaskService for RankerController
        // Task task = new Task(0, "Test task", "testing", new Attribute(1, 1, 1, 1), new Date(), Group
        //         .BUSINESS_AND_DEVELOPMENT, Status.DRAFT, 1);
        //
        // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm a z").create();
        //
        // RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rank")
        //         .content(gson.toJson(task)).contentType(MediaType.APPLICATION_JSON);
        //
        // MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        // assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void oneUnavailable() {

    }

}