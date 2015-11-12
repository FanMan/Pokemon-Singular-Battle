package catching.pokemon;

import java.util.*;

/**
 * @author Michael Jaramillo
 * 
 * This is a simulation of catching a Pokémon with interactions of damaging it
 * Capture method is calculated based up the formula from Generation 3 and 4
 * 
 * All formulas used can be found on Bulbapedia.bulbagarden.net
 */

public class BattleScene
{	
	private Random rand = new Random();
	private Scanner scan = new Scanner(System.in);
	public Timer t = new Timer();
	
	// variables used to calculate the pokemon's catch rate
	// alpha = the the catch rate
	// beta = pokéball shake probability
	private double alpha, beta;
	// the amount of times the pokéball shakes before capture
	private int shake_count;
	
	private String option = "";
	
	// the Pokemon's stats
	private int curr_Health, max_Health;
	private double catch_rate;
	private String name;
	
	public BattleScene(int health, String n)
	{
		// minimum health is 30 and max health is 60
		//max_Health = 30 + rand.nextInt(30);
		max_Health = health;
		curr_Health = max_Health;
		catch_rate = 190;
		name = n;
		shake_count = 0;
		alpha = 0;
		beta = 0;
	}
	
	// Method to calculate the percent chance of catching the Pokemon
	public double CatchRate(double maxHealth, double currHealth, double rate, double ballMod, double statusMod)
	{
		double catchrate = 0.0;
		
		catchrate = ((((3 * maxHealth) - (2 * currHealth)) * (rate * ballMod)) / (3 * maxHealth)) * statusMod;
		
		return catchrate;
	}
	
	public void DamageSystem()
	{
		// minimum damage is 1 and maximum damage is 10
		int damage = rand.nextInt(10) + 1;
		
		// there is a 20% chance that the damage dealt is a critical hit
		if(Math.random() < 0.20)
		{
			damage *= 2;
			
			System.out.println("Critical Hit! You did " + damage + " damage to " + name + ".");
		}
		else
		{
			System.out.println("You did " + damage + " damage to " + name + ".");
		}
		
		if((curr_Health - damage) <= 0)
		{
			System.out.println(name + " has fainted.");
			StartOver();
		}
		else
		{
			curr_Health = curr_Health - damage;
			PokemonStats();
			MenuChoice();
		}
	}
	
	public void CatchingSystem()
	{
		alpha = CatchRate((double) max_Health, (double) curr_Health, (double) catch_rate, 1.0, 1.0);
		
		beta = 1048560 / Math.sqrt(Math.sqrt(16711680 / alpha));
		
		// number of times the pokéball shakes
		//int shake1 = rand.nextInt(65536);
		//int shake2 = rand.nextInt(65536);
		//int shake3 = rand.nextInt(65536);
		//int shake4 = rand.nextInt(65536);
		
		if(alpha < 255.0)
		{
			/*
			 * this does simultaneous shakes instead of checking for one shake every second
			if(beta > shake1 && beta > shake2 && beta > shake3 && beta > shake4)
				System.out.println(name + " has been caught!");
			else
			{
				System.out.println("Aww, it got away.");
				StartOver();
			}
			*/
			
			// This needs to be worked on properly as it officialy only checks
			// for 3 shakes while it increases the variable to 4
			while(beta > rand.nextInt(65536) && shake_count < 4)
			{
				t.setDelay(1.5);
				t.start();
				while(t.hasTimePassed()){}
				
				shake_count++;
				System.out.println("Shake " + shake_count);
			}
			
			t.setDelay(1);
			t.start();
			while(t.hasTimePassed()){}
			
			if(shake_count == 4)
			{
				System.out.println(name + " has been caught!");
			}
			else
			{
				System.out.println("Aww, it got away.");
				StartOver();
			}
		}
		else
			System.out.println(name + " has been caught!");
	}
	
	// The main menu
	public void MenuChoice()
	{
		System.out.println("What would you like to do?\n\tDamage\tCatch");
		
		option = scan.nextLine();
		option = option.toLowerCase();
		
		if(option.equals("damage"))
		{
			DamageSystem();
		}	
		else if(option.equals("catch"))
		{
			System.out.println("Go pokeball");
			CatchingSystem();
		}
		else
		{
			System.out.println("Not a valid option, try again.");
			MenuChoice();
		}
	}
	
	// The stats of the pokemon
	public void PokemonStats()
	{
		System.out.println(name + "'(s) stats: \n\tMaxHealth: " + max_Health + "\tcurrentHealth: " + curr_Health);
	}
	
	// resets the stats of the Pokemon and the battle variables
	public void ResetBattleStats()
	{
		curr_Health = max_Health;
		shake_count = 0;
		alpha = 0;
		beta = 0;
	}
	
	// Starts a new game
	public void NewGame()
	{
		System.out.println("Let's catch " + name + "!");
		ResetBattleStats();
		PokemonStats();
		MenuChoice();
	}
	
	public void StartOver()
	{
		System.out.println("Try again? \n\tYes\tNo");
		
		option = scan.nextLine();
		option = option.toLowerCase();
		
		if(option.equals("yes"))
			NewGame();
	}
	
	public static void main(String[] args)
	{
		PokemonStat ps = new PokemonStat(25, 15);
		BattleScene p = new BattleScene(ps.CalculateMaxHP(35), "Pikachu");
		Timer j = new Timer();
		j.start();
		p.NewGame();
		System.out.println(j.TimePassed());
	}

}
