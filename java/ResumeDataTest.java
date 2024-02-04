import java.util.ArrayList;

public class ResumeDataTest {

    public static void main(String[] args) {
        // Create an instance of ResumeData
        ResumeData resumeData = new ResumeData();

        // Set values using setter methods
        resumeData.setName("John Doe");
        resumeData.setPhone("123-456-7890");
        resumeData.setAddress("123 Main St, City");
        resumeData.setLinks("LinkedIn: linkedin.com/johndoe");
        resumeData.setEducation("BS in Computer Science, University XYZ");
        resumeData.setExperience("Software Engineer, ABC Inc.");
        resumeData.setSkills("Java, Python, SQL");
        resumeData.setAbout("Detail-oriented software engineer with a passion for coding.");

        // Print the values using getter methods
        System.out.println("Name: " + resumeData.getName());
        System.out.println("Phone: " + resumeData.getPhone());
        System.out.println("Address: " + resumeData.getAddress());
        System.out.println("Links: " + resumeData.getLinks());
        System.out.println("Education: " + resumeData.getEducation());
        System.out.println("Experience: " + resumeData.getExperience());
        System.out.println("Skills: " + resumeData.getSkills());
        System.out.println("About: " + resumeData.getAbout());

        // Create an ArrayList to store ResumeData objects
        ArrayList<AbstractResumeData> resumeList = new ArrayList<>();

        // Add the created ResumeData object to the ArrayList
        resumeList.add(resumeData);

        // Display the stored data from the ArrayList
        System.out.println("\nData in the ArrayList:");
        for (AbstractResumeData data : resumeList) {
            System.out.println("Name: " + data.getName());
            System.out.println("Phone: " + data.getPhone());
            System.out.println("Address: " + data.getAddress());
            System.out.println("Links: " + data.getLinks());
            System.out.println("Education: " + data.getEducation());
            System.out.println("Experience: " + data.getExperience());
            System.out.println("Skills: " + data.getSkills());
            System.out.println("About: " + data.getAbout());
        }
    }
}
