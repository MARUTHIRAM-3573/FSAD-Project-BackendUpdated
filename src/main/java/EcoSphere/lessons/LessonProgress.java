package EcoSphere.lessons;

import jakarta.persistence.*;

@Entity
public class LessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail; // 🔥 identify user
    private Long lessonId;

    public LessonProgress() {}

    public LessonProgress(String userEmail, Long lessonId) {
        this.userEmail = userEmail;
        this.lessonId = lessonId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getLessonId() {
        return lessonId;
    }
}