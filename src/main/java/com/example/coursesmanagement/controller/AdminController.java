package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.*;
import com.example.coursesmanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/main-page")
@RequiredArgsConstructor
public class AdminController {
    private final CourseService courseService;
    private final UserService userService;
    private final BlockService blockService;
    private final ClassService classService;
    private final NotificationService notificationService;

    @GetMapping("/courses-manager")
    public String getCoursesManagerView() {
        return "courses-manager";
    }

    @GetMapping("/blocks-manager")
    public String getBlocksManagerView() {
        return "blocks-manager";
    }

    @GetMapping("/classes-manager")
    public String getClassesManagerView() {
        return "classes-manager";
    }

    @GetMapping("/notifications-manager")
    public String getNotificationsManagerView() {
        return "notifications-manager";
    }

    @GetMapping("/users-manager")
    public String getUsersManagerView() {
        return "users-manager";
    }

    @GetMapping("/registrations-manager")
    public String getRegistrationsManagerView() {
        return "registrations-manager";
    }

    @GetMapping
    public String getMainPageView() {
        return "main-page";
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
        return "redirect:/main-page/courses/add-course-summary";
    }

    @GetMapping("/courses/add-course-summary")
    public String showAddCourseSummary() {
        return "add-course-summary";
    }

    @GetMapping("/all-courses-for-admin")
    public String getAllCourses(Model model) {
        List<CourseDto> coursesFromDb = courseService.allCourses();
        model.addAttribute("courses", coursesFromDb);
        return "all-courses-for-admin";
    }

    @GetMapping("/courses/delete-course-summary")
    public String showDeleteCourseSummary() {
        return "delete-course-summary";
    }

    @GetMapping("/courses/delete-course")
    public String getDeleteCourseView(CourseDto courseDto, Model model) {
        List<CourseDto> allCourses = courseService.allCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("courseToDelete", courseDto);
        return "delete-course";
    }

    @PostMapping("/courses/delete")
    public String deleteCourseById(CourseDto courseDto) {
        courseService.deleteCourseById(courseDto);
        return "redirect:/main-page/courses/delete-course-summary";
    }

    @GetMapping("/courses/details/{courseId}")
    public String courseDetails(Model model, @PathVariable Long courseId) {
        CourseDto courseById = courseService.getCourseById(courseId);
        model.addAttribute("courseById", courseById);
        List<BlockDto> blocksInCourse = blockService.findBlocksInCourse(courseById);
        model.addAttribute("blocksInCourse", blocksInCourse);
        return "course-details";
    }

    @GetMapping("/blocks/details/{blockId}")
    public String blockDetails(Model model, @PathVariable Long blockId) {
        BlockDto blockById = blockService.getBlockById(blockId);
        model.addAttribute("blockById", blockById);
        List<ClassDto> classesInBlock = classService.findClassesInBlock(blockById);
        model.addAttribute("classesInBlock", classesInBlock);
        return "block-details";
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
        return "redirect:/main-page/users/add-user-summary";
    }

    @GetMapping("/users/add-user-summary")
    public String showAddUserSummary() {
        return "add-user-summary";
    }

    @GetMapping("/users/delete-user")
    public String getDeleteUserView(Model model, UserDto userDto) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        model.addAttribute("userToDelete", userDto);
        return "delete-user";
    }

    @PostMapping("/users/delete")
    public String deleteUserById(UserDto userDto) {
        userService.deleteUserById(userDto);
        return "redirect:/main-page/users/delete-user-summary";
    }

    @GetMapping("/users/delete-user-summary")
    public String showDeleteUserSummary() {
        return "delete-user-summary";
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
        return "redirect:/main-page/blocks/add-block-summary";
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

    @GetMapping("/blocks/delete-block")
    public String getDeleteBlockView(BlockDto blockDto, Model model) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        model.addAttribute("blockToDelete", blockDto);
        return "delete-block";
    }

    @PostMapping("/blocks/delete")
    public String deleteBlockById(BlockDto blockDto) {
        blockService.deleteBlockById(blockDto);
        return "redirect:/main-page/blocks/delete-block-summary";
    }

    @GetMapping("/blocks/delete-block-summary")
    public String showDeleteBlockSummary() {
        return "delete-block-summary";
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
        return "redirect:/main-page/classes/add-class-summary";
    }

    @GetMapping("/classes/add-class-summary")
    public String showAddClassSummary() {
        return "add-class-summary";
    }

    @GetMapping("/all-classes")
    public String getAllClasses(Model model) {
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        return "all-classes";
    }

    @GetMapping("/classes/delete-class")
    public String getDeleteClassView(ClassDto classDto, Model model) {
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        model.addAttribute("classToDelete", classDto);
        return "delete-class";
    }

    @PostMapping("/classes/delete")
    public String deleteClassById(ClassDto classDto) {
        classService.deleteClassById(classDto);
        return "redirect:/main-page/classes/delete-class-summary";
    }

    @GetMapping("/classes/delete-class-summary")
    public String showDeleteClassSummary() {
        return "delete-class-summary";
    }

    @GetMapping("/all-notifications")
    public String getAllNotifications(Model model) {
        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        return "all-notifications";
    }

    @GetMapping("/notifications/delete-notification")
    public String getDeleteNotificationView(NotificationDto notificationDto, Model model) {
        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        model.addAttribute("notificationToDelete", notificationDto);
        return "delete-notification";
    }

    @PostMapping("/notifications/delete")
    public String deleteNotificationById(NotificationDto notificationDto) {
        notificationService.deleteNotificationById(notificationDto);
        return "redirect:/main-page/notifications/delete-notification-summary";
    }

    @GetMapping("/notifications/delete-notification-summary")
    public String showDeleteNotificationSummary() {
        return "delete-notification-summary";
    }

    @GetMapping("/notifications")
    public String getAddNotificationView(Model model) {
        model.addAttribute("newNotification", new NotificationDto());
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        return "add-notification";
    }

    @PostMapping("/notifications/add")
    public String addNotification(NotificationDto notificationDto) {
        notificationService.addNotification(notificationDto);
        return "redirect:/main-page/notifications/add-notification-summary";
    }

    @GetMapping("/notifications/add-notification-summary")
    public String showAddNotificationSummary() {
        return "add-notification-summary";
    }


}
