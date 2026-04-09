package EcoSphere.lessons;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class LessonProgressController {

    private final LessonProgressService service;
    private final LessonProgressRepository lessonProgressRepository;

    public LessonProgressController(
            LessonProgressService service,
            LessonProgressRepository lessonProgressRepository
    ) {
        this.service = service;
        this.lessonProgressRepository = lessonProgressRepository;
    }

    // 🔥 Mark complete
    @PostMapping("/{lessonId}")
    public String completeLesson(
            @RequestHeader("User-Email") String userEmail,
            @PathVariable Long lessonId
    ) {
        service.markComplete(userEmail, lessonId);
        return "Marked as completed";
    }

    // 🔥 Get completed lessons
    @GetMapping
    public List<Long> getCompleted(
            @RequestHeader("User-Email") String userEmail
    ) {
        return service.getCompletedLessons(userEmail);
    }

    // 🔥 ADD THIS METHOD (USER ENGAGEMENT)
    @GetMapping("/stats")
    public long getTotalCompletions() {
        return lessonProgressRepository.count();
    }
    @GetMapping("/admin/all")
    public List<LessonProgress> getAllProgress() {
        return lessonProgressRepository.findAll();
    }
}