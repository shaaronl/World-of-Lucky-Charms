import processing.core.PImage;

import java.util.List;

public class Obstacle implements Entity, AnimationEntity{
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;
    private final double animationPeriod;

    public Obstacle(String id, Point position, List<PImage> images, double animationPeriod) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;

        this.animationPeriod = animationPeriod;
    }
    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    public int getImageIndex() {
        return imageIndex;
    }
    public void setImageIndex(int index){
        imageIndex = index;
    }
    public List<PImage> getImages() {
        return images;
    }
    public double getAnimationPeriod() {return animationPeriod;}
}

