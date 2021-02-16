import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TextEditor {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setContentPane(new app().panel1);

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                frame.setTitle(frame.getTitle() + "!");
            }
        }, 0, 1, TimeUnit.SECONDS);

        frame.setVisible(true);

    }

}
