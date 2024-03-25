/**
 * A class to represent the appointment schedule for a given day.
 * Each day is assumed to have hour-long timeslots starting at 8am and
 * with the final timeslot starting at 4pm
 **/
public class DailyApptSchedule {

    private Appointment[] apptsForTheDay;

    public static final int NUM_SLOTS = 9;
    public static final String[] TIMES = {"8am", "9am", "10am", "11am",
            "12noon", "1pm", "2pm", "3pm", "4pm"};

    // No-arg constructor creates an empty schedule for a day
    public DailyApptSchedule() {
        apptsForTheDay = new Appointment[NUM_SLOTS];
    }

    /**
     * A method to display the list of appointments for the day
     * In the format:
     *    time: appointment_info
     *    time: appointment_info ... etc
     * Any empty slots (null Appointments in the array) should be listed as Free
     */
    public void displayAppointments() {
        for (int i = 0; i < NUM_SLOTS; i++) {
            System.out.print(TIMES[i] + ": ");
            if (apptsForTheDay[i] == null) {
                System.out.println("Free");
            } else {
                System.out.println(apptsForTheDay[i].toString());
            }
        }
    }

    /**
     * A method to add the given appointment to the schedule in the specified timeslot
     * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
     * @param appt represents the appointment object
     * @return true if it was successful and false if not successful
     */
    public boolean addAppointment(int whichSlot, Appointment appt) {
        if (whichSlot >= 0 && whichSlot < NUM_SLOTS && apptsForTheDay[whichSlot] == null) {
            apptsForTheDay[whichSlot] = appt;
            System.out.println(" adding appointment");
            return true;
        }
        return false;
    }

    /**
     * A method to add an appointment for the given person, venue, purpose, and instructor to the schedule in the specified timeslot.
     * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
     * @param n represents the name of the student
     * @param v represents the location of the appointment
     * @param p represents the reason for the meeting
     * @param instructor represents the instructor for the appointment
     * @return true if it was successful and false if not successful (i.e. if the given slot is invalid or taken)
     */
    public boolean addAppointment(int whichSlot, String n, String v, String p, String instructor) {
        Appointment appt = new Appointment(n, v, p, instructor);
        return addAppointment(whichSlot, appt);
    }

    /**
     * A method to add the given appointment to the schedule in any free timeslot.
     * @param appt represents the appointment object
     * @return the index of the chosen timeslot or -1 if no free timeslot exists.
     */
    public int addAppointment(Appointment appt) {
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (apptsForTheDay[i] == null) {
                apptsForTheDay[i] = appt;
                return i;
            }
        }
        return -1;
    }

    /**
     * A method to add an appointment for the given person, venue, purpose, and instructor
     * @param n represents the name of the student
     * @param v represents the location of the appointment
     * @param p represents the reason for the meeting
     * @param instructor represents the instructor for the appointment
     * @return the index of the chosen timeslot or -1 if no free timeslot exists.
     */
    public int addAppointment(String n, String v, String p, String instructor) {
        Appointment appt = new Appointment(n, v, p, instructor);
        return addAppointment(appt);
    }

    /**
     * Remove the appointment in the given slot
     * @param slot the index of the timeslot
     * @return true if operation was successful and false if not
     */
    public boolean removeAppointment(int slot){
        if (slot >= 0 && slot < NUM_SLOTS && apptsForTheDay[slot] != null) {
            apptsForTheDay[slot] = null;
            return true;
        }
        return false;
    }

    /**
     * Remove the appointment corresponding to the given student name
     * @param n represents the name of the student
     * @return true if operation was successful and false if not
     */
    public boolean removeAppointment(String n){
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (apptsForTheDay[i] != null && apptsForTheDay[i].getStudentName().equals(n)) {
                apptsForTheDay[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Get the appointment at the given time slot
     * @param timeSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
     * @return the Appointment object at the given time slot, or null if the time slot is empty
     */
    public Appointment getAppointmentAt(int timeSlot) {
        if (timeSlot >= 0 && timeSlot < NUM_SLOTS) {
            return apptsForTheDay[timeSlot];
        }
        return null;
    }
}