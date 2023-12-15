import java.util.*;

public class Task4 {
    private static boolean checkBrackets(String text){
        Map<Character, Character> brackets = new HashMap<>(); // Создаем карту для хранения пар скобок
        brackets.put(')', '('); //ключи - закрывающие скобки, значения - открывающие.
        brackets.put('}', '{');
        brackets.put(']', '[');

        Deque<Character> stack = new LinkedList<>();
        for(Character c : text.toCharArray()){ //итерируемся по всей строке
            if (brackets.containsValue(c)){ //Если открывающая скобка - добавляем в стек.
                stack.push(c);
            }
            else if (brackets.containsKey(c)){ //если закрывающая скобка
                if(stack.isEmpty() || stack.pop() != brackets.get(c)){ //Если стек пуст или
                    //верхний элемент стека не соответствует открывающей скобке, то получаем false
                    return false;
                }
            }
        }
        return stack.isEmpty(); //В конце проверяем остались ли ещё открывающие скобки в стеке.
        //Если он пуст, то выражение написано верно
    }

    public static void main(String[] args) {
        System.out.println("Enter the data: ");
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        System.out.println(checkBrackets(data));
    }
}
