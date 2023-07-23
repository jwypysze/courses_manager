package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.rest.request.SaveCourseRequest;
import com.example.coursesmanagement.model.rest.response.CourseResponse;
import com.example.coursesmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.coursesmanagement.controller.ApiConstraints.COURSE;

@RestController
@RequestMapping(COURSE)
@RequiredArgsConstructor
public class CourseController {

    public static final String FIND_COURSE_BY_ID = "/{id}";
    public static final String SAVE_COURSE = "/save";
    private final CourseService courseService;

    @GetMapping(FIND_COURSE_BY_ID)
    public ResponseEntity<CourseResponse> findCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(CourseResponse.from(courseService.findCourseById(id)));
    }

    @PostMapping(SAVE_COURSE)
    public ResponseEntity<CourseResponse> saveCourse(@RequestBody SaveCourseRequest saveCourseRequest) {
        return ResponseEntity.ok(CourseResponse.from(courseService.saveCourse(SaveCourseRequest.toDto(saveCourseRequest))));
    }
}
