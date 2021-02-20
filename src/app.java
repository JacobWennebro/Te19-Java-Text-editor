import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class app {
    public static JFrame frame = new JFrame("NoTE19pad (*editing unnamed.txt)");
    public JPanel panel1;
    private JButton openButton;
    private JButton fontButtonPlus;
    private JButton fontButtonMinus;
    private JToolBar Toolbar;
    private JTextPane textPane;
    private JButton saveButton;

    private int fontSize;
    private File activeFile = null;

    public app() {
        openButton.addActionListener(actionEvent -> {
            JFileChooser chooser = new JFileChooser();

            if(chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                activeFile = file;

                frame.setTitle(String.format("NoTE19pad [*editing %s]", file.getName()));

                Scanner reader;
                StringBuilder fileContent = new StringBuilder();

                try {
                    reader = new Scanner(file);
                    while(reader.hasNextLine()) fileContent.append(reader.nextLine()).append("\n");

                    reader.close();
                    textPane.setText(fileContent.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        fontButtonPlus.addActionListener(actionEvent -> setFontSize(++fontSize));

        fontButtonMinus.addActionListener(actionEvent -> setFontSize(--fontSize));

        saveButton.addActionListener(actionEvent -> {
            if(activeFile != null) {
                try {
                    saveFile(activeFile, textPane.getText());
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    saveFile(null, textPane.getText());
                    JOptionPane.showMessageDialog(null, "Saved unnamed.txt to desktop.");
                } catch(IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "There was an issuing saving unnamed.txt to your desktop.");
                }
            }
        });
    }

    public void setFontSize(int size) {
        Font font = new Font("Comic Sans MS", Font.PLAIN, size);
        this.textPane.setFont(font);
        this.fontSize = size;
    }

    public void saveFile(File file, String contents) throws IOException {
        FileWriter writer;

        if(file == null) writer = new FileWriter(new File(System.getProperty("user.home"), "unnamed.txt"));
        else writer = new FileWriter(file);

        writer.write(contents);
        writer.close();
    }

    public static void main(String[] args) {
        app myapp = new app();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300,800);
        frame.setContentPane(myapp.panel1);

        myapp.setFontSize(24);
        myapp.Toolbar.setBackground(Color.BLUE);

        frame.setVisible(true);
    }
}
