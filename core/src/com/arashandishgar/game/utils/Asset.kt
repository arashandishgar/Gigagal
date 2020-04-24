package com.arashandishgar.game.utils

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable

object Asset : Disposable, AssetErrorListener {

    private val TAG = this.javaClass.name
    private val assetManager = AssetManager().apply {
        setErrorListener(this@Asset)
    }
    private val textureAtlas by lazy {
        assetManager.load(TEXTURE_ATLAS, TextureAtlas::class.java)
        assetManager.finishLoading()
        assetManager.get(TEXTURE_ATLAS) as TextureAtlas
    }

    override fun dispose() {
        assetManager.dispose()
        textureAtlas.dispose()
    }

    override fun error(asset: AssetDescriptor<*>?, throwable: Throwable?) {
    }

    object GigaGalAsset {
        val stnadingRight = textureAtlas.findRegion(STANDING_RIGHT)
        val stnadingLeft = textureAtlas.findRegion(STANDING_LEFT)
        val jumpingRight = textureAtlas.findRegion(JUMPING_RIGHT)
        val jumpingLeft = textureAtlas.findRegion(JUMPING_LEFT)
        private val arrayOfWalkLoopLeft = Array<TextureAtlas.AtlasRegion>().apply {
            add(textureAtlas.findRegion(WALKING_1_LEFT))
            add(textureAtlas.findRegion(WALKING_2_LEFT))
            add(textureAtlas.findRegion(WALKING_3_LEFT))
        }
        private val arrayOfWalkLoopRight = Array<TextureAtlas.AtlasRegion>().apply {
            add(textureAtlas.findRegion(WALKING_1_RIGHT))
            add(textureAtlas.findRegion(WALKING_2_RIGHT))
            add(textureAtlas.findRegion(WALKING_3_RIGHT))
        }
        val animationWalkLoopLeft = Animation<TextureAtlas.AtlasRegion>(Gigal_WALK_LOOP_DURATION, arrayOfWalkLoopLeft, Animation.PlayMode.LOOP_PINGPONG)
        val animationWalkLoopRight = Animation(Gigal_WALK_LOOP_DURATION, arrayOfWalkLoopRight, Animation.PlayMode.LOOP_PINGPONG)
    }

    object PlatformAsset {
        val platformNinePathch = NinePatch(textureAtlas.findRegion(PLATFORM), NINEPATCH_EDJE, NINEPATCH_EDJE, NINEPATCH_EDJE, NINEPATCH_EDJE);
    }
    object EnemyTexture{
        val enemy=textureAtlas.findRegion(ENEMY);
    }
    object Bullet{
        val bullet= textureAtlas.findRegion(BULLET)
    }
    object PowrUp{
        val powerUp= textureAtlas.findRegion(POWER_UP)
    }
    object Explosion{
        private val array=Array<TextureRegion>().apply {
            add(textureAtlas.findRegion(EXPLOSION_LARGE))
            add(textureAtlas.findRegion(EXPLOSION_MEDUIM))
            add(textureAtlas.findRegion(EXPLOSION_SMALL))
        }
        val animation=Animation<TextureRegion>(EXPLOSION_DURATION, array,Animation.PlayMode.LOOP)

    }

}
