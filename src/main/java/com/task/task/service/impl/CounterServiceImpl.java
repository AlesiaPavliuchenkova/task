package com.task.task.service.impl;

import com.task.task.service.CounterService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CounterServiceImpl implements CounterService {

    @Override
    public Map<String, Long> countWordsEntries(List<String> wordsList) {
        return wordsList.stream()
                .map(word -> word.replaceAll("[^a-zA-Z0-9]",""))
                .filter(word -> word.length() != 0)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
