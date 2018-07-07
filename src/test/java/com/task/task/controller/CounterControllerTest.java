package com.task.task.controller;

import com.task.task.entity.Body;
import com.task.task.service.impl.CounterServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CounterControllerTest {
    @Mock
    private CounterServiceImpl counterService;

    private MockMvc mockMvc;

    @InjectMocks
    private CounterController counterController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(counterController).build();
    }

    @Test
    public void countTest() throws Exception {
        Body request = new Body();
        List<String> wordsList = Arrays.asList("AAA","abc","ccc");
        request.setData(wordsList);
        Map<String, Long> expectedWords = generateExpectedWords();
        when(counterService.countWordsEntries(wordsList)).thenReturn(expectedWords);

        MvcResult result = mockMvc.perform(post("/count")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToByteArray(request)))
                .andExpect(status().isOk())
                .andReturn();
        Map<String, Long> resultWords = TestUtil.getCollectionObjects(result);
        assertTrue(resultWords.equals(expectedWords));
        verify(counterService, times(1)).countWordsEntries(wordsList);
    }

    @Test
    public void countTestBadRequest() throws Exception {
        mockMvc.perform(post("/count")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToByteArray("not a Body object")))
                .andExpect(status().isBadRequest());
        verifyNoMoreInteractions(counterService);
    }

    @Test
    public void countTestExceptionHandler() throws Exception {
        final Body request = new Body();
        request.setData(Arrays.asList("AAA","abc","ccc"));
        doThrow(new Exception()).when(counterService);

        MvcResult result = mockMvc.perform(post("/count")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToByteArray(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals("Invalid return message.", "Bad input data", result.getResponse().getContentAsString());
    }

    private Map<String, Long> generateExpectedWords() {
        return new HashMap<String, Long>(){{
            put("AAA", 1L);
            put("abc", 1L);
            put("ccc", 1L);
        }};
    }
}
