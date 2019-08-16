package com.wisely.highlight_springmvc4.web.ch4_6;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.wisely.highlight_springmvc4.MyMvcConfig;
import com.wisely.highlight_springmvc4.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
//@WebAppConfiguration注解在类上，用来声明加载的
// ApplicationContex是一个WebApplicationContext。它的属性指
// 定的是Web资源的位置，默认为src/main/webapp，本例修改为
//src/main/resources。
@WebAppConfiguration("src/main/resources") //1 
public class TestControllerIntegrationTests {
    //MockMvc-模拟MVC对象，通过 MockMvcBuilders.webAppContextSetup（this.wac）.build（） 初始化。
    private MockMvc mockMvc; //2
    @Autowired
    //可以在测试用例中注入Spring的Bean。
    private DemoService demoService;//3
    @Autowired
    //可注入WebApplicationContext。
            WebApplicationContext wac; //4
    @Autowired
    //可注入模拟的http session，此处仅作演示，没有使用。
    MockHttpSession session; //5
    @Autowired
    //可注入模拟的http request，此处仅作演示，没有使用。
    MockHttpServletRequest request; //6

    //@Before在测试开始前进行的初始化工作。
    @Before //7
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); //2
    }

    @Test
    public void testNormalController() throws Exception {
        mockMvc.perform(get("/normal")) //8 模拟向/normal进行get请求。
                .andExpect(status().isOk())//9 预期控制返回状态为200。
                .andExpect(view().name("page"))//10 预期view的名称为page。
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 预期页面转向的真正路径为/WEB-INF/classes/views/page.jsp。
                .andExpect(model().attribute("msg", demoService.saySomething()));//12 预期model里的值是demoService.saySomething（）返回 值hello。
    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest")) //13 模拟向/testRest进行get请求。
                .andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=UTF-8"))//14 预期返回值的媒体类型为text/plain；charset=UTF-8。
                .andExpect(content().string(demoService.saySomething()));//15 预期返回值的内容为demoService.saySomething（）返回 值hello。
    }
}