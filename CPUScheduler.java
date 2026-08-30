import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Process {
    String id;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;
    int completionTime;

    public Process(String id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }

    public Process copy() {
        return new Process(this.id, this.arrivalTime, this.burstTime);
    }
}

public class CPUScheduler {

    public static void runFCFS(List<Process> processes) {
        Queue<Process> queue = new ArrayDeque<>();
        for (Process p : processes) queue.offer(p.copy());

        int currentTime = 0;
        int totalWT = 0, totalTAT = 0;

        System.out.println("--- FCFS Execution ---");
        while (!queue.isEmpty()) {
            Process p = queue.poll();
            if (currentTime < p.arrivalTime) currentTime = p.arrivalTime;
            
            p.waitingTime = currentTime - p.arrivalTime;
            currentTime += p.burstTime;
            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;

            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
            System.out.printf("%s finished at %d | WT: %d | TAT: %d\n", p.id, currentTime, p.waitingTime, p.turnaroundTime);
        }
        System.out.printf("Average WT: %.2f | Average TAT: %.2f\n\n", (double)totalWT/processes.size(), (double)totalTAT/processes.size());
    }

    public static void runRoundRobin(List<Process> processes, int quantum) {
        Queue<Process> queue = new ArrayDeque<>();
        List<Process> completed = new ArrayList<>();
        for (Process p : processes) queue.offer(p.copy());

        int currentTime = 0, contextSwitches = 0;

        System.out.println("--- Round Robin (TQ=" + quantum + ") Execution ---");
        while (!queue.isEmpty()) {
            Process p = queue.poll();
            int execTime = Math.min(p.remainingTime, quantum);
            
            p.remainingTime -= execTime;
            currentTime += execTime;

            if (p.remainingTime > 0) {
                if (!queue.isEmpty()) contextSwitches++;
                queue.offer(p);
            } else {
                p.completionTime = currentTime;
                p.turnaroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                completed.add(p);
                System.out.printf("%s finished at %d\n", p.id, currentTime);
            }
        }

        double totalWT = 0, totalTAT = 0;
        System.out.println("\nSummary:");
        for (Process p : completed) {
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
            System.out.printf("%s -> WT: %d | TAT: %d\n", p.id, p.waitingTime, p.turnaroundTime);
        }
        System.out.printf("Average WT: %.2f | Average TAT: %.2f | Context Switches: %d\n", 
                totalWT/processes.size(), totalTAT/processes.size(), contextSwitches);
    }

    public static void main(String[] args) {
   
        List<Process> processes = Arrays.asList(
            new Process("P1", 0, 8),
            new Process("P2", 0, 4),
            new Process("P3", 0, 9),
            new Process("P4", 0, 5)
        );

        runFCFS(processes);
        runRoundRobin(processes, 3);
    }
}