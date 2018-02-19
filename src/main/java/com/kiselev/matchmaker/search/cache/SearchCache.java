package com.kiselev.matchmaker.search.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SearchCache {

    private Map<String, Object> cache = Maps.newConcurrentMap();

    private Map<Long, LinkedMap<String, Object>> temporaryCache = Maps.newConcurrentMap();

    public synchronized void initializeQuery(String currentOperation, Object[] arguments, Object result) {
        long currentThreadId = Thread.currentThread().getId();
        if (temporaryCache.containsKey(currentThreadId)) {
            temporaryCache.remove(currentThreadId); // Clear before initialization
        }
        LinkedMap<String, Object> linkedMap = new LinkedMap<>();
        linkedMap.put(composeOperationHash(currentOperation, arguments), result);
        temporaryCache.put(currentThreadId, linkedMap); // Put a new query
    }

    public synchronized void collectQueryResults(String currentOperation, Object[] arguments, Object result) {
        long currentThreadId = Thread.currentThread().getId();
        if (temporaryCache.containsKey(currentThreadId)) {
            LinkedMap<String, Object> operationToResultMap = temporaryCache.get(currentThreadId);
            // Push a new query, which based on last previous operation
            String lastOperation = operationToResultMap.lastKey();
            operationToResultMap.put(composeOperationHash(lastOperation, currentOperation, arguments), result);
        }
    }

    public synchronized void completeQuery(Object result) {
        long currentThreadId = Thread.currentThread().getId();

        if (temporaryCache.containsKey(currentThreadId)) {
            LinkedMap<String, Object> operationToResultMap = temporaryCache.get(currentThreadId);

            // Fill permanent cache with all intermediate operations
            for (Map.Entry<String, Object> entry : operationToResultMap.entrySet()) {
                cache.put(entry.getKey(), entry.getValue());
            }

            // Complete permanent cache with performing operation
            String lastOperation = operationToResultMap.lastKey();
            cache.put(composeOperationHash(lastOperation), result);
        }
    }

    private String composeOperationHash(String operation, Object[] arguments) {
        List<String> stringArgs = Lists.newArrayList();
        for (Object argument : arguments) {
            if (argument instanceof String) {
                stringArgs.add(argument.toString());
            } else {
                stringArgs.add(argument.toString()); // TODO: Decompose of arrays and lists
            }
        }

        return operation + "(" + String.join(", " + stringArgs) + ")";
    }

    private String composeOperationHash(String lastOperation, String operation, Object[] arguments) {
        return lastOperation + "_" + composeOperationHash(operation, arguments);
    }

    private String composeOperationHash(String operation) {
        return operation + "_perform()";
    }
}
