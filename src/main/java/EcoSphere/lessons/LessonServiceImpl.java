package EcoSphere.lessons;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
    
    @Override
    public List<Lesson> getLessonsByCategory(String category) {
        return lessonRepository.findByCategory(category);
    }
    @Override
    public List<Lesson> searchLessons(String keyword) {
        return lessonRepository.findByTitleContainingIgnoreCase(keyword);
    }
    @Override
    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        lesson.setTitle(updatedLesson.getTitle());
        lesson.setContent(updatedLesson.getContent());
        lesson.setCategory(updatedLesson.getCategory());

        return lessonRepository.save(lesson);
    }
}