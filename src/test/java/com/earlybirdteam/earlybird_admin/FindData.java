package com.earlybirdteam.earlybird_admin;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.log.visit.VisitEventLog;
import com.earlybirdteam.earlybird_admin.web.service.v1.AppointmentList;
import com.earlybirdteam.earlybird_admin.web.service.v1.VisitEventLogList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FindData {

    @Autowired
    private VisitEventLogList visitEventLogList;

    @Autowired
    private AppointmentList appointmentList;

    @DisplayName("")
    @Test
    void test() {
        // given

        List<VisitEventLog> visitEventLogs = visitEventLogList.getVisitEventLogs();
        List<Appointment> appointments = appointmentList.getAppointments();

        List<Appointment> error = appointments.stream()
                .filter(a ->
                        visitEventLogs.stream().map(VisitEventLog::getClientId).noneMatch(id -> id.equals(a.getClientId()))
                )
                .toList();

        for (Appointment appointment : error) {
            System.out.println(appointment);
        }

        for (int i = 71; i < 169; i++) {
            VisitEventLog visitEventLog = visitEventLogs.get(i-71);
            if (visitEventLog.getId() != i) {
                System.out.println("!");
            }
        }


        // when


        // then


    }

}
