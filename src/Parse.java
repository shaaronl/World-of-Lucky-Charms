import java.util.*;

public class Parse {

    public static final String LUCKYCHARM_KEY = "luckycharm";
    public static final int LUCKYCHARM_ANIMATION_PERIOD = 0;
    public static final int LUCKYCHARM_NUM_PROPERTIES = 1;
    public static final String LEPRECHAUN_KEY = "leprechaun";
    public static final int LEPRECHAUN_NUM_PROPERTIES = 3;
    public static final String RAINBOW_KEY = "rainbow";
    public static final String STUMP_KEY = "stump";
    public static final String SAPLING_KEY = "sapling";
    public static final String TREE_KEY = "tree";
    public static final String UNICORN_KEY = "unicorn";
    public static final double TREE_ANIMATION_MAX = 0.600;
    public static final double TREE_ANIMATION_MIN = 0.050;
    public static final double TREE_ACTION_MAX = 1.400;
    public static final double TREE_ACTION_MIN = 1.000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;
    private static final int PROPERTY_KEY = 0;
    private static final int PROPERTY_ID = 1;
    private static final int PROPERTY_COL = 2;
    private static final int PROPERTY_ROW = 3;
    private static final int ENTITY_NUM_PROPERTIES = 4;
    private static final String OBSTACLE_KEY = "obstacle";
    private static final int OBSTACLE_ANIMATION_PERIOD = 0;
    private static final int OBSTACLE_NUM_PROPERTIES = 1;

    public static final String DUDE_KEY = "dude";
    private static final int DUDE_ACTION_PERIOD = 0;
    private static final int DUDE_ANIMATION_PERIOD = 1;
    private static final int DUDE_LIMIT = 2;
    private static final int DUDE_NUM_PROPERTIES = 3;

    public static final String HOUSE_KEY = "house";
    private static final int HOUSE_NUM_PROPERTIES = 0;

    public static final String FAIRY_KEY = "fairy";
    private static final int FAIRY_ANIMATION_PERIOD = 0;
    private static final int FAIRY_ACTION_PERIOD = 1;
    private static final int FAIRY_NUM_PROPERTIES = 2;
    public static final String EVILFAIRY_KEY = "evilFairy";
    private static final int TREE_ANIMATION_PERIOD = 0;
    private static final int TREE_ACTION_PERIOD = 1;
    private static final int TREE_HEALTH = 2;
    private static final int TREE_NUM_PROPERTIES = 3;
    private static final int STUMP_NUM_PROPERTIES = 0;

