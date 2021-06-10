import java.util.StringJoiner;

public class Car implements myClasses {
    private String model;
    private int speed;

    public Car() {
        this.model = "Default model";
        this.speed = 0;
    }

    public Car(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    public int boost(int value) {
        this.speed += value;
        return speed;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("firstName='" + model + "'")
                .add("height=" + speed)
                .toString();
    }
}