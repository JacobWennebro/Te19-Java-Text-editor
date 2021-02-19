import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextPane textPane1;
    private JButton saveButton;

    private int fontSize;
    private File activeFile = null;

    public app() {
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();

                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    activeFile = file;

                    frame.setTitle("NoTE19pad [*editing " + file.getName() + "]");

                    Scanner myReader = null;
                    String fileContent = "";
                    try {
                        myReader = new Scanner(file);

                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            fileContent += data+"\n";
                        }

                        myReader.close();
                        textPane1.setText(fileContent);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        fontButtonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setFontSize(++fontSize);
            }
        });
        fontButtonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setFontSize(--fontSize);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(activeFile != null) {
                    try {
                        saveFile(activeFile, textPane1.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Saved unnamed.txt to desktop");
                    try {
                        saveFile(null, textPane1.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setFontSize(int size) {
        Font font = new Font("Comic Sans MS", Font.TRUETYPE_FONT, size);
        this.textPane1.setFont(font);
        this.fontSize = size;
    }

    public void saveFile(File file, String contents) throws IOException {
        FileWriter myWriter;

        if(file == null) {
            myWriter = new FileWriter(new File(System.getProperty("user.home"), "unnamed.txt"));
        } else {
            myWriter = new FileWriter(file);
        }

        myWriter.write(contents);
        myWriter.close();
    }

    public static void main(String[] args) {
        app myapp = new app();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300,800);
        frame.setContentPane(myapp.panel1);

        myapp.setFontSize(24);
        myapp.Toolbar.setBackground(Color.BLUE);

        frame.setVisible(true);
    }
}
