import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Task6_v2 {
    public static String toJSON(Object object) {
        if (object == null) { // Если нам передали null, то и возвращаем пустой JSON
            return "{}";
        }
        if (object instanceof String) { // Если передали String:
            return "\"" + object + "\"";
        } else if (object instanceof Number || object instanceof Boolean) {
            return String.valueOf(object);
        } else if (object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) object;
            String json = "{"; //Первым символом ставим открывающую фигурную скобку
            for (Map.Entry<String, Object> entry : map.entrySet()) { //используется для итерации по набору пар ключ-значение объекта map
                json += "\"" + entry.getKey() + "\": " + toJSON(entry.getValue()) + ", "; //добавляем в строку запись в формате ключ-значение
            }
            json = json.substring(0, json.length() - 1) + "}"; //Получаем старую строку, но убираем последнюю запятую и вместо него ставим закрывающую скобку
            return json;
        }else if (object instanceof List) {
            List<?> list = (List<?>) object;
            String json = "[";
            for (Object item : list) {
                json += toJSON(item) + ",";
            }
            json = json.substring(0, json.length() - 1) + "]";
            return json;
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("Key", 1);
        testMap.put("Key1", 15);
        System.out.println(toJSON(testMap));

        String testString = "TEST STRING";
        System.out.println(toJSON(testString));

        int testInt = 10;
        System.out.println(toJSON(testInt));

        boolean testBoolean = false;
        System.out.println(toJSON(testBoolean));

        List<String> list = new LinkedList<>();
        list.add("Test");
        list.add("Testing");
        System.out.println(toJSON(list));
    }
}
