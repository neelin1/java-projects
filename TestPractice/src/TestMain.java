
public class TestMain {

    public TestMain() {

    }

    public class Bicycle {

        // the Bicycle class has three fields
        public int cadence;
        public int gear;
        public int speed;

        // the Bicycle class has one constructor
        public Bicycle(int startCadence, int startSpeed, int startGear) {
            gear= startGear;
            cadence= startCadence;
            speed= startSpeed;
        }

        // the Bicycle class has four methods
        public void setCadence(int newValue) {
            cadence= newValue;
        }

        public void setGear(int newValue) {
            gear= newValue;
        }

        public void applyBrake(int decrement) {
            speed-= decrement;
        }

        public void speedUp(int increment) {
            speed+= increment;
        }

    }

    public class MountainBike extends Bicycle {

        // the MountainBike subclass adds one field
        public int seatHeight;

        // the MountainBike subclass has one constructor
        public MountainBike(int startHeight,
            int startCadence,
            int startSpeed,
            int startGear) {
            super(startCadence, startSpeed, startGear);
            seatHeight= startHeight;
        }

        // the MountainBike subclass adds one method
        public void setHeight(int newValue) {
            seatHeight= newValue;
        }
    }

    public static void main(String[] args) {
        TestMain test= new TestMain();
        Bicycle[] bikes= new MountainBike[5];
        Bicycle bike1= test.new Bicycle(0, 0, 0);
        bikes[0]= bike1;

    }
}
