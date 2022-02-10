package engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

    //2:nuestro singleton para el mouse listener (como hicimos con el window)
    private static MouseListener instance;
    private double scrollX, scrollY;

    //2:para los valores dx y dy
    private double xPos, yPos, lastY, lastX;

    //2:guarda que boton fue presionado. Agregaremos un catch
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;

    }

    //2:Creamos el metodo get como en la ventana. Esto es porque todos nuestros metodos son estaticos
    public static MouseListener get() {
        //Si la instancia es nula (lo es en la primer entrada)
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();

        }
        return MouseListener.instance;
    }

    //2:Ahora creamos los callbacks que necesitamos. Vemos la guia Input de JLFW
    public static void mousePosCallback(long window, double xpos, double ypos) {
        //2: seteamos las ultimas posiciones antes de cambiarlas a sus nuevas posiciones
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
        //si la posicion del mouse se mueve pero un boton esta apretado
        //sabemos que se arrastra algo. Entonces decimos arriba, si cualquier boton es apretado
        // y el mouse se mueve claramente el usuario esta arrastrando

    }

    //2: Ahora queremos un mouse input callback. Mods son botones extras como el shift y el ctrl
    public static void mouseButtonCallback(long window, int button, int action, int mods) {

        //2: chequeamos que el boton haya sido presionado
        // y que solo acepte 3 botones. Ignoramos si tiene mas
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }

        }else if (action == GLFW_RELEASE) {
            //revisamos que tengamos lugar para guardar ese boton
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                //2 ademas, sabemos que si el boton dejo de se presionado y estaba
                //arrastrando, ya no lo esta haciendo mas.
                get().isDragging = false;
            }

        }

        //2.1: como sabemos si estamos arrastrando? aca tenemos q si el boton
        //esta apretado es true. mientras es apretado (while) un boton es true
        //entonces sabemos que si la posicion del mouse se mueve pero un boton esta apretado
        //sabemos que se arrastra algo.
    }

    //2: Mouse scroll callback - scroll input
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollY = yOffset;
        get().scrollX = xOffset;
    }

    //2 Generamos la funcion que nos asegure el final de los frames del mouse
    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;

        //2: Ahora los deltas
        //Inherentemente setean los deltas a 0 porque si los ultimos x e Y son
        //los x e Y en los que estamos, cuando los restemos dan 0
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    //2 generamos nuestors get estaticos. este es el get de la posicion de x
    public static float getX() {
        return (float)get().xPos;
    }
    public static float getY() {
        return (float)get().yPos;
    }
    //2 este trae el ultimo x menos xPos
    public static float getDx() {
        //casteamos toda la expresion a un float
        //nos da la cantidad de pos x transcurrida en el frame actual
        return (float)(get().lastX - get().xPos);
    }
    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }
    public static float getScrollY() {
        return (float)get().scrollY;
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        }else {
            return false;
        }

    }
}
