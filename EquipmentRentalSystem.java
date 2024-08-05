import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EquipmentRentalSystem {
    private static Map<String, RentalInfo> rental = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void rentItem() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNo = scanner.nextLine();
        System.out.print("Enter equipment ID: ");
        int eid = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter equipment name: ");
        String ename = scanner.nextLine();

        if (rental.containsKey(rollNo)) {
            System.out.println("Equipment already rented");
            return;
        }
        Equipment equipment = Equipment.getEquipmentById(eid);
        if (equipment != null && equipment.getAvailableQuantity() > 0) {
            rental.put(rollNo, new RentalInfo(studentName, eid, ename, "rented", "good"));
            equipment.setAvailableQuantity(equipment.getAvailableQuantity() - 1);
            System.out.println("Equipment rented successfully");
        } else {
            System.out.println("Equipment not available");
        }
    }

    public static void returnItem() {
        System.out.print("Enter roll number: ");
        String rollNo = scanner.nextLine();
        System.out.print("Enter return condition: ");
        String returnCondition = scanner.nextLine();

        if (rental.containsKey(rollNo)) {
            RentalInfo info = rental.get(rollNo);
            info.setReturnCondition(returnCondition);
            // Update equipment availability
            Equipment equipment = Equipment.getEquipmentById(info.getEid());
            if (equipment != null) {
                equipment.setAvailableQuantity(equipment.getAvailableQuantity() + 1);
                System.out.println("Equipment returned successfully");
            }
            // Optionally remove the rental record
            rental.remove(rollNo);
        } else {
            System.out.println("Student not found");
        }
    }

    private static class RentalInfo {
        private String studentName;
        private int eid;
        private String ename;
        private String status;
        private String returnCondition;

        public RentalInfo(String studentName, int eid, String ename, String status, String returnCondition) {
            this.studentName = studentName;
            this.eid = eid;
            this.ename = ename;
            this.status = status;
            this.returnCondition = returnCondition;
        }

        public int getEid() {
            return eid;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setReturnCondition(String returnCondition) {
            this.returnCondition = returnCondition;
        }
    }

    public static void main(String[] args) {
        // Sample data
        Equipment.addEquipmentToMap(new Equipment(1, "Laptop", "Dell", 10, 10));
        Equipment.addEquipmentToMap(new Equipment(2, "Projector", "Epson", 5, 5));

        while (true) {
            System.out.println("\n1. Rent Equipment");
            System.out.println("2. Return Equipment");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    rentItem();
                    break;
                case 2:
                    returnItem();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
