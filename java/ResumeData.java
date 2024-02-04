// Abstract class for Resume Data
abstract class AbstractResumeData {
    abstract String getName();
    abstract String getPhone();
    abstract String getAddress();
    abstract String getLinks();
    abstract String getEducation();
    abstract String getExperience();
    abstract String getSkills();
    abstract String getAbout();
}

class ResumeData extends AbstractResumeData {
    private String name;
    private String phone;
    private String address;
    private String links;
    private String education;
    private String experience;
    private String skills;
    private String about;

    // Constructors, getters, and setters for the fields

    @Override
    String getName() {
        return name;
    }

    @Override
    String getPhone() {
        return phone;
    }

    @Override
    String getAddress() {
        return address;
    }

    @Override
    String getLinks() {
        return links;
    }

    @Override
    String getEducation() {
        return education;
    }

    @Override
    String getExperience() {
        return experience;
    }

    @Override
    String getSkills() {
        return skills;
    }

    @Override
    String getAbout() {
        return about;
    }

    public void setName(String text) {
        this.name = text;
    }

    public void setPhone(String text) {
        this.phone = text;
    }

    public void setAddress(String text) {
        this.address = text;
    }

    public void setLinks(String text) {
        this.links = text;
    }

    public void setEducation(String text) {
        this.education = text;
    }

    public void setExperience(String text) {
        this.experience = text;
    }

    public void setSkills(String text) {
        this.skills = text;
    }

    public void setAbout(String text) {
        this.about = text;
    }
}
