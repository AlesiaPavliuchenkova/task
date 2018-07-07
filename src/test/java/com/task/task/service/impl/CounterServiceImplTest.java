package com.task.task.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CounterServiceImplTest {
    private final static String ABC = "abc";
    private final static String CCC = "ccc";
    private final static String BBB = "bbb";
    private final static String AAA = "aaa";

    @InjectMocks
    private CounterServiceImpl counterService;

    @Test
    public void countWordsEntriesTest() {
        List<String> wordsAllList = Arrays.asList("!" + ABC, CCC, BBB, ABC, AAA, CCC + ",", ABC, "");
        Map<String, Long> expectedWords = new HashMap<String, Long>() {{
            put(ABC, 3L);
            put(CCC, 2L);
            put(BBB, 1L);
            put(AAA, 1L);
        }};
        Map<String, Long> resultWords = counterService.countWordsEntries(wordsAllList);
        assertEquals("Size mismatch for maps.", expectedWords.size(), resultWords.size());
        assertNotNull("Returned map shouldn't be null.", resultWords);
        assertEquals("Keys mismatch in maps.", expectedWords.keySet(), resultWords.keySet());
        resultWords.keySet().forEach((key) ->
            assertEquals(String.format("Invalid count of word %s.", key), resultWords.get(key), expectedWords.get(key))
        );
    }
}
