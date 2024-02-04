import javax.swing.*;
import java.awt.*;

public class ResumeStyleSelector extends JFrame {
    public ResumeStyleSelector(ResumeBuilderApp resumeBuilderApp) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        setMinimumSize(new Dimension(800, 800));
        setMaximumSize(new Dimension(800, 800));

        ImageIcon style1Icon = new ImageIcon("src/main/draftResumes/style1.png");
        JButton style1Button = new JButton(style1Icon);
        style1Button.setPreferredSize(new Dimension(620, 700));
        style1Button.addActionListener(e -> {
            // Create an instance of AbstractResumeData using the createResumeData method
            AbstractResumeData resumeData = resumeBuilderApp.createResumeData();

            // Call the generateResume method with the created resumeData
            resumeBuilderApp.generateResume("src/main/draftResumes/style1.pdf", resumeData);
        });
        add(style1Button);

        ImageIcon style2Icon = new ImageIcon("src/main/draftResumes/style2.png");
        JButton style2Button = new JButton(style2Icon);
        style2Button.setPreferredSize(new Dimension(620, 700));
        style2Button.addActionListener(e -> {
            // Create an instance of AbstractResumeData using the createResumeData method
            AbstractResumeData resumeData = resumeBuilderApp.createResumeData();

            // Call the generateResume method with the created resumeData
            resumeBuilderApp.generateResume("src/main/draftResumes/style2.pdf", resumeData);
        });
        add(style2Button);

        pack();
        setVisible(true);
    }
}
