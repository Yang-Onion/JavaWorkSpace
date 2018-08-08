package com.yangonion.springbootrestfulapi;

import com.yangonion.springbootrestfulapi.api.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class UserApiTests {

    private MockMvc mvc;

    @Before
    public  void setUp() throws  Exception{
        mvc= MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public  void testUserApi() throws  Exception{

        RequestBuilder request = null;

        //1:第一次请求,userlist应该为[]
        request=get("/users/");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2:向userlist添加一个user
        request =post("/users/")
                .param("id","1")
                .param("name","Yang-Onion")
                .param("age","18");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("1")));

        //3:再向userlist添加一个user
        request =post("/users/")
                .param("id","2")
                .param("name","Yang-Onion-2")
                .param("age","20");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("2")));

        //4:删除一个user
        request=delete("/users/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Remove Sucess!")));

        request=put("/users/")
                .param("id","1")
                .param("name","Mr.Yang")
                .param("age","100");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Update Sucess!")));

        /*
        request=get("/users/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
        */
    }

}
