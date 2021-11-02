package com.Spring.stud.demo.core.utils.AOP;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Data
public class UtilService {
    private ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public void addServiceToStatistics(String serviceName, Long liveDuration) {
        if(map.containsKey(serviceName)) {
            Long buff = map.get(serviceName) + liveDuration;
            map.put(serviceName, buff);
        }else {
            map.put(serviceName, liveDuration);
        }
    }
}
