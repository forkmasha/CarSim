import java.util.List;

public class GasStationStatistics {
    private List<Double> waitingTimes;
    private List<Double> systemTimes;
    private List<Double> queueTimes;
    private int numDeparted;

    public GasStationStatistics(List<Double> waitingTimes, List<Double> systemTimes, List<Double> queueTimes, int numDeparted) {
        this.waitingTimes = waitingTimes;
        this.systemTimes = systemTimes;
        this.queueTimes = queueTimes;
        this.numDeparted = numDeparted;
    }

    public double calculateMean(List<Double> values) {
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    public double calculateStd(List<Double> values) {
        double mean = calculateMean(values);
        double sum = 0;
        for (Double value : values) {
            sum += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sum / values.size());
    }

    public double calculateConfidenceInterval(double std, int sampleSize) {
        double criticalValue = 1.96; // Assuming 95% confidence level
        return criticalValue * (std / Math.sqrt(sampleSize));
    }

    public double getMeanStime() {
        return calculateMean(systemTimes);
    }

    public double getMeanQtime() {
        return calculateMean(waitingTimes);  // Calculate mean waiting time instead of queue time
    }

    public double getStdDevStime() {
        return calculateStd(systemTimes);
    }

    public double getStdDevQtime() {
        return calculateStd(waitingTimes);  // Calculate standard deviation of waiting time instead of queue time
    }

    public double getConfidenceIntervalStime() {
        double std = getStdDevStime();
        int sampleSize = numDeparted;
        return calculateConfidenceInterval(std, sampleSize);
    }

    public double getConfidenceIntervalQtime() {
        double std = getStdDevQtime();
        int sampleSize = numDeparted;
        return calculateConfidenceInterval(std, sampleSize);
    }
}
