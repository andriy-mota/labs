package cource4semester1.lab2;

import utils.Utils;

import java.util.Arrays;

public class Lab2 {

    public static void main(String[] args) {
        Double[] data = Utils.readLab2Data();
        Arrays.stream(data).forEach(i -> System.out.print(i + "\t"));
        System.out.println();

        Option bigFactoryOption = getBigFactoryOption(data);
        Option bigFactoryWaitingOption = getBigFactoryWaitingOption(data);

        Option smallFactoryOption = getSmallFactoryOption(data);
        Option smallFactoryWaitingOption = getSmallFactoryWaitingOption(data);

        Double bigFactoryWaitingProfit = calculateWaitingProfit(bigFactoryWaitingOption);
        System.out.println("Expectation and big factory profit: " + bigFactoryWaitingProfit);
        Double smallFactoryWaitingProfit = calculateWaitingProfit(smallFactoryWaitingOption);
        System.out.println("Expectation and small factory profit: " + smallFactoryWaitingProfit);

        Double bigFactoryProfit = calculateProfit(bigFactoryOption);
        System.out.println("Big factory profit: " + bigFactoryProfit);
        Double smallFactoryProfit = calculateProfit(smallFactoryOption);
        System.out.println("Small factory profit: " + smallFactoryProfit);

        Double waitingProfitability = calculateProfitabilityAfterWaiting(data, Math.max(bigFactoryWaitingProfit, smallFactoryWaitingProfit));
        System.out.println("Waiting and data collection: " + waitingProfitability);

        System.out.println("The best profit of this task is: " + getMax(bigFactoryProfit, smallFactoryProfit, waitingProfitability));
    }

    private static Option getBigFactoryOption(Double[] data) {
        return new Option(data[0], data[1], data[2], data[3], data[4]);
    }

    private static Option getBigFactoryWaitingOption(Double[] data) {
        return new Option(data[0], data[1], data[12], data[3], data[13]);
    }

    private static Option getSmallFactoryOption(Double[] data) {
        return new Option(data[5], data[6], data[7], data[8], data[9]);
    }

    private static Option getSmallFactoryWaitingOption(Double[] data) {
        return new Option(data[5], data[6], data[12], data[8], data[13]);
    }

    private static Double calculateProfit(Option option) {
        return ((option.expectedProfit * option.expectedProfitProbability) + (option.loose * option.looseProbability)) * 5 - option.cost;
    }

    private static Double calculateWaitingProfit(Option option) {
        return ((option.expectedProfit * option.expectedProfitProbability) + (option.loose * option.looseProbability)) * 4 - option.cost;
    }

    private static Double getMax(Double option1, Double option2, Double option3) {
        Double intermediate = Math.max(option1, option2);
        return Math.max(intermediate, option3);
    }

    private static Double calculateProfitabilityAfterWaiting(Double[] data, Double waitingProfit) {
        return (waitingProfit + data[10] * 0.0 * 1.0) + (data[11] * 0.0 * 1.0 - 0.0);
    }
}

class Option {
    Double cost;
    Double expectedProfit;
    Double expectedProfitProbability;
    Double loose;
    Double looseProbability;

    public Option(Double cost, Double expectedProfit, Double expectedProfitProbability, Double loose, Double looseProbability) {
        this.cost = cost;
        this.expectedProfit = expectedProfit;
        this.expectedProfitProbability = expectedProfitProbability;
        this.loose = loose;
        this.looseProbability = looseProbability;
    }
}