    private static final int SAPLING_HEALTH = 0;
    private static final int SAPLING_NUM_PROPERTIES = 1;
    static void parseDude(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == DUDE_NUM_PROPERTIES) {
            Entity entity = Factory.createDudeNotFull(id, pt, Double.parseDouble(properties[DUDE_ACTION_PERIOD]),
                    Double.parseDouble(properties[DUDE_ANIMATION_PERIOD]), Integer.parseInt(properties[DUDE_LIMIT]), imageStore.getImageList(DUDE_KEY));
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", DUDE_KEY, DUDE_NUM_PROPERTIES));
        }
    }
    public static void parseLeprechaun(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == LEPRECHAUN_NUM_PROPERTIES) {
            Entity entity = Factory.createLeprechaun(id, pt, Double.parseDouble(properties[DUDE_ACTION_PERIOD]), Double.parseDouble(properties[DUDE_ANIMATION_PERIOD]), Integer.parseInt(properties[DUDE_LIMIT]), imageStore.getImageList(DUDE_KEY));
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", LEPRECHAUN_KEY, LEPRECHAUN_NUM_PROPERTIES));
        }
    }

    static void parseFairy(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == FAIRY_NUM_PROPERTIES) {
            Entity entity = Factory.createFairy(id, pt, Double.parseDouble(properties[FAIRY_ACTION_PERIOD]),
                    Double.parseDouble(properties[FAIRY_ANIMATION_PERIOD]), imageStore.getImageList(FAIRY_KEY));
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", FAIRY_KEY, FAIRY_NUM_PROPERTIES));
        }
    }
    private static void parseSapling(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == SAPLING_NUM_PROPERTIES) {
            int health = Integer.parseInt(properties[SAPLING_HEALTH]);
            Entity entity = Factory.createSapling(id, pt, imageStore.getImageList(SAPLING_KEY), health);
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", SAPLING_KEY, SAPLING_NUM_PROPERTIES));
        }
    }

    static void parseTree(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == TREE_NUM_PROPERTIES) {
            Entity entity = Factory.createTree(id, pt, Double.parseDouble(properties[TREE_ACTION_PERIOD]),
                    Double.parseDouble(properties[TREE_ANIMATION_PERIOD]), Integer.parseInt(properties[TREE_HEALTH]), imageStore.getImageList(TREE_KEY));
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", TREE_KEY, TREE_NUM_PROPERTIES));
        }
    }

    static void parseObstacle(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Entity entity = Factory.createObstacle(id, pt, Double.parseDouble(properties[OBSTACLE_ANIMATION_PERIOD]), imageStore.getImageList(OBSTACLE_KEY));
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", OBSTACLE_KEY, OBSTACLE_NUM_PROPERTIES));
        }
    }
    static void parseLuckyCharm(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == LUCKYCHARM_NUM_PROPERTIES) {
            Entity entity;
            switch (id) {
                case "luckycharm1" -> entity = Factory.createMoonCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                        imageStore.getImageList("luckycharm1"));
                case "luckycharm2" -> entity = Factory.createHorseShoeCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                        imageStore.getImageList("luckycharm2"));
                case "luckycharm3" -> entity = Factory.createStarCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                        imageStore.getImageList("luckycharm3"));
                case "luckycharm4" -> entity = Factory.createHeartCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                        imageStore.getImageList("luckycharm4"));
                case "luckycharm5" -> entity = Factory.createHatCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                        imageStore.getImageList("luckycharm5"));
                case "luckycharm6" -> entity = Factory.createBalloonCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                        imageStore.getImageList("luckycharm6"));
                default -> // case 0 or default
                        entity = Factory.createRainbowCharm(id, pt, Double.parseDouble(properties[LUCKYCHARM_ANIMATION_PERIOD]),
                                imageStore.getImageList("luckycharm0"));
            }
            world.tryAddEntity(entity);
        } else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", LUCKYCHARM_KEY, LUCKYCHARM_NUM_PROPERTIES));
        }
    }

    static void parseHouse(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == HOUSE_NUM_PROPERTIES) {
            Entity entity = Factory.createHouse(id, pt, imageStore.getImageList(HOUSE_KEY));
            world.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", HOUSE_KEY, HOUSE_NUM_PROPERTIES));
        }
    }

    static void parseStump(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == STUMP_NUM_PROPERTIES) {
            Entity entity = Factory.createStump(id, pt, imageStore.getImageList(STUMP_KEY));
            world.tryAddEntity(entity);
        } else {
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", STUMP_KEY, STUMP_NUM_PROPERTIES));
        }
    }

    static void parseEntity(WorldModel world, String line, ImageStore imageStore) {
        String[] properties = line.split(" ", ENTITY_NUM_PROPERTIES + 1);
        if (properties.length >= ENTITY_NUM_PROPERTIES) {
            String key = properties[PROPERTY_KEY];
            String id = properties[PROPERTY_ID];
            Point pt = new Point(Integer.parseInt(properties[PROPERTY_COL]), Integer.parseInt(properties[PROPERTY_ROW]));

            properties = properties.length == ENTITY_NUM_PROPERTIES ?
                    new String[0] : properties[ENTITY_NUM_PROPERTIES].split(" ");

            switch (key) {
                case OBSTACLE_KEY -> parseObstacle(world, properties, pt, id, imageStore);
                case DUDE_KEY -> parseDude(world, properties, pt, id, imageStore);
                case FAIRY_KEY -> parseFairy(world, properties, pt, id, imageStore);
                case HOUSE_KEY -> parseHouse(world, properties, pt, id, imageStore);
                case TREE_KEY -> parseTree(world, properties, pt, id, imageStore);
                case SAPLING_KEY -> parseSapling(world, properties, pt, id, imageStore);
                case STUMP_KEY -> parseStump(world, properties, pt, id, imageStore);
                case LEPRECHAUN_KEY -> parseLeprechaun(world, properties, pt, id, imageStore);
                case LUCKYCHARM_KEY -> parseLuckyCharm(world, properties, pt, id, imageStore);
                default -> throw new IllegalArgumentException("Entity key is unknown");
            }
        }else{
            throw new IllegalArgumentException("Entity must be formatted as [key] [id] [x] [y] ...");
        }
    }

    public static void parseSaveFile(WorldModel world, Scanner saveFile, ImageStore imageStore, Background defaultBackground){
        String lastHeader = "";
        int headerLine = 0;
        int lineCounter = 0;
        while(saveFile.hasNextLine()){
            lineCounter++;
            String line = saveFile.nextLine().strip();
            if(line.endsWith(":")){
                headerLine = lineCounter;
                lastHeader = line;
                switch (line){
                    case "Backgrounds:" -> world.setBackground(new Background[world.getNumRows()][world.getNumCols()]);
                    case "Entities:" -> {
                        world.setOccupancy(new Entity[world.getNumRows()][world.getNumCols()]);
                        world.setEntities(new HashSet<>());
                    }
                }
            }else{
                switch (lastHeader){
                    case "Rows:" -> world.setNumRows(Integer.parseInt(line));
                    case "Cols:" -> world.setNumCols(Integer.parseInt(line));
                    case "Backgrounds:" -> parseBackgroundRow(world, line, lineCounter-headerLine-1, imageStore);
                    case "Entities:" -> parseEntity(world, line, imageStore);
                }
            }
        }
    }

    private static void parseBackgroundRow(WorldModel world, String line, int row, ImageStore imageStore) {
        String[] cells = line.split(" ");
        if(row < world.getNumRows()){
            int rows = Math.min(cells.length, world.getNumCols());
            for (int col = 0; col < rows; col++){
                world.getBackground()[row][col] = new Background(cells[col], imageStore.getImageList(cells[col]));
            }
        }
    }
    public static HashMap<Integer, List<Integer>> createRGBPoints(){
        HashMap<Integer, List<Integer>> RGBpoints = new HashMap<Integer, List<Integer>>();
        RGBpoints.put(21, List.of(25));
        RGBpoints.put(22, List.of(25, 24));
        RGBpoints.put(23, List.of(24,23));
        RGBpoints.put(24, List.of(23, 22));
        RGBpoints.put(25, List.of(23, 22));
        RGBpoints.put(26, List.of(23, 22));
        RGBpoints.put(27, List.of(23, 22));
        RGBpoints.put(28, List.of(23, 22));
        RGBpoints.put(29, List.of(23, 22));
        RGBpoints.put(30, List.of(23, 22));
        RGBpoints.put(31, List.of(23, 22));
        RGBpoints.put(32, List.of(23, 22));
        RGBpoints.put(33, List.of(23, 22));
        RGBpoints.put(34, List.of(24, 23));
        RGBpoints.put(35, List.of(25, 24));
        RGBpoints.put(36, List.of(25));
        return RGBpoints;
    }


    public static void parseRainbow(WorldModel world, ImageStore imageStore) {
        HashMap<Integer, List<Integer>> RGBpoints = Parse.createRGBPoints();
        for (int x = 21; x <=36; x ++){
            if(RGBpoints.containsKey(x)) {
                for (int y : RGBpoints.get(x)) {
                    world.getBackground()[y][x] =
                        new Background("rainbow", imageStore.getImageList(RAINBOW_KEY));
            }
          }
        }
    }

}
