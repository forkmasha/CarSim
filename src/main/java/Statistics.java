import java.util.List;

public interface Statistics {
    double calculateMean(List<Double> values);

    double calculateStd(List<Double> values);

    double calculateConfidenceInterval(double std, int sampleSize);
}