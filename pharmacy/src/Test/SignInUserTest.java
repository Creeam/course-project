package Test;

import Controllers.Controller;
import org.junit.*;
import static org.junit.Assert.*;

public class SignInUserTest {

    @Test
    public void testSignInUser() {
        Controller controller = new Controller();
        assertEquals(true, controller.signIn("qwe", "1234"));
    }

}
