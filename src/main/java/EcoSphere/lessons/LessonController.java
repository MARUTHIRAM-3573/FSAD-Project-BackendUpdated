package EcoSphere.lessons;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "Lesson deleted successfully";
    }
    @GetMapping("/category/{category}")
    public List<Lesson> getLessonsByCategory(@PathVariable String category) {
        return lessonService.getLessonsByCategory(category);
    }
    @GetMapping("/search")
    public List<Lesson> searchLessons(@RequestParam String keyword) {
        return lessonService.searchLessons(keyword);
    }
    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        return lessonService.updateLesson(id, lesson);
    }
    
    
}