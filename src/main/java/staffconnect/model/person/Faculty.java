package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Person's faculty in the staff book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidFaculty(String)}
 */
public class Faculty {
    public static final String MESSAGE_CONSTRAINTS =
            "The content should be a String representing a real faculty of NUS";

    /**
     * For this version, a valid faculty value should match exactly the full name of the facult,
     * or the value is invalid
     */
    public enum FacultyEnum {
        ARTS_AND_SOCIAL_SCIENCES("Arts and Social Sciences"),
        BUSINESS("Business"),
        COMPUTING("Computing"),
        CONTINUING_AND_LIFELONG_EDUCATION("Continuing and Lifelong Education"),
        DENTISTRY("Dentistry"),
        DESIGN_AND_ENVIRONMENT("Design and Environment"),
        DUKE_NUS_MEDICAL_SCHOOL("Duke-NUS Medical School"),
        ENGINEERING("Engineering"),
        INTEGRATIVE_SCIENCES_AND_ENGINEERING("Integrative Sciences and Engineering"),
        LAW("Law"),
        MEDICINE("Medicine"),
        MUSIC("Music"),
        PUBLIC_HEALTH("Public Health"),
        PUBLIC_POLICY("Public Policy"),
        SCIENCE("Science"),
        UNIVERSITY_SCHOLARS_PROGRAMME("University Scholars Programme"),
        YALE_NUS_COLLEGE("Yale-NUS College");

        private final String facultyName;

        FacultyEnum(String facultyName) {
            this.facultyName = facultyName;
        }

        /**
         * Links enum member to its name.
         *
         * @return name of the faculty
         */
        public String getFacultyName() {
            return facultyName;
        }
    }
    private final FacultyEnum value;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        value = fromString(faculty);
    }

    /**
     * Verifies if a faculty name is valid.
     *
     * @param test string representing the faculty name
     * @return True if the input matches any faculty name.
     */
    public static boolean isValidFaculty(String test) {
        requireNonNull(test, "faculty cannot be null");

        return Arrays.stream(FacultyEnum.values())
                .anyMatch(faculty -> faculty.getFacultyName().equalsIgnoreCase(test));
    }

    private static FacultyEnum fromString(String name) {
        for (FacultyEnum faculty : FacultyEnum.values()) {
            if (faculty.getFacultyName().equalsIgnoreCase(name)) {
                return faculty;
            }
        }
        throw new IllegalArgumentException("No enum constant matches the provided name: " + name);
    }

    @Override
    public String toString() {
        return value.getFacultyName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Faculty)) {
            return false;
        }

        Faculty otherFaculty = (Faculty) obj;
        return this.value.equals(otherFaculty.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
