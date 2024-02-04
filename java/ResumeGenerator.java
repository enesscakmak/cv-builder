import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ResumeGenerator {
    public static void main(String[] args) {
        ResumeBuilderApp app = new ResumeBuilderApp();
        app.showStyleSelectionDialog();
    }

    public static void generateResume(String templatePath, AbstractResumeData resumeData) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    file = new File(file.getAbsolutePath() + ".pdf");
                }

                PdfDocument draftPdf = new PdfDocument(new PdfReader(templatePath), new PdfWriter(file));
                PdfAcroForm form = PdfAcroForm.getAcroForm(draftPdf, true);

                setFormData(form, (ResumeData) resumeData);

                draftPdf.close();

                JOptionPane.showMessageDialog(null, "Resume generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating resume.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void setFormData(PdfAcroForm form, ResumeData resumeData) {
        form.getField("Name").setValue(resumeData.getName());
        form.getField("Phone").setValue(resumeData.getPhone());
        form.getField("Address").setValue(resumeData.getAddress());
        form.getField("Links").setValue(resumeData.getLinks());
        form.getField("Education").setValue(resumeData.getEducation());
        form.getField("Experience").setValue(resumeData.getExperience());
        form.getField("Skills").setValue(resumeData.getSkills());
        form.getField("About").setValue(resumeData.getAbout());
    }
}
