package engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

//import static org.lwjgl.glfw.GLFW.*;

public class Window {

    //Solo queremos una clase ventana, que sea un singleton y solo Window pueda crearlo

    private int width, height;
    private String title;
    private long glfwWindow;

    // creamos un objeto privado window que es el singleton
    //inicializado a null. Esta es nuestra unica instancia de window.
    private static Window window = null;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "mario";
    }

    public static Window get(){
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run() {
        System.out.println("Hola LWJGL" + Version.getVersion() + "!");
        //Asi sabemos que version es la de lwjgl
        init();
        loop();
    }

    //Debemos hacer varias cosas manuales para que corra la ventana
    public void init() {
        //Seteamos a donde nos tira el error lwjgl, queremos que sea en la consola
        //System.err.println("Tenemos un error! ");
        GLFWErrorCallback.createPrint(System.err).set();

        //Inicializamos GLFW. Retorna True si inicializa y false si no. Si hay error larga mensaje
        if (!glfwInit()) {
            throw new IllegalStateException("Error de inicializacion de GLFW.");
        }

        //Configuramos GLFW. DefaultWindowHints da todas las opciones normales para acomodar la ventana.
        glfwDefaultWindowHints();

        //Hacemos que las hints no sean visibles en cuento creamos la ventana, sino luego de crearse
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        //Hacemos que se pueda ajustar la ventana. El default es true
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        //Nos aseguramos que arranque con el tamanio completo
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE);

        //Ahora si creamos la ventana. Hacemos lo anterior porque queremos que GLFW use esas hints para configurar la ventana.
        glfwWindow = glfwCreateWindow(this.width,this.height,this.title, NULL, NULL);
        //glfwCreateWindow retorna un LONG, numero que es una direccion en memoria donde la ventana se aloja.

        if (glfwWindow == NULL){
            throw new IllegalStateException("Error en la creacion de la ventana glfw");

        }

        //Hacemos que OPENGL sea el contexto actual
        glfwMakeContextCurrent(glfwWindow);
        //Permitimos V-sync. Asi vamos de acuerdo al refreshrate del monitor.
        glfwSwapInterval(1);

        //La ventana ya se creo, hacemos que sea visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGl
        // bindings available for use.
        GL.createCapabilities();
    }

    //Ya tenemos inicializada la ventana, ahora necesitamos el loop
    public void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            //Poll events
            glfwPollEvents();

            //glfPollEvents agarra los eventos y los lleva a nuestros key listeners
           //que setearemos mas tarde


            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

            //Esto dice a gl toma el colo seteado recien y daselo a toda la pantalla
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}
