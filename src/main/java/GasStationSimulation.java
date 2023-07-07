public class GasStationSimulation {
    public static void main(String[] args) {
        double meanArrivalInterval = 1.0;
        double meanServiceTime = 0.5;
        int numServers = 5;
        int queueLength = 10;
        int maxCars = 5000;

        GasStation gasStation = new GasStation(meanArrivalInterval, meanServiceTime, numServers, queueLength, maxCars);
        gasStation.simulate();

        System.out.println("Arrivals: " + gasStation.getNumArrived());
        System.out.println("Departures: " + gasStation.getNumDeparted());
        System.out.println("Blocked: " + gasStation.getNumBlocked());

        GasStationStatistics statistics = new GasStationStatistics(gasStation.getWaitingTimes(), gasStation.getSystemTimes(), gasStation.getQueueTimes(), gasStation.getNumDeparted());
        System.out.println("Mean Stime: " + statistics.getMeanStime());
        System.out.println("Mean Qtime: " + statistics.getMeanQtime());
        System.out.println("StdDev Stime: " + statistics.getStdDevStime());
        System.out.println("StdDev Qtime: " + statistics.getStdDevQtime());
        System.out.println("Confidence Interval Stime: " + statistics.getConfidenceIntervalStime());
        System.out.println("Confidence Interval Qtime: " + statistics.getConfidenceIntervalQtime());
    }
}