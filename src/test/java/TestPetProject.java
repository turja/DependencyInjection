import com.pet.project.MyPetProject;
import com.pet.project.Post;
import com.pet.project.TwitterPost;
import org.junit.Test;

/**
 * Created by Tushar on 6/25/20.
 */

public class TestPetProject {
    @Test
    public void testTwitterPost() {
        MyPetProject project = new MyPetProject();
        project.initBeans();

        Post twitterPost = project.announce(MyPetProject.ePostType.TWITTER);

    //  will test for singleton prototype
    }

    @Test
    public void testRedditPost() {
        MyPetProject project = new MyPetProject();
        project.initBeans();

        Post twitterPost = project.announce(MyPetProject.ePostType.REDDIT);

        //will test for prototype pattern, test for two objects being different
    }
}
