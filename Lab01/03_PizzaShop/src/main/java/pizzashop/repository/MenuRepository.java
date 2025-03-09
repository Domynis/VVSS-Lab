package pizzashop.repository;

import pizzashop.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static final String FILENAME = "data/menu.txt";
    private List<MenuItem> listMenu;

    public MenuRepository() {
        // Constructor for loading in javafx
    }

    private void readMenu() {
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(FILENAME);
        this.listMenu = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                MenuItem menuItem = parseLineToMenuItem(line);
                listMenu.add(menuItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MenuItem parseLineToMenuItem(String line) {
        MenuItem item = null;
        if (line == null || line.isEmpty()) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(line, ",");
        String name = st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuItem(name, 0, price);
        return item;
    }

    public List<MenuItem> getMenu() {
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}
