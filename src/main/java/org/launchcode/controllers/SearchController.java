package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.models.JobData.findByColumnAndValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    //ArrayList<HashMap<String, String>> jobs = findByColumnAndValue("searchType", "searchTerm");

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, String searchTerm) {
        ArrayList<HashMap<String, String>> jobs; // JobData.findByColumnAndValue("searchType", "searchTerm");
        //note change jobs to reflect search choice
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            //model.addAttribute("columns", ListController.columnChoices);
        }
//        else if (searchType == null) {
//            jobs = JobData.findByValue(searchTerm);
//        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("aSearchType", searchType);
        return "search";
    }
}
