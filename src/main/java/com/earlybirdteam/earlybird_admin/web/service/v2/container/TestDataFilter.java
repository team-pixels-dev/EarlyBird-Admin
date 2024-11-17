package com.earlybirdteam.earlybird_admin.web.service.v2.container;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TestDataFilter {

    public boolean isNotTestData(String data) {
        return !(data.toLowerCase().contains("test") || data.toLowerCase().contains("테스트"));
    }
}
