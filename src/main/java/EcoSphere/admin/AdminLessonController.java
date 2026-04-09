package EcoSphere.admin;

import org.springframework.web.bind.annotation.*;

import EcoSphere.lessons.Lesson;
import EcoSphere.lessons.LessonService;

@RestController
@RequestMapping("/api/admin/lessons")
public class AdminLessonController {

    private final LessonService lessonService;

    public AdminLessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "Lesson deleted successfully";
    }
}