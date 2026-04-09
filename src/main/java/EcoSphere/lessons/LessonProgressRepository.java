package EcoSphere.lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    List<LessonProgress> findByUserEmail(String userEmail);

    boolean existsByUserEmailAndLessonId(String userEmail, Long lessonId);

    // 🔥 THIS LINE (already provided by JpaRepository, but safe to include)
    long count();
    
    List<LessonProgress> findAll();
}