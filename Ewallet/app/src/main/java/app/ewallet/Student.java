package app.ewallet;

/**
 * Created by Seth Legaspi on 10/11/2015.
 * This is the java file that contains the setter and getter methods for the items in the Student database
 */
public class Student {

    private int _id_number;
    private int _pin;
    private String _name; //(Last, First)

    public Student() {
    //empty constructor for stubbing - Will explode if this isn't here
    }

    /**
     * Constructs the student entry object
     * @param id - id number
     * @param pin - pin passcode
     * @param name - name (Last, First)
     */
    public Student(int id, String name, int pin ) {
        this._id_number = id;
        this._pin = pin;
        this._name = name;
    }

    /**
     *  Sets the ID number of this particular entry
     * @param id - ID number
     */
    public void setID(int id) {
        this._id_number = id;
    }

    /**
     *  Gets the ID number of this particular entry
     * @return - returns ID number of this particular entry
     */
    public int getID() {
        return this._id_number;
    }

    /**
     *  Sets the pin of the student
     * @param pin - New PIN of the student
     */
    public void setPin(int pin) {
        this._pin = pin;
    }

    /**
     *  Gets the pin of the student
     * @return - Returns the current pin of the student
     */
    public int getPIN() {
        return this._pin;
    }

    /**
     *  Sets name of the student (Last, First)
     * @param name - New name of the student
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     *  Gets current name of the student (Last, First)
     * @return - Returns the current name of the student
     */
    public String getName() {
        return this._name;
    }

}
