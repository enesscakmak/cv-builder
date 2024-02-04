import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResumeBuilderAppTest {

    @Test
    void testGenerateResume() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an instance of ResumeBuilderApp
        ResumeBuilderApp resumeBuilderApp = new ResumeBuilderApp();

        // Set some sample data (you can customize this)
        resumeBuilderApp.nameField.setText("John Doe");
        resumeBuilderApp.phoneField.setText("123-456-7890");
        resumeBuilderApp.addressArea.setText("123 Main St, City");
        resumeBuilderApp.linkArea.setText("LinkedIn: linkedin.com/johndoe");
        resumeBuilderApp.educationArea.setText("BS in Computer Science, University XYZ");
        resumeBuilderApp.experienceArea.setText("Software Engineer, ABC Inc.");
        resumeBuilderApp.skillsArea.setText("Java, Python, SQL");
        resumeBuilderApp.aboutArea.setText("Detail-oriented software engineer with a passion for coding.");

        // Create an instance of AbstractResumeData using the createResumeData method
        AbstractResumeData resumeData = resumeBuilderApp.createResumeData();

        // Call the generateResume method with the created resumeData
        resumeBuilderApp.generateResume("src/main/draftResumes/style1.pdf", resumeData);

        // Capture the output
        String output = outputStream.toString().trim();

        // Check if the success message is printed
        assertEquals("Resume generated successfully!", output);

        // Reset System.out to the original PrintStream
        System.setOut(System.out);
    }
}
