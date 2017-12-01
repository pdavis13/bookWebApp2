package edu.wctc.distjava.jgl.bookwebapp.repository;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jlombardo
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
    public abstract List<Author> findByAuthorName(@Param ("authorName") String authorName);
            
	// example of a projection query
    @Query("SELECT a.authorName FROM Author a")
    public Object[] findAllWithNameOnly();
}
