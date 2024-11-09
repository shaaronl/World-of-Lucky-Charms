import processing.core.PImage;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Leprechaun extends DudeNotFull{
    // essentially a dudeNotFull but will randomly add in lucky charms into the world while also chopping down trees
    public Leprechaun(String id, Point position, List<PImage> images, double animationPeriod,
                             double actionPeriod, int resourceLimit, int resourceCount)  {
        super(id, position, images, animationPeriod,actionPeriod, resourceLimit, resourceCount);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !super.transformNotFull(world, scheduler, imageStore)) {
            super.executeActivity(world, imageStore, scheduler);
        }

        if (Functions.rand.nextInt(18) == 1) {
            List<Point> getList = new ArrayList<>();
            getList.add((new Point(getPosition().x, getPosition().y - 1)));
            getList.add((new Point(getPosition().x, getPosition().y + 1)));
            getList.add((new Point(getPosition().x - 1, getPosition().y)));
            getList.add((new Point(getPosition().x + 1, getPosition().y)));

            Random rand = new Random();
            Point newp = getList.get(rand.nextInt(getList.size()));

            if (world.withinBounds(newp) && (!world.isOccupied(newp)) && newp.adjacent(getPosition())) {
                int charm = rand.nextInt(7);
                AnimationEntity lc;
                switch(charm) {
                    case 1:
                        lc = Factory.createMoonCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                    case 2:
                        lc = Factory.createHorseShoeCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                    case 3:
                        lc = Factory.createStarCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                    case 4:
                        lc = Factory.createHeartCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                    case 5:
                        lc = Factory.createHatCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                    case 6:
                        lc = Factory.createBalloonCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                    default: // case 0 or default
                        lc = Factory.createRainbowCharm(Parse.LUCKYCHARM_KEY,
                                newp, 1, imageStore.getImageList(Parse.LUCKYCHARM_KEY + charm));
                        break;
                }
                world.addEntity(lc);
                lc.scheduleActions(scheduler,world,imageStore);
                VirtualWorld.luckyCharmCount += 1;
            }
        }
    }
    protected ActionEntity _getDude() {
        return Factory.createLeprechaun(this.getId(), this.getPosition(), this.getActionPeriod(),
                this.getAnimationPeriod(), this.getResourceLimit(), this.getImages());
    }
}
