import java.util.*;

class Task7{

    public static class Node {
        private final String name;
        private Stack<Node> reversePath = new Stack<>();
        private int distance = Integer.MAX_VALUE; //расстояние от начального узла до текущего
        private List<Node> shortestPath = new LinkedList<>();
        private Map<Node, Integer> adjacentNodes = new HashMap<>(); //карта смежных узлов и их весов

        public Node(String name) {
            this.name = name;
        }
        public Stack<Node> getReversePath() {
            return reversePath;
        }

        public void addAdjacentNode(Node node, int weight) {//метод добавляющий смежный узел с весом
            adjacentNodes.put(node, weight);
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        private void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }
        public void printPaths(){
            if (shortestPath.isEmpty()) {
                System.out.println("Кратчайший путь для узла " + name + " не определен.");
            } else {
                System.out.print("Прямой маршрут из столицы в город " + name + ": ");
                for (Node node : shortestPath) {
                    System.out.print(node.name + " -> ");
                }
                System.out.println(name);
                System.out.println("Общее расстояние: " + distance + " км.");

                System.out.print("Маршрут в столицу для города " + name + ": ");
                while (!reversePath.isEmpty()) {
                    Node node = reversePath.pop();
                    System.out.print(node.name);
                    if (!reversePath.isEmpty()) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void calculateShortestPath(Node source) {
        source.setDistance(0);//Устанавливаем расстояние для начального узла
        Set<Node> settledNodes = new HashSet<>(); //Список посещенных узлов
        Queue<Node> unsettledNodes = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));//Очередь непосещенных узлов которые мы сортируем по дистанции
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) { //Пока у нас есть непосещенные узлы
            Node currentNode = unsettledNodes.poll(); //Получаем узел с наименьшим расстоянием, т.к очередь посортирована

            for (Map.Entry<Node, Integer> entry : currentNode.getAdjacentNodes().entrySet()) { //здесь мы перебираем смежные узлы
                Node adjacentNode = entry.getKey();
                int edgeWeight = entry.getValue();

                if (!settledNodes.contains(adjacentNode)) { //если узел не посещен
                    evaluateDistanceAndPath(adjacentNode, edgeWeight, currentNode); //оцениваем расстояние и путь
                    unsettledNodes.add(adjacentNode); //и добавляем смежный узел в нашу очередь
                }
            }

            settledNodes.add(currentNode); //теперь добавляем наш узел в посещенные.
        }
    }

    private static void evaluateDistanceAndPath(Node adjacentNode, int edgeWeight, Node sourceNode) { //оцениваем расстояние и путь до смежного узла
        int newDistance = sourceNode.getDistance() + edgeWeight; //новое расстояние до смежного узла

        if (newDistance < adjacentNode.getDistance()) { // Если новое расстояние меньше текущего расстояния до узла
            adjacentNode.setDistance(newDistance); //обновляем расстояние
            List<Node> newPath = new LinkedList<>(sourceNode.getShortestPath()); //создаем новый путь к смежному узлу делая глубокую копию ShortestPath
            newPath.add(sourceNode);
            adjacentNode.setShortestPath(newPath); //обновляем кратчайший путь у смежного узла

            // Обновляем стек маршрута для обратного пути
            adjacentNode.getReversePath().clear();
            adjacentNode.getReversePath().addAll(newPath);
            adjacentNode.getReversePath().add(adjacentNode);
        }
    }

    public static void main(String[] args) {

        Node bratislava = new Node("Bratislava");
        Node nitra = new Node("Nitra");
        Node noveZamky = new Node("Nove Zamky");
        Node levice = new Node("Levice");
        Node prievidza = new Node("Prievidza");
        Node koshice = new Node("Koshice");

        bratislava.addAdjacentNode(nitra, 92);
        bratislava.addAdjacentNode(noveZamky, 113);

        nitra.addAdjacentNode(noveZamky, 38);
        nitra.addAdjacentNode(levice, 61);
        nitra.addAdjacentNode(prievidza, 84);

        noveZamky.addAdjacentNode(levice, 53);

        levice.addAdjacentNode(prievidza, 94);
        levice.addAdjacentNode(koshice, 289);

        prievidza.addAdjacentNode(koshice, 281);

        calculateShortestPath(bratislava);

        koshice.printPaths();
        System.out.println("------------------------------------------------------------------------");
        prievidza.printPaths();
        System.out.println("------------------------------------------------------------------------");
        levice.printPaths();
    }
}