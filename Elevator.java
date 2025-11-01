import java.util.*;

public class Elevator {
    
    private Direction state;
    private int currentFloor = 1;
    // storing floor -> list of people on that floor, waiting for service
    private HashMap<Integer, List<Integer>> upCalls;
    private HashMap<Integer, List<Integer>> downCalls;
    // storing floor -> list of people wanting to go to that floor
    private HashMap<Integer, List<Integer>> passengers;
     // storing person -> floor they requested, once onboard
    private HashMap<Integer, Integer> destinations;
    
    public Elevator(){
        this.state = Direction.IDLE;
        this.upCalls = new HashMap<>();
        this.downCalls = new HashMap<>();
        this.passengers = new HashMap<>();
        this.destinations = new HashMap<>();
    }
    
    public boolean hasPendingUpCalls() {
        return !upCalls.isEmpty() || !passengers.isEmpty();
    }
    
    public boolean hasPendingDownCalls() {
        return !downCalls.isEmpty() || !passengers.isEmpty();
    }
    
    public void requestService(int person, int floor, Direction d){
        if (d == Direction.UP){
            upCalls.putIfAbsent(floor, new ArrayList<>());
            upCalls.get(floor).add(person);
        } else if (d == Direction.DOWN){
            downCalls.putIfAbsent(floor, new ArrayList<>());
            downCalls.get(floor).add(person);
        }
    }
    
    public void selectFloor(int person, int targetFloor){
        destinations.put(person, targetFloor);
    }
    
    public void process(Direction d){
        if (d == Direction.UP){
            while (hasPendingUpCalls()){
                print("This is Floor " + currentFloor);
                if (passengers.containsKey(currentFloor)){
                    for (int i: passengers.get(currentFloor)){
                        print("Person " + i + " has left the Elevator on Floor " + currentFloor);
                    }
                    passengers.remove(currentFloor);
                }
                if (upCalls.containsKey(currentFloor)){
                    for (int i: upCalls.get(currentFloor)){
                        print("Person " + i + " has entered the Elevator on Floor " + currentFloor);
                        int targetFloor = destinations.get(i);
                        passengers.putIfAbsent(targetFloor, new ArrayList<>());
                        passengers.get(targetFloor).add(i);
                        print("Person " + i + " has selected to go to Floor " + targetFloor + ". Adding it to our queue.");
                    }
                    upCalls.remove(currentFloor);
                }
                currentFloor += 1;
            }
            print("No more up calls.");
            currentFloor--;
        }
        if (d == Direction.DOWN){
            while (hasPendingDownCalls()){
                print("This is Floor " + currentFloor);
                if (passengers.containsKey(currentFloor)){
                    for (int i: passengers.get(currentFloor)){
                        print("Person " + i + " has left the Elevator on Floor " + currentFloor);
                    }
                    passengers.remove(currentFloor);
                }
                if (downCalls.containsKey(currentFloor)){
                    for (int i: downCalls.get(currentFloor)){
                        print("Person " + i + " has entered the Elevator on Floor " + currentFloor);
                        int targetFloor = destinations.get(i);
                        passengers.putIfAbsent(targetFloor, new ArrayList<>());
                        passengers.get(targetFloor).add(i);
                        print("Person " + i + " has selected to go to Floor " + targetFloor + ". Adding it to our queue.");
                    }
                    downCalls.remove(currentFloor);
                }
                currentFloor -= 1;
            }
            print("No more down calls.");
            currentFloor++;
        }
    }
    
    public void print(String s){
        System.out.println(s);
    }
    
    public static void main(String[] args){
        Elevator e = new Elevator();
        
        // people pressing buttons, requesting elevator, moving up
        e.requestService(101, 1, Direction.UP);
        e.requestService(102, 1, Direction.UP);
        e.requestService(103, 2, Direction.UP);
        e.requestService(104, 4, Direction.UP);
        e.requestService(105, 5, Direction.UP);
        e.selectFloor(101, 2);
        e.selectFloor(102, 3);
        e.selectFloor(103, 6);
        e.selectFloor(104, 5);
        e.selectFloor(105, 6);
        // people pressing buttons, requesting elevator, moving down
        e.requestService(201, 5, Direction.DOWN);
        e.requestService(202, 4, Direction.DOWN);
        e.requestService(203, 4, Direction.DOWN);
        e.selectFloor(201, 4);
        e.selectFloor(202, 1);
        e.selectFloor(203, 2);
        
        
        e.process(Direction.UP);
        e.process(Direction.DOWN);
        
    }
}
