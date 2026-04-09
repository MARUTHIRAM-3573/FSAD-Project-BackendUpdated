package EcoSphere.lessons;

import java.util.List;

public interface LessonProgressService {

    void markComplete(String userEmail, Long lessonId);

    List<Long> getCompletedLessons(String userEmail);
}