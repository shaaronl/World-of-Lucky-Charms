import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public interface Entity {

    String getId();
    Point getPosition();
    void setPosition(Point position);
    void setImageIndex(int index);
    int getImageIndex();
    List<PImage> getImages();
    default PImage getCurrentImage() {
        return this.getImages().get(getImageIndex() % this.getImages().size());
    } // default are all entities using getCurrentImage?
    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     */
    default String log(){
        return this.getId().isEmpty() ? null :
                String.format("%s %d %d %d", this.getId(), this.getPosition().getX(), this.getPosition().getY(), this.getImageIndex());
    }

}
