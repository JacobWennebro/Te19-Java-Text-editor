import javax.swing.*;

public class app {
    private JTextArea textArea1;
    public JPanel panel1;
    private JButton fileButton;
    private JButton copyButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("app");
        frame.setContentPane(new app().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
