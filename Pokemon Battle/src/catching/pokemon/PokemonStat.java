package catching.pokemon;

import java.util.*;

/**
 * @author Michael Jaramillo
 * 
 * This class calculates the stats of a Pokémon based upon its defined
 * base stats and level
 * 
 * For calculating the Nature
 * 	any Pokémon with a neutral nature (the nature does not affects its stats), the number is 1.0
 * 	any Pokémon with a nature that changes its stats (Adamant: raises attack, lowers sp. attack)
 * 		the raised stat Nature is 1.1 and the lowered stat Nature is 0.9
 * 
 * All formulas and the stats of each individual Pokémon can be found on Bulbapedia.bulbagarden.net
 */

public class PokemonStat {
	
	public int hp, level, EV, stat;
	public Random rand = new Random();
	public int hp_iv, att_iv, def_iv, sp_att_iv, sp_def_iv, sped_iv;
	public String name;
	
	//public PokemonStat(int l, String n) <-- See main method for reason
	public PokemonStat(int l, int ev)
	{
		//name = n;
		level = l;
		// when a pokemon is caught or hatched, its EV should always start at 0
		EV = ev;
		GenerateIVs();
	}
	
	public void GenerateIVs()
	{
		// all stats are randomized between 0 and 31;
		hp_iv = rand.nextInt(32);
		att_iv = rand.nextInt(32);
		def_iv = rand.nextInt(32);
		sp_att_iv = rand.nextInt(32);
		sp_def_iv = rand.nextInt(32);
		sped_iv = rand.nextInt(32);
	}
	
	// calculates the max base health of the pokemon
	public int CalculateMaxHP(int base)
	{
		// health = ( [ ( 2 * base stat + IV + [EV / 4] ) * Level ] / 100 ) + Level + 10
		hp = (((2 * base + hp_iv + (EV / 4)) * level) / 100) + level + 10;
		// the health stat must be rounded down
		hp = (int) Math.floor(hp);
		return hp;
	}
	
	// calculates the max base stat of the pokemon
	public int CalculateStat(int base, int IV, double Nature)
	{
		// stat = ( [ ( 2 * base stat + IV + [EV / 4] ) * level ] / 100 ) + 5
		stat = (((2 * base + IV + (EV / 4)) * level) / 100) + 5;
		// stat = Math.floor( stat * Nature )
		//   all stats must be rounded down
		stat = (int) Math.floor(stat * Nature);
		return stat;
	}
	
	public int AttackIV()
	{
		return att_iv;
	}
	public int DefenseIV()
	{
		return def_iv;
	}
	public int SpAttackIV()
	{
		return sp_att_iv;
	}
	public int SpDefenseIV()
	{
		return sp_def_iv;
	}
	public int SpeedIV()
	{
		return sped_iv;
	}
	
	/*
	 * Un-comment this just to test the stats of a Pokémon only
	public static void main(String[] args) {
		PokemonStat p = new PokemonStat(50, "Chikorita");
		System.out.println(p.Name() + "'(s) health at level 50: " + p.CalculateMaxHP(45));
		System.out.println(p.Name() + "'(s) attack at level 50: " + p.CalculateStat(49, p.AttackIV(), 1.0));
		System.out.println(p.Name() + "'(s) defense at level 50: " + p.CalculateStat(65, p.DefenseIV(), 1.0));
		System.out.println(p.Name() + "'(s) special attack at level 50: " + p.CalculateStat(49, p.SpAttackIV(), 1.0));
		System.out.println(p.Name() + "'(s) special defense at level 50: " + p.CalculateStat(65, p.SpDefenseIV(), 1.0));
		System.out.println(p.Name() + "'(s) speed at level 50: " + p.CalculateStat(45, p.SpeedIV(), 1.0));
	}
	*/
}
