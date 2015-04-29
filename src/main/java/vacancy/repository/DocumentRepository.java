package vacancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vacancy.entity.Document;

/**
 * Created by felix on 28.04.15.
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
