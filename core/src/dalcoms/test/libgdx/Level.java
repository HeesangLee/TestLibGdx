package dalcoms.test.libgdx;

public class Level {
    private int levelNum;
    private float shootingVelocity;
    private float radius;
    private Rotatingtype rotatingType;
    private float angularVelocityMin;
    private float angularVelocityMax;
    private float angularVelocity;
    private RotationDirection rotationDirection;

    public Level(){

    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public float getShootingVelocity() {
        return shootingVelocity;
    }

    public void setShootingVelocity(float shootingVelocity) {
        this.shootingVelocity = shootingVelocity;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Rotatingtype getRotatingType() {
        return rotatingType;
    }

    public void setRotatingType(Rotatingtype rotatingType) {
        this.rotatingType = rotatingType;
    }

    public float getAngularVelocityMin() {
        return angularVelocityMin;
    }

    public void setAngularVelocityMin(float angularVelocityMin) {
        this.angularVelocityMin = angularVelocityMin;
    }

    public float getAngularVelocityMax() {
        return angularVelocityMax;
    }

    public void setAngularVelocityMax(float angularVelocityMax) {
        this.angularVelocityMax = angularVelocityMax;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public RotationDirection getRotationDirection() {
        return rotationDirection;
    }

    public void setRotationDirection(RotationDirection rotationDirection) {
        this.rotationDirection = rotationDirection;
    }

    public enum Rotatingtype{
        CONSTANT, ACCELERATION, DECELERATION
    }
    public enum RotationDirection{
        CW,CCW
    }
}
