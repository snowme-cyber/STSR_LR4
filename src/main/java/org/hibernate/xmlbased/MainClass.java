package org.hibernate.xmlbased;


import org.hibernate.xmlbased.dao.DeveloperDAO;
import org.hibernate.xmlbased.model.Developer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {
    static void main() {
        List<Developer> developers = new ArrayList<>(Arrays.asList(
                new Developer("Igor", "Java Developer", 2),
                new Developer("Alexander", "C++ Developer", 4),
                new Developer("Ivan", "DevOps", 3)
        ));

        DeveloperDAO developerDAO = new DeveloperDAO();

        for (Developer dev : developers){
            try {
                developerDAO.AddDevelopers(dev);
                System.out.println("Успешно добавлен: " + dev.getName());
            }
            catch(Exception e){
                System.out.println("Пропуск: " + dev.getName() +
                        " уже есть в базе данных.");
            }
        }


        developerDAO.getDevelopers().stream().forEach(System.out::println);

        System.out.println(developerDAO.getDeveloperById(2));

        developerDAO.updateDeveloper(1, 5);

        developerDAO.removeDeveloper(2);

        developerDAO.getDevelopers().stream().forEach(System.out::println);

    }
}
