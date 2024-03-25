
/**
 * A class to represent an appointment
 **/
public class Appointment {
    private String studentName;
    private String venue;
    private String purpose;
    private String instructor;

    // Constructor
    public Appointment(String name, String location, String whatFor, String instructor) {
        studentName = name;
        venue = location;
        purpose = whatFor;
        this.instructor = instructor;
    }

    // Accessor method for the studentName variable
    public String getStudentName() {
        return studentName;
    }

    // Accessor method for the venue variable
    public String getVenue() {
        return venue;
    }

    // Accessor method for the purpose variable
    public String getPurpose() {
        return purpose;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    // Mutator method for the studentName variable
    public void setStudentName(String name) {
        studentName = name;
    }

    // Mutator method for the venue variable
    public void setVenue(String location) {
        venue = location;
    }

    // Mutator method for the purpose variable
    public void setPurpose(String whatFor) {
        purpose = whatFor;
    }

    // A method to display the details of the appointment
    public void displayDetails() {
        System.out.println("Student name: " + getStudentName());
        System.out.println("Venue: " + getVenue());
        System.out.println("Purpose: " + getPurpose());
        System.out.println("StaffName: " + getInstructor());
    }

    // A method to return a String representation of the appointment
    public String toString() {
        return ("Name: " + getStudentName() + ", venue: " + getVenue() +
                ", purpose: " + getPurpose() + "  StaffName: " + getInstructor());
    }
}