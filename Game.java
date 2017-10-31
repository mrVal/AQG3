package com.angrygoose.aqg3;

import com.angrygoose.aqg3.resources.Assets;
import com.angrygoose.aqg3.rooms.Room0;
import com.angrygoose.aqg3.rooms.Room;
import com.angrygoose.aqg3.rooms.RoomManager;
import com.angrygoose.aqg3.states.AdventureState;
import com.angrygoose.aqg3.states.CombatState;
import com.angrygoose.aqg3.states.State;
import com.angrygoose.aqg3.states.StateManager;
import com.angrygoose.aqg3.utilities.CoreFramework;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author angrygoose
 */
public class Game extends CoreFramework{

    BAD BAD STUFF!!

    Handler handler;

    //States
    protected StateManager stateManager;
    private State adventureState;
    private State combatState;

    //Rooms
    RoomManager roomManager;
    Room room0;

    @Override
    protected void initializeGame(){
        super.initializeGame();
        handler = new Handler(this);
        handler.setKeyboard(keyboard);
        handler.setMouse(mouse);
        Assets.init();

        //States
        /*
        * When adding a state to the Map of states always use put()
        * with .getClass().getSimpleName() to make sure that
        * the map key coincides with the class name
        */
        Map states = new HashMap<String,State>();
        adventureState = new AdventureState();
        combatState = new CombatState();
        states.put(adventureState.getClass().getSimpleName(), adventureState);
        states.put(combatState.getClass().getSimpleName(), combatState);
        stateManager = new StateManager(states);
        stateManager.setState("AdventureState");

        //rooms

        room0 = new Room0(handler);

        /*
        * When adding a room to the Map of rooms always use put()
        * with .getClass().getSimpleName() to make sure that
        * the map key coincides with the class name
        */
        Map rooms = new HashMap<String, Room>();
        rooms.put(room0.getClass().getSimpleName(), room0);

        roomManager = new RoomManager(rooms);
    }

    @Override
    protected void processInput(){
        super.processInput();
        if(keyboard.keyDownOnce(KeyEvent.VK_1)){
            StateManager.setState("AdventureState");
        }
        if(keyboard.keyDownOnce(KeyEvent.VK_2)){
            StateManager.setState("CombatState");
        }

        RoomManager.getCurrentRoom().processInput();
    }

    @Override
    protected void updateObjects(){
        super.updateObjects();

        if(StateManager.getState() != null){
            StateManager.getState().updateObjects();
        }

        RoomManager.getCurrentRoom().updateObjects();
    }

    @Override
    protected void renderStuff(Graphics g){
        //g.drawImage(Assets.hillsBgr, 0, 0, null);
        super.renderStuff(g);
        g.drawString("I'm alive", 200, 50);

        if(StateManager.getState() != null){
            StateManager.getState().render(g);
        }

        RoomManager.getCurrentRoom().render(g);

        System.out.println("current room: " + RoomManager.getCurrentRoom().toString());

    }

    public static void main(String[] args) {

        Game game = new Game();

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
               game.createAndShowGUI();
            }
        });
    }
}
