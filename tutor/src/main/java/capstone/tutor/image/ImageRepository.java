package capstone.tutor.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByImagePathIn(Collection<String> imagePaths);
}
