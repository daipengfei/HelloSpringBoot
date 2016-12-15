import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/*********************************
 *                               *
 Created by daipengfei on 16/11/17.
 *                               *
 ********************************/

public class TestUnit {
    @Test
    public void  test(){
        System.out.println("hi!");
    }

    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        Request request = Request.method(TestUnit.class,"test");
        core.run(request);
    }


}
