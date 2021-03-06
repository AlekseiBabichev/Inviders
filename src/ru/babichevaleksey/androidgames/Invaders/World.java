package ru.babichevaleksey.androidgames.Invaders;

import ru.babichevaleksey.androidgames.framework.math.OverlapTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Aleksei on 27.01.14.
 */
public class World {
    public interface WorldListener {
        public void explotion();
        public void shot();
    }

    final static float WORLD_MIN_X = -14;
    final static float WORLD_MAX_X = 14;
    final static float WORLD_MIN_Z = -15;

    WorldListener listener;
    int waves = 1;
    int score = 0;
    float speedMultiplier = 1;
    final List<Shot> shots = new ArrayList<Shot>();
    final List<Invader> invaders = new ArrayList<Invader>();
    final List<Shield> shields = new ArrayList<Shield>();
    final Ship ship;
    long lastShotTime;
    Random random;

    public World(){
        ship = new Ship(0, 0, 0);
        generateInvaders();
        generateShields();
        lastShotTime = System.nanoTime();
        random = new Random();
    }

    private void generateShields() {
        for (int shield = 0; shield < 3; shield++) {
            shields.add(new Shield(-10 + shield * 10 - 1, 0, -3));
            shields.add(new Shield(-10 + shield * 10 + 0, 0, -3));
            shields.add(new Shield(-10 + shield * 10 + 1, 0, -3));
            shields.add(new Shield(-10 + shield * 10 - 1, 0, -2));
            shields.add(new Shield(-10 + shield * 10 + 1, 0, -2));
        }
    }

    private void generateInvaders() {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 8; column++) {
                Invader invader = new Invader(-WORLD_MAX_X / 2 + column * 2f, 0, WORLD_MIN_Z + row * 2f);
                invaders.add(invader);
            }
        }
    }

    public void setWorldListener(WorldListener worldListener){
        this.listener = worldListener;
    }

    public void update(float deltaTime, float accelY){
        ship.update(deltaTime, accelY);
        updateInvaders(deltaTime);
        updateShots(deltaTime);

        checkShotCollisions();
        checkInvadersCollisions();

        if (invaders.size() == 0){
            generateInvaders();
            waves++;
            speedMultiplier += 0.5f;
        }
    }

    private void checkShotCollisions() {
        int len = shots.size();
        for (int i = 0; i < len; i++) {
            Shot shot = shots.get(i);
            boolean shotRemoved = false;

            int len2 = shields.size();
            for (int j = 0; j < len2; j++) {
                Shield shield = shields.get(j);
                if (OverlapTester.overlapSpheres(shield.sphere, shot.sphere)){
                    shields.remove(j);
                    shots.remove(i);
                    i--;
                    len--;
                    shotRemoved = true;
                    break;
                }
            }
            if (shotRemoved)
                continue;

            if (shot.velocity.z < 0){
                len2 = invaders.size();
                for (int j = 0; j < len2; j++) {
                    Invader invader = invaders.get(j);
                    if (OverlapTester.overlapSpheres(invader.sphere, shot.sphere)
                            && invader.state == Invader.INVADER_ALIVE){
                        invader.kill();
                        listener.explotion();
                        score += 10;
                        shots.remove(i);
                        i--;
                        len--;
                        break;
                    }
                }
            } else {
                if (OverlapTester.overlapSpheres(shot.sphere, ship.sphere) && ship.state == Ship.SHIP_ALIVE){
                    ship.kill();
                    listener.explotion();
                    shots.remove(i);
                    i--;
                    len--;
                }
            }
        }
    }

    private void checkInvadersCollisions() {
        if (ship.state == Ship.SHIP_EXPLODING)
            return;
        for(Invader invader : invaders){
            if (OverlapTester.overlapSpheres(ship.sphere, invader.sphere)){
                ship.lives = 1;
                ship.kill();
            }
        }
    }

    private void updateInvaders(float deltaTime) {
        int len = invaders.size();
        for (int i = 0; i < len; i++) {
            Invader invader = invaders.get(i);
            invader.update(deltaTime, speedMultiplier);

            if (invader.state == Invader.INVADER_ALIVE){
                if (random.nextFloat() < 0.001f){
                    Shot shot = new Shot(invader.position.x, invader.position.y, invader.position.z, Shot.SHOT_VELOCITY);
                    shots.add(shot);
                    listener.shot();
                }
            }
            if (invader.state == Invader.INVADER_DEAD && invader.stateTime > Invader.INVADER_EXPLOSION_TIME){
                invaders.remove(i);
                i--;
                len--;
            }
        }
    }

    private void updateShots(float deltaTime) {
        int len = shots.size();
        for (int i = 0; i < len; i++) {
            Shot shot = shots.get(i);
            shot.update(deltaTime);
            if (shot.position.z < WORLD_MIN_Z || shot.position.z > 0){
                shots.remove(i);
                i--;
                len--;
            }
        }
    }

    public boolean isGameOver(){
        return ship.lives == 0;
    }

    public void shot(){
        if (ship.state == Ship.SHIP_EXPLODING)
            return;
        int friendlyShots = 0;
        int len = shots.size();
        for (Shot shot : shots) {
            if (shot.velocity.z < 0)
                friendlyShots++;
        }
        if (System.nanoTime() - lastShotTime > 1000000000 || friendlyShots == 0){
            shots.add(new Shot(ship.position.x, ship.position.y, ship.position.z, -Shot.SHOT_VELOCITY));
            lastShotTime = System.nanoTime();
            listener.shot();
        }
    }

}
