package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.ServiceCounterAspect;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ServiceCounterController {
    private ServiceCounterAspect serviceCounterAspect;

    @GetMapping
    @RequestMapping("/api/stats")
    public Map<String, Integer> getStatistics() {
        return serviceCounterAspect.getServiceToCountMap();
    }

    @GetMapping
    @RequestMapping("/stats")
    public String getStatisticsPage(Model model){
        model.addAttribute("statisticMap", serviceCounterAspect.getServiceToCountMap());

        return "stats-page";
    }
}
