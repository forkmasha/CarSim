import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GasStation {
    private double meanArrivalInterval;
    private double meanServiceTime;
    private int numServers;
    private int queueLength;
    private int maxCars;

    private List<Double> arrivalTimes;
    private List<Double> serviceTimes;
    private List<Double> waitingTimes;
    private List<Double> systemTimes;
    private List<Double> queueTimes;

    private int numArrived;
    private int numDeparted;
    private int numBlocked;

    public GasStation(double meanArrivalInterval, double meanServiceTime, int numServers, int queueLength, int maxCars) {
        this.meanArrivalInterval = meanArrivalInterval;
        this.meanServiceTime = meanServiceTime;
        this.numServers = numServers;
        this.queueLength = queueLength;
        this.maxCars = maxCars;

        this.arrivalTimes = new ArrayList<>();
        this.serviceTimes = new ArrayList<>();
        this.waitingTimes = new ArrayList<>();
        this.systemTimes = new ArrayList<>();
        this.queueTimes = new ArrayList<>();

        this.numArrived = 0;
        this.numDeparted = 0;
        this.numBlocked = 0;
    }

    public void addArrivalEvent(double arrivalTime, double serviceTime) {
        arrivalTimes.add(arrivalTime);
        serviceTimes.add(serviceTime);
        numArrived++;
    }

    public void addDepartureEvent(double departureTime) {
        if (!arrivalTimes.isEmpty() && !serviceTimes.isEmpty()) {
            waitingTimes.add(departureTime - arrivalTimes.get(0) - serviceTimes.get(0));
            systemTimes.add(departureTime - arrivalTimes.get(0));
            queueTimes.add(serviceTimes.get(0));

            arrivalTimes.remove(0);
            serviceTimes.remove(0);
            numDeparted++;

            if (systemTimes.size() > numServers + queueLength) {
                numBlocked++;
            }
        }
    }

    public void simulate() {
        Random random = new Random();
        EventQueue eventQueue = new EventQueue(maxCars);

        for (int i = 0; i < maxCars; i++) {
            double arrivalTime = meanArrivalInterval * random.nextDouble();
            double serviceTime = meanServiceTime * random.nextDouble();
            addArrivalEvent(arrivalTime, serviceTime);
        }

        for (int i = 0; i < maxCars; i++) {
            double arrivalTime = arrivalTimes.get(i);
            double serviceTime = serviceTimes.get(i);
            eventQueue.addEvent(new ArrivalEvent(this, arrivalTime, serviceTime));
        }

        eventQueue.processEvents();
    }


    public int getNumArrived() {
        return numArrived;
    }

    public int getNumDeparted() {
        return numDeparted;
    }

    public int getNumBlocked() {
        return numBlocked;
    }

    public List<Double> getWaitingTimes() {
        return waitingTimes;
    }

    public List<Double> getSystemTimes() {
        return systemTimes;
    }

    public List<Double> getQueueTimes() {
        return queueTimes;
    }
}