package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.*;
import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserRole;
import com.example.coursesmanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
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
        return "redirect:/main-page/courses/delete-course";
    }

    @GetMapping("/courses/update-course")
    public String getUpdateCourseView(Model model, CourseDto courseDto) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "/admin/courses/update-course";
    }

    @GetMapping("/courses/update/{courseId}")
    public String courseUpdateById(Model model, @PathVariable Long courseId) {
        CourseDto courseById = courseService.getCourseById(courseId);
        model.addAttribute("courseById", courseById);
        return "/admin/courses/update-course-by-id";
    }

    @PostMapping("/courses/update")
    public String updateCourseById(@RequestParam("idToUpdate") Long id,
                                   @RequestParam("image") MultipartFile file, @RequestParam("title") String title) {
        CourseDto courseById = courseService.findCourseById(id);
        courseById.setImageName(file.getOriginalFilename());
        courseById.setTitle(title);
        courseService.updateCourseById(courseById, file);
        return "redirect:/main-page/courses/update-course";
    }

    @GetMapping("/registrations/update-registration")
    public String getUpdateRegistrationView(Model model, RegistrationDto registrationDto) {
        List<RegistrationDto> allRegistrations = registrationService.getAllRegistrations();
        model.addAttribute("registrations", allRegistrations);
        return "/admin/registrations/update-registration";
    }

    @GetMapping("/registrations/update/{registrationId}")
    public String registrationUpdateById(Model model, @PathVariable Long registrationId) {
        RegistrationDto registrationById = registrationService.findRegistrationById(registrationId);
        model.addAttribute("registrationById", registrationById);
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "/admin/registrations/update-registration-by-id";
    }
    @PostMapping("/registrations/update")
    public String updateRegistrationById(@RequestParam("idToUpdate") Long id,
                                         @RequestParam("userId") Long userId,
                                         @RequestParam("courseId") Long courseId) {
        RegistrationDto registrationById = registrationService.findRegistrationById(id);
        registrationById.setUserId(userId);
        registrationById.setCourseId(courseId);
        registrationService.updateRegistrationById(registrationById);
        return "redirect:/main-page/registrations/update-registration";
    }

    @GetMapping("/registrations/update-registration-summary")
    public String showUpdateRegistrationSummary() {
        return "/admin/registrations/update-registration-summary";
    }

    @GetMapping("/users/update-user")
    public String getUpdateUserView(UserDto userDto, Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "/admin/users/update-user";
    }

    @GetMapping("/users/update/{userId}")
    public String userUpdateById(Model model, @PathVariable Long userId) {
        UserDto userById = userService.findUserById(userId);
        model.addAttribute("userById", userById);
        return "/admin/users/update-user-by-id";
    }

    @PostMapping("/users/update")
    public String updateUserById(@RequestParam("idToUpdate") Long id,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") UserRole userRole,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("active")ActiveUser activeUser) {
        UserDto userById = userService.findUserById(id);
        userById.setLogin(login);
        userById.setPassword(password);
        userById.setUserRole(userRole);
        userById.setName(name);
        userById.setSurname(surname);
        userById.setActiveUser(activeUser);
        userService.updateUserById(userById);
        return "redirect:/main-page/users/update-user";
    }

    @GetMapping("/blocks/update-block")
    public String getUpdateBlockView(Model model, BlockDto blockDto) {
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        return "/admin/blocks/update-block";
    }

    @GetMapping("/blocks/update/{blockId}")
    public String blockUpdateById(Model model, @PathVariable Long blockId) {
        BlockDto blockById = blockService.findBlockById(blockId);
        model.addAttribute("blockById", blockById);
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "/admin/blocks/update-block-by-id";
    }

    @PostMapping("/blocks/update")
    public String updateBlockById(@RequestParam("idToUpdate") Long id,
                                  @RequestParam("title") String title,
                                  @RequestParam("courseId") Long courseId) {
        BlockDto blockById = blockService.findBlockById(id);
        blockById.setBlockTitle(title);
        blockById.setCourseId(courseId);
        blockService.updateBlockById(blockById);
        return "redirect:/main-page/blocks/update-block";
    }

    @GetMapping("/classes/update-class")
    public String getUpdateClassView(Model model, ClassDto classDto) {
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        return "/admin/classes/update-class";
    }

    @GetMapping("/classes/update/{classId}")
    public String classUpdateById(Model model, @PathVariable Long classId) {
        ClassDto classById = classService.findClassById(classId);
        model.addAttribute("classById", classById);
        List<BlockDto> allBlocks = blockService.getAllBlocks();
        model.addAttribute("blocks", allBlocks);
        return "/admin/classes/update-class-by-id";
    }

    @PostMapping("/classes/update")
    public String updateClassById(@RequestParam("idToUpdate") Long id,
                                  @RequestParam("topic") String topic,
                                  @RequestParam("date") LocalDate date,
                                  @RequestParam("time") LocalTime time,
                                  @RequestParam("blockId") Long blockId) {
        ClassDto classById = classService.findClassById(id);
        classById.setTopic(topic);
        classById.setDate(date);
        classById.setTime(time);
        classById.setBlockId(blockId);
        classService.updateClassById(classById);
        return "redirect:/main-page/classes/update-class";
    }


    @GetMapping("/notifications/update-notification")
    public String getUpdateNotificationView(Model model, NotificationDto notificationDto) {
        List<NotificationDto> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        return "/admin/notifications/update-notification";
    }

    @GetMapping("/notifications/update/{notificationId}")
    public String notificationUpdateById(Model model, @PathVariable Long notificationId) {
        NotificationDto notificationById = notificationService.findNotificationById(notificationId);
        model.addAttribute("notificationById", notificationById);
        List<ClassDto> allClasses = classService.getAllClasses();
        model.addAttribute("classes", allClasses);
        return "/admin/notifications/update-notification-by-id";
    }

    @PostMapping("/notifications/update")
    public String updateNotificationById(@RequestParam("idToUpdate") Long id,
                                         @RequestParam("topic") String topic,
                                         @RequestParam("text") String text,
                                         @RequestParam("classId") Long classId) {
        NotificationDto notificationById = notificationService.findNotificationById(id);
        notificationById.setTopic(topic);
        notificationById.setText(text);
        notificationById.setClassId(classId);
        notificationService.updateNotificationById(notificationById);
        return "redirect:/main-page/notifications/update-notification";
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
    public String deleteUserById(@RequestParam("idToDelete") Long id) {
        UserDto userById = userService.findUserById(id);
        userService.deleteUserById(userById);
        return "redirect:/main-page/users/delete-user";
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
        return "redirect:/main-page/blocks/delete-block";
    }

    @PostMapping("/registrations/delete")
    public String deleteRegistrationById(@RequestParam("idToDelete") Long id) {
        RegistrationDto registrationById = registrationService.findRegistrationById(id);
        registrationService.deleteRegistrationById(registrationById);
        return "redirect:/main-page/registrations/delete-registration";
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
        return "redirect:/main-page/classes/delete-class";
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
    public String deleteNotificationById(@RequestParam("idToDelete") Long id) {
        NotificationDto notificationById = notificationService.findNotificationById(id);
        notificationService.deleteNotificationById(notificationById);
        return "redirect:/main-page/notifications/delete-notification";
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
