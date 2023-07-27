package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.BlockDto;
import com.example.coursesmanagement.model.dto.ClassDto;
import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.service.BlockService;
import com.example.coursesmanagement.service.ClassService;
import com.example.coursesmanagement.service.CourseService;
import com.example.coursesmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CourseService courseService;
    private final UserService userService;
    private final BlockService blockService;
    private final ClassService classService;

    @GetMapping
    public String getAdminView() {
        return "admin";
    }


    @GetMapping("/courses")
    public String getAddCourseView(Model model) {
        model.addAttribute("newCourse", new CourseDto());
        return "add-course";
    }


    @PostMapping("/courses/add")
    public String addCourse(CourseDto courseDto, @RequestParam("image") MultipartFile file) {
        courseDto.setImageName(file.getOriginalFilename());
        courseService.addCourse(courseDto, file);
        return "redirect:/admin/courses/add-course-summary";
    }

    @GetMapping("/courses/add-course-summary")
    public String showAddCourseSummary() {
        return "add-course-summary";
    }


    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "all-users";
    }

    @GetMapping("/users")
    public String getAddUserView(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/admin/users/add-user-summary";
    }

    @GetMapping("/users/add-user-summary")
    public String showAddUserSummary() {
        return "add-user-summary";
    }

    @GetMapping("/all-courses-for-admin")
    public String getAllCourses(Model model) {
        List<CourseDto> coursesFromDb = courseService.allCourses();
        model.addAttribute("courses", coursesFromDb);
        return "all-courses-for-admin";
    }

    @GetMapping("/blocks")
    public String getAddBlockView(Model model) {
        model.addAttribute("newBlock", new BlockDto());
        List<CourseDto> coursesFromDb = courseService.allCourses();
        model.addAttribute("courses", coursesFromDb);
        return "add-block";
    }

    @PostMapping("/blocks/add")
    public String addBlock(BlockDto blockDto) {
        blockService.addBlock(blockDto);
        return "redirect:/admin/blocks/add-block-summary";
    }

    @GetMapping("/blocks/add-block-summary")
    public String showAddBlockSummary() {
        return "add-block-summary";
    }

    @GetMapping("/all-blocks")
    public String getAllBlocks(Model model) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        return "all-blocks";
    }

    @GetMapping("/classes")
    public String getAddClassView(Model model) {
        model.addAttribute("newClass", new ClassDto());
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        return "add-class";
    }

    @PostMapping("/classes/add")
    public String addClass(ClassDto classDto) {
        classService.addClass(classDto);
        return "redirect:/admin/classes/add-class-summary";
    }

    @GetMapping("/classes/add-class-summary")
    public String showAddClassSummary() {
        return "add-class-summary";
    }

}
