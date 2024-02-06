package affichage;
import javax.swing.*;

import authentification.Authentification;


public class PageAccueil
{
	//on cr�e les diff�rents bouttons de la page d'accueil
   JFrame frame = new JFrame();
   
   JMenu menu, smenu;
   JMenuItem myProjectButton2, myGroupsButton2, myImportProjectButton2, myImportGroupButton2;
   //Cr�er le conteneur des onglets
  // public static JTabbedPane onglets = new JTabbedPane();
   public static JTabbedPane onglets;
   
   public PageAccueil()
   {
	   onglets = new JTabbedPane();
	   //ajout de la barre de navigation
	   new BarreNavigation(frame);   
	    
      
    //Cr�er le panneau 1,2 et 3
	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    JPanel p4 = new JPanel();
	    JPanel p5 = new JPanel();
	    //on ajoute la page groupe et page d'acceuil
	    p1.add((new OngletPageAcceuil()).getContentPane());
	    p2.add((new PageGroupe()).framePageGroupe.getContentPane());
	    p3.add((new PageProjet()).framePageProjet.getContentPane());
		p4.add((new PageStats()).framePageStats.getContentPane());
	    

	    //D�finir la position de conteneur d'onglets
	    onglets.setBounds(40,20,600,600);
	    //Associer chaque panneau � l'onglet correspondant
	    onglets.add("Homepage", p1);
	    onglets.add("Groups", p2);
	    onglets.add("Projects", p3);
	    onglets.add("Statistics", p4);
	   // onglets.add("How to use", p5);
	    
	  //Ajouter les onglets au frame
	    frame.add(onglets);
	    
	  //parametre de la page d'accueil
	      frame.setSize(1200,800);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	      
   }  
   public static void main(String args[]) {
   	java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
               new PageAccueil();
           }
       });
   }
} 
