package me.zeroest.rxjava.single_maybe_completeable.subscribeon_observeon;

public class MyShape {
    private String color;
    private String shape;

    public MyShape(String color, String shape) {
        this.color = color;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "MyShape{" +
            "color='" + color + '\'' +
            ", shape='" + shape + '\'' +
            '}';
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
