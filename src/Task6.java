import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class Task6 {
    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;
        public double weight;

        public Cat() {
        }
    }
    @JsonAutoDetect // Помечает класс как готовый к сериализации в JSON.
    public static class Dog{
        @JsonProperty("alias") // Позволяет задать другое имя поля при сериализации.
        public String name;
        public int age;
        @JsonIgnore // Свойство игнорируется при сериализации.
        public double weight;
    }



    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Knopa";
        cat.age = 6;
        cat.weight = 4;

        //Serialization:
        StringWriter writer = new StringWriter(); //писать результат сериализации будем во Writer
        ObjectMapper mapper = new ObjectMapper(); //объект Jackson, который выполняет сериализацию
        mapper.writeValue(writer, cat); //сама сериализация: 1-куда, 2-что

        String result = writer.toString(); //преобразовываем все записанное во StringWriter в строку
        System.out.println(result);

        //Deserialization:
        String jsonString = "{ \"name\":\"Knopa\", \"age\":6, \"weight\":4}";
        StringReader reader = new StringReader(jsonString);

        Cat cat1 = mapper.readValue(reader, Cat.class);
        System.out.println("Deserialized Cat:");
        System.out.println("Name: " + cat1.name);
        System.out.println("Age: " + cat1.age);
        System.out.println("Weight: " + cat1.weight);


        Dog dog = new Dog();
        dog.name = "Neya";
        dog.age = 2;
        dog.weight = 20;

        StringWriter writer1 = new StringWriter();
        ObjectMapper mapper1 = new ObjectMapper();
        mapper1.writeValue(writer1, dog);
        String result1 = writer1.toString();
        System.out.println(result1);

    }
}
