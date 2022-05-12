package engine;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene{

    private boolean ChangingScene = false;
    private float timeToChangeScene = 2.0f;
    public LevelEditorScene(){
        System.out.println("Inside Level Editor Scene");

    }

    @Override
    public void update(float dt) {
        System.out.println("Running at: " + (1.0f / dt) + "FPS");

        if (!ChangingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            ChangingScene = true;
        }

        if (ChangingScene && timeToChangeScene > 0){
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        }
        else if (ChangingScene){
            Window.changeScene(1);
        }
    }


}
