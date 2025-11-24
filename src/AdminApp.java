import java.util.List;
import java.util.Scanner;

public class AdminApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuItemDAO menuDao = new MenuItemDAO();
    private static final OrderDAO orderDao = new OrderDAO();

    public static void main(String[] args) {
        System.out.println("== School Cafeteria — Admin Console ==");
        if (!login()) {
            System.out.println("Login failed. Exiting.");
            return;
        }
        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": listMenuItems(); break;
                    case "2": addMenuItem(); break;
                    case "3": updateMenuItem(); break;
                    case "4": deleteMenuItem(); break;
                    case "5": listOrders(); break;
                    case "6": markOrderCompleted(); break;
                    case "0": System.out.println("Goodbye"); return;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static boolean login() {
        System.out.print("Username: ");
        String user = scanner.nextLine().trim();
        System.out.print("Password: ");
        String pass = scanner.nextLine().trim();
        // simple auth: check against admins table
        try (var c = Database.getConnection();
             var ps = c.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            try (var rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Auth error: " + e.getMessage());
            return false;
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1) List menu items");
        System.out.println("2) Add menu item");
        System.out.println("3) Update menu item");
        System.out.println("4) Delete menu item");
        System.out.println("5) List orders");
        System.out.println("6) Mark order as COMPLETED");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
    }

    private static void listMenuItems() throws Exception {
        List<MenuItem> items = menuDao.listAll();
        if (items.isEmpty()) {
            System.out.println("No menu items found.");
            return;
        }
        for (MenuItem m : items) System.out.println(m);
    }

    private static void addMenuItem() throws Exception {
        System.out.print("Name: "); String name = scanner.nextLine().trim();
        System.out.print("Description: "); String desc = scanner.nextLine().trim();
        System.out.print("Price: "); double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Available (true/false): "); boolean avail = Boolean.parseBoolean(scanner.nextLine().trim());
        MenuItem m = new MenuItem(name, desc, price, avail);
        menuDao.add(m);
        System.out.println("Added.");
    }

    private static void updateMenuItem() throws Exception {
        System.out.print("Menu item id: "); int id = Integer.parseInt(scanner.nextLine().trim());
        MenuItem m = menuDao.findById(id);
        if (m == null) { System.out.println("Not found"); return; }
        System.out.print("New name (enter to keep) ["+m.getName()+"]: "); String name = scanner.nextLine().trim();
        if (!name.isEmpty()) m.setName(name);
        System.out.print("New desc (enter to keep) ["+m.getDescription()+"]: "); String d = scanner.nextLine().trim();
        if (!d.isEmpty()) m.setDescription(d);
        System.out.print("New price (enter to keep) ["+m.getPrice()+"]: "); String p = scanner.nextLine().trim();
        if (!p.isEmpty()) m.setPrice(Double.parseDouble(p));
        System.out.print("Available (true/false, enter to keep) ["+m.isAvailable()+"]: "); String a = scanner.nextLine().trim();
        if (!a.isEmpty()) m.setAvailable(Boolean.parseBoolean(a));
        menuDao.update(m);
        System.out.println("Updated.");
    }

    private static void deleteMenuItem() throws Exception {
        System.out.print("Menu item id to delete: "); int id = Integer.parseInt(scanner.nextLine().trim());
        menuDao.delete(id);
        System.out.println("Deleted.");
    }

    private static void listOrders() throws Exception {
        var orders = orderDao.listAll();
        if (orders.isEmpty()) { System.out.println("No orders."); return; }
        for (Order o : orders) System.out.println(o);
    }

    private static void markOrderCompleted() throws Exception {
        System.out.print("Order id to mark COMPLETED: "); int id = Integer.parseInt(scanner.nextLine().trim());
        orderDao.setStatus(id, "COMPLETED");
        System.out.println("Marked completed.");
    }
}
