package staffconnect.model.person;

import java.util.List;
import java.util.Set;

import staffconnect.model.availability.Availability;
import staffconnect.model.meeting.Meeting;
import staffconnect.model.tag.Tag;


/**
 * Contains utility methods used for dealing with data in the various *Person classes.
 */
public class PersonUtil {

    /**
     * Copies and returns a new Person with the data of {@code personToCopy}.
     */
    public static Person copyPerson(Person personToCopy) {
        Name copiedName = personToCopy.getName();
        Phone copiedPhone = personToCopy.getPhone();
        Email copiedEmail = personToCopy.getEmail();
        Module copiedModule = personToCopy.getModule();
        Faculty copiedFaculty = personToCopy.getFaculty();
        Venue copiedVenue = personToCopy.getVenue();
        Set<Tag> copiedTags = personToCopy.getTags();
        Set<Availability> copiedAvailabilities = personToCopy.getAvailabilities();
        List<Meeting> copiedMeetings = personToCopy.getMeetings();
        Favourite copiedFavourite = personToCopy.getFavourite();

        Person copiedPerson = new Person(copiedName, copiedPhone, copiedEmail, copiedModule, copiedFaculty, copiedVenue,
                copiedTags, copiedAvailabilities, copiedFavourite);
        copiedPerson.setMeetings(copiedMeetings);
        return copiedPerson;
    }
}
