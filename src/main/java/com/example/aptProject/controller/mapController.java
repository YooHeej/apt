package com.example.aptProject.controller;

import com.example.aptProject.dao.LocationCodeDao;
import com.example.aptProject.entity.APIResult;
import com.example.aptProject.entity.APIResultIncludeTotalCount;
import com.example.aptProject.entity.Users;
import com.example.aptProject.service.ApiService;
import com.example.aptProject.service.ApiServiceImpl;
import com.example.aptProject.service.LocationService;
import jakarta.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class mapController {
    /**
     * 쥰내바꿈 0318 기준
     */
    @Autowired
    private ApiService apiService;
    @Autowired
    private LocationCodeDao lDao;


    @Autowired
    private LocationService lSvc;

    @GetMapping("getResult/{LAWD_CD}/{period}")
    public String getResult(@PathVariable String LAWD_CD, @PathVariable String period, HttpSession session, Model model) throws IOException, ParseException {
//        String totalCount = "100000";
        String totalCount = apiService.getTotalCount(LAWD_CD, period);
        Map<String, Double> center = new HashMap<>();

        APIResultIncludeTotalCount result = apiService.getResultList(totalCount, LAWD_CD, period);
        List<APIResult> resultList = result.getApiResults();
        center = apiService.getCenterGeoCode(resultList);
        Double lon, lat;
        lon = center.get("lon");
        lat = center.get("lat");
        session.setAttribute("centerLon", lon);
        session.setAttribute("centerLat", lat);

        session.setAttribute("totalCount",resultList.size());
        System.out.println("totalCount ======= " + totalCount);
        System.out.println("notExistCount -------- " + result.getNotExistCount());
        System.out.println("resultlist==============" + resultList.size());
        model.addAttribute("resultList", resultList);

        List<String> firstNames = lSvc.getAllFirstNames();
        model.addAttribute("firstNames", firstNames);


        return "map/detail13";
    }


    @GetMapping("/search/secondNames")
    @ResponseBody
    public List<String> getSecondNamesByFirstName(@RequestParam("firstName") String firstName) {
        List<String> secondNames = lSvc.getSecondNamesByFirstName(firstName);
        return secondNames;
    }


    @GetMapping("/search/lastNames")
    @ResponseBody
    public List<String> getLastNamesByFirstNameAndSecondName(@RequestParam("firstName") String firstName,@RequestParam("secondName") String secondName) {
        List<String> lastNames = lSvc.getLastNamesByFirstNameAndSecondName(firstName, secondName);
        return lastNames;
    }

    @GetMapping("/search/firstNames")
    public List<String> getAllFirstNames() {
        return lSvc.getAllFirstNames();
    }

    @PostMapping("/search")
    public String searchProc(String firstName, String secondName, String lastName, String period) {

        int lCode = 0;
        if(lastName.isEmpty()){
            lCode = lSvc.getLocationCodeByFirstNameAndSecondName(firstName, secondName).getlCode();
        }else{
            lCode = lSvc.getLocationCodeByFirstNameAndSecondNameAndLastName(firstName, secondName, lastName).getlCode();
        }

        return "redirect:/api/getResult/" + lCode + "/" + period;

    }
}
