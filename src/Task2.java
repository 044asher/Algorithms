import java.util.LinkedList;
import java.util.Queue;

public class Task2 {
   public static class BinaryTree {
       private static class Node { // представляет узел бинарного дерева
           private char value; // Значение узла
           private Node right, left; // Ссылки на левого и правого потомков

           public Node(char value) { // Конструктор узла
               this.value = value;
           }
       }

       private Node root; // Корневой узел дерева
       public BinaryTree(){
           root = null;
       }
       public void add(char value) {
                   // Метод добавляет новый узел в дерево.
           if (root == null) { // Если корневой узел не существует,
               root = new Node(value); // то новый элемент и есть корень
           }
           else {
               Node current = root;
               while (true) { // Проходим по дереву, пока не найдем место для вставки
                    if(value == current.value){ // если такой элемент в дереве уже есть, не сохраняем его
                        return; // просто выходим из метода
                    }
                    else if (value < current.value){
                        if(current.left == null){ // если слева пусто,
                            current.left = new Node(value); // то вставить слева и выйти из метода
                            return;
                        }
                        else {
                            current = current.left;
                        }
                    }
                    else if(value > current.value){
                        if(current.right == null){
                            current.right = new Node(value);
                            return;
                        }
                        else {
                            current = current.right;
                        }
                    }
               }
           }
       }

       //--------------------------------------------------------//
       public void recursive(){ // Метод выполняет рекурсивный обход дерева в глубину.
           recursive(root); // начинаем обход с корня
       }
       private void recursive(Node node){
           if(node != null){ // Если узел не null, выполнить обход его потомков
               recursive(node.left);  // Обход левого поддерева
               System.out.print(node.value + "\t");
               recursive(node.right); // Обход правого поддерева
           }
       }
       //--------------------------------------------------------//
       public void width(){ // Метод выполняет итеративный обход дерева в ширину.
           Queue<Node> queue = new LinkedList<>();  // Очередь для хранения узлов
           queue.offer(root); // Добавляем корень в очередь

           while(!queue.isEmpty()){
               Node node = queue.poll(); // Извлечь узел из очереди. Первым пойдет корень, и далее будем добавлять потомков пока они есть и извлекать их пока дерево не станет пустым и сама очередь не закончится
               System.out.print(node.value + "\t");

               if(node.left != null){ // Если есть левый потомок, добавить его в очередь
                   queue.offer(node.left);
               }
               if (node.right != null){ // Если есть правый потомок, добавить его в очередь
                   queue.offer(node.right);
               }
           }
       }
   }

    public static void main(String[] args) {
       BinaryTree tree = new BinaryTree();
       tree.add('C');
       tree.add('B');
       tree.add('D');
       tree.add('E');
       tree.add('A');
       tree.add('F');
       tree.add('A');



       System.out.println("Обход в глубину:");
       tree.recursive();

       System.out.println("\nОбход в ширину:");
       tree.width();
    }
}
