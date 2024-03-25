public class WeeklyApptSchedule {

    private DailyApptSchedule[] apptsForTheWeek;

    public static final int NUM_DAYS = 5;
    public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday"};

    public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY}

    ;

    // No-arg constructor creates an array of DailyApptSchedule
    // for the week
    public WeeklyApptSchedule() {
        apptsForTheWeek = new DailyApptSchedule[NUM_DAYS];
        for (int i = 0; i < NUM_DAYS; i++) {
            apptsForTheWeek[i] = new DailyApptSchedule();
        }
    }

    // display weekly appointments
    // Display the set of appointsments for the week by showing
    // the schedule for each day.
    public void displayWeeklyAppointments() {
        for (int i = 0; i < NUM_DAYS; i++) {
            System.out.println(DAYS[i] + ":");
            apptsForTheWeek[i].displayAppointments();
            System.out.println();
        }
    }

    // Add an appointment for a given timeslot on a given day.
    // Note: You may find it helpful to invoke day.ordinal()
    // ordinal() is a method automatically defined for enumerations
    // it gives an "index" of a particular value in the enumeration.
    public boolean addAppointment(Appointment appt, Day day, int timeSlot) {
        int dayIndex = day.ordinal();
        if (dayIndex >= 0 && dayIndex < NUM_DAYS) {
            return apptsForTheWeek[dayIndex].addAppointment(timeSlot, appt);
        }
        return false;
    }

    // Cancels (removes) the appointment in a given timeslot on
    // a given day
    public void cancelAppointment(Day day, int timeSlot) {
        int dayIndex = day.ordinal();
        if (dayIndex >= 0 && dayIndex < NUM_DAYS) {
            apptsForTheWeek[dayIndex].removeAppointment(timeSlot);
        }
    }

    // Reschedule an appointment from one timeslot to another on the same day
    public boolean rescheduleAppointment(Day day, int oldTimeSlot, int newTimeSlot) {
        int dayIndex = day.ordinal();
        if (dayIndex >= 0 && dayIndex < NUM_DAYS) {
            Appointment appt = apptsForTheWeek[dayIndex].getAppointmentAt(oldTimeSlot);
            if (appt != null) {
                apptsForTheWeek[dayIndex].removeAppointment(oldTimeSlot);
                return apptsForTheWeek[dayIndex].addAppointment(newTimeSlot, appt);
            }
        }
        return false;
    }

}