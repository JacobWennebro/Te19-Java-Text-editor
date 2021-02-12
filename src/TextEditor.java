import javax.swing.*;

public class TextEditor {

    public static void main(String[] args) {

        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);


        JTextArea text = new JTextArea();
        frame.getContentPane().add(text); // Adds Button to content pane of frame
        frame.setVisible(true);

    }

}
