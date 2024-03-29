package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;


public class Bird {
    private Vector2 position;
    private Vector2 velocity; //скорость
    private Vector2 acceleration; //ускорение

    private float rotation; //угол поворота птички
    private int width;
    private int height;

    private Circle boundingCircle;

    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }
        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);
        //поворот против часовой стрелки
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }
        //поворот по часовой стрелке
        if (isFalling()) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70;
    }

    public Circle getBoundingCircle(){
        return boundingCircle;
    }
}
