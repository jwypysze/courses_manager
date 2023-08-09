package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.*;
import com.example.coursesmanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping
    public String mainPageForStudent() {
        return "/student/main-page";
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


    @GetMapping("/courses")
    public String getAllCourses(Model model, CourseDto courseDto) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("courseToSignUp", courseDto);
        return "/student/all-courses";
    }


    @PostMapping("/courses/sign-up-for-course")
    public String addRegistration(Principal principal,
                                  @RequestParam(name = "courseId") Long courseId, RegistrationDto registrationDto) {
        String name = principal.getName();
        UserDto userByUsername = userService.findUserByUsername(name);
        registrationDto.setCourseId(courseId);
        registrationDto.setUserId(userByUsername.getId());
        registrationService.addRegistrationByUser(registrationDto);
        return "redirect:/student/registrations/add-registration-summary";
    }

    @GetMapping("/registrations/add-registration-summary")
    public String showAddRegistrationSummary() {
        return "/student/add-registration-summary";
    }

    @GetMapping("/registrations/by-student")
    public String showRegistrations(Model model, Principal principal) {
        String name = principal.getName();
        UserDto userByUsername = userService.findUserByUsername(name);
        List<RegistrationDto> studentRegistrations = registrationService.findStudentRegistrations(userByUsername);
        model.addAttribute("studentRegistrations", studentRegistrations);
        return "/student/registrations-by-student";
    }

}
