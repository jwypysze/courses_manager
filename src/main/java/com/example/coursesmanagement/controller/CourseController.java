package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String courses(Model model) {
        List<CourseDto> coursesFromDb = courseService.getCourses();
        model.addAttribute("courses", coursesFromDb);
        return "courses";
    }


    @GetMapping("/course-details")
    @RequestMapping("/details/{courseId}")
    public String courseDetails(Model model, @PathVariable Long courseId) {
        log.info("courseId = " + courseId);
        return "course-details";


        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}
