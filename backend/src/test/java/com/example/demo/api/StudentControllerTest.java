package com.example.demo.api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addStudent() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "Test");
        json.put("surname", "Test");
        json.put("phone", "(999) 999-9999");
        json.put("city", "Test");
        json.put("district", "Test");
        json.put("description", "Test");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/student")
                .accept(MediaType.APPLICATION_JSON)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getAllStudents() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/student");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getStudentById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/student/e67bf1a9-8b82-4699-902b-cdd9ce12b8fc");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deleteStudentById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/student/b2384c34-7716-4108-94c9-009ded37f29f");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updateStudent() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "Update");
        json.put("surname", "Update");
        json.put("phone", "(888) 888-8888");
        json.put("city", "Update");
        json.put("district", "Update");
        json.put("description", "Update");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/student/444b9930-577f-4208-a589-b5fb6d0796f5")
                .accept(MediaType.APPLICATION_JSON)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void uploadFile() throws Exception {
        JSONObject json = new JSONObject();
        json.put("file", "SGVsbG8gd29ybGQ=");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/student/upload/482055c1-8601-4d59-a4fb-5c93b9f592c3")
                .accept(MediaType.APPLICATION_JSON)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deleteFile() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/student/file-delete/992e0940-fc00-4a0a-a52a-74c1eab396e0");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getFileById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/student/file/319c0d8b-b39b-4e1b-b2ba-78ce59510a58");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}