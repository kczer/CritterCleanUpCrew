package views;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import listeners.*;
import model.Game;
import moves.Move;

public class GameView extends JPanel {

	private Game currentGame;
	private Map<String, JPanel> subviews;
	private Map<String, MouseInputListener> mouseListeners;
	protected Map<String, BufferedImage> images;
	private Move buttonMove;

	//settings
	final static int frameWidth = 1000; 
	final static int frameHeight = 700;

	public GameView(){
		currentGame = new Game(); //instantiate a game
		loadImages();

		initFrame();

		initViews();

		//pack();

		initListeners();

		buttonMove = null;

	}

	/**
	 * Sets up the frame itself. Gray background, gives the size specified,
	 * makes it visible and makes it closable.
	 */
	public void initFrame(){
		setBackground(Color.gray);
		setSize(frameWidth, frameHeight);
		setVisible(true);
	}

	/**
	 * Instantiates the views and sorts them out to
	 * where they should go. Weights can be changed to
	 * alter the look of the screen, though so far it seems
	 * to be fine.
	 */
	public void initViews(){
		subviews = new HashMap<String, JPanel>(); //initliaze map
		//make the views and put them in a map
		subviews.put("Interaction", new InteractionView(this));
		subviews.put("Toolbox", new ToolboxView(this));
		subviews.put("Minimap", new MinimapView(this));
		subviews.put("Information", new InformationView(this));
		subviews.put("Money", new MoneyView(this));
		subviews.put("Happiness", new HappinessView(this));
		subviews.put("Time", new TimeView(this));

		setLayout(new GridBagLayout()); //initialize layout

		GridBagConstraints interactionC = new GridBagConstraints(); //Minimap view constraints
		interactionC.gridx=0;
		interactionC.gridy=1;
		interactionC.fill=GridBagConstraints.BOTH;
		interactionC.weightx=1;
		interactionC.weighty=0.8;
		interactionC.gridwidth=3;
		add(subviews.get("Interaction"), interactionC);

		GridBagConstraints minimapC = new GridBagConstraints(); //Minimap view constraints
		minimapC.gridx=0;
		minimapC.gridy=2;
		minimapC.fill=GridBagConstraints.BOTH;
		minimapC.weightx=0.35;
		minimapC.weighty=0.2;
		add(subviews.get("Minimap"), minimapC);

		GridBagConstraints toolboxC = new GridBagConstraints(); //Toolbox view constraints
		toolboxC.gridx=1;
		toolboxC.gridy=2;
		toolboxC.fill=GridBagConstraints.BOTH;
		toolboxC.weightx = 0.6;
		toolboxC.weighty=0.2;
		add(subviews.get("Toolbox"), toolboxC);

		GridBagConstraints informationC = new GridBagConstraints(); //Information view constraints
		informationC.gridx=2;
		informationC.gridy=2;
		informationC.fill=GridBagConstraints.BOTH;
		informationC.weightx=0.2;
		informationC.weighty=0.2;
		add(subviews.get("Information"), informationC);

		GridBagConstraints moneyC = new GridBagConstraints(); //Counter view constraints
		moneyC.gridx=0;
		moneyC.gridy=0;
		moneyC.weightx=0.35;
		moneyC.weighty=0.02;
		moneyC.fill = GridBagConstraints.BOTH;
		//moneyC.anchor=GridBagConstraints.FIRST_LINE_START;
		moneyC.gridwidth=1;
		add(subviews.get("Money"), moneyC);

		GridBagConstraints happinessC = new GridBagConstraints(); //Counter view constraints
		happinessC.gridx=1;
		happinessC.gridy=0;
		happinessC.weightx=0.4;
		happinessC.weighty=0.02;
		happinessC.fill = GridBagConstraints.BOTH;
		//happinessC.anchor=GridBagConstraints.FIRST_LINE_START;
		happinessC.gridwidth=1;
		add(subviews.get("Happiness"), happinessC);

		GridBagConstraints timeC = new GridBagConstraints(); //Counter view constraints
		timeC.gridx=2;
		timeC.gridy=0;
		timeC.weightx=0.2;
		timeC.weighty=0.02;
		timeC.fill = GridBagConstraints.BOTH;
		//timeC.anchor=GridBagConstraints.FIRST_LINE_START;
		timeC.gridwidth=1;
		add(subviews.get("Time"), timeC);
	}

	/**
	 * Initializes the listeners and puts them where they're supposed to go.
	 */
	private void initListeners(){
		InteractionMouseListener il = new InteractionMouseListener(this);
		IsoInteractionMouseListener iil = new IsoInteractionMouseListener(this);

		if (((InteractionView)subviews.get("Interaction")).isIso()){
			subviews.get("Interaction").addMouseListener(iil);
			subviews.get("Interaction").addMouseMotionListener(iil);

		}else{
			subviews.get("Interaction").addMouseListener(il);
			subviews.get("Interaction").addMouseMotionListener(il);
		}

		subviews.get("Toolbox").addMouseListener(new ToolboxMouseListener());

		MinimapMouseListener ml = new MinimapMouseListener(this);
		subviews.get("Minimap").addMouseListener(ml);
		subviews.get("Minimap").addMouseMotionListener(ml);

		addKeyListener(new GameKeyListener(this));
	}

	/**
	 * Loads the images found in the assets folder into
	 * a map for further use by all views. <ap is accessed
	 * by using a getter.
	 */
	private void loadImages(){
		images = new HashMap<String, BufferedImage>();
		File folder = new File("assets/");
		for (File cur: folder.listFiles()){ //for every file in folder
			try{
				BufferedImage im = ImageIO.read(cur); //read file
				String nameOfPicture = cur.getName().split("[.]")[0]; //remove file extension
				images.put(nameOfPicture, im); //put into map
			}catch(IOException e){
				//Most likely found SVN folder.
			}
		}
	}

	public Map<String, BufferedImage> getImages(){
		return images;
	}

	public void setCurrentGame(Game game){
		currentGame =game;
	}

	public Game getCurrentGame(){
		return currentGame;
	}

	public Map<String, JPanel> getSubviews(){
		return subviews;
	}

	public void setButtonMove(Move m){
		buttonMove = m;
	}

	public Move getButtonMove(){
		return buttonMove;
	}

	public void saveGame(){// throws IOException{ Used to save the game
		try{
			FileOutputStream fos = new FileOutputStream("savedData.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(currentGame);
			oos.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void loadGame(){// throws IOException, ClassNotFoundException{ used to load the game
		try{
		FileInputStream fis = new FileInputStream("savedData.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game loadGameStatus = (Game) ois.readObject();
        setCurrentGame(loadGameStatus);				//setCurrentGame() method is used to set the game to the status read from the file
        ois.close();
		}catch(IOException e){e.printStackTrace();}
		catch(ClassNotFoundException ex){ex.printStackTrace();}
	}

	//	/**
	//	 * Adjusts the MouseEvents coor
	//	 * @return
	//	 */
	//	public MouseEvent fixMouseEventCoordinates(JPanel origin, JPanel target, MouseEvent event){
	//		
	//	}
}
