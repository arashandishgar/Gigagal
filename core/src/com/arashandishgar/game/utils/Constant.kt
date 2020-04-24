package com.arashandishgar.game.utils

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

//World
val KILL_PLANE = -100
val BACK_GROUND_COLOR = Color.SKY
val TEXTURE_ATLAS = "images/gigagal.pack.atlas"
val WORLD_SIZE: Float = 160f
val GRAVITY = WORLD_SIZE / 10

//Giggal
val STANDING_LEFT: String = "standing-left"
val STANDING_RIGHT = "standing-right"
val JUMPING_RIGHT = "jumping-right"
val JUMPING_LEFT = "jumping-left"
val WALKING_2_RIGHT = "walk-2-right"
val WALKING_2_LEFT = "walk-2-left"
val WALKING_1_RIGHT = "walk-1-right"
val WALKING_1_LEFT = "walk-1-left"
val WALKING_3_RIGHT = "walk-3-right"
val WALKING_3_LEFT = "walk-3-left"
val GIGAGAL_EYE_POSITION = Vector2(16f, 24f)
val GIGAGAL_STANCE_WIDTH = 21.0f
val GIGAGAL_EYE_HEIGHT = 16f
val GIGAGAL_HEIGHT =23
val GIGAGAL_MOVMENT_SPEED = Vector2(WORLD_SIZE / 1.5f, 1.5f * WORLD_SIZE)
val Gigal_WALK_LOOP_DURATION = .15f
val GIGAGAL_MAX_JUMPTIME = .1f
val GigaGal_ENEMY_KNOCKBACK=Vector2(200f,200f)
val GIGAGAL_CANNON_OFFSET_FROM_EYE = Vector2(12f, -8f)
val GIGAGAL_MAX_BULLET_NUMBER: Int = 10


//platfotm
val NINEPATCH_EDJE = 8
val PLATFORM = "platform"

//Enemy
val ENEMY="enemy"
val ENEMY_CENTER = Vector2(14f, 22f)
val ENEMY_CIRCLE_RAIDIUS: Float= ENEMY_CENTER.x+1
val ENEMY_HEALTH: Int=5
val ENEMY_SPEED=Vector2(10f,10f);
val EMEMY_DURATION: Float =3f
val MAX_ENEMY_JUMP_HEIGHT: Float=2f

//ChaseCam
val CAMERA_DEBUG_SPEED = Vector2(50f, 50f);

//Bullet
val BULLET="bullet"
val BULLET_HEIGHT=5f
val BULLET_WIDTH: Float = 7f
val BULLET_SPEED=Vector2(150f,0f)

//Power up
val POWER_UP="powerup"
val POWER_UP_HEIGHT=14
val POWER_UP_WIDTH=15
val POWER_UP_NUMBER: Int =10

//explosion
val EXPLOSION_LARGE="explosion-large"
val EXPLOSION_SMALL="explosion-small"
val EXPLOSION_MEDUIM="explosion-medium"
val EXPLOSION_DURATION=.2f
val EXPLOSION_OFFSET=Vector2(9f,9f)

//exit portal
val EXIT_POORTAL_1="exit-portal-1"
val EXIT_POORTAL_2="exit-portal-2"
val EXIT_POORTAL_3="exit-portal-3"
val EXIT_POORTAL_4="exit-portal-4"
val EXIT_POORTAL_5="exit-portal-5"
val EXIT_POORTAL_6="exit-portal-6"