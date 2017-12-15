package HomeExR3;

import java.io.IOException;

import org.junit.*; // remove, JUNIT4 should not used

// Add "import org.junit.jupiter.api.BeforeEach;" to do before each @Test something required for all Junit Tests

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

// This interface is not tested with method which use "PersonDatabase"
interface PersonDatabase<E> {
    List<Person> getAllPersons() throws IOException;
}

// should be added interface (PersonDatabase<E>),
// Because all top-level classes are static, having the "STATIC" keyword in a top-level class definition is pointless.
static class Person { // Fix -> remove "static", need to add "implements PersonDatabase" and write new method implementation getAllPersons()

    private String firstName;
    private String lastName;
    String gender;
    private float height; //

    static volatile Float totalHeight = 0.0f;

    public Person(String firstName, String lastName, float height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        return height; // constructor MUST return nothing, remove this line.
    }

    public int getHeight() { // fix from "public int" to "public float"
        return (int) height; // method returns INT, most effective way is to fix it to return FLOAT, remove "(int)
    }

    public void grow(float height) {
        float newHeight = this.height + height;
        if (newHeight < 1 && newHeight > 6.0f) {
            // should be less than 1.0f OR (||) more than 6.0. Be less than 1 and more than 6 is impossible
            // if (newHeight < 1.0f || newHeight > 6.0f)
            throw new IllegalArgumentException("height");
        }
        height += height; // fix to "this.height += height; change the "height" of object, which call the method (not global)
    }

    @Override
    public boolean equals(Object obj) {
        // Fix to "return (this.lastName.equals(((Person) obj).lastName))"
        return this.lastName == ((Person) obj).lastName; // String need to check by "String.equals(String)
    }

    // This method is not tested with interface "PersonDatabase<E>"
    public static float countPersons(PersonDatabase<Person> personPersonDatabase) {
        List<Person> personsList = null;
        try {
            personsList = personPersonDatabase.getAllPersons();
        } catch (IOException e) {

        }

        personsList.parallelStream().forEach(person -> {
            totalHeight += person.getHeight();
        });

    }

}

class PersonTest {

    private Person person;

    @Before // Change to  @BeforeEach
    public void begin() {
        person = new Person("Alan", "Turing", 5.3); // last method argument change from "5.3" to "5.3f", required FLOAT
    }

    @Test
    public void testHeight() {
        float height = person.getHeight();
        Assert.assertEquals(height, 5.3f); // All "Assert.assertEquals" to "Assertions.assertEquals"
    }

    @Test
    public void testGrow1() {
        person.grow(0.1f);
        float height = person.getHeight();
        Assertions.assertEquals(height, 5.4f);
    }

    @Test
    public void testGrow2() {
        person.grow(0.6f);
        float height = person.getHeight();
        Assertions.assertEquals(height, 5.9f);
    }

    @Test
    public void testGrow3() {
        person.grow(0.7f);
        float height = person.getHeight();
        Assertions.assertEquals(height, 6.0f);
    }

    @Test()
    public void testGrow4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            person.grow(0.8f);
        });
    }

    @Test()
    public void testGrow5() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            person.grow(0.9f); // Testing same as "testGrow4", better to change to upper boundary "7.0f"
        });
    }

    @Test
    public void testEquals() {
        // to create new copy of object, need to start with it name type, fix to "Person person2 = new Person..."
        person2 = new Person("Alan", "Turning", 5.3f); // Name from "Turning" to "Turing"
        Assert.assertEquals(person, person2); // 2 copy's of object with same values, as still 2 different objects.
        // Also there is "equals" method not used.
        // So Test could be changed to " Assertions.assertEquals(person.equals(person2), person.equals(person));", if require to test "person.equals()"
    }

}
