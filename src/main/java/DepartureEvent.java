public  class DepartureEvent implements Event {
    private GasStation gasStation;
    private double departureTime;

    public DepartureEvent(GasStation gasStation, double departureTime) {
        this.gasStation = gasStation;
        this.departureTime = departureTime;
    }

    public void process() {
        gasStation.addDepartureEvent(departureTime);
    }

    public double getEventTime() {
        return departureTime;
    }
}