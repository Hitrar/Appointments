import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentBookingApp {
    private static WeeklyApptSchedule weeklySchedule = new WeeklyApptSchedule();
    private static Scanner scanner = new Scanner(System.in);

    private static CourseStaffManager staffManager = new CourseStaffManager();
//    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            handleChoice(choice);
        } while (choice != 6);
    }

// Method to display the menu options
    private static void displayMenu() {
        System.out.println("\n*** Appointment Booking Application ***");
        System.out.println("1. Book an appointment");
        System.out.println("2. Cancel an appointment");
        System.out.println("3. Reschedule an appointment");
        System.out.println("4. Display appointments");
        System.out.println("5. Display course staff");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
//Mthod to parse selected options on the menu using switch statement
    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                bookAppointment();
                break;
            case 2:
                cancelAppointment();
                break;
            case 3:
                rescheduleAppointment();
                break;
            case 4:
                displayAppointments();
                break;
            case 5:
                displayCourseStaff();
                break;
            case 6:
                System.out.println("Exiting the application...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    // method to add an appointment
    private static void bookAppointment() {
        System.out.print("Enter the course staff name: ");
        String staffName = scanner.nextLine();
        CourseStaff staff = staffManager.getCourseStaffByName(staffName);
// checks if staff selected is valid
        if (staff == null) {
            System.out.println("Invalid course staff name.");
            return;
        }

        System.out.print("Enter the day (Monday, Tuesday, Wednesday, Thursday, Friday): ");
        String dayString = scanner.nextLine();
        WeeklyApptSchedule.Day day = WeeklyApptSchedule.Day.valueOf(dayString.toUpperCase());

        System.out.print("Enter the time slot (0-8): ");
        int timeSlot = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the venue: ");
        String venue = scanner.nextLine();

        System.out.print("Enter the purpose: ");
        String purpose = scanner.nextLine();

        Appointment appointment = new Appointment(name, venue, purpose, staffName);
        if (weeklySchedule.addAppointment(appointment, day, timeSlot)) {
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("Unable to book the appointment. Please try a different time slot.");
        }
    }
//  method to show the staff members
    private static void displayCourseStaff() {
        List<CourseStaff> courseStaff = staffManager.getCourseStaff();
        System.out.println("\nCourse Staff:");
        for (CourseStaff staff : courseStaff) {
            System.out.println(staff.name + " (" + staff.role + ")");
        }
    }

//  method to cancell appointments
    private static void cancelAppointment() {
        System.out.print("Enter the day (Monday, Tuesday, Wednesday, Thursday, Friday): ");
        String dayString = scanner.nextLine();
        WeeklyApptSchedule.Day day = WeeklyApptSchedule.Day.valueOf(dayString.toUpperCase());

        System.out.print("Enter the time slot (0-8): ");
        int timeSlot = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        weeklySchedule.cancelAppointment(day, timeSlot);
        System.out.println("Appointment canceled.");
    }

    private static void rescheduleAppointment() {
        System.out.print("Enter the day (Monday, Tuesday, Wednesday, Thursday, Friday): ");
        String dayString = scanner.nextLine();
        WeeklyApptSchedule.Day day = WeeklyApptSchedule.Day.valueOf(dayString.toUpperCase());

        System.out.print("Enter the current time slot (0-8): ");
        int oldTimeSlot = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the new time slot (0-8): ");
        int newTimeSlot = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (weeklySchedule.rescheduleAppointment(day, oldTimeSlot, newTimeSlot)) {
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("Unable to reschedule the appointment. Please try again.");
        }
    }

    private static void displayAppointments() {
        weeklySchedule.displayWeeklyAppointments();
    }

    static class CourseStaff {
        private String name;
        private String role; // e.g., "Faculty Instructor", "Faculty Intern"
        private WeeklyApptSchedule schedule;

        public CourseStaff(String name, String role) {
            this.name = name;
            this.role = role;
            this.schedule = new WeeklyApptSchedule();
        }

        // Getters and setters for name, role, and schedule

        // Add methods to interact with the schedule
        public boolean bookAppointment(Appointment appt, WeeklyApptSchedule.Day day, int timeSlot) {
            return schedule.addAppointment(appt, day, timeSlot);
        }

        public void cancelAppointment(WeeklyApptSchedule.Day day, int timeSlot) {
            schedule.cancelAppointment(day, timeSlot);
        }
//      method to reschedule an appointment
        public boolean rescheduleAppointment(WeeklyApptSchedule.Day day, int oldTimeSlot, int newTimeSlot) {
            return schedule.rescheduleAppointment(day, oldTimeSlot, newTimeSlot);
        }
//      displays weekly appointments
        public void displaySchedule() {
            System.out.println(name + " (" + role + ") Schedule:");
            schedule.displayWeeklyAppointments();
        }
    }
// class to manage course staff
    static class CourseStaffManager {
        private List<CourseStaff> courseStaff;

        public CourseStaffManager() {
            courseStaff = new ArrayList<>();
            // Initialize the list with course staff members
            courseStaff.add(new CourseStaff("John Doe", "Faculty Instructor"));
            courseStaff.add(new CourseStaff("Jane Smith", "Faculty Instructor"));
            courseStaff.add(new CourseStaff("Bob Johnson", "Faculty Instructor"));
            courseStaff.add(new CourseStaff("Alice Lee", "Faculty Intern"));
            courseStaff.add(new CourseStaff("Tom Wilson", "Faculty Intern"));
        }

        public List<CourseStaff> getCourseStaff() {
            return courseStaff;
        }
//      get name of staff member for an appointment
        public CourseStaff getCourseStaffByName(String name) {
            for (CourseStaff staff : courseStaff) {
                if (staff.name.equalsIgnoreCase(name)) {
                    return staff;
                }
            }
            return null;
        }
    }
}