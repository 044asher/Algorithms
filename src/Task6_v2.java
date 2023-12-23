    import java.lang.reflect.Field;
    import java.util.*;

    public class Task6_v2 {
        public static String toJSON(Object object) throws IllegalAccessException {
            if (object == null) { // Если нам передали null, то и возвращаем пустой JSON
                return "{}";
            }
            if (object instanceof String) { // Если передали String:
                return "\"" + object + "\"";
            } else if (object instanceof Number || object instanceof Boolean) {
                return String.valueOf(object);
            } else if (object instanceof Map) {
                Map<?, Object> map = (Map<?, Object>) object;
                String json = "{"; //Первым символом ставим открывающую фигурную скобку
                for (Map.Entry<?, Object> entry : map.entrySet()) { //используется для итерации по набору пар ключ-значение объекта map
                    json += "\"" + entry.getKey() + "\": " + toJSON(entry.getValue()) + ","; //добавляем в строку запись в формате ключ-значение
                }
                json = json.substring(0, json.length() - 1) + "}"; //Получаем старую строку, но убираем последнюю запятую и вместо него ставим закрывающую скобку
                return json;
            } else if (object instanceof List) {
                List<?> list = (List<?>) object;
                String json = "[";
                for (Object item : list) {
                    json += toJSON(item) + ",";
                }
                json = json.substring(0, json.length() - 1) + "]";
                return json;
            } else if (object instanceof Set) {
                Set<?> set = (Set<?>) object;
                String json = "[";
                for (Object item : set) {
                    json += toJSON(item) + ",";
                }
                json = json.substring(0, json.length() - 1) + "]";
                return json;
            } else {
                Class<?> tempClass = object.getClass();//Получаем информацию о классе, а конкретно о его полях
                String json = "{";
                for (Field field : tempClass.getDeclaredFields()) { //Этот цикл перебирает все поля класса так как getDeclaredFields возвращает массив объектов Field представляющий все поля, даже приватные
                    Object value = field.get(object); //Получает значение поля из объекта. Метод get извлекает значение поля, даже если оно является приватным
                    json += "\"" + field.getName() + "\": " + toJSON(value) + ", "; //Тут мы добавляем в строку запись в формате ключ-значение, где ставим ключом имя поля, а значение получаем рекурсивно вызывая toJSON для значения поля.

                }
                json = json.substring(0, json.length() - 2) + "}";
                return json;
            }
        }
        public static class Person {
            static class PersonData {
                private int passportID = 143154;
                private String nationality = "Ukrainian";

                static class PersonFamily{
                    private String mother = "Elizabeth";
                    private String father = "Arthur";
                }
            }
            private String name;
            private int age;
            private PersonData personData;
            private PersonData.PersonFamily personFamily;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
                this.personData = new PersonData();
                this.personFamily = new PersonData.PersonFamily();
            }
        }
        public static void main(String[] args) throws IllegalAccessException {
            Map<String, Integer> testMap = new HashMap<>();
            testMap.put("Key", 1);
            testMap.put("Key1", 15);
            System.out.println("Проверка карт: " + toJSON(testMap));

            String testString = "TEST STRING";
            System.out.println("Проверка строк: " + toJSON(testString));

            int testInt = 10;
            System.out.println("Проверка чисел: " + toJSON(testInt));

            boolean testBoolean = false;
            System.out.println("Проверка булевых значений: " + toJSON(testBoolean));

            List<String> list = new LinkedList<>();
            list.add("Test");
            list.add("Testing");
            System.out.println("Проверка коллекций List: " + toJSON(list));

            Set<String> set = new HashSet<>();
            set.add("Set1"); set.add("Set2"); set.add("Set3");
            System.out.println("Проверка Set: " + toJSON(set));

            Person person = new Person("John Doe", 30);
            System.out.println(toJSON(person));


        }
    }
