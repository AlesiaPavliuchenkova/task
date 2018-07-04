package com.task.task.service;

import java.util.List;
import java.util.Map;

public interface CounterService {
    Map<String, Long> countWordsEntries(List<String> words);
}
