package nz.co.vincens.service;

import nz.co.vincens.login.LoginController;
import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Manager;
import nz.co.vincens.model.TeamMember;
import nz.co.vincens.model.User;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        List<User> users = new ArrayList<>();
        users.add(new Manager("1", "Manager", "James Too", "James@hotmail.com", "123"));
        users.add(new TeamMember("2", "Team Member", "Bob Ross", "Bob@ross.com",  "123"));
        given(this.userService.getUsers()).willReturn(users);
    }
    
    @Test
    public void loginSuccessTeamMember() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").param("username",
                "Team Member").param("password", "123")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
    
    @Test
    public void loginFail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").param("username",
                "Hacker").param("password", "abc")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
    }
    
    @Test
    public void loginBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }
    
}