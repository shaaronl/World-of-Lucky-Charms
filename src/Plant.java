public interface Plant extends Entity{
    int getHealth();
    void setHealth(int health);
    default boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getHealth() <= 0) {
            Entity stump = Factory.createStump(Parse.STUMP_KEY + "_" + this.getId(), this.getPosition(),
                    imageStore.getImageList(Parse.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }
}
