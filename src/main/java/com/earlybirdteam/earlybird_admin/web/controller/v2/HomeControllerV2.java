package com.earlybirdteam.earlybird_admin.web.controller.v2;

import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeControllerV2 {

    private final AppointmentListContainer appointmentListContainer;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("dataLastUpdateAt", appointmentListContainer.getLastUpdateAt());
        return "v2/home";
    }

}
