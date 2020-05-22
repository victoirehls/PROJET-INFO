package projet_info;

import javax.swing.ImageIcon;


//DEFINITION DES CHAMPS
	public class Joueur extends Unite{
		String nom;
		int points_conquetes;
		double experience; //en pourcentage
		int niveau; // entier compris entre 1 et 3
		String[] territoires;// on mettra un tableau extensible par la suite
		int tour; //compte le nombre de tours de la partie
		double[] coordonnees;
		int points_dactions;
		int main;
		//private Image imgJoueur;
		//private ImageIcon icoJoueur;

		
	//CONSTRUCTEUR
		public Joueur(String nom, int x, int y) {
			super(x,y,30,30);
			this.nom = nom;
			this.points_conquetes = 0; //on initialise tout Ã  0 pour l'instant
			this.niveau = 1;
			this.experience = 0;
			this.points_dactions = 10;
			this.coordonnees[0] = x; //initialisation des coordonnÃ©es de chaque joueur
			this.coordonnees[1] = y;
			this.territoires = new String[100]; // on prend un "grand " tableau pour l'instant pour Ã©viter d'utiliser les tableaux exte
			super.icoObj = new ImageIcon(getClass().getResource("/images/vaisseau.png")); //
			super.imgObj = this.icoObj.getImage(); //
		}
	
	//GETTER
		//public Image getImgJoueur() {return imgJoueur;}
		
		
		
	//METHODES
		public void passer_tour(Joueur j1, Joueur j2) { // Ã  appeler Ã  chaque tour d'un seul joueur
			tour = tour + 1;
			j1.main = 0;
			j2.main = 1;
			
		}
		
		public void points(Joueur j, Astre a) {
			j.points_conquetes = j.points_conquetes - a.points_conquetes;
		}
		
		/*public void deplacer(Joueur j, int x) { //on considÃ¨re le pavÃ© de dÃ©placement 5123
			if(x==1) {
				j.coordonnees[0] += -1;
			}
			if(x==2) {
				j.coordonnees[1] += -1;
			}
			if(x==3) {
				j.coordonnees[0] += 1;
			}
			if(x==5) {
				j.coordonnees[1] += 1;
			}
			points_dactions -= 1;
		}*/
		
		public int compter_territoires (Joueur j) {
			int m = 0;
			for(int i =0; i<= j.territoires.length; i++){
				if (j.territoires[i] != null ) {
					m = m+1;}
				}
			return m;
		}
		
		public void conquerir(Joueur j, Astre a) {
			if (points_dactions >= 5) {
				if (j.points_conquetes >= a.points_conquetes)
						{
						int m = compter_territoires (j);
						a.tour_conquete = j.tour;
						j.territoires[m] = (a.nom);
						System.out.println("Bravo, vous Ãªtes moche mais vous avez conquis un nouveau territoire");
						a.conquis = true;
						j.points_conquetes -= a.points_conquetes;
						int taille_totale = Partie.getTaille();
						j.experience += a.taille / taille_totale;
						j.points_dactions -= 5;
						} 
				else {System.out.println("Vous n'avez pas assez de points concquêtes");
				} }
				else {System.out.println("Vous n'avez pas assez de points d'actions");
			}
			
			// rajouter l'experience au joueur	// c'est fait !
		}
		
		public void attaquer(Joueur j1, Joueur j2, Astre a) {
			if (points_dactions >= 5) {
				System.out.println("Le joueur" + j1.nom + " attaque l'astre " + a.nom + "de" + j2.nom);
				if ((j1.points_conquetes >= a.points_conquetes) || (a.x == j1.coordonnees[0]) || (a.y == j1.coordonnees[1])) {
				conquerir (j1, a); 
					for(int i =0; i<= j2.territoires.length; i++){ 
						if (j2.territoires[i] == a.nom) {
							j2.territoires[i]= j2.territoires[compter_territoires(j2)-1];
							territoires[compter_territoires(j2)-1] = null;
					}
						System.out.println("Le joueur" + j1.nom + " a conquit l'astre " + a.nom + "de" + j2.nom);
			
						}
					j1.points_dactions -= 7;
					}
				
			
				else {
					System.out.println("Le joueur" + j1.nom + " n'a pas assez de points conquêtes pour conquérir l'astre " + a.nom + "de" + j2.nom + " en plus il est moche ");
				
			}}
			else {System.out.println("Vous n'avez pas assez de points d'actions"); }
			
			}
		
		
		
		public void evoluer(Joueur j) { //correspond au changement de niveau
			if (j.points_conquetes < 50000) { 
				System.out.println("Vous n'avez pas assez de points conquêtes pour réaliser cette action ");
				}
			
			else if(j.points_conquetes > 50000) { //50 000 arbitraire
				j.niveau = 2;
				j.points_dactions = 20;
			}
			
			else if(j.points_conquetes > 100000){ //100 000 arbitraire
				j.niveau = 3;
				j.points_dactions = 30;
			}
		
		//public String[] display(Joueur j) {
			//String tab[] = { "nom : " + j.nom, " niveau : " + String.valueOf(j.niveau), "experience : " + String.valueOf(j.experience), "coordonnees : " + String.valueOf(j.coordonnees), "tour : " +String.valueOf(j.tour), "point conquetes : " + String.valueOf(j.points_conquetes) };
			// return tab;
		}
		/*public String[] display(Joueur j){
		String tab[] = new String[6];
		tab[0] = "nom : " + j.nom;
		tab[1] = " niveau : " + String.valueOf(j.niveau);
		tab[2] = "experience : " + String.valueOf(j.experience);
		tab[3] = "coordonnees : " + String.valueOf(j.coordonnees);
		tab[4] = "point conquetes : " + String.valueOf(j.points_conquetes);
		tab[5] = "tour : " + String.valueOf(j.tour);
		return tab;
		//for(int i=0 ; i <= tab.length ; i++){
			//return(tab[i]);
		}  */

	}
