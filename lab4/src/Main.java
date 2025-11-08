import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        int philosophersCount = 5;
        //TestNaive(philosophersCount);
        //TestStarving(philosophersCount);
        //TestEvenOdd(philosophersCount);
        TestArbiter(philosophersCount);
    }

    public static void TestNaive(int n) throws InterruptedException
    {
        var table = new NaiveTable(n);

        var philosophers = new ArrayList<NaivePhilosopher>();
        for(int i = 0; i < n; i++)
        {
            philosophers.add(new NaivePhilosopher(table, i, n));
            philosophers.get(i).start();
        }

        Thread.sleep(5000);
        for (var philosopher : philosophers)
            philosopher.interrupt();

        for (var philosopher : philosophers)
            philosopher.join(1000);

        var philosophersToExport = new ArrayList<BasePhilosopher>(philosophers);
        ExportToCSV(philosophersToExport, "NaiveResults.csv");
    }

    public static void TestStarving(int n) throws InterruptedException
    {
        var table = new StarvingTable(n);

        var philosophers = new ArrayList<StarvingPhilosopher>();
        for(int i = 0; i < n; i++)
        {
            philosophers.add(new StarvingPhilosopher(table, i, n));
            philosophers.get(i).start();
        }

        Thread.sleep(10000);
        for (var philosopher : philosophers)
            philosopher.interrupt();

        for (var philosopher : philosophers)
            philosopher.join(1000);

        var philosophersToExport = new ArrayList<BasePhilosopher>(philosophers);
        ExportToCSV(philosophersToExport, "StarvingResults.csv");
    }

    public static void TestEvenOdd(int n) throws InterruptedException
    {
        var table = new NaiveTable(n);

        var philosophers = new ArrayList<EvenOddPhilosopher>();
        for(int i = 0; i < n; i++)
        {
            philosophers.add(new EvenOddPhilosopher(table, i, n));
            philosophers.get(i).start();
        }

        Thread.sleep(5000);
        for (var philosopher : philosophers)
            philosopher.interrupt();

        for (var philosopher : philosophers)
            philosopher.join(1000);

        var philosophersToExport = new ArrayList<BasePhilosopher>(philosophers);
        ExportToCSV(philosophersToExport, "EvenOddResults.csv");
    }

    public static void TestArbiter(int n) throws InterruptedException
    {
        var table = new ArbiterTable(n);
        var philosophers = new ArrayList<ArbiterPhilosopher>();
        for (int i = 0; i < n; i++)
        {
            philosophers.add(new ArbiterPhilosopher(table, i, n));
            philosophers.get(i).start();
        }

        Thread.sleep(5000);
        for (var philosopher : philosophers)
            philosopher.interrupt();

        for (var philosopher : philosophers)
            philosopher.join(1000);

        var philosophersToExport = new ArrayList<BasePhilosopher>(philosophers);
        ExportToCSV(philosophersToExport, "ArbiterResults.csv");
    }

    private static void ExportToCSV(List<BasePhilosopher> philosophers, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("PhilosopherID;TotalMeals;AverageTime;WaitTime\n");

            for (var philosopher : philosophers) {
                List<Long> waitTimes = philosopher.GetWaitingTimes();
                int meals = philosopher.GetEatingCount();
                int averageTime = (int)waitTimes.stream().mapToLong(Long::longValue).average().orElse(0);
                writer.write(String.format("%d;%d;%d;", philosopher.GetId(), meals, averageTime));

                for (long waitTime : waitTimes) {
                    writer.write(String.format("%d,", waitTime));
                }

                writer.write("\n");
            }

            System.out.println("Data exported to: " + filename);

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
            throw new RuntimeException();
        }
    }
}
