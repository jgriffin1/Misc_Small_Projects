import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
// java.util.Timer;

public class MyTimerEventListener implements ActionListener{
    
    //make a ton of mobs.... maybe one per level;
    public void actionPerformed(ActionEvent e){
        gameplay.moveMobs();
        gameplay.refreshMap();
        
    }
}
