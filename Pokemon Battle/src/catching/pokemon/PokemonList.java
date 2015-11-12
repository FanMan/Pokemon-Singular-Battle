package catching.pokemon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PokemonList
{
	ArrayList<PokemonList> Pokemon = new ArrayList<PokemonList>();
	
	public PokemonList()
	{
		
	}
	
	public void ReadList(String args[])
	{
		try
		{
			FileInputStream inFile = new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
			String str = br.readLine();
			
			do
			{
				
				System.out.println(str + ",");
				str = br.readLine();
			}while(str != null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		PokemonList p = new PokemonList();
		p.ReadList(args);
	}

}
