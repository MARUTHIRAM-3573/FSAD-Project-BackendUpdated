package EcoSphere.lessons;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonProgressServiceImpl implements LessonProgressService {

    private final LessonProgressRepository repo;

    public LessonProgressServiceImpl(LessonProgressRepository repo) {
        this.repo = repo;
    }

    @Override
    public void markComplete(String userEmail, Long lessonId) {

        if (!repo.existsByUserEmailAndLessonId(userEmail, lessonId)) {
            repo.save(new LessonProgress(userEmail, lessonId));
        }
    }

    @Override
    public List<Long> getCompletedLessons(String userEmail) {
        return repo.findByUserEmail(userEmail)
                .stream()
                .map(LessonProgress::getLessonId)
                .collect(Collectors.toList());
    }
}