public class ArrivalEvent implements Event {
    private GasStation gasStation;
    private double arrivalTime;
    private double serviceTime;

    public ArrivalEvent(GasStation gasStation, double arrivalTime, double serviceTime) {
        this.gasStation = gasStation;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void process() {
        gasStation.addArrivalEvent(arrivalTime, serviceTime);
        gasStation.addDepartureEvent(arrivalTime + serviceTime);
    }

    public double getEventTime() {
        return arrivalTime;
    }
}