import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ResumeBuilderApp {
    JTextField nameField;
    JTextField phoneField;
    JTextArea aboutArea;
    JTextArea addressArea;
    JTextArea educationArea;
    JTextArea experienceArea;
    JTextArea skillsArea;
    JTextArea linkArea;

    private final ArrayList<AbstractResumeData> resumeList = new ArrayList<>();

    public ResumeBuilderApp() {
        nameField = new JTextField();
        phoneField = new JTextField();
        aboutArea = new JTextArea();
        addressArea = new JTextArea();
        educationArea = new JTextArea();
        experienceArea = new JTextArea();
        skillsArea = new JTextArea();
        linkArea = new JTextArea();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResumeBuilderApp::createAndShowGUI);
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Resume Builder App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the application when the window is closed

        // Stackoverflow said this is not recommended but it works (at least on 1920x1080)
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setMaximumSize(new Dimension(500, 600));

        ResumeBuilderApp app = new ResumeBuilderApp();
        app.addComponentsToPane(frame.getContentPane());

        frame.setLocationRelativeTo(null);  // To center window on screen

        frame.pack();
        frame.setVisible(true);
    }

    void addComponentsToPane(Container pane) {
        pane.setLayout(new GridLayout(9, 2));

        addCenteredLabel(pane, "Full Name:");
        nameField = new JTextField();
        pane.add(nameField);

        addCenteredLabel(pane, "Phone Number:");
        phoneField = new JTextField();
        pane.add(phoneField);

        addCenteredLabel(pane, "Address:");
        addressArea = new JTextArea();
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane scrollAddressPane = new JScrollPane(addressArea);  // Add a scroll bar
        pane.add(scrollAddressPane);

        addCenteredLabel(pane, "Links(LinkedIn, GitHub etc.):");
        linkArea = new JTextArea();
        linkArea.setLineWrap(true);
        linkArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(linkArea);
        pane.add(scrollPane);

        addCenteredLabel(pane, "Education:");
        educationArea = new JTextArea();
        educationArea.setLineWrap(true);
        educationArea.setWrapStyleWord(true);
        JScrollPane scrollEducationPane = new JScrollPane(educationArea);
        pane.add(scrollEducationPane);

        addCenteredLabel(pane, "Experience:");
        experienceArea = new JTextArea();
        experienceArea.setLineWrap(true);
        experienceArea.setWrapStyleWord(true);
        JScrollPane scrollExperiencePane = new JScrollPane(experienceArea);
        pane.add(scrollExperiencePane);

        addCenteredLabel(pane, "Skills:");
        skillsArea = new JTextArea();
        skillsArea.setLineWrap(true);
        skillsArea.setWrapStyleWord(true);
        JScrollPane scrollSkillsPane = new JScrollPane(skillsArea);
        pane.add(scrollSkillsPane);

        addCenteredLabel(pane, "About:");
        aboutArea = new JTextArea();
        aboutArea.setLineWrap(true);
        aboutArea.setWrapStyleWord(true);
        JScrollPane scrollAboutPane = new JScrollPane(aboutArea);
        pane.add(scrollAboutPane);

        JButton chooseStyleButton = new JButton("Choose Style");
        chooseStyleButton.addActionListener(e -> showStyleSelectionDialog());  // Show the style selection dialog when the button is clicked
        pane.add(chooseStyleButton);
    }

    private void addCenteredLabel(Container container, String text) {
        JLabel label = new JLabel(text);
        // Center the label text
        label.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(label);
    }


    AbstractResumeData createResumeData() {
        ResumeData resumeData = new ResumeData();

        // Retrieve data from UI components and set them in the ResumeData object
        resumeData.setName(nameField.getText());
        resumeData.setPhone(phoneField.getText());
        resumeData.setAddress(addressArea.getText());
        resumeData.setLinks(linkArea.getText());
        resumeData.setEducation(educationArea.getText());
        resumeData.setExperience(experienceArea.getText());
        resumeData.setSkills(skillsArea.getText());
        resumeData.setAbout(aboutArea.getText());

        return resumeData;
    }


    void showStyleSelectionDialog() {
        JFrame styleFrame = new JFrame("Choose Style");
        styleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        styleFrame.setLayout(new GridLayout(1, 2));
        styleFrame.setMinimumSize(new Dimension(800, 800));
        styleFrame.setMaximumSize(new Dimension(800, 800));

        ImageIcon style1Icon = new ImageIcon("src/main/draftResumes/style1.png");
        JButton style1Button = new JButton(style1Icon);
        style1Button.setPreferredSize(new Dimension(620, 700)); // Set preferred size
        style1Button.addActionListener(e -> {
            AbstractResumeData resumeData = createResumeData();
            generateResume("src/main/draftResumes/style1.pdf", resumeData);
        });
        styleFrame.add(style1Button);

        ImageIcon style2Icon = new ImageIcon("src/main/draftResumes/style2.png");
        JButton style2Button = new JButton(style2Icon);
        style2Button.setPreferredSize(new Dimension(620, 700)); // Set preferred size
        style2Button.addActionListener(e -> {
            AbstractResumeData resumeData = createResumeData();
            generateResume("src/main/draftResumes/style2.pdf", resumeData);
        });
        styleFrame.add(style2Button);
        styleFrame.pack();
        styleFrame.setLocationRelativeTo(null);
        styleFrame.setVisible(true);
    }

    void generateResume(String templatePath, AbstractResumeData resumeData) {
        try {
            JFileChooser fileChooser = new JFileChooser();  // Create a file chooser
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".pdf")) {  // If the file name does not end with .pdf, append it
                    file = new File(file.getAbsolutePath() + ".pdf");  // To make sure that the file is saved as a PDF
                }

                PdfDocument draftPdf = new PdfDocument(new PdfReader(templatePath), new PdfWriter(file));  // Create a PDF document
                PdfAcroForm form = PdfAcroForm.getAcroForm(draftPdf, true);  // Get the form from the PDF document

                setFormFieldValue(form, "Name", resumeData.getName());
                setFormFieldValue(form, "Phone", resumeData.getPhone());
                setFormFieldValue(form, "Address", resumeData.getAddress());
                setFormFieldValue(form, "Links", resumeData.getLinks());
                setFormFieldValue(form, "Education", resumeData.getEducation());
                setFormFieldValue(form, "Experience", resumeData.getExperience());
                setFormFieldValue(form, "Skills", resumeData.getSkills());
                setFormFieldValue(form, "About", resumeData.getAbout());

                draftPdf.close();

                JOptionPane.showMessageDialog(null, "Resume generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating resume.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFormFieldValue(PdfAcroForm form, String fieldName, String value) {
        PdfFormField field = form.getField(fieldName);
        if (field != null) {
            field.setValue(value);
        } else {
            System.out.println("Field '" + fieldName + "' not found in the PDF template.");
        }
    }
}
