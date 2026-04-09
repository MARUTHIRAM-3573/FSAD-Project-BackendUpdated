package EcoSphere.lessons;

import java.util.List;

public interface LessonService {

    Lesson createLesson(Lesson lesson);

    List<Lesson> getAllLessons();
    
    List<Lesson> getLessonsByCategory(String category);
    
    List<Lesson> searchLessons(String keyword);

    Lesson getLessonById(Long id);
    
    Lesson updateLesson(Long id, Lesson lesson);

    void deleteLesson(Long id);
}