// Optional: seed some demo menu items
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseSeeder {
    public static void main(String[] args) {
        String[] items = {
            """INSERT INTO menu_items (name, description, price, available) VALUES ('Vegetable Chapati', 'Chapati with mixed vegetables', 60.00, true);""" ,
            """INSERT INTO menu_items (name, description, price, available) VALUES ('Beef Stew', 'Beef stew with rice', 120.00, true);""" ,
            """INSERT INTO menu_items (name, description, price, available) VALUES ('Fruit Salad', 'Seasonal fruit cup', 40.00, true);""" 
        };
        try (Connection c = Database.getConnection()) {
            for (String s : items) {
                try (PreparedStatement ps = c.prepareStatement(s)) {
                    ps.executeUpdate();
                }
            }
            System.out.println("Seeded demo menu items.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
