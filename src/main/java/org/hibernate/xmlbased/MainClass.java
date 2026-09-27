package org.hibernate.xmlbased;

import java.util.Arrays;
import java.util.List;

import org.hibernate.xmlbased.dao.DeveloperDAO;
import org.hibernate.xmlbased.model.Developer;

public class MainClass {

    public static void main(String[] args) {
        List<Developer> developers = Arrays.asList(
                new Developer("Igor", "Java Developer", 2),
                new Developer("Alexander", "C++ Developer", 4),
                new Developer("Ivan", "DevOps", 3)
        );

        DeveloperDAO developerDAO = new DeveloperDAO();

        // 1. Пытаемся добавить, ловим ошибки дубликатов
        for (Developer dev : developers) {
            try {
                developerDAO.addDeveloper(dev);
                System.out.println("Успешно добавлен: " + dev.getName());
            } catch (Exception e) {
                System.out.println("Запись уже существует в БД, пропуск: " + dev.getName());
            }
        }

        // 2. Вывод всех
        System.out.println("Все разработчики в БД:");
        developerDAO.getDevelopers().forEach(System.out::println);

        // 3. Получение по ID (если удален - вернет null, это нормально)
        System.out.println("Разработчик с ID 2: " + developerDAO.getDeveloperById(2));

        // 4. Обновление (внутри уже есть защита от несуществующих данных)
        developerDAO.updateDeveloper(1, 5);

        // 5. Удаление (внутри уже есть защита от несуществующих данных)
        developerDAO.removeDeveloper(2);

        System.out.println("Итоговый список:");
        developerDAO.getDevelopers().forEach(System.out::println);
    }
}
