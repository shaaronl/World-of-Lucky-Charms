import processing.core.PImage;

import java.util.List;

public class Factory {
    private static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
    private static final int SAPLING_HEALTH_LIMIT = 5;

    public static Entity createHouse(String id, Point position, List<PImage> images) {
        return new House(id, position, images);
    }
    //charms
    public static AnimationEntity createBalloonCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new BalloonCharm(id, position, images, animationPeriod);
    }
    public static AnimationEntity createHorseShoeCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new HorseShoeCharm(id, position, images, animationPeriod);
    }
    public static AnimationEntity createRainbowCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new RainbowCharm(id, position, images, animationPeriod);
    }
    public static AnimationEntity createHatCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new HatCharm(id, position, images, animationPeriod);
    }
    public static AnimationEntity createStarCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new StarCharm(id, position, images, animationPeriod);
    }
    public static AnimationEntity createHeartCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new HeartCharm(id, position, images, animationPeriod);
    }
    public static AnimationEntity createMoonCharm(String id, Point position, double animationPeriod, List<PImage> images) {
        return new MoonCharm(id, position, images, animationPeriod);
    }


    public static AnimationEntity createObstacle(String id, Point position, double animationPeriod, List<PImage> images) {
        return new Obstacle(id, position, images, animationPeriod);
    }

    //plants
    public static ActionEntity createTree(String id, Point position, double actionPeriod, double animationPeriod, int health, List<PImage> images) {
        return new Tree(id, position, images, animationPeriod, actionPeriod, health);
    }

    public static Entity createStump(String id, Point position, List<PImage> images) {
        return new Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static ActionEntity createSapling(String id, Point position, List<PImage> images, int health) {
        return new Sapling(id, position, images, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
    }

    public static ActionEntity createDudeNotFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
        return new DudeNotFull(id, position, images, animationPeriod, actionPeriod, resourceLimit, 0);
    }

    public static ActionEntity createDudeFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
        return new DudeFull(id, position, images, animationPeriod, actionPeriod, resourceLimit);
    }
    public static ActionEntity createLeprechaun(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
        return new Leprechaun(id, position, images, animationPeriod, actionPeriod, resourceLimit, 0);
    }

    public static ActionEntity createFairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        return new Fairy(id, position, images, animationPeriod, actionPeriod);
    }
    public static ActionEntity createEvilFairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        return new EvilFairy(id, position, images, animationPeriod, actionPeriod);
    }
    public static ActionEntity createUnicorn(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images){
        return new Unicorn(id, position, images, animationPeriod, actionPeriod);
    }

    public static Action createActivityAction(ActionEntity entity, WorldModel world, ImageStore imageStore) {
        return new Activity(entity, world, imageStore);
    }

    public static Action createAnimationAction(AnimationEntity entity, int repeatCount) {
        return new Animation(entity, repeatCount);
    }

}
