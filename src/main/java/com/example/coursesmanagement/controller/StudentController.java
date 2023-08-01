package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.*;
import com.example.coursesmanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final CourseService courseService;
    private final BlockService blockService;
    private final ClassService classService;
    private final RegistrationService registrationService;
    private final UserService userService;

    @GetMapping()
    public String getAllCourses(Model model) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "/student/all-courses";
    }

    @GetMapping("/courses/details/{courseId}")
    public String courseDetails(Model model, @PathVariable Long courseId) {
        CourseDto courseById = courseService.getCourseById(courseId);
        model.addAttribute("courseById", courseById);
        List<BlockDto> blocksInCourse = blockService.findBlocksInCourse(courseById);
        model.addAttribute("blocksInCourse", blocksInCourse);
        return "/student/course-details";
    }

    @GetMapping("/blocks/details/{blockId}")
    public String blockDetails(Model model, @PathVariable Long blockId) {
        BlockDto block = blockService.getBlockById(blockId);
        model.addAttribute("block", block);
        List<ClassDto> classesInBlock = classService.findClassesInBlock(block);
        model.addAttribute("classesInBlock", classesInBlock);
        return "/student/block-details";
    }

    @PostMapping("/courses/sign-up-for-course")
    public String addRegistration(RegistrationDto registrationDto) {
        registrationService.addRegistration(registrationDto);
        return "redirect:/student/registrations/add-registration-summary";
    }

    @GetMapping("/registrations/add-registration-summary")
    public String showAddRegistrationSummary() {
        return "/student/add-registration-summary";
    }

    @GetMapping("/courses/sign-up-for-course/{courseId}")
    public String addRegistrationToTheChosenCourse(Model model, @PathVariable Long courseId,
                                                   String userName, String userSurname) {
        CourseDto courseById = courseService.getCourseById(courseId);
        model.addAttribute("course", courseById);
        model.addAttribute("userName", userName);
        model.addAttribute("userSurname", userSurname);
        UserDto userByNameAndSurname = userService.findUserByNameAndSurname(userName, userSurname);
        Long userId = userByNameAndSurname.getId();
        model.addAttribute("newRegistration", new RegistrationDto());
        return "/student/sign-up-for-course";
    }
}
