package com.earlybirdteam.earlybird_admin.web.service;

import com.earlybirdteam.earlybird_admin.web.service.v1.ArriveOnTimeEventLogList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArriveOnTimeEventLogListTest {

    @Autowired
    private ArriveOnTimeEventLogList arriveOnTimeEventLogList;

    @DisplayName("")
    @Test
    void test() {
        // given
        System.out.println(arriveOnTimeEventLogList.getArriveOnTimeAndRecreateAppointmentPercent());

        // when


        // then


    }


}