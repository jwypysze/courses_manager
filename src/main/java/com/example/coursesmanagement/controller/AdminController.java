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
    private final RegistrationService registrationService;

    @GetMapping("/courses-manager")
    public String getCoursesManagerView() {
        return "/admin/courses-manager";
    }

    @GetMapping("/blocks-manager")
    public String getBlocksManagerView() {
        return "/admin/blocks-manager";
    }

    @GetMapping("/classes-manager")
    public String getClassesManagerView() {
        return "/admin/classes-manager";
    }

    @GetMapping("/notifications-manager")
    public String getNotificationsManagerView() {
        return "/admin/notifications-manager";
    }

    @GetMapping("/users-manager")
    public String getUsersManagerView() {
        return "/admin/users-manager";
    }

    @GetMapping("/registrations-manager")
    public String getRegistrationsManagerView() {
        return "/admin/registrations-manager";
    }

    @GetMapping
    public String getMainPageView() {
        return "/admin/main-page";
    }


    @GetMapping("/courses")
    public String getAddCourseView(Model model) {
        model.addAttribute("newCourse", new CourseDto());
        return "/admin/courses/add-course";
    }

    @PostMapping("/courses/add")
    public String addCourse(CourseDto courseDto, @RequestParam("image") MultipartFile file) {
        courseDto.setImageName(file.getOriginalFilename());
        courseService.addCourse(courseDto, file);
        return "redirect:/main-page/courses/add-course-summary";
    }

    @GetMapping("/courses/add-course-summary")
    public String showAddCourseSummary() {
        return "/admin/courses/add-course-summary";
    }

    @GetMapping("/all-courses")
    public String getAllCourses(Model model) {
        List<CourseDto> coursesFromDb = courseService.getAllCourses();
        model.addAttribute("courses", coursesFromDb);
        return "/admin/courses/all-courses";
    }

    @GetMapping("/courses/delete-course-summary")
    public String showDeleteCourseSummary() {
        return "/admin/courses/delete-course-summary";
    }

    @GetMapping("/courses/delete-course")
    public String getDeleteCourseView(CourseDto courseDto, Model model) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("courseToDelete", courseDto);
        return "/admin/courses/delete-course";
    }

    @PostMapping("/courses/delete")
    public String deleteCourseById(@RequestParam("idToDelete") Long id) {
        CourseDto courseById = courseService.findCourseById(id);
        courseService.deleteCourseById(courseById);
        return "redirect:/main-page/courses/delete-course-summary";
    }

    @GetMapping("/courses/update-course")
    public String getUpdateCourseView(Model model, CourseDto courseDto) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("courseToUpdate", courseDto);
        return "/admin/courses/update-course";
    }

    @GetMapping("/registrations/update-registration")
    public String getUpdateRegistrationView(Model model, RegistrationDto registrationDto) {
        List<RegistrationDto> allRegistrations = registrationService.getAllRegistrations();
        model.addAttribute("registrations", allRegistrations);
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        model.addAttribute("registrationToUpdate", registrationDto);
        return "/admin/registrations/update-registration";
    }

    @PostMapping("/registrations/update")
    public String updateRegistrationById(RegistrationDto registrationDto) {
        registrationService.updateRegistrationById(registrationDto);
        return "redirect:/main-page/registrations/update-registration-summary";
    }

    @GetMapping("/registrations/update-registration-summary")
    public String showUpdateRegistrationSummary() {
        return "/admin/registrations/update-registration-summary";
    }

    @PostMapping("/courses/update")
    public String updateCourseById(CourseDto courseDto, @RequestParam("image") MultipartFile file) {
        courseDto.setImageName(file.getOriginalFilename());
        courseService.updateCourseById(courseDto, file);
        return "redirect:/main-page/courses/update-course-summary";
    }

    @GetMapping("/courses/update-course-summary")
    public String showUpdateCourseSummary() {
        return "/admin/courses/update-course-summary";
    }

    @GetMapping("/users/update-user")
    public String getUpdateUserView(UserDto userDto, Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        model.addAttribute("userToUpdate", userDto);
        return "/admin/users/update-user";
    }

    @PostMapping("/users/update")
    public String updateUserById(UserDto userDto) {
        userService.updateUserById(userDto);
        return "redirect:/main-page/users/update-user-summary";
    }

    @GetMapping("/users/update-user-summary")
    public String showUpdateUserSummary() {
        return "/admin/users/update-user-summary";
    }

    @GetMapping("/blocks/update-block")
    public String getUpdateBlockView(Model model, BlockDto blockDto) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("blockToUpdate", blockDto);
        return "/admin/blocks/update-block";
    }

    @PostMapping("/blocks/update")
    public String updateBlockById(BlockDto blockDto) {
        blockService.updateBlockById(blockDto);
        return "redirect:/main-page/blocks/update-block-summary";
    }

    @GetMapping("/blocks/update-block-summary")
    public String showUpdateBlockSummary() {
        return "/admin/blocks/update-block-summary";
    }

    @GetMapping("/classes/update-class")
    public String getUpdateClassView(Model model, ClassDto classDto) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        model.addAttribute("classToUpdate", classDto);
        return "/admin/classes/update-class";
    }

    @PostMapping("/classes/update")
    public String updateClassById(ClassDto classDto) {
        classService.updateClassById(classDto);
        return "redirect:/main-page/classes/update-class-summary";
    }

    @GetMapping("/classes/update-class-summary")
    public String showUpdateClassSummary() {
        return "/admin/classes/update-class-summary";
    }

    @GetMapping("/notifications/update-notification")
    public String getUpdateNotificationView(Model model, NotificationDto notificationDto) {
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        model.addAttribute("notificationToUpdate", notificationDto);
        return "/admin/notifications/update-notification";
    }

    @PostMapping("/notifications/update")
    public String updateNotificationById(NotificationDto notificationDto) {
        notificationService.updateNotificationById(notificationDto);
        return "redirect:/main-page/notifications/update-notification-summary";
    }

    @GetMapping("/notifications/update-notification-summary")
    public String showUpdateNotificationSummary() {
        return "/admin/notifications/update-notification-summary";
    }

    @GetMapping("/courses/details/{courseId}")
    public String courseDetails(Model model, @PathVariable Long courseId) {
        CourseDto courseById = courseService.getCourseById(courseId);
        model.addAttribute("courseById", courseById);
        List<BlockDto> blocksInCourse = blockService.findBlocksInCourse(courseById);
        model.addAttribute("blocksInCourse", blocksInCourse);
        return "/admin/courses/course-details";
    }

    @GetMapping("/blocks/details/{blockId}")
    public String blockDetails(Model model, @PathVariable Long blockId) {
        BlockDto blockById = blockService.getBlockById(blockId);
        model.addAttribute("blockById", blockById);
        List<ClassDto> classesInBlock = classService.findClassesInBlock(blockById);
        model.addAttribute("classesInBlock", classesInBlock);
        return "/admin/blocks/block-details";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "/admin/users/all-users";
    }

    @GetMapping("/users")
    public String getAddUserView(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "/admin/users/add-user";
    }

    @PostMapping("/users/add")
    public String addUser(UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/main-page/users/add-user-summary";
    }

    @GetMapping("/users/add-user-summary")
    public String showAddUserSummary() {
        return "/admin/users/add-user-summary";
    }

    @GetMapping("/users/delete-user")
    public String getDeleteUserView(Model model, UserDto userDto) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        model.addAttribute("userToDelete", userDto);
        return "/admin/users/delete-user";
    }

    @PostMapping("/users/delete")
    public String deleteUserById(UserDto userDto) {
        userService.deleteUserById(userDto);
        return "redirect:/main-page/users/delete-user-summary";
    }

    @GetMapping("/users/delete-user-summary")
    public String showDeleteUserSummary() {
        return "/admin/users/delete-user-summary";
    }

    @GetMapping("/blocks")
    public String getAddBlockView(Model model) {
        model.addAttribute("newBlock", new BlockDto());
        List<CourseDto> coursesFromDb = courseService.getAllCourses();
        model.addAttribute("courses", coursesFromDb);
        return "/admin/blocks/add-block";
    }

    @PostMapping("/blocks/add")
    public String addBlock(BlockDto blockDto) {
        blockService.addBlock(blockDto);
        return "redirect:/main-page/blocks/add-block-summary";
    }

    @GetMapping("/blocks/add-block-summary")
    public String showAddBlockSummary() {
        return "/admin/blocks/add-block-summary";
    }

    @GetMapping("/all-blocks")
    public String getAllBlocks(Model model) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        return "/admin/blocks/all-blocks";
    }

    @GetMapping("/blocks/delete-block")
    public String getDeleteBlockView(BlockDto blockDto, Model model) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        model.addAttribute("blockToDelete", blockDto);
        return "/admin/blocks/delete-block";
    }

    @GetMapping("/registrations/delete-registration")
    public String getDeleteRegistrationView(RegistrationDto registrationDto, Model model) {
        List<RegistrationDto> allRegistrations = registrationService.getAllRegistrations();
        model.addAttribute("registrations", allRegistrations);
        model.addAttribute("registrationToDelete", registrationDto);
        return "/admin/registrations/delete-registration";
    }

    @PostMapping("/blocks/delete")
    public String deleteBlockById(@RequestParam("idToDelete") Long id) {
        BlockDto blockById = blockService.findBlockById(id);
        blockService.deleteBlockById(blockById);
        return "redirect:/main-page/blocks/delete-block-summary";
    }

    @PostMapping("/registrations/delete")
    public String deleteRegistrationById(RegistrationDto registrationDto) {
        registrationService.deleteRegistrationById(registrationDto);
        return "redirect:/main-page/registrations/delete-registration-summary";
    }

    @GetMapping("/registrations/delete-registration-summary")
    public String showDeleteRegistrationSummary() {
        return "/admin/registrations/delete-registration-summary";
    }

    @GetMapping("/blocks/delete-block-summary")
    public String showDeleteBlockSummary() {
        return "/admin/blocks/delete-block-summary";
    }

    @GetMapping("/classes")
    public String getAddClassView(Model model) {
        model.addAttribute("newClass", new ClassDto());
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        return "/admin/classes/add-class";
    }

    @GetMapping("/registrations")
    public String getAddRegistrationView(Model model) {
        model.addAttribute("newRegistration", new RegistrationDto());
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "/admin/registrations/add-registration";
    }

    @PostMapping("/classes/add")
    public String addClass(ClassDto classDto) {
        classService.addClass(classDto);
        return "redirect:/main-page/classes/add-class-summary";
    }

    @PostMapping("/registrations/add")
    public String addRegistration(RegistrationDto registrationDto) {
        registrationService.addRegistration(registrationDto);
        return "redirect:/main-page/registrations/add-registration-summary";
    }

    @GetMapping("/registrations/add-registration-summary")
    public String showAddRegistrationSummary() {
        return "/admin/registrations/add-registration-summary";
    }

    @GetMapping("/classes/add-class-summary")
    public String showAddClassSummary() {
        return "/admin/classes/add-class-summary";
    }

    @GetMapping("/all-classes")
    public String getAllClasses(Model model) {
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        return "/admin/classes/all-classes";
    }

    @GetMapping("/classes/delete-class")
    public String getDeleteClassView(ClassDto classDto, Model model) {
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        model.addAttribute("classToDelete", classDto);
        return "/admin/classes/delete-class";
    }

    @PostMapping("/classes/delete")
    public String deleteClassById(@RequestParam("idToDelete") Long id) {
        ClassDto classById = classService.findClassById(id);
        classService.deleteClassById(classById);
        return "redirect:/main-page/classes/delete-class-summary";
    }

    @GetMapping("/classes/delete-class-summary")
    public String showDeleteClassSummary() {
        return "/admin/classes/delete-class-summary";
    }

    @GetMapping("/all-notifications")
    public String getAllNotifications(Model model) {
        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        return "/admin/notifications/all-notifications";
    }

    @GetMapping("/all-registrations")
    public String getAllRegistrations(Model model) {
        List<RegistrationDto> allRegistrations = registrationService.getAllRegistrations();
        model.addAttribute("registrations", allRegistrations);
        return "/admin/registrations/all-registrations";
    }

    @GetMapping("/notifications/delete-notification")
    public String getDeleteNotificationView(NotificationDto notificationDto, Model model) {
        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        model.addAttribute("notificationToDelete", notificationDto);
        return "/admin/notifications/delete-notification";
    }

    @PostMapping("/notifications/delete")
    public String deleteNotificationById(NotificationDto notificationDto) {
        notificationService.deleteNotificationById(notificationDto);
        return "redirect:/main-page/notifications/delete-notification-summary";
    }

    @GetMapping("/notifications/delete-notification-summary")
    public String showDeleteNotificationSummary() {
        return "/admin/notifications/delete-notification-summary";
    }

    @GetMapping("/notifications")
    public String getAddNotificationView(Model model) {
        model.addAttribute("newNotification", new NotificationDto());
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        return "/admin/notifications/add-notification";
    }

    @PostMapping("/notifications/add")
    public String addNotification(NotificationDto notificationDto) {
        notificationService.addNotification(notificationDto);
        return "redirect:/main-page/notifications/add-notification-summary";
    }

    @GetMapping("/notifications/add-notification-summary")
    public String showAddNotificationSummary() {
        return "/admin/notifications/add-notification-summary";
    }



}
